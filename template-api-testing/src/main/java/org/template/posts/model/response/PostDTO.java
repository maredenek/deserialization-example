package org.template.posts.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class PostDTO {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

}
