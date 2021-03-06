/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.controller;

import ch.uprisesoft.butler.model.Host;
import ch.uprisesoft.butler.service.ConsulNodeService;
import ch.uprisesoft.butler.service.NodeService;
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
@RequestMapping("/nodes")
public class NodeController {

    Logger log = LoggerFactory.getLogger(NodeController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String dashboard(Model model) {

        log.debug("================= Nodes =================");
        NodeService s = new ConsulNodeService("zuara.io:8500");
        model.addAttribute("nodes", s.getNodes());
        for (Host cn : s.getNodes()) {
            log.debug(cn.toString());
        }
        log.debug("================= Nodes =================");

        return "nodes";
    }

}
