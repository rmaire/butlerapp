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
public class Database {
    private String hostname;
    private String password;
    private String container;

    public Database(String hostname, String password, String container) {
        this.hostname = hostname;
        this.password = password;
        this.container = container;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPassword() {
        return password;
    }

    public String getContainer() {
        return container;
    }
}
