package samples.base.elasticsearch.api.multi;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.RequestOptions;
import org.junit.jupiter.api.Test;
import samples.base.elasticsearch.api.AbstractAPI;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author: daibin
 * @date: 2021/8/12 3:54 下午
 */
public class MultiGetAPI extends AbstractAPI {

    @Test
    void test_mget() throws IOException {
        MultiGetRequest request = new MultiGetRequest();
        request.add(new MultiGetRequest.Item(
                "index",
                "example_id"));
        request.add(new MultiGetRequest.Item("index", "another_id"));

        MultiGetResponse multiGetResponse = client.mget(request, RequestOptions.DEFAULT);
        MultiGetItemResponse[] multiGetItemResponses = multiGetResponse.getResponses();
        GetResponse[] getResponses = Arrays.stream(multiGetItemResponses).map(MultiGetItemResponse::getResponse).toArray(GetResponse[]::new);
        System.out.println(Arrays.toString(getResponses));
    }
}
