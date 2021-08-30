package samples.base.elasticsearch.api.single;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.junit.jupiter.api.Test;
import samples.base.elasticsearch.api.AbstractAPI;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: daibin
 * @date: 2021/8/12 3:42 下午
 * {@link <url>https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.1/java-rest-high-document-index.html</url>}
 */
@Slf4j
public class IndexAPI extends AbstractAPI {

    @Test
    void test_String() throws IOException {
        log.info("test");
        IndexRequest request = new IndexRequest("posts");
        request.id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    @Test
    void test_Map() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");

        IndexRequest request = new IndexRequest("posts")
                .id("2").source(jsonMap);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    @Test
    void test_XContentBuilder() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "kimchy");
            builder.timeField("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest request = new IndexRequest("posts")
                .id("3").source(builder);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    @Test
    void test_keypairs() throws IOException {
        IndexRequest request = new IndexRequest("posts")
                .id("4")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

//    @Test
    void test_option() throws IOException {

        IndexRequest request = new IndexRequest("posts")
                .id("1")
                .source("field", "value");

        request.routing("routing");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.version(2);
        request.versionType(VersionType.EXTERNAL);
        request.opType(DocWriteRequest.OpType.CREATE);
        request.setPipeline("pipeline");


//        同步
//        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);


//异步
        client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }
}
