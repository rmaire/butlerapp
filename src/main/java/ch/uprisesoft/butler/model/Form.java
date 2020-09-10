/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.model;

import ch.uprisesoft.butler.plugin.api.model.values.Value;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rma
 */
public class Form {
    private String name = "";
    private String id = "";
    private List<Value> elements;

    public Form() {
        this.elements = new ArrayList<>();
    }
    
    public void addElement(Value v){
        elements.add(v);
    }

    public List<Value> getElements() {
        return elements;
    }

    public void setElements(List<Value> elements) {
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
