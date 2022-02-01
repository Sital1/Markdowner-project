package com.markdown.auth.initialize;

import com.markdown.auth.daos.RoleDAO;
import com.markdown.auth.daos.UserDAO;
import com.markdown.auth.models.MarkDownRoleModel;
import com.markdown.auth.models.MarkDownUserModel;
import com.markdown.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Profile({"dev","test"})
@Component
public class InitializeTestData {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TokenService tokenService;

    @EventListener // listens when the app is ready
    public void appReady(ApplicationReadyEvent event){

        addRoles();

        addUsers();

    }

    void addRoles()
    {
        // delete all the data first
        roleDAO.deleteAll();

        MarkDownRoleModel markDownRoleModel1 = new MarkDownRoleModel();
        markDownRoleModel1.setRole("ADMIN");

        MarkDownRoleModel markDownRoleModel2 = new MarkDownRoleModel();
        markDownRoleModel2.setRole("USER");

        roleDAO.save(markDownRoleModel1);
        roleDAO.save(markDownRoleModel2);
    }

    void addUsers()
    {
        // delete
        userDAO.deleteAll();

        MarkDownUserModel markDownUserModel1 = new MarkDownUserModel();
        markDownUserModel1.setUsername("admin");
        markDownUserModel1.setEmail("admin@admin.com");
        markDownUserModel1.setPassword(bCryptPasswordEncoder.encode("admin"));
        markDownUserModel1.setRoles(Collections.singletonList("ADMIN"));
        tokenService.generateToken(markDownUserModel1);
        markDownUserModel1.setDisplayName("adminDisplayName");

        userDAO.save(markDownUserModel1);

        MarkDownUserModel markDownUserModel2 = new MarkDownUserModel();
        markDownUserModel2.setUsername("user");
        markDownUserModel2.setEmail("user@user.com");
        markDownUserModel2.setPassword(bCryptPasswordEncoder.encode("user"));
        markDownUserModel2.setRoles(Collections.singletonList("USER"));
        tokenService.generateToken(markDownUserModel2);
        markDownUserModel2.setDisplayName("userDisplayName");

        userDAO.save(markDownUserModel2);

    }


}
