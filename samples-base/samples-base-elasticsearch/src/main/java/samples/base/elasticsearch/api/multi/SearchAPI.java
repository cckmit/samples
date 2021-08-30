package samples.base.elasticsearch.api.multi;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import samples.base.elasticsearch.api.AbstractAPI;

import java.io.IOException;

/**
 * @author: daibin
 * @date: 2021/8/13 3:48 下午
 */
public class SearchAPI extends AbstractAPI {


    @Test
    void test_search() throws IOException {

        SearchRequest request = new SearchRequest("posts");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        //"query": {
        //    "match_all": {}
        //  }
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchSourceBuilder.query(QueryBuilders.matchQuery("user","kimchy"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        System.out.println(response);

    }
}
