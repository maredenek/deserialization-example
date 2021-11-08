package org.template.posts;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.HttpStatus;
import org.template.BaseEndpoint;
import org.template.posts.model.response.PostDTO;

import java.lang.reflect.Type;
import java.util.Objects;

@Setter
@Accessors(chain = true)
public class GetPostByIdApiRequest extends BaseEndpoint<GetPostByIdApiRequest, PostDTO> {

    private Integer id;

    public GetPostByIdApiRequest(String baseUri, String basePath) {
        super(baseUri, basePath, response -> response.getStatusCode() == HttpStatus.SC_OK);
    }

    @Override
    protected Type getModelType() {
        return PostDTO.class;
    }

    @Override
    public GetPostByIdApiRequest sendRequest() {
        Objects.requireNonNull(id, "Firstly set post id!");
        response = baseSpec()
                .pathParam("id", id)
                .get("/posts/{id}");
        return this;
    }
}
