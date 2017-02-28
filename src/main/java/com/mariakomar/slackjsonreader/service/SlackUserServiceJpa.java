package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.repositories.SlackUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Maria Komar on 28.02.17.
 */

@Service
public class SlackUserServiceJpa implements SlackUserService {
    @Autowired
    private SlackUserRepository repository;

    @Override
    public List<SlackUser> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public SlackUser findUserById(String id) {
        return repository.findOne(id);
    }

    @Override
    public SlackUser createUser(SlackUser user) {
        return repository.save(user);
    }
}
