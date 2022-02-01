package com.markdown.doc.dtos;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class DocDTO {
    private String id;
    private String content;
    private String userId;
    private String title;
    private boolean available;
    private Date createdAt;
    private Date updatedAt;

}
