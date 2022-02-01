package com.markdown.auth.daos;

import com.markdown.auth.models.MarkDownUserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public interface UserDAO extends MongoRepository<MarkDownUserModel,String> {
    Optional<MarkDownUserModel> findByUsername(String username);
    Optional<MarkDownUserModel> findByJwtToken(String jwtToken);
    List<MarkDownUserModel> findByDisplayNameOrUsernameOrEmail(String username);
}
