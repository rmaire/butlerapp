/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api.model.values;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmaire
 */
public class ListValue implements Value {
    private String name = "";
    private List<Value> value = new ArrayList<>();
    
//    @Override
//    public Boolean isList() {
//        return true;
//    }
    
    @Override
    public ListValue asList(){
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

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }
    
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
