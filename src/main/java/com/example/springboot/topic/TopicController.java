package com.example.springboot.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TopicController {

    // @Autowired   - field injection is not recommended
    private TopicService topicService;

    @RequestMapping("/topics")
    public ResponseEntity<List> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    @RequestMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable String id) {
        Topic topic = topicService.getTopic(id);
        if (topic == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(topic);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic) {
        Topic createdTopic = topicService.addTopic(topic);

        // return a 404 not found error
        if (createdTopic == null) return ResponseEntity.notFound().build();

        // populate the HTTP Header Location and return 201 Created
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTopic.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(createdTopic);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        Topic updatedTopic = topicService.updateTopic(topic, id);
        if (updatedTopic == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedTopic);
    }

    // uses constructor-based dependency injection
    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(method = RequestMethod.DELETE ,value = "/topics/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable String id) {
        if (!topicService.deleteTopic(id)) { return ResponseEntity.notFound().build(); }
        return ResponseEntity.noContent().build();
    }


}
