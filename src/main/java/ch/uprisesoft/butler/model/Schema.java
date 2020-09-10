/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rma
 */
public class Schema {

    private List<SchemaElement> fields = new ArrayList<>();
    private List<SchemaElement> optionals = new ArrayList<>();
    private Map<String, String> model = new HashMap<>();
    
    private SchemaElement tempElement = null;

    public List<SchemaElement> getFields() {
        return fields;
    }

    public List<SchemaElement> getOptionals() {
        return optionals;
    }

    public Map<String, String> getModel() {
        return model;
    }

    public Schema withText(String label, String placeholder) {        
        tempElement = new SchemaElement("input", "text").withLabel(label).withPlaceholder(placeholder);
        return this;
    }

    public Schema withSelect(String label) {
        tempElement = new SchemaElement("select").withLabel(label);
        return this;
    }
    
    public Schema addOption(String option) {
        tempElement.withMultiValue(option);
        return this;
    }
    
    public Schema commit() {
        fields.add(tempElement);
        tempElement = null;
        return this;
    }
    
//    private String toModelName(String name) {
//        return name
//                .toLowerCase()
//                .replace(" ", "-")
//                .replace("_", "-")
//                .replace(".", "-")
//                .replace("_", "-");
//    }

}
