/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rmaire
 */
@Controller
@Slf4j
@RequestMapping("/dashboard")
public class DashboardController {

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
