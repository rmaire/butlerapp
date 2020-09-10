/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.model.Template;
import ch.uprisesoft.butler.service.TemplateService;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rmaire
 */
@Controller
@Slf4j
@RequestMapping("/templates1")
public class TemplatesController1 {
    
    @Autowired
    TemplateService ts;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView templates(Model model) {
        log.debug("================= Templates =================");
        for(Template t: ts.getTemplates()) {
            log.debug("Template: " + t.getCode());
        }
        ModelAndView mav = new ModelAndView("templates");
        mav.addObject("templates", ts.getTemplates());        
        log.debug("================= Templates =================");
        return mav;
    }
    
    @RequestMapping(value = {"/{name}", "/{name}/"}, method = RequestMethod.GET)
    @ResponseBody
    public Template template(@PathVariable("name") String name) {
        log.debug("================= Template =================");
        log.debug("Requested template: " + name);
        for(Template t: ts.getTemplates()) {
            if(t.getName().equals(name)) {
                return t;
            }
        }     
        log.debug("================= Template =================");
        return new Template();
    }
    
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String newTemplate(HttpServletRequest request,
            HttpServletResponse response,
            Template template,
            Model model) {
        
        log.debug("================= New Template =================");
        Template newTemplate = new Template();
        newTemplate.setName(template.getName());
        newTemplate.setCode(template.getCode());
        
        
        Map<String, String> newParams = new HashMap<>();
//        for(Entry<String, String> param: template.getParameters().entrySet()) {
//            newParams.put(param.getValue(), "");
//        }
//        newTemplate.setParameters(newParams);
//        
//        ts.getTemplates().add(newTemplate);
        
        
        log.debug("Name: " + template.getName());
        log.debug("Code: " + template.getCode());
//        for(Entry<String, String> param: template.getParameters().entrySet()) {
//            log.debug(param.getKey() + ": " + param.getValue());
//        }
        log.debug("================= New Template =================");
        return "redirect:/templates";
    }
    
    @RequestMapping(value = {"{name}", "/{name}"}, method = RequestMethod.POST)
    public String updateTemplate(HttpServletRequest request,
            HttpServletResponse response,
            Template template,
            Model model) {
        
        log.debug("================= New Template =================");
        Template newTemplate = new Template();
        newTemplate.setName(template.getName());
        newTemplate.setCode(template.getCode());
        
        
        Map<String, String> newParams = new HashMap<>();
//        for(Entry<String, String> param: template.getParameters().entrySet()) {
//            newParams.put(param.getValue(), "");
//        }
//        newTemplate.setParameters(newParams);
        
        ts.getTemplates().add(newTemplate);
        
        
        log.debug("Name: " + template.getName());
        log.debug("Code: " + template.getCode());
//        for(Entry<String, String> param: template.getParameters().entrySet()) {
//            log.debug(param.getKey() + ": " + param.getValue());
//        }
        log.debug("================= New Template =================");
        return "redirect:/templates";
    }
}
