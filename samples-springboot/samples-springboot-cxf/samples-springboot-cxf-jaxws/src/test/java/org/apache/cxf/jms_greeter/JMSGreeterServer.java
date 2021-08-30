package org.apache.cxf.jms_greeter;

import lombok.extern.java.Log;
import org.apache.cxf.feature.LoggingFeature;
import org.junit.jupiter.api.Test;

import javax.xml.ws.Endpoint;

/**
 * @author: daibin
 * @date: 2021/10/18 4:24 下午
 */
public class JMSGreeterServer {


    public static void main(String[] args) throws Exception {
        new JMSGreeterServer().testServer();
    }
    @Test
    void testServer() throws Exception {
        Server s = new Server();
        System.out.println("Server ready...");
        try {
            Thread.sleep(500 * 60 * 1000);
            System.out.println("Server exiting");
        } finally {
            s.shutdown();
        }
    }

    static class Server {
        Endpoint ep;

        protected Server() throws Exception {
            System.out.println("Starting Server");
            ep = Endpoint.publish(null, new GreeterJMSImpl(), new LoggingFeature());

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    shutdown();
                }
            });
        }

        public void shutdown() {
            if (ep != null) {
                ep.stop();
                ep = null;
            }
        }
    }

    @javax.jws.WebService(portName = "GreeterPort", serviceName = "JMSGreeterService",
            targetNamespace = "http://cxf.apache.org/jms_greeter",
            endpointInterface = "org.apache.cxf.jms_greeter.JMSGreeterPortType",
            wsdlLocation = "classpath:/wsdl/jms_greeter.wsdl")
    @Log
    public static class GreeterJMSImpl implements JMSGreeterPortType {


        public void greetMeOneWay(String me) {
            log.info("Executing operation greetMeOneWay");
            System.out.println("Executing operation greetMeOneWay\n");
            System.out.println("Hello there " + me);
        }
    }
}
