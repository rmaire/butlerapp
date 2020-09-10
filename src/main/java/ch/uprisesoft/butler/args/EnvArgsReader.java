/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.args;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author rmaire
 */

/**
 * Arguments for a Jira:
 * - Base URL *
 * - DB Hostname *
 * - DB Port *
 * - Database Name *
 * - Database Username *
 * - Database Password *
 * - Database Schema (public)
 * - License String *
 * - Application Title
 * - Base URL
 * - Administrator Name *
 * - Administrator Mail *
 * - Administrator Username *
 * - Administrator Password *
 */

public class EnvArgsReader implements ArgsReader {
    
    private Map<String, String> env;

    public EnvArgsReader() {
        ProcessBuilder pb = new ProcessBuilder();
        env = pb.environment();
    }

    public EnvArgsReader(Map<String, String> env) {
        this.env = env;
    }
    
    @Override
    public Optional<String> getArg(String argName) {
        String name = transformOptionName(argName);
        return hasArg(argName) ? Optional.of(env.get(name)): Optional.empty();
    }

    @Override
    public boolean hasArg(String argName) {
//        System.out.println(argName + " / " + transformOptionName(argName));
        String name = transformOptionName(argName);
        return env.containsKey(name);
    }
    
    private String transformOptionName(String name) {
        return "ATLAS_" + name.toUpperCase().replace("-", "_");
    }
}
