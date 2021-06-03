package com.ssafy.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.Sharing;
import com.ssafy.test.model.SharingRepository;


@RestController
@RequestMapping("/sharing")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SharingController {
	
	@Autowired
	private SharingRepository sharingRepository;
	
	@PostMapping(value ="/create")
	public Sharing create(@RequestBody Sharing sharing) {
//		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
//		String format_time1 = format1.format (System.currentTimeMillis());
		Date date = new Date();
		TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		format1.setTimeZone(time);
		System.out.println("캬캬캬");
		String format_time1 = format1.format (date);
		sharing.setDate(format_time1);
		return sharingRepository.save(sharing);
	}
	
	@GetMapping(value = "/read")
	public List<Sharing> read(@RequestParam String type, @RequestParam String word){
		System.out.println("read 들어옴");
		List<Sharing> list = new LinkedList<Sharing>();
		if(type.equals("")) {
			Page<Sharing> page = sharingRepository.findAll(PageRequest.of(Integer.parseInt(word) - 1, 10,Sort.by(Direction.DESC,"date")));			
			return page.getContent();
		}
		else if(type.equals("id")) {
			list.add(sharingRepository.findById(word).get());	
		}
		return list;
	}
	
	@GetMapping(value = "/count")
	public int count() {
		return (int)sharingRepository.count();
	}
	
}
