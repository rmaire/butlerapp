/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.cli;

import ch.uprisesoft.butler.cli.model.Bundle;
import ch.uprisesoft.butler.cli.model.Database;
import ch.uprisesoft.butler.cli.model.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author rma
 */
public class CliRunner {

    private final Bundle bundle;

    public CliRunner() {
        String bundleName = "";
        String domain = "";
        String dbHostname = "";
        String dbPassword = "";
        String dbContainer = "";

        ProcessBuilder pb = new ProcessBuilder();
        Map<String, String> env = pb.environment();

        // Find all products
        Set<String> jiras = new HashSet<>();
        Set<String> confluences = new HashSet<>();
        Set<String> bitbuckets = new HashSet<>();
        Set<String> bamboos = new HashSet<>();

        for (Entry<String, String> ev : env.entrySet()) {

            if (ev.getKey().equals("BUNDLE_NAME")) {
                bundleName = ev.getValue();
            }

            if (ev.getKey().equals("BUNDLE_DOMAIN")) {
                domain = ev.getValue();
            }

            if (ev.getKey().equals("DB_HOSTNAME")) {
                dbHostname = ev.getValue();
            }

            if (ev.getKey().equals("DB_PW")) {
                dbPassword = ev.getValue();
            }

            if (ev.getKey().equals("DB_CONTAINER")) {
                dbContainer = ev.getValue();
            }

            if (ev.getKey().startsWith("JIRA_")) {
                jiras.add(extractNum(ev.getKey()));
            }

            if (ev.getKey().startsWith("CONFLUENCE_")) {
                confluences.add(extractNum(ev.getKey()));
            }

            if (ev.getKey().startsWith("BITBUCKET_")) {
                bitbuckets.add(extractNum(ev.getKey()));
            }

            if (ev.getKey().startsWith("BAMBOO_")) {
                bamboos.add(extractNum(ev.getKey()));
            }

        }

        // Extract product objects
        List<Product> products = new ArrayList<>();

        for (String jira : jiras) {
            Integer port = 8080;
            String type = "jira";

            String prefix = "JIRA_" + jira + "_";

            String hostname = extractStringVariable(env, prefix + "HOSTNAME");
            String image = extractStringVariable(env, prefix + "IMAGE");
            Integer cpu = extractIntegerVariable(env, prefix + "CPU");
            Integer minMemory = extractIntegerVariable(env, prefix + "MEM_MIN");
            Integer maxMemory = extractIntegerVariable(env, prefix + "MEM_MAX");
            String container = extractStringVariable(env, prefix + "CONTAINER");

            products.add(new Product(hostname, type, image, cpu, minMemory, maxMemory, port, container));
        }

        for (String confluence : confluences) {
            Integer port = 8090;
            String type = "confluence";

            String prefix = "CONFLUENCE_" + confluence + "_";

            String hostname = extractStringVariable(env, prefix + "HOSTNAME");
            String image = extractStringVariable(env, prefix + "IMAGE");
            Integer cpu = extractIntegerVariable(env, prefix + "CPU");
            Integer minMemory = extractIntegerVariable(env, prefix + "MEM_MIN");
            Integer maxMemory = extractIntegerVariable(env, prefix + "MEM_MAX");
            String container = extractStringVariable(env, prefix + "CONTAINER");

            products.add(new Product(hostname, type, image, cpu, minMemory, maxMemory, port, container));
        }

        for (String bitbucket : bitbuckets) {
            Integer port = 7990;
            String type = "bitbucket";

            String prefix = "BITBUCKET_" + bitbucket + "_";

            String hostname = extractStringVariable(env, prefix + "HOSTNAME");
            String image = extractStringVariable(env, prefix + "IMAGE");
            Integer cpu = extractIntegerVariable(env, prefix + "CPU");
            Integer minMemory = extractIntegerVariable(env, prefix + "MEM_MIN");
            Integer maxMemory = extractIntegerVariable(env, prefix + "MEM_MAX");
            String container = extractStringVariable(env, prefix + "CONTAINER");

            products.add(new Product(hostname, type, image, cpu, minMemory, maxMemory, port, container));
        }

        for (String bamboo : bamboos) {
            Integer port = 8085;
            String type = "bamboo";

            String prefix = "BAMBOO_" + bamboo + "_";

            String hostname = extractStringVariable(env, prefix + "HOSTNAME");
            String image = extractStringVariable(env, prefix + "IMAGE");
            Integer cpu = extractIntegerVariable(env, prefix + "CPU");
            Integer minMemory = extractIntegerVariable(env, prefix + "MEM_MIN");
            Integer maxMemory = extractIntegerVariable(env, prefix + "MEM_MAX");
            String container = extractStringVariable(env, prefix + "CONTAINER");

            products.add(new Product(hostname, type, image, cpu, minMemory, maxMemory, port, container));
        }

        // Construct bundle object
        Database db = new Database(dbHostname, dbPassword, dbContainer);
        bundle = new Bundle(bundleName, domain, products, db);

        System.out.println("Network name: " + bundle.getName());
        System.out.println("Domain: " + bundle.getDomain());
        System.out.println("Database Host: " + bundle.getDatabase().getHostname());
        System.out.println("Database Container: " + bundle.getDatabase().getContainer());
        System.out.println("Products:");
        for (Product p : bundle.getProducts()) {
            System.out.println("Product Type: " + p.getType());
            System.out.println("Product Host: " + p.getHostname());
            System.out.println("Product Container: " + p.getContainer());
        }
    }

    public String generateJobFile() throws InterruptedException, IOException, TemplateException {

        // Generate template
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setNumberFormat("computer");

        Template temp = cfg.getTemplate("nomad.ftl.json");
        Writer out = new StringWriter();
        temp.process(bundle, out);

        return out.toString();
    }

    public void setup() {
        DockerNetwork net = new DockerNetwork(bundle.getName());
        net.connectContainer(bundle.getDatabase().getContainer(), bundle.getDatabase().getHostname());
        DockerDbCreator creator = new DockerDbCreator(bundle.getDatabase().getPassword(), bundle.getDatabase().getHostname(), bundle.getName());
        for (Product p : bundle.getProducts()) {
            if (p.getType().equals("jira")) {
                System.out.println("Setting up Jira " + p.getHostname());
                creator.setupJira(p.getHostname());
            }
            if (p.getType().equals("confluence")) {
                System.out.println("Setting up Confluence " + p.getHostname());
                creator.setupConfluence(p.getHostname());
            }

            net.connectContainer(p.getContainer(), p.getHostname());
        }
    }

    private String extractNum(String key) {
        return key.split("_")[1];
    }

    private String extractStringVariable(Map<String, String> env, String variable) {
        for (Entry<String, String> ev : env.entrySet()) {
            if (ev.getKey().equals(variable)) {
                return ev.getValue();
            }
        }
        return "";
    }

    private Integer extractIntegerVariable(Map<String, String> env, String variable) {
        for (Entry<String, String> ev : env.entrySet()) {
            if (ev.getKey().equals(variable)) {
                try {
                    return Integer.parseInt(ev.getValue());
                } catch (java.lang.NumberFormatException nfe) { }

            }
        }
        return null;
    }

}
