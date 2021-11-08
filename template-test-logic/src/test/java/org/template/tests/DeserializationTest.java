package org.template.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.template.posts.GetPostsBulkApiRequest;
import org.template.posts.GetPostByIdApiRequest;
import org.template.posts.model.response.PostDTO;

import java.net.URL;
import java.util.List;

public class DeserializationTest {

    private final String baseUri = "https://jsonplaceholder.typicode.com/";
    // check the responses using following commands in your terminal
    private final String curlFetchAllPosts = "curl 'https://jsonplaceholder.typicode.com/posts'";
    private final String curlFetchPostWithId1 = "curl 'https://jsonplaceholder.typicode.com/posts/1'";

    @Test
    public void deserializationTest() {
        PostDTO dataFromFile = extractDataFromFile();
        PostDTO singlePostData = fetchPostWithId(1);
        List<PostDTO> allPosts = fetchAllPosts();
        Assertions.assertEquals(dataFromFile, allPosts.get(0)); //comparison with first
        Assertions.assertEquals(dataFromFile, singlePostData);
    }

    @SneakyThrows
    private PostDTO extractDataFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        URL fileData = getClass().getClassLoader().getResource("postData.json");
        return mapper.readValue(fileData, PostDTO.class);
    }

    private List<PostDTO> fetchAllPosts() {
        GetPostsBulkApiRequest apiRequest = new GetPostsBulkApiRequest(baseUri, "");
        return apiRequest
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();
    }

    private PostDTO fetchPostWithId(Integer id) {
        GetPostByIdApiRequest apiRequest = new GetPostByIdApiRequest(baseUri, "");
        return apiRequest
                .setId(id)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();
    }

}
