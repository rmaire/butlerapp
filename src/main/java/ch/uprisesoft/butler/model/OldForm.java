/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rmaire
 */
public class OldForm {
    String name = "test";
    Map<String, String> elements = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getElements() {
        return elements;
    }

    public void setElements(Map<String, String> forms) {
        this.elements = forms;
    }
    
    
}
