package com.ssafy.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.Board;
import com.ssafy.test.model.BoardRepository;

import javax.print.attribute.standard.PageRanges;
import javax.validation.Valid;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@PostMapping(value ="/create")
	public Board create(@RequestBody Board board) {
		Date date = new Date();
		TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		format1.setTimeZone(time);
		System.out.println("캬캬캬");
		String format_time1 = format1.format (date);
		board.setDate(format_time1);
		return boardRepository.save(board);
	}
	
	@GetMapping(value = "/read")
	public List<Board> read(@RequestParam String type, @RequestParam String word){
		System.out.println("read 들어옴");
		List<Board> list = new LinkedList<Board>();
		if(type.equals("")) {
			Page<Board> page = boardRepository.findAll(PageRequest.of(Integer.parseInt(word) - 1, 10,Sort.by(Direction.DESC,"date")));			
			return page.getContent();
		}
		else if(type.equals("id")) {
			list.add(boardRepository.findById(word).get());	
		}
		return list;
	}
	
	@GetMapping(value = "/count")
	public int count() {
		return (int)boardRepository.count();
	}
}
