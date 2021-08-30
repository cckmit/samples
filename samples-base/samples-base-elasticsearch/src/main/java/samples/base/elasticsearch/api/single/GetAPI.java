package samples.base.elasticsearch.api.single;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.junit.jupiter.api.Test;
import samples.base.elasticsearch.api.AbstractAPI;

import java.io.IOException;

/**
 * @author: daibin
 * @date: 2021/8/12 3:49 下午
 */
public class GetAPI extends AbstractAPI {

    /**
     * GET /{_index}/{{_type}}/{_id}
     *
     * @throws IOException
     */
    @Test
    void test_get() throws IOException {
        String index = "posts";
        String id = "1";
        GetRequest getRequest = new GetRequest(index, id);

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(getResponse);

    }
}
