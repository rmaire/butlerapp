/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api.model.values;

/**
 *
 * @author rmaire
 */
public class BooleanValue implements Value {
    private String name = "";
    private Boolean value = false;
    
//    @Override
//    public Boolean isBoolean() {
//        return true;
//    }
    
    @Override
    public BooleanValue asBoolean(){
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

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
