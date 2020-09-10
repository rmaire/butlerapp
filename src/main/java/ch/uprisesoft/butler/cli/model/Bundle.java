/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.cli.model;

import java.util.List;

/**
 *
 * @author rmaire
 */
public class Bundle {
    private String name;
    private String domain;
    private List<Product> products;
    private Database database;

    public Bundle(String name, String domain, List<Product> products, Database database) {
        this.name = name;
        this.domain = domain;
        this.products = products;
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Database getDatabase() {
        return database;
    }
    
    
}
