/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.api.model.values.ListValue;
import ch.uprisesoft.butler.api.model.values.Value;
import ch.uprisesoft.butler.model.helper.ValueDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/value")
public class ValueTestController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Value test(Model model) {

        List<Value> values = new ArrayList<>();
        values.add(Value.of("stringsample", "Hello world"));
        values.add(Value.of("booleansample", Boolean.TRUE));
        values.add(Value.of("integersample", 42));
        values.add(Value.of("floatsample", 42.23d));

        List<Value> nestedList = new ArrayList<>();
        nestedList.add(Value.of("sample1"));
        nestedList.add(Value.of(99));
        values.add(Value.of("listsample", nestedList));

        Map<String, Value> nestedMap = new HashMap<>();
        nestedMap.put("mapsample1", Value.of("sample1"));
        nestedMap.put("mapsample2", Value.of("sample2", 99));
        values.add(Value.of("mapsample", nestedMap));

        ListValue result = Value.of(values);

        return result;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    @ResponseBody
    public Value testPost(@RequestBody Value value) {
        System.out.println("========================");
        System.out.println(value);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Value.class, new ValueDeserializer());
        mapper.registerModule(module);
        
//        Value val = null;
//        try {
//            val = mapper.readValue(value, Value.class);
//        } catch (IOException ex) {
//            Logger.getLogger(ValueTestController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        System.out.println(value.toString());
//        List<Value> values = new ArrayList<>();
//        values.add(Value.of("stringsample", "Hello world"));
//        values.add(Value.of("booleansample", Boolean.TRUE));
//        values.add(Value.of("integersample", 42));
//        values.add(Value.of("floatsample", 42.23d));
//        
//        List<Value> nestedList = new ArrayList<>();
//        nestedList.add(Value.of("sample1"));
//        nestedList.add(Value.of(99));
//        values.add(Value.of("listsample", nestedList));
//        
//        Map<String, Value> nestedMap = new HashMap<>();
//        nestedMap.put("mapsample1", Value.of("sample1"));
//        nestedMap.put("mapsample2", Value.of("sample2", 99));
//        values.add(Value.of("mapsample", nestedMap));
//        
//        ListValue result = Value.of(values);
        System.out.println("========================");
        return value;
    }
}
