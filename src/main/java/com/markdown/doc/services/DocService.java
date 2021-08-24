package com.markdown.doc.services;

import com.markdown.doc.dtos.DocDTO;
import com.markdown.doc.exceptions.UserNotAllowedException;

import java.util.List;

public interface DocService {
    void createDocument(DocDTO docDTO);

    List<DocDTO> fetchDocsForUserId(String userId, String callerUserId);

    DocDTO fetchDoc(String docId, String userId);

    List<DocDTO> fetchTopRecentDocs();

    void updateDoc(DocDTO docDTO, String userId) throws UserNotAllowedException;
}
