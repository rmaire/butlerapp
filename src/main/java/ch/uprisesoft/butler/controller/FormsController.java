/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.model.Form;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/forms")
@Slf4j
public class FormsController {
    
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<Form> getForms(HttpServletRequest request, HttpServletResponse response) {
        List<Form> forms = new ArrayList<>();

//        Form testForm = new Form();
//        testForm.addElement(Value.of("Test Field 1", "Test Value 1"));
//        testForm.addElement(Value.of("Test Field 2", ""));
//        testForm.addElement(Value.of("Test Field 3", 22));
//        
//        forms.add(testForm);
        return forms;
    }
    
}
