/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.cli.model;

/**
 *
 * @author rmaire
 */
public class Product {
    private String hostname;
    private String type;
    private String image;
    private Integer cpu;
    private Integer minMemory;
    private Integer maxMemory;
    private Integer port;
    private String container;

    public Product(String hostname, String type, String image, Integer cpu, Integer minMemory, Integer maxMemory, Integer port, String container) {
        this.hostname = hostname;
        this.type = type;
        this.image = image;
        this.cpu = cpu;
        this.minMemory = minMemory;
        this.maxMemory = maxMemory;
        this.port = port;
        this.container = container;
    }

    public String getHostname() {
        return hostname;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public Integer getCpu() {
        return cpu;
    }

    public Integer getMinMemory() {
        return minMemory;
    }

    public Integer getMaxMemory() {
        return maxMemory;
    }

    public Integer getPort() {
        return port;
    }

    public String getContainer() {
        return container;
    }
    
}
