/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api.model.values;

import java.util.List;
import java.util.Map;

/**
 *
 * @author rmaire
 */
public interface Value {

    public void dispatch(DispatchReceiver dr);
    
    public String getType();
        
    public default BooleanValue asBoolean(){
        return null;
    }
    
    public default DoubleValue asDouble(){
        return null;
    }
    
    public default IntegerValue asInteger(){
        return null;
    }
    
    public default ListValue asList(){
        return null;
    }
    
    public default MapValue asMap(){
        return null;
    }
    
    public default StringValue asString(){
        return null;
    }

    public static BooleanValue of(String name, Boolean value) {
        BooleanValue val = new BooleanValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }

    public static StringValue of(String name, String value) {
        StringValue val = new StringValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }
    
    public static SecureValue of(String name, String value, boolean secure) {
        SecureValue val = new SecureValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }

    public static DoubleValue of(String name, Double value) {
        DoubleValue val = new DoubleValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }

    public static IntegerValue of(String name, Integer value) {
        IntegerValue val = new IntegerValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }

    public static ListValue of(String name, List<Value> value) {
        ListValue val = new ListValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }
    
    public static OptionalListValue of(String name, List<Value> value, boolean optional) {
        OptionalListValue val = new OptionalListValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }

    public static MapValue of(String name, Map<String, Value> value) {
        MapValue val = new MapValue();
        val.setName(name);
        val.setValue(value);
        return val;
    }

    public static BooleanValue of(Boolean value) {
        BooleanValue val = new BooleanValue();
        val.setValue(value);
        return val;
    }
    
    public static StringValue of(String value) {
        StringValue val = new StringValue();
        val.setValue(value);
        return val;
    }
    
    public static SecureValue of(String value, boolean secure) {
        SecureValue val = new SecureValue();
        val.setValue(value);
        return val;
    }

    public static DoubleValue of(Double value) {
        DoubleValue val = new DoubleValue();
        val.setValue(value);
        return val;
    }

    public static IntegerValue of(Integer value) {
        IntegerValue val = new IntegerValue();
        val.setValue(value);
        return val;
    }

    public static ListValue of(List<Value> value) {
        ListValue val = new ListValue();
        val.setValue(value);
        return val;
    }
    
    public static OptionalListValue of(List<Value> value, boolean optional) {
        OptionalListValue val = new OptionalListValue();
        val.setValue(value);
        return val;
    }

    public static MapValue of(Map<String, Value> value) {
        MapValue val = new MapValue();
        val.setValue(value);
        return val;
    }
}
