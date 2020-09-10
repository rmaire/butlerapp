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
public class IntegerValue implements Value {
    private String name = "";
    private Integer value = Integer.MIN_VALUE;
    
//    @Override
//    public Boolean isInteger() {
//        return true;
//    }

    @Override
    public IntegerValue asInteger(){
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
