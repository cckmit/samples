package org.apache.cxf.jms_greeter;

import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.io.Closeable;
import java.io.IOException;
import java.net.URL;

/**
 * @author: daibin
 * @date: 2021/10/18 4:24 下午
 */
public class JMSGreeterClient {
    private static final QName SERVICE_NAME =
            new QName("http://cxf.apache.org/jms_greeter", "JMSGreeterService");
    private static final QName PORT_NAME =
            new QName("http://cxf.apache.org/jms_greeter", "GreeterPort");

    public static void main(String[] args) throws IOException {
        new JMSGreeterClient().testClient();
    }
    @Test
    void testClient() throws IOException {
        URL wsdlURL = ClassLoader.getSystemResource("wsdl/jms_greeter.wsdl");

        JMSGreeterService service = new JMSGreeterService(wsdlURL, SERVICE_NAME);
        JMSGreeterPortType greeter = (JMSGreeterPortType) service.getPort(PORT_NAME, JMSGreeterPortType.class);

        System.out.println("Invoking greetMeOneWay...");
        greeter.greetMeOneWay("123234");
        System.out.println("No response from server as method is OneWay");
        System.out.println();

        if (greeter instanceof Closeable) {
            ((Closeable) greeter).close();
        }
    }
}
