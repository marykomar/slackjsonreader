package com.mariakomar.slackjsonreader.controller;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Maria Komar on 24.01.17.
 */
@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @Value("${welcome.message:test}")
    private String message = "Hello world";

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        List<SlackMessage> messages = messageService.getAllMessages();
        logger.error("This is an error message");
        model.put("messages", messages);
        model.put("message", this.message);
        return "index";
    }
}
