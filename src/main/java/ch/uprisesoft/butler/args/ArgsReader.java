/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.args;

import java.util.Optional;

/**
 *
 * @author rmaire
 */
public interface ArgsReader {
    public Optional<String> getArg(String argName);
    public boolean hasArg(String argName);
}
