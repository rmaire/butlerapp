/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api.model.values;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rmaire
 */
public class MapValue implements Value {
    private String name = "";
    private Map<String, Value> value = new HashMap<>();

    @Override
    public MapValue asMap(){
        return this;
    }
    
    @Override
    public void dispatch(DispatchReceiver dr) {
        dr.exec(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Value> getValue() {
        return value;
    }

    public void setValue(Map<String, Value> value) {
        this.value = value;
    }
    
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
