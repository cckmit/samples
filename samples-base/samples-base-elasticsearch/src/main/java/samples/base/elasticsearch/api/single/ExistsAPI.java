package samples.base.elasticsearch.api.single;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.junit.jupiter.api.Test;
import samples.base.elasticsearch.api.AbstractAPI;

import java.io.IOException;

/**
 * @author: daibin
 * @date: 2021/8/12 3:53 下午
 */
public class ExistsAPI extends AbstractAPI {

    @Test
    void test_exists() throws IOException {
        GetRequest request = new GetRequest();
        request.index("posts");
        request.id("*");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);

        System.out.println(exists);
    }
}
