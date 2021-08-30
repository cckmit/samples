package samples.springboot.cxf.jaxws.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import samples.springboot.cxf.jaxws.web.impl.UserWsApiImpl;

import javax.xml.ws.Endpoint;

/**
 * @author: daibin
 * @date: 2021/10/16 8:09 上午
 */
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private MetricsProvider metricsProvider;

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new UserWsApiImpl(), null, null, new MetricsFeature[]{
                new MetricsFeature(metricsProvider)
        });
        endpoint.publish("user");
        return endpoint;
    }
    @Bean
    public Endpoint productEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new UserWsApiImpl(), null, null, new MetricsFeature[]{
                new MetricsFeature(metricsProvider)
        });
        endpoint.publish("product");
        return endpoint;
    }
}
