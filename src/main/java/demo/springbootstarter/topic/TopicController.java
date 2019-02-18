package demo.springbootstarter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService ;
	
	@RequestMapping("/topics")
	public ResponseEntity<List<Topic>> getAllTopics() {
		List<Topic> topics = topicService.getAllTopics();
		if(topics.isEmpty()) {
			return new ResponseEntity<List<Topic>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Topic>>(topics, HttpStatus.OK);
	}
	
	@RequestMapping("/topics/{id}")
	public ResponseEntity<Topic> getTopic(@PathVariable String id) {
		System.out.println("Fetching topic with id" +id);
		Topic topic = topicService.getTopic(id);
		if(topic == null) {
			System.out.println("Topic with id" +id + "not found");
			return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Topic>(topic, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/topics")
	public ResponseEntity<Void> addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/topics/{id}")
	public ResponseEntity<Void> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.addTopic(topic);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/topics/{id}")
	public ResponseEntity<Void> deleteTopic(@PathVariable String id) {
		 topicService.deleteTopic(id);
		 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
