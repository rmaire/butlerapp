/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

//import ch.uprisesoft.butler.api.Observer;
//import ch.uprisesoft.butler.api.Runner;
//import ch.uprisesoft.butler.api.RunnerFactory;
//import ch.uprisesoft.butler.api.model.values.BooleanValue;
//import ch.uprisesoft.butler.api.model.values.DispatchReceiver;
//import ch.uprisesoft.butler.api.model.values.DoubleValue;
//import ch.uprisesoft.butler.api.model.values.IntegerValue;
//import ch.uprisesoft.butler.api.model.values.ListValue;
//import ch.uprisesoft.butler.api.model.values.MapValue;
//import ch.uprisesoft.butler.api.model.values.StringValue;
//import ch.uprisesoft.butler.api.model.values.Value;
import ch.uprisesoft.butler.plugin.api.Observer;
import ch.uprisesoft.butler.plugin.api.Runner;
import ch.uprisesoft.butler.plugin.api.RunnerFactory;
import ch.uprisesoft.butler.plugin.api.model.values.BooleanValue;
import ch.uprisesoft.butler.plugin.api.model.values.DispatchReceiver;
import ch.uprisesoft.butler.plugin.api.model.values.DoubleValue;
import ch.uprisesoft.butler.plugin.api.model.values.IntegerValue;
import ch.uprisesoft.butler.plugin.api.model.values.ListValue;
import ch.uprisesoft.butler.plugin.api.model.values.MapValue;
import ch.uprisesoft.butler.plugin.api.model.values.StringValue;
import ch.uprisesoft.butler.plugin.api.model.values.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/plugins")
public class PluginTestController implements Observer, DispatchReceiver {

    Logger log = LoggerFactory.getLogger(PluginTestController.class);
    
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Value>> getPluginInfo() throws InterruptedException {

//        List<String> mis = new ArrayList<>();
        Map<String, List<Value>> plugins = new HashMap<>();
        
        log.debug("================= Plugins =================");
        PluginManager pluginManager = new DefaultPluginManager();
        log.debug(System.getProperty("pf4j.pluginsDir"));
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        List<RunnerFactory> runners = pluginManager.getExtensions(RunnerFactory.class);
        Runner testRunner = null;
        for (RunnerFactory rf : runners) {
            log.debug("Found plugin " + rf.getName());
//            for(Value name: rf.getStandardInputNames()) {
//                log.debug(name.toString());
//            }
//            plugins.put(rf.getName(), rf.getStandardInputNames());
            if (rf.getName().equals("sshrunner")) {
                
                testRunner = rf.getRunner();
//                mis = rf.getStandardInputNames();
            }
        }

//        for (String mi : mis) {
//            log.debug("Mandatory input: " + mi);
//        }

        testRunner.register(this);
        
        List<Value> inputs = new ArrayList<>();
        
        inputs.add(Value.of("host", "10.3.5.20"));
        inputs.add(Value.of("user", "vagrant"));
        inputs.add(Value.of("key", "-----BEGIN RSA PRIVATE KEY-----\n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\n-----END RSA PRIVATE KEY-----"));

        inputs.add(Value.of("NAME", "HELLO"));
        inputs.add(Value.of("BLA", "WORLD!"));

        String command = "";
        command += "echo $NAME\n";
        command += "sleep 2\n";
        command += "echo $BLA";

        testRunner.setInputs(inputs);
        testRunner.setTemplate(command);
        
//        testRunner.run();
//
//        while (!testRunner.isDone()) {
//            log.debug("Still running");
//            Thread.sleep(1000);
//        }
        
        pluginManager.stopPlugins();
        log.debug("================= Plugins =================");

        return plugins;
    }
    
    @Override
    public void inform(StringValue value) {
        log.debug("Log: " + value.getValue());
    }

    @Override
    public void exec(BooleanValue bv) {
        log.debug("Dispatched to Boolean: " + bv.getValue());
    }

    @Override
    public void exec(DoubleValue fv) {
        log.debug("Dispatched to Double: " + fv.getValue());
    }

    @Override
    public void exec(ListValue lv) {
        log.debug("Dispatched to Double: " + lv.getValue());
    }

    @Override
    public void exec(MapValue mv) {
        log.debug("Dispatched to Map: " + mv.getValue());
    }
    
    @Override
    public void exec(IntegerValue iv) {
        log.debug("Dispatched to Integer: " + iv.getValue());
    }

    @Override
    public void exec(StringValue sv) {
        log.debug("Dispatched to String: " + sv.getValue());
    }
}
