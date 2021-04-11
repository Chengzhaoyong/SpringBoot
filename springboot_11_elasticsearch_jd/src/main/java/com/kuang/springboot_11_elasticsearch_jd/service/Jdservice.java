package com.kuang.springboot_11_elasticsearch_jd.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.springboot_11_elasticsearch_jd.controller.pojo.content;
import com.kuang.springboot_11_elasticsearch_jd.controller.utils.HtmlParseUtil;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.PipedReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Jdservice {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ObjectMapper objectMapper;
//将爬取的数据插入es
    public boolean parseContent(String keyword) throws IOException {
        ArrayList<content>  list=new HtmlParseUtil().HtmlParse(keyword);
       BulkRequest bulkRequest= new BulkRequest();
       for(int i=0;i<list.size();i++){
         bulkRequest.add(new IndexRequest("yong").id(""+(i+1)).source(objectMapper.writeValueAsString(list.get(i)),XContentType.JSON));
       }
      BulkResponse responses= restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
       return !responses.hasFailures();
    }

    //es查询数据
    public List<Map<String,Object>> searchEs(String keyword, int PageNo, int PageSize) throws IOException {
        SearchRequest request=new SearchRequest("yong");

        //查询构造
        SearchSourceBuilder builder=new SearchSourceBuilder();
        MatchQueryBuilder queryBuilder=new MatchQueryBuilder("title",keyword);
        builder.query(queryBuilder);
//高亮构造器
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("title");
        //多个地方高亮
        highlightBuilder.requireFieldMatch(false);
        //高亮红色
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        builder.highlighter(highlightBuilder);
        builder.from(PageNo);
        builder.size(PageSize);
        request.source(builder);
        SearchResponse response=restHighLevelClient.search(request,RequestOptions.DEFAULT);
       List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for (SearchHit hit : response.getHits().getHits()) {
            //解析高亮的字段，将原来的字段换为我们高亮的字段即可
           Map<String, HighlightField> highlightFieldMap= hit.getHighlightFields();
            HighlightField title1 = highlightFieldMap.get("title");

            //原来的字段
        Map<String,Object> map=hit.getSourceAsMap();
        if(title1!=null){
            Text[] fragments = title1.fragments();
            String n_title = "";
            for (Text text : fragments) {
                n_title += text;
            }

            map.put("title", n_title);
        }
            list.add(map);
        }

        return list;

    }


}
