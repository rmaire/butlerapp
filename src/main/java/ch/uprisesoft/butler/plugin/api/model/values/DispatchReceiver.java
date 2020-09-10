/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api.model.values;

/**
 *
 * @author rma
 */
public interface DispatchReceiver {
    public default void exec(BooleanValue bv) {}
    public default void exec(DoubleValue fv) {}
    public default void exec(IntegerValue iv) {}
    public default void exec(ListValue lv) {}
    public default void exec(OptionalListValue lv) {}
    public default void exec(MapValue mv) {}
    public default void exec(StringValue sv) {}
    public default void exec(SecureValue sv) {}
}
