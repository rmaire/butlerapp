/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.atlasrunner.AtlasRunnerPlugin;
import ch.uprisesoft.butler.atlasrunner.AtlasRunnerPlugin.AtlasRunnerFactory;
import ch.uprisesoft.butler.plugin.api.Schema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * https://github.com/vue-generators/vue-form-generator
 */
@Controller
@Slf4j
@RequestMapping("/atlas2")
public class AtlasController2 {

    @GetMapping("/")
    public String dashboard(Model model) {

        log.debug("================= Atlassian Instances =================");
        return "atlas2";
    }
    
    @RequestMapping(value = {"/schema", "/schema/"}, method = RequestMethod.GET)
    @ResponseBody
    public Schema getSchema(Model model) {

        log.debug("================= Get Atlassian Schema =================");
        AtlasRunnerFactory arf = new AtlasRunnerPlugin.AtlasRunnerFactory();
            Schema s = arf.getInputNames();
        log.debug("================= Get Atlassian Schema =================");
        return s;
    }

}
