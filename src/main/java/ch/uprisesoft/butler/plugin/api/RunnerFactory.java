/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api;

import java.util.List;
import org.pf4j.ExtensionPoint;

/**
 *
 * @author rma
 */
public interface RunnerFactory extends ExtensionPoint {
    public Runner getRunner();
    public Schema getInputNames();
    public Schema getOutputNames();
    public String getName();
}
