package over35.samples.elasticsearch.api.single;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.junit.jupiter.api.Test;
import over35.samples.elasticsearch.api.AbstractAPI;

import java.io.IOException;

/**
 * @author: daibin
 * @date: 2021/8/12 3:50 下午
 */
public class DeleteAPI extends AbstractAPI {


    @Test
    void test_() throws IOException {

        DeleteRequest request = new DeleteRequest();


//        {
//            //        DELETE /posts/_doc/1
//            request.index("posts");
//            request.id("1");
//        }

        {
            //        DELETE /posts
            request.index("posts");
        }



        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(response);

    }
}
