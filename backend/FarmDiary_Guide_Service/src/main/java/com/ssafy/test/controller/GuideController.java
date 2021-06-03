package com.ssafy.test.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.Guide;
import com.ssafy.test.model.GuideRepository;


@RestController
@RequestMapping("/guide")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuideController {
	
	@Autowired
	private GuideRepository guideRepository;
	
	@GetMapping("/{place}")
	public ResponseEntity<List<Guide>> searchCrops(@PathVariable("place") String place) throws Exception{
		return new ResponseEntity<List<Guide>>(guideRepository.findByPlace(place), HttpStatus.OK);
	}
	
	@GetMapping("/crop/{name}")
	public ResponseEntity<List<Guide>> searchCrop(@PathVariable("name") String name) throws Exception{
		return new ResponseEntity<List<Guide>>(guideRepository.findByName(name), HttpStatus.OK);
	}
	
}
