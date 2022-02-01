package com.markdown.doc.models;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Document(collection = "docs")
public class DocModel implements Serializable, Persistable<String> {

    public DocModel(){
        this.id = UUID.randomUUID().toString();
    }

    @Id
    private String id;
    private String content;

    @Indexed
    private String userId;

   @Indexed(unique = true)
    private String title;

//    @Getter
    private Boolean available;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    @Override
    public boolean isNew() {
        return isNull(this.createdAt);
    }
}
