package com.mariakomar.slackjsonreader.repositories;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maria Komar on 28.02.17.
 */

@Primary
@Repository
public interface SlackMessageRepository extends JpaRepository<SlackMessage, Long> {
    @Query("SELECT m FROM SlackMessage m WHERE m.channelName = ?1")
    List<SlackMessage> findByChannel(String channel);

    List<SlackMessage> findByUser(SlackUser user);
}
