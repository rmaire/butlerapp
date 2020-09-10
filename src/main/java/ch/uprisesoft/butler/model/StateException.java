/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.model;

import java.io.IOException;

/**
 *
 * @author rmaire
 */
public class StateException extends RuntimeException {

    public StateException() {
        super();
    }

    public StateException(String message) {
        super(message);
    }

    public StateException(Throwable cause) {
        super(cause);
    }

    public StateException(String message, Throwable cause) {
        super(message, cause);
    }

}
