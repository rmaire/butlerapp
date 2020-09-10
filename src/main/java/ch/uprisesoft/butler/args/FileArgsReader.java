/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.args;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author rmaire
 */
public class FileArgsReader implements ArgsReader {
    
    Map<String, String> args;
    
    public FileArgsReader(File argumentFile) throws FileNotFoundException, JsonProcessingException, IOException {
        
        args = new HashMap<>();
        
        StringBuilder props = new StringBuilder();
        Scanner scanner = new Scanner(argumentFile);
        while (scanner.hasNextLine()) {
            props.append(scanner.nextLine()).append("\n");
        }
        String properties = props.toString();
        
        JavaPropsMapper mapper = new JavaPropsMapper();
        mapper.registerModule(new Jdk8Module());
        ObjectNode node;
        node = mapper.readValue(properties, ObjectNode.class);

        Map<String, Object> results = mapper.convertValue(node, Map.class);
        
        for(Entry<String, Object> result: results.entrySet()) {
            String key = result.getKey();
            String val = (String)result.getValue();
            args.put(key, val);
        }
    }
    
    @Override
    public Optional<String> getArg(String argName) {
        return hasArg(argName) ? Optional.of(args.get(argName)) : Optional.empty();
    }

    @Override
    public boolean hasArg(String argName) {
        return args.containsKey(argName);
    }
}
