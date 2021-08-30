package samples.springboot.cxf.jaxws.client;

import javax.xml.namespace.QName;

/**
 * @author: daibin
 * @date: 2021/10/18 8:38 上午
 */
public class UserWsApiClient {

    private static final QName SERVICE_NAME =
            new QName("http://cxf.apache.org/jms_greeter", "JMSGreeterService");
    private static final QName PORT_NAME =
            new QName("http://cxf.apache.org/jms_greeter", "GreeterPort");

    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.out.println("please specify wsdl");
//            System.exit(1);
//        }
//
//        File wsdl = new File(args[0]);
//
//        JMSGreeterService service = new JMSGreeterService(wsdl.toURI().toURL(), SERVICE_NAME);
//        JMSGreeterPortType greeter = (JMSGreeterPortType)service.getPort(PORT_NAME, JMSGreeterPortType.class);
//
//        System.out.println("Invoking greetMeOneWay...");
//        greeter.greetMeOneWay(System.getProperty("user.name"));
//        System.out.println("No response from server as method is OneWay");
//        System.out.println();
//
//        if (greeter instanceof Closeable) {
//            ((Closeable)greeter).close();
//        }
//
//        System.exit(0);
    }
}
