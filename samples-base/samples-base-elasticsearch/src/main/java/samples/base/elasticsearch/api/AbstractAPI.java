package samples.base.elasticsearch.api;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

/**
 * @author: daibin
 * @date: 2021/8/12 5:13 下午
 */
public abstract class AbstractAPI {

    protected RestHighLevelClient client;

    @BeforeEach
    void beforeEach() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9201, "http")));
    }

    @AfterEach
    void afterEach() throws IOException {
        if (client != null) {
            client.close();
        }
    }
}
