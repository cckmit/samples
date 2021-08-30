package samples.base.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author: daibin
 * @date: 2021/8/12 3:32 下午
 */
public class ElasticsearchConfig {


    RestHighLevelClient client() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9201, "http")));
        return client;
    }
}
