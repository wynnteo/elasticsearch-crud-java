package com.wynntech.services;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wynntech.model.Product;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;

@Service
public class ProductService {
    private static final String INDEX = "products";
    private static final String TYPE = "_doc";

    private RestHighLevelClient client;

    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
    }

    // Create Product
    public String createProduct(Product product) throws IOException {
        Map<String, Object> productMap = objectMapper.convertValue(product, Map.class);
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, product.getId())
            .source(productMap, XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getId();
    }

    // Get Product
    public Product getProduct(String id) throws IOException {
        GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        if (getResponse.isExists()) {
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            return objectMapper.convertValue(sourceAsMap, Product.class);
        }
        return null;
    }

    // Update Product
    public String updateProduct(Product product) throws IOException {
        Map<String, Object> productMap = objectMapper.convertValue(product, Map.class);
        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, product.getId())
            .doc(productMap);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        return updateResponse.getId();
    }

    // Delete Product
    public String deleteProduct(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse.getId();
    }

}
