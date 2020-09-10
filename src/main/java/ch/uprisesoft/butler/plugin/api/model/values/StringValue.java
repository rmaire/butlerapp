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
public class StringValue implements Value {
    
    private String name = "";
    private String value = "";
    
//    @Override
//    public Boolean isString() {
//        return true;
//    }


    @Override
    public StringValue asString(){
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

}
