package com.epam.lab.client;

import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import com.epam.lab.ws.HelloWebService;
import com.epam.lab.ws.HelloWebServiceImplPortBindingStub;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws RemoteException {
	HelloWebService service = new HelloWebServiceImplPortBindingStub();
	System.out.println(service.hello("sdfsdfsdf"));
    }
}
