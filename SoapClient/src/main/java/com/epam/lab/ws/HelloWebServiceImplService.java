/**
 * HelloWebServiceImplService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.epam.lab.ws;

public interface HelloWebServiceImplService extends javax.xml.rpc.Service {
    public java.lang.String getHelloWebServiceImplPortAddress();

    public com.epam.lab.ws.HelloWebService getHelloWebServiceImplPort() throws javax.xml.rpc.ServiceException;

    public com.epam.lab.ws.HelloWebService getHelloWebServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
