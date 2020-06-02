package com.example.springboot.topic;

import com.example.springboot.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service // created as a Singleton - only one instance for the whole app
public class TopicService {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    // initialize our list with some values.. uses ArrayList as the result of Arrays.asList is immutable
    List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("spring", "Spring Framework", "Spring Framework Description"),
            new Topic("java", "Core Java", "Core Java Description"),
            new Topic("javascript", "JavaScript", "JavaScript Description"),
            new Topic("xslt", "XSL Transformations", "XSL Transformation Description")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String id) {
        Topic topic = null;
        try {
            topic = topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        } catch (NoSuchElementException e) {
            logger.warn("Topic not found : " + id);
        }
        return topic;
    }

    public Topic addTopic(Topic topic) {
        topics.add(topic);
        return topic;
    }
}
