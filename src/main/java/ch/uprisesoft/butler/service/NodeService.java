/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.service;

import ch.uprisesoft.butler.model.Host;
import java.util.List;

/**
 *
 * @author rmaire
 */
public interface NodeService {
    
    List<Host> getNodes();
    
}
