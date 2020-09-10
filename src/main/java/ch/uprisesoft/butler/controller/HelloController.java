/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.model.Host;
import ch.uprisesoft.butler.model.OldForm;

import freemarker.template.Configuration;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HelloController {

    Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    Configuration cfg;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        File key = null;
//        try {
//            key = ResourceUtils.getFile("classpath:id_test");
//        } catch (FileNotFoundException ex) {
//            logger.error(ex.getMessage());
//        }
//        Target linux = new Target.Builder()
//                .withHost("10.3.5.20")
//                .withUsername("vagrant")
//                .withKeyFile(key)
//                .build();
//
//        Command c = new Command(linux, OsType.LINUX);
//        c.addEnvironmentVariable("NAME", "World!");
//        c.runCommand("echo $NAME");

        model.addAttribute("name", name);
        return "greeting";
    }

    /*@GetMapping("/dashboard")
    public String dashboard(Model model) {

        NodeService s = new ConsulNodeService("throwaway.ch:8500");
        model.addAttribute("nodes", s.getNodes());

        log.debug("=================");
        for (Host cn : s.getNodes()) {
            log.debug(cn.toString());
        }
        log.debug("=================");

        return "dashboard";
    }*/
    @GetMapping("/test")
    public String test(Model model) {

        /*Map<String, String> snd = new HashMap<>();
        List<String> third = new ArrayList();
        third.add("Nein!");
        third.add("Doch!");
        third.add("Oooh!");

        snd.put("second", "Hell Yeah!");
        model.addAttribute("first", snd);
        model.addAttribute("third", third);
        NodeService s = new ConsulNodeService("throwaway.ch:8500");
        model.addAttribute("nodes", s.getNodes());

        logger.debug("=================");
        for (Host cn : s.getNodes()) {
            logger.debug(cn.toString());
        }
        logger.debug("=================");*/
        StringBuffer templateString = new StringBuffer();
        templateString
                .append("echo \"${first}\"")
                .append("\n")
                .append("echo \\")
                .append("\n")
                .append("\"${second}\"");

        List<Host> nodes = new ArrayList<>();
        nodes.add(new Host("1.2.3.4", "Test", true, "", ""));
        model.addAttribute("nodes", nodes);

        Map<String, String> forms = new HashMap<>();
        forms.put("Test 1", "Bla");
        forms.put("Test 2", "Blubb");
        model.addAttribute("forms", forms);

        OldForm f = new OldForm();
        Map<String, String> formElements = new HashMap<>();
        formElements.put("Test 3", "Bli");
        formElements.put("Test 4", "Blo");
        f.setElements(formElements);
        f.setName("Formit");
        model.addAttribute("forms2", f);

//        Template temp;
//        Writer out = null;
//
//        Map<String, String> inputs = new HashMap<>();
//        inputs.put("first", "Hello World!");
//        inputs.put("second", "Farewell, and thanks for all the fish!");
//        try {
//            //temp = cfg.getTemplate("command.ftl");
//            temp = new Template("test", templateString.toString(), cfg);
//            out = new StringWriter();
//            temp.process(inputs, out);
//        } catch (IOException | TemplateException ex) {
//            log.error(ex.toString());
//        } finally {
//            try {
//                out.close();
//            } catch (IOException ex) {
//                log.error(ex.toString());
//            }
//        }
//
//        Target t = null;
//        try {
//            t = new Target.Builder()
//                    .withHost("10.3.5.20")
//                    .withUsername("vagrant")
//                    //.withPassword("vagrant")
//                    .withKeyFile(ResourceUtils.getFile("classpath:id_test"))
//                    .build();
//        } catch (FileNotFoundException ex) {
//            log.error(ex.toString());
//        }
//
//        MemoryOutputObserver o = new MemoryOutputObserver();
//        Command c = new Command(t, OsType.LINUX);
//        c.register(o);
//
//        log.debug("=================");
//        //c.runCommand(out.toString());
//        for(String command: getLinesFromWriter(out)) {
//            c.runCommand(command);
//            log.debug(command);
//        }
//        log.debug("=================");
//        for (OutputEntry oe : o.getEntries()) {
//            log.debug(oe.getEntry());
//        }
//        log.debug("=================");
//        //log.debug(out.toString());
//        //log.debug("=================");
        return "test";
    }
    
    @GetMapping("/test2")
    public ModelAndView test2() {
        ModelAndView mav = new ModelAndView("test2");
        /*Map<String, String> snd = new HashMap<>();
        List<String> third = new ArrayList();
        third.add("Nein!");
        third.add("Doch!");
        third.add("Oooh!");

        snd.put("second", "Hell Yeah!");
        model.addAttribute("first", snd);
        model.addAttribute("third", third);
        NodeService s = new ConsulNodeService("throwaway.ch:8500");
        model.addAttribute("nodes", s.getNodes());

        logger.debug("=================");
        for (Host cn : s.getNodes()) {
            logger.debug(cn.toString());
        }
        logger.debug("=================");*/


        OldForm f = new OldForm();
        Map<String, String> formElements = new HashMap<>();
        formElements.put("Test3", "Bli");
        formElements.put("Test4", "Blo");
        f.setElements(formElements);
        f.setName("Testform");
        mav.addObject("forms2", f);

//        Template temp;
//        Writer out = null;
//
//        Map<String, String> inputs = new HashMap<>();
//        inputs.put("first", "Hello World!");
//        inputs.put("second", "Farewell, and thanks for all the fish!");
//        try {
//            //temp = cfg.getTemplate("command.ftl");
//            temp = new Template("test", templateString.toString(), cfg);
//            out = new StringWriter();
//            temp.process(inputs, out);
//        } catch (IOException | TemplateException ex) {
//            log.error(ex.toString());
//        } finally {
//            try {
//                out.close();
//            } catch (IOException ex) {
//                log.error(ex.toString());
//            }
//        }
//
//        Target t = null;
//        try {
//            t = new Target.Builder()
//                    .withHost("10.3.5.20")
//                    .withUsername("vagrant")
//                    //.withPassword("vagrant")
//                    .withKeyFile(ResourceUtils.getFile("classpath:id_test"))
//                    .build();
//        } catch (FileNotFoundException ex) {
//            log.error(ex.toString());
//        }
//
//        MemoryOutputObserver o = new MemoryOutputObserver();
//        Command c = new Command(t, OsType.LINUX);
//        c.register(o);
//
//        log.debug("=================");
//        //c.runCommand(out.toString());
//        for(String command: getLinesFromWriter(out)) {
//            c.runCommand(command);
//            log.debug(command);
//        }
//        log.debug("=================");
//        for (OutputEntry oe : o.getEntries()) {
//            log.debug(oe.getEntry());
//        }
//        log.debug("=================");
//        //log.debug(out.toString());
//        //log.debug("=================");
        return mav;
    }
    
    @RequestMapping(value = {"/test", "/test/"}, method = RequestMethod.POST)
    public String testPost(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam Map<String, String> data,
            Model model) {

        log.debug("=================");
        for (Entry<String, String> datum : data.entrySet()) {
            log.debug(datum.getKey() + ": " + datum.getValue());
        }
        log.debug("=================");

        return "redirect:/test";
    }

    @RequestMapping(value = {"/test2", "/test2/"}, method = RequestMethod.POST)
    public String testPost2(HttpServletRequest request,
            HttpServletResponse response,
            OldForm form) {

        log.debug("=================");
        log.debug(form.getName());
        for (Entry<String, String> datum : form.getElements().entrySet()) {
            log.debug(datum.getKey() + ": " + datum.getValue());
        }
        log.debug("=================");

        return "redirect:/test2";
    }

    @GetMapping("/healthcheck")
    public ResponseEntity<String> myCustomCheck() {
        String message = "Testing my healh check function";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private List<String> getLinesFromWriter(Writer sw) {
        List<String> sourceStrings = Arrays.asList(sw.toString().split("\\n"));
        List<String> resultStrings = new ArrayList<>();
        String tempLine = "";

        for (String line : sourceStrings) {
            if (line.endsWith(" \\")) {
                tempLine += " " + line.substring(0, line.length() - 1);
            } else if (!tempLine.equals("")) {
                tempLine += " " + line;
                resultStrings.add(tempLine);
                tempLine = "";
            } else {
                resultStrings.add(line);
            }
        }

        return resultStrings;
    }

}
