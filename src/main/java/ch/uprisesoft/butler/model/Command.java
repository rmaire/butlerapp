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
public class Command {
    
    String template = "";
    Map<String, String> parameters = new HashMap<>();

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    
    
}
