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
@RequestMapping("/container")
public class ContainerController {

    Logger log = LoggerFactory.getLogger(ContainerController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String dashboard(Model model) {

        //NodeService s = new ConsulNodeService("throwaway.ch:8500");
        //model.addAttribute("nodes", s.getNodes());
        log.debug("================= Container =================");
//        for (ClusterNode cn : s.getNodes()) {
//            log.debug(cn.toString());
//        }
//        log.debug("=================");

        return "container";
    }

}
