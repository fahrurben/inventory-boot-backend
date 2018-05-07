package com.kyrosoft.inventory;

public class InventoryException extends Exception {

    /**
     * Constructor with message parameter.
     *
     * @param message the message
     */
    public InventoryException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause parameters.
     *
     * @param message the message
     * @param cause the cause
     */
    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
