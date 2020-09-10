/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.cli;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerNetwork;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rma
 */
public class DockerNetwork {

    private final DockerClientConfig config;
    private final DockerClient docker;
    private String networkId = "";
    private final String networkName;

    public DockerNetwork(String name) {
        this.networkName = name;
        config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("unix:///var/run/docker.sock")
                .build();
        docker = DockerClientBuilder.getInstance(config).build();

        List<Network> networks = docker.listNetworksCmd().withNameFilter(name).exec();
        if (networks.size() <= 0) {
            System.out.println("Network " + name + " does not exist, creating...");
            CreateNetworkResponse cnr = docker.createNetworkCmd().withDriver("bridge").withName(name).exec();
            networkId = cnr.getId();
        } else {
            System.out.println("Network " + name + " already created.");
            networkId = networks.get(0).getId();
        }

    }

    public Boolean connectContainer(String containerName, String hostName) {
        List<Container> containers = docker.listContainersCmd().withNameFilter(Arrays.asList(new String[]{containerName})).exec();
        if (containers.size() > 0) {
            System.out.println("Connecting container " + containerName + " to network " + networkName );
            String containerId = containers.get(0).getId();
            docker
                    .connectToNetworkCmd()
                    .withNetworkId(networkId)
                    .withContainerId(containerId)
                    .withContainerNetwork(new ContainerNetwork()
                                    .withAliases(hostName)
                    ).exec();
            return true;
        } else {
            System.out.println("Connecting to network " + networkName + ": Could not find container " + containerName );
        }
        return false;
    }

//    public void remove() {
//        
//    }
}
