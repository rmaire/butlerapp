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
public class DoubleValue implements Value {

    private String name = "";
    private Double value = Double.NaN;

//    @Override
//    public Boolean isFloat() {
//        return true;
//    }  
    
    @Override
    public DoubleValue asDouble() {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
