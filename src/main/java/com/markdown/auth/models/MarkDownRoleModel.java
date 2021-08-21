package com.markdown.auth.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "roles")
@EqualsAndHashCode(callSuper = true)
public class MarkDownRoleModel extends GenericModel{

    @Indexed(direction = IndexDirection.DESCENDING, unique = true)
    private String role;

    public MarkDownRoleModel() {
        super();
    }
}
