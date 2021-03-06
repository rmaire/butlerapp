/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.plugin.api.model.values.Value;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rmaire
 */
@Controller
@RequestMapping("/atlas")
public class AtlasController {

    Logger log = LoggerFactory.getLogger(AtlasController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String dashboard(Model model) {

        log.debug("================= Atlassian Instances =================");
        return "atlas";
    }
    
    @RequestMapping(value = {"/model", "/model/"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Value> getModel(Model model) {

        log.debug("================= Get Atlassina Instances =================");
//        Map<String, Value> arguments = new HashMap<>();
//        List<Value> types = new ArrayList<>();
//        types.add(Value.of("Jira"));
//        types.add(Value.of("Confluence"));
//        types.add(Value.of("Bitbucket"));
//        types.add(Value.of("Bamboo"));
//        
//        arguments.put("Type", Value.of(types));
//        arguments.put("Name", Value.of("Instance Hostname here..."));
//        arguments.put("Max Memory", Value.of("Jira"));
//        Value templateJira = Value.of(arguments);
        List<Value> forms = new ArrayList<>();
//        forms.add(Value.of("Name", "Atlassian Instance"));
//        forms.add(Value.of("Atlas Bundle", new AtlasRunnerPlugin.AtlasRunnerFactory().getStandardInputNames()));
        return forms;
    }

}
