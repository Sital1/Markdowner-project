package com.markdown.auth.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collation = "roles")
@EqualsAndHashCode(callSuper = true)
public class MarkDownRoleModel extends GenericModel{
    private String role;

    public MarkDownRoleModel() {
        super();
    }
}
