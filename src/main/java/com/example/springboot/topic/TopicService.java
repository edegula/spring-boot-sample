package com.example.springboot.topic;

import com.example.springboot.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service // created as a Singleton - only one instance for the whole app
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll()
                .forEach(topics::add);  // uses method reference
        return topics;
    }

    public Topic getTopic(String id) {
        if (topicRepository.findById(id).isPresent()) {
            return topicRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public Topic addTopic(Topic topic) {
        topicRepository.save(topic);
        return topic;
    }

    public Topic updateTopic(Topic topic, String id) {
        topicRepository.save(topic);
        return topic;
    }

    public boolean deleteTopic(String id) {
        if (topicRepository.findById(id).isPresent()) {
            topicRepository.deleteById(id);
            return true;
        } else {
            logger.warn("Topic not found : " + id);
            return false;
        }
    }
}
