package com.markdown.doc.controllers;

import com.markdown.doc.dtos.DocDTO;
import com.markdown.doc.exceptions.UserNotAllowedException;
import com.markdown.doc.services.DocService;
import com.markdown.doc.services.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/doc")
public class DocsController {

    @Autowired
    DocService docService;

    @Autowired
    TokenService tokenService;


    // create document

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public DocDTO createDocument(@RequestBody DocDTO docDTO) {
     // service

        docService.createDocument(docDTO);

        return docDTO;

    }

    // fetch own documents
    @GetMapping("all/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','ANONYMOUS')")
    public List<DocDTO> fetchUserDocs(@PathVariable String userId,HttpServletRequest httpServletRequest){

        String jwtToken = getJwtTokenFromHeader(httpServletRequest);
        String callerUserId =  tokenService.getUserId(jwtToken);
        
        // service

        return docService.fetchDocsForUserId(userId,callerUserId);

    }

    // fetch public documents

    @GetMapping("/fetch/{docId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','ANONYMOUS')")
    public DocDTO fetchDocument(@PathVariable String docId,HttpServletRequest httpServletRequest){

        String jwtToken = getJwtTokenFromHeader(httpServletRequest);
        String userId =  tokenService.getUserId(jwtToken);

        // service
        return docService.fetchDoc(docId,userId);
    }



    // fetch 10 most recent documents that are public
    @GetMapping("/recent")
    @PreAuthorize("hasAnyRole('USER','ADMIN','ANONYMOUS')")
    public List<DocDTO> fetchRecentDocsDocs(){

        // service
        return docService.fetchTopRecentDocs();

    }

    // modify own document
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public DocDTO updateDoc(@RequestBody DocDTO docDTO, HttpServletRequest httpServletRequest) throws UserNotAllowedException {

        String jwtToken = getJwtTokenFromHeader(httpServletRequest);


        // extract UserId
        String userId =  tokenService.getUserId(jwtToken); 

        // service
        docService.updateDoc(docDTO,userId);
        return docDTO;
    }

    private String getJwtTokenFromHeader(HttpServletRequest httpServletRequest) {
        try {
            String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION);
            String jwtToken = StringUtils.removeStart(tokenHeader,"Bearer").trim();
            return jwtToken;
        }catch (NullPointerException e){
            return StringUtils.EMPTY;
        }

    }


    // delete document

    @DeleteMapping("//delete/{docId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity deleteDoc(@PathVariable String docId, HttpServletRequest httpServletRequest) throws UserNotAllowedException {

        String jwtToken = getJwtTokenFromHeader(httpServletRequest);

        String userId = tokenService.getUserId(jwtToken);

        // call the service
        docService.deleteDocById(docId,userId);

        return ResponseEntity.noContent().build();

    }




}
