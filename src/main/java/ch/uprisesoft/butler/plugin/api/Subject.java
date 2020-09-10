/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api;

/**
 *
 * @author rma
 */
public interface Subject {
    public void register(Observer observer);
}
