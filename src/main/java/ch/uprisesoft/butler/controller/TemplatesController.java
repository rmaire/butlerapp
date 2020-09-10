
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.model.Parameter;
import ch.uprisesoft.butler.model.Template;
import ch.uprisesoft.butler.service.TemplateService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/templates")
public class TemplatesController {
    
    @Autowired
    TemplateService ts;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Template> getAllTemplates() {
        log.debug("================= Templates =================");
//        for(Template t: ts.getTemplates()) {
//            log.debug("Template: " + t.getCode());
//        }
//        ModelAndView mav = new ModelAndView("templates");
//        mav.addObject("templates", ts.getTemplates());        
        log.debug("================= Templates =================");
        return  ts.getTemplates();
    }
    
    @RequestMapping(value = {"/{id}", "/{id}/"}, method = RequestMethod.GET)
    @ResponseBody
    public Template getTemplate(@PathVariable("id") Long id) {
        log.debug("================= Template =================");
        log.debug("Requested template: " + id.toString());
        for(Template t: ts.getTemplates()) {
            if(t.getId().equals(id)) {
                return t;
            }
        }     
        log.debug("================= Template =================");
        return new Template();
    }
    
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    @ResponseBody
    public Template newTemplate(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Template template) {
        
        log.debug("================= New Template =================");
        log.debug("Name: " + template.getName());
        log.debug("Code: " + template.getCode());
        for(Parameter p: template.getParameters()){
            log.debug(p.getKey() + ": " + p.getValue());
        }
        
        template = ts.addTemplate(template);

        log.debug("================= New Template =================");
        return template;
    }
    
    @RequestMapping(value = {"", "/"}, method = RequestMethod.PUT)
    @ResponseBody
    public Template updateTemplate(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Template template) {
        
        log.debug("================= Update Template =================");
        log.debug("Name: " + template.getName());
        log.debug("Code: " + template.getCode());
        for(Parameter p: template.getParameters()){
            log.debug(p.getKey() + ": " + p.getValue());
        }
        
        template = ts.updateTemplate(template);

        log.debug("================= Update Template =================");
        return template;
    }
    
}
