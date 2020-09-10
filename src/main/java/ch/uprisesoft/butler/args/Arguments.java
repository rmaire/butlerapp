/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.args;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.cli.ParseException;

public class Arguments {

    private final List<ArgsReader> argsReader;

//    Set<String> productOptions = new HashSet<>(
//            Arrays.asList(
//                    "file",
//                    "product",
//                    "base-url",
//                    "db-hostname",
//                    "db-port",
//                    "db-name",
//                    "db-user",
//                    "db-password",
//                    "license",
//                    "admin-name",
//                    "admin-mail",
//                    "admin-user",
//                    "admin-password",
//                    "debug",
//                    "screenshot")
//    );

    public Arguments(String[] args, Set<String> neededArgs) throws ParseException, FileNotFoundException, JsonProcessingException, IOException {
        
//        for(String a: neededArgs) {
//            System.out.println(a);
//        }
        
        argsReader = new ArrayList<>();
        argsReader.add(new CliArgsReader(args, neededArgs));
        argsReader.add(new EnvArgsReader());
        
        if(hasArg("file")) {
            String file = getArg("file").get();
            
            File argsFile = new File(file);
            argsReader.add(new FileArgsReader(argsFile));
        }
    }
    
    public boolean hasArg(String name) {
        for(ArgsReader ar: argsReader) {
            if(ar.hasArg(name)) return true;
        }
        return false;
    }
    
    public Optional<String> getArg(String name) {
        Optional<String> arg = Optional.empty();
        
        for(ArgsReader ar: argsReader) {
            if(ar.hasArg(name)) return ar.getArg(name);
        }
        
        return arg;
    }
}
