package org.template.posts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.template.BaseEndpoint;
import org.template.posts.model.response.PostDTO;

import java.lang.reflect.Type;
import java.util.List;

public class GetPostsBulkApiRequest extends BaseEndpoint<GetPostsBulkApiRequest, List<PostDTO>> {

    public GetPostsBulkApiRequest(String baseUri, String basePath) {
        super(baseUri, basePath, response -> response.getStatusCode() == HttpStatus.SC_OK);
    }

    /**
     * Example how to deserialize generics in two ways.
     * @return
     */
    @Override
    protected Type getModelType() {
        /*
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(List.class, PostDTO.class);
        /*/
        return new TypeReference<List<PostDTO>>(){}.getType();
        //*/
    }

    @Override
    public GetPostsBulkApiRequest sendRequest() {
        response = baseSpec()
                .get("/posts");
        return this;
    }
}
