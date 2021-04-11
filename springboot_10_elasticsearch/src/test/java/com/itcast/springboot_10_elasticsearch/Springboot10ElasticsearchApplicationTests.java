package com.itcast.springboot_10_elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.springboot_10_elasticsearch.pojo.user;
import jdk.swing.interop.SwingInterOpUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;

import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.Query;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
class Springboot10ElasticsearchApplicationTests {
@Autowired
private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() throws IOException {
    CreateIndexRequest request=new CreateIndexRequest("itcast");
    CreateIndexResponse response=restHighLevelClient.indices().create(request,RequestOptions.DEFAULT);
        System.out.println(response);
    }

    //
    @Test
   // @Qualifier("restHighLevelClient")
    void test1() throws IOException {
        GetIndexRequest request=new GetIndexRequest("itcast");
        boolean response=restHighLevelClient.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    void test01() throws IOException {
   DeleteIndexRequest request=new DeleteIndexRequest("itcast");
        AcknowledgedResponse delete=restHighLevelClient.indices().delete(request,RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    //  增加文档
    @Test
    void testCreateIndex() throws IOException {
        user student=new user("好好学习",34);

       IndexRequest request= new IndexRequest("kuang");
       request.id("1");
       //使用jackson
        ObjectMapper mapper=new ObjectMapper();
       String string= mapper.writeValueAsString(student);
       request.source(string,XContentType.JSON);

        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(index.toString());
        System.out.println(index.status());



    }

    //判断文档是否存在
    @Test
    void existDocument() throws IOException {
        GetRequest getRequest = new GetRequest("kuang", "1");

        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
@Autowired
ObjectMapper mapper;
    //更新文档
    @Test
    void testUpdate() throws IOException {
        UpdateRequest request=new UpdateRequest("kuang","1");
        user user=new user("程兆永",22);

        request.doc(mapper.writeValueAsString(user),XContentType.JSON);
        request.timeout("1s");

      UpdateResponse response= restHighLevelClient.update(request,RequestOptions.DEFAULT);
        System.out.println(response.status());
        System.out.println(response);
    }


    // 获取文档
    @Test
    void getDocument() throws IOException {
        GetRequest getRequest = new GetRequest("kuang", "1");

        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields);
        System.out.println(documentFields.getSourceAsString());
    }

    // 删除文档
    @Test
    void delDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("kuang","1");
        deleteRequest.timeout("1s");
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete);
        System.out.println(delete.status());
    }
    // 批量插入
    @Test
    void bulkDocument() throws IOException {
        ArrayList<user> users = new ArrayList<>();
        users.add(new user("aa", 100));
        users.add(new user("aa", 110));
        users.add(new user("aa", 122));
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(new IndexRequest("yong").id("" + (i + 4)).source(mapper.writeValueAsString(users.get(i)),XContentType.JSON));
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk);
        System.out.println(bulk.status());
        System.out.println(bulk.hasFailures()); // 返回false代表成功
    }
//org.elasticsearch.action.bulk.BulkResponse@1f39269d
//OK
//false


    //查询
    @Test
    void searchDoc() throws IOException {
        //查询请求
        SearchRequest request=new SearchRequest("zgc_index");
        //查询构造
        SearchSourceBuilder builder=new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("name", "aa");
        builder.query(termQueryBuilder);
        request.source(builder);

     SearchResponse response= restHighLevelClient.search(request,RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(mapper.writeValueAsString(response.getHits()));
        System.out.println("-------------------");

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println("===========================================");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }





}
