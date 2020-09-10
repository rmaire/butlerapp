/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.plugin.api;

import ch.uprisesoft.butler.plugin.api.model.values.StringValue;


/**
 *
 * @author rma
 */
public interface Observer {
     public void inform(StringValue value);
}
