package samples.springboot.cxf.jaxws.web;

import org.apache.cxf.staxutils.StaxUtils;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: daibin
 * @date: 2021/10/16 8:14 下午
 */
public class UserWsApiTest {

    @Test
    void testClient1() throws MalformedURLException {
        String address = "http://localhost:8080/services/product";
        String request = "<q0:getUser xmlns:q0=\"http://jaxws.cxf.webservice.base.samples/\"><username>Elan</username></q0:getUser>";

        StreamSource source = new StreamSource(new StringReader(request));
        Service service = Service.create(new URL(address + "?wsdl"),
                new QName("http://jaxws.cxf.webservice.base.samples/", "UserWsApiImplService"));
        Dispatch<Source> disp = service.createDispatch(new QName("http://jaxws.cxf.webservice.base.samples/", "UserWsApiImplPort"),
                Source.class, javax.xml.ws.Service.Mode.PAYLOAD);

        Source result = disp.invoke(source);
        String resultAsString = StaxUtils.toString(result);
        System.out.println(resultAsString);
    }
//
//    @Test
//    void testClient2() throws MalformedURLException {
//
//        final QName SERVICE_NAME =
//                new QName("http://cxf.apache.org/jms_greeter", "JMSGreeterService");
//        final QName PORT_NAME =
//                new QName("http://cxf.apache.org/jms_greeter", "GreeterPort");
//
////        if (args.length == 0) {
////            System.out.println("please specify wsdl");
////            System.exit(1);
////        }
//
//        File wsdl = new File();
//
//        JMSGreeterService service = new JMSGreeterService(wsdl.toURI().toURL(), SERVICE_NAME);
//        JMSGreeterPortType greeter = (JMSGreeterPortType) service.getPort(PORT_NAME, JMSGreeterPortType.class);
//
//        System.out.println("Invoking greetMeOneWay...");
//        greeter.greetMeOneWay(System.getProperty("user.name"));
//        System.out.println("No response from server as method is OneWay");
//        System.out.println();
//
//        if (greeter instanceof Closeable) {
//            ((Closeable) greeter).close();
//        }
//    }

}
