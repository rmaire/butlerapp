/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.cli;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DockerDbCreator {

    private final DockerClientConfig config;
    private final DockerClient docker;

    // "CREATE USER $CONFLUENCE_1_HOSTNAME PASSWORD '$DB_PW'"
    private final String CONFLUENCE_CREATE_USER = "CREATE USER %s PASSWORD '%s'";
    // CREATE DATABASE $CONFLUENCE_1_HOSTNAME WITH ENCODING='UTF8' OWNER=$CONFLUENCE_1_HOSTNAME CONNECTION LIMIT=-1
    private final String CONFLUENCE_CREATE_DB = "CREATE DATABASE %s WITH ENCODING='UTF8' OWNER=%s CONNECTION LIMIT=-1";
    // GRANT ALL PRIVILEGES ON DATABASE $CONFLUENCE_1_HOSTNAME TO $CONFLUENCE_1_HOSTNAME
    private final String CONFLUENCE_GRANT = "GRANT ALL PRIVILEGES ON DATABASE %s TO %s";

    // "CREATE USER $JIRA_1_HOSTNAME PASSWORD '$DB_PW'
    private final String JIRA_CREATE_USER = "CREATE USER %s PASSWORD '%s'";
    // "CREATE DATABASE $JIRA_1_HOSTNAME WITH ENCODING 'UNICODE' LC_COLLATE 'C' LC_CTYPE 'C' TEMPLATE template0
    private final String JIRA_CREATE_DB = "CREATE DATABASE %s WITH ENCODING 'UNICODE' LC_COLLATE 'C' LC_CTYPE 'C' TEMPLATE template0";
    // GRANT ALL PRIVILEGES ON DATABASE $JIRA_1_HOSTNAME TO $JIRA_1_HOSTNAME
    private final String JIRA_GRANT = "GRANT ALL PRIVILEGES ON DATABASE %s TO %s";
    
    private final String CONTAINER_NAME = "dbtoolscontainer";
    
    private final String DB_CONTAINER_IMAGE = "postgres:9.5.19";

    private final String dbPassword;
    private final String dbHostName;
    
    List<String> psqlArgs;
    
    private String networkId = null;

    public DockerDbCreator(String password, String host, String network) {
        this.dbPassword = password;
        this.dbHostName = host;
//        this.networkName = network;
        
        config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("unix:///var/run/docker.sock")
                .build();
        docker = DockerClientBuilder.getInstance(config).build();
        
        // Check and pull image
        List<Image> images = docker.listImagesCmd().withImageNameFilter(DB_CONTAINER_IMAGE).exec();
        if (images.size() > 0) {
            System.out.println("Image found");
        } else {
            System.out.println("Image not found, pulling...");
            try {
                docker.pullImageCmd(DB_CONTAINER_IMAGE).start().awaitCompletion();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        
        // Check and create network.
        List<Network> networks = docker.listNetworksCmd().withNameFilter(network).exec();
        if (networks.size() <= 0) {
            System.out.println("Network " + network + " does not exist, creating...");
            CreateNetworkResponse cnr = docker.createNetworkCmd().withDriver("bridge").withName(network).exec();
            networkId = cnr.getId();
        } else {
            System.out.println("Network " + network + " already created.");
            networkId = networks.get(0).getId();
        }
        
        psqlArgs = Arrays.asList(new String[]{"psql", "-e", "-w", "-h", dbHostName, "-U", "postgres", "-c"});
    }

    public void setupJira(final String schema) {
//        String password = String.format("PGPASSWORD=%s", dbPassword);
        
        // Create user in DB
        String command = String.format(JIRA_CREATE_USER, schema, dbPassword);
        List<String> commandWithArgs = new ArrayList<>(psqlArgs);
        commandWithArgs.add(command);
        runCommand(commandWithArgs);
        
        command = String.format(JIRA_CREATE_DB, schema);
        commandWithArgs = new ArrayList<>(psqlArgs);
        commandWithArgs.add(command);
        runCommand(commandWithArgs);
        
        command = String.format(JIRA_GRANT, schema, schema);
        commandWithArgs = new ArrayList<>(psqlArgs);
        commandWithArgs.add(command);
        runCommand(commandWithArgs);
    }

    public void setupConfluence(final String schema) {

        String command = String.format(CONFLUENCE_CREATE_USER, schema, dbPassword);
        List<String> commandWithArgs = new ArrayList<>(psqlArgs);
        commandWithArgs.add(command);
        runCommand(commandWithArgs);
        
        command = String.format(CONFLUENCE_CREATE_DB, schema, schema);
        commandWithArgs = new ArrayList<>(psqlArgs);
        commandWithArgs.add(command);
        runCommand(commandWithArgs);
        
        command = String.format(CONFLUENCE_GRANT, schema, schema);
        commandWithArgs = new ArrayList<>(psqlArgs);
        commandWithArgs.add(command);
        runCommand(commandWithArgs);
    }
    
    private void runCommand(List<String> command){
        
        String containerId = null;
        
        // Check and create container
        List<Container> containers = docker.listContainersCmd().withNameFilter(Arrays.asList(new String[]{CONTAINER_NAME})).exec();
        containerId = null;
        if (containers.size() <= 0) {
            System.out.println("Container does not exist, creating...");
            CreateContainerResponse setupContainer = docker
                    .createContainerCmd("postgres:9.5.19")
                    .withEnv("PGPASSWORD=" + dbPassword)
                    .withName(CONTAINER_NAME)
                    .withCmd(command)
                    .withAttachStdout(Boolean.TRUE)
                    .withAttachStderr(Boolean.TRUE)
                    .exec();
            containerId = setupContainer.getId();
        } else {
            System.out.println("Container already created.");
            containerId = containers.get(0).getId();
        }
                
        // Connect container to network
        docker.connectToNetworkCmd().withContainerId(containerId).withNetworkId(networkId).exec();
        
        // Run command
        System.out.println("Running command on container " + CONTAINER_NAME + ": " + command);
        StartContainerCmd sccmd = docker.startContainerCmd(containerId);
        sccmd.exec();
        
        // Print logs
        LogContainerCmd lcc = docker.logContainerCmd(containerId);

        lcc.withStdOut(true).withStdErr(true);
        lcc.withTimestamps(Boolean.TRUE);

        try {
            lcc.exec(new LogContainerResultCallback() {
                @Override
                public void onNext(Frame item) {
                    System.out.println(item.toString());
                }
            }).awaitCompletion();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        
        // Teardown
        System.out.println("Removing container " + CONTAINER_NAME);
        docker.stopContainerCmd(containerId).exec();
        docker.removeContainerCmd(containerId).exec();
    }

}
