package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackUser;

import java.util.List;

/**
 * Created by Maria Komar on 28.02.17.
 */
public interface SlackUserService {
    List<SlackUser> findAllUsers();
    SlackUser findUserById(String id);
    SlackUser createUser(SlackUser user);
}
