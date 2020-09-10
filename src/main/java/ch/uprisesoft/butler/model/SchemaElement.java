/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class SchemaElement {
    
    private String type = null;
    private String inputType = null;
    private String label = null;    
    private String model = null;
    private String placeholder = null;
    private String buttonText = null;
    private String disabled = null;    
    private String value = null;
    private List<String> values = null;
    
//    private String type = "";
//    private String inputType = "";
//    private String label = "";    
//    private String model = "";
//    private String placeholder = "";
//    private String buttonText = "";
//    private String disabled = "";    
//    private String value = "";
//    private List<String> values = new ArrayList<>();

    public SchemaElement(String type) {
        this.type = type;
    }
    
    public SchemaElement(String type, String inputType) {
        this.type = type;
        this.inputType = inputType;
    }
    
    
    public SchemaElement withLabel(String label) {
        this.label = label;
        this.model = toModelName(label);
        return this;
    }
    
    public SchemaElement withPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }
    
    public SchemaElement withValue(String value) {
        this.value = value;
        return this;
    }
    
    public SchemaElement withMultiValue(String value) {
        if(this.values == null) {
            this.values = new ArrayList<>();
        }
        
        this.values.add(value);
        
        return this;
    }
    
    private String toModelName(String name) {
        return name
                .toLowerCase()
                .replace(" ", "-")
                .replace("_", "-")
                .replace(".", "-")
                .replace("_", "-");
    }

    public String getType() {
        return type;
    }

    public String getInputType() {
        return inputType;
    }

    public String getLabel() {
        return label;
    }

    public String getModel() {
        return model;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getDisabled() {
        return disabled;
    }

    public String getValue() {
        return value;
    }

    public List<String> getValues() {
        return values;
    }
    
    
    

}
