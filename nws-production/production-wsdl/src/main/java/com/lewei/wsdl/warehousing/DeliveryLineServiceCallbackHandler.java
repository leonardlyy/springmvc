/**
 * DeliveryLineServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.4  Built on : Oct 21, 2016 (10:47:34 BST)
 */
package com.lewei.wsdl.warehousing;


/**
 *  DeliveryLineServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class DeliveryLineServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public DeliveryLineServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public DeliveryLineServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for delete method
     * override this method for handling normal response from delete operation
     */
    public void receiveResultdelete(
        com.lewei.wsdl.warehousing.DeliveryLineServiceStub.DeleteResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from delete operation
     */
    public void receiveErrordelete(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for create method
     * override this method for handling normal response from create operation
     */
    public void receiveResultcreate(
        com.lewei.wsdl.warehousing.DeliveryLineServiceStub.CreateResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from create operation
     */
    public void receiveErrorcreate(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for list method
     * override this method for handling normal response from list operation
     */
    public void receiveResultlist(
        com.lewei.wsdl.warehousing.DeliveryLineServiceStub.ListResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from list operation
     */
    public void receiveErrorlist(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for show method
     * override this method for handling normal response from show operation
     */
    public void receiveResultshow(
        com.lewei.wsdl.warehousing.DeliveryLineServiceStub.ShowResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from show operation
     */
    public void receiveErrorshow(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for change method
     * override this method for handling normal response from change operation
     */
    public void receiveResultchange(
        com.lewei.wsdl.warehousing.DeliveryLineServiceStub.ChangeResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from change operation
     */
    public void receiveErrorchange(java.lang.Exception e) {
    }
}
