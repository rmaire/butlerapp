/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rmaire
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    Logger log = LoggerFactory.getLogger(DashboardController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String dashboard(Model model) {

        log.debug("================= Dashboard =================");
        return "dashboard";
    }
    
    @RequestMapping(value = {"2", "2/"}, method = RequestMethod.GET)
    public String dashboard2(Model model) {

        log.debug("================= Dashboard =================");
        return "dashboard2";
    }

}
