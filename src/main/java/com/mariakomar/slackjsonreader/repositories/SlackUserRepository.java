package com.mariakomar.slackjsonreader.repositories;

import com.mariakomar.slackjsonreader.model.SlackUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Maria Komar on 23.02.17.
 */

@Repository
public interface SlackUserRepository extends JpaRepository<SlackUser, String> {
}
