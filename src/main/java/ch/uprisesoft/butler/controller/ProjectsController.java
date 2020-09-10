/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.model.Template;
import ch.uprisesoft.butler.service.TemplateService;
import ch.uprisesoft.jconstruct.Command;
import ch.uprisesoft.jconstruct.OsType;
import ch.uprisesoft.jconstruct.executor.MemoryOutputObserver;
import ch.uprisesoft.jconstruct.executor.OutputEntry;
import ch.uprisesoft.jconstruct.target.Target;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rmaire
 */
@Controller
@Slf4j
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    TemplateService ts;

    @Autowired
    freemarker.template.Configuration cfg;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView projects(Model model) {

        log.debug("================= Projects =================");
        ModelAndView mav = new ModelAndView("projects");
        mav.addObject("templates", ts.getTemplates());
        log.debug("================= Projects =================");

        return mav;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String runCommand(HttpServletRequest request,
            HttpServletResponse response,
            String template,
            @RequestParam() Map<String, String> parameters) {

        log.debug("================= Run Command =================");

        Template t = ts.getTemplate(template);
        freemarker.template.Template temp;
        Writer out = null;

        try {
            temp = new freemarker.template.Template("template", t.getCode(), cfg);
            out = new StringWriter();
            temp.process(parameters, out);
        } catch (IOException | freemarker.template.TemplateException ex) {
            log.error(ex.toString());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                log.error(ex.toString());
            }
        }

        log.debug("Realized template:");
        log.debug(out.toString());

        Target target = null;
        try {
            target = new Target.Builder()
                    .withHost("116.203.213.234")
                    .withUsername("administrator")
                    //.withPassword("vagrant")
                    .withKeyFile(ResourceUtils.getFile("classpath:id_infra"))
                    .build();
        } catch (FileNotFoundException ex) {
            log.error(ex.toString());
            //Files.readLines(ResourceUtils.getFile("classpath:id_infra"), Charset.defaultCharset()).stream().collect(Collectors.joining());
        }
        
        MemoryOutputObserver o = new MemoryOutputObserver();
        Command c = new Command(target, OsType.LINUX);
        c.register(o);

        log.debug("=================");
        //c.runCommand(out.toString());
        log.debug("Normalized command:");
        for(String line: getLinesFromWriter(out)) {
            log.debug(line);
        }
        for (String command : getLinesFromWriter(out)) {
            c.runCommand(command);
            log.debug(command);
        }
        for(OutputEntry oe: o.getEntries()) {
            log.debug(oe.getEntry());
        }

        log.debug("================= Run Command =================");
        return "redirect:/projects";
    }
    
    private List<String> getLinesFromWriter(Writer sw) {
        List<String> sourceStrings = Arrays.asList(sw.toString().split("\\n"));
        List<String> resultStrings = new ArrayList<>();
        String tempLine = "";

        for (String line : sourceStrings) {
            line = line.replace("\r", "");
            line = line.replace("\n", "");
            if (line.endsWith(" \\")) {
                tempLine += " " + line.substring(0, line.length() - 1);
            } else if (!tempLine.equals("")) {
                tempLine += " " + line;
                resultStrings.add(tempLine);
                tempLine = "";
            } else {
                resultStrings.add(line.trim());
            }
        }

        return resultStrings;
    }

}
