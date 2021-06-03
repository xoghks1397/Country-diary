package com.ssafy.test.controller;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.ReReply;
import com.ssafy.test.model.Reply;
import com.ssafy.test.model.ReplyRepository;

@RestController
@RequestMapping("/reply")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReplyController {

	@Autowired
	private ReplyRepository replyRepository;
	
	@PostMapping(value = "/create")
	public Reply create(@RequestBody Reply reply) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		String format_time1 = format1.format (System.currentTimeMillis());
		reply.setDate(format_time1);
		return replyRepository.save(reply);
	}
	
	@GetMapping(value = "/read")
	public List<Reply> read(@RequestParam String boardId){
		List<Reply> list = new LinkedList<Reply>();
		return replyRepository.findByBoardId(boardId);
	}
	
	@PutMapping(value = "/updateReReply")
	public void create(@RequestBody ReReply reReply) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		String format_time1 = format1.format (System.currentTimeMillis());
		reReply.setDate(format_time1);
		replyRepository.updateReReply(reReply);
		return;
	}
}
