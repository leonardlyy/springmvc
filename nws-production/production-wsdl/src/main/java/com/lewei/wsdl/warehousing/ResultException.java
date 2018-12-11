/**
 * ResultException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.4  Built on : Oct 21, 2016 (10:47:34 BST)
 */
package com.lewei.wsdl.warehousing;

public class ResultException extends java.lang.Exception {
    private static final long serialVersionUID = 1512967357363L;
    private com.lewei.wsdl.warehousing.DeliveryLineServiceStub.Result faultMessage;

    public ResultException() {
        super("ResultException");
    }

    public ResultException(java.lang.String s) {
        super(s);
    }

    public ResultException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public ResultException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        com.lewei.wsdl.warehousing.DeliveryLineServiceStub.Result msg) {
        faultMessage = msg;
    }

    public com.lewei.wsdl.warehousing.DeliveryLineServiceStub.Result getFaultMessage() {
        return faultMessage;
    }
}
