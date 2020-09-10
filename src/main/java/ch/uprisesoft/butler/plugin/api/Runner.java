/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api;

import ch.uprisesoft.butler.plugin.api.model.values.Value;
import java.util.List;

/**
 *
 * @author rma
 */
public interface Runner extends Subject {
    public void setInputs(List<Value> inputs);
    public void setTemplate(String template);
    public List<Value> getOutputs();
    public void run();
    public Boolean isDone();
}
