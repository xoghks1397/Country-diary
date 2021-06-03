package com.ssafy.test.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.test.model.Board;
import com.ssafy.test.model.FilesStorageService;
import com.ssafy.test.model.ResponseMessage;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilesController {

	 @Autowired
	 FilesStorageService storageService;
	
	 @PostMapping("/uploadBrd")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    System.out.println(file + "이다");
	    String name = file.getOriginalFilename();
	    int dot = name.lastIndexOf(".");
	    String ext = name.substring(dot);
	    String now = LocalDateTime.now().toString();
	    String newName = now.replace("-", "").replace(".", "").replace(":", "")+ ext;
//	    System.out.println(newName);
//	    System.out.println(System.getProperty("user.dir"));
	    
	    try {
	    	System.out.println("1");
//	    	System.out.println(System.getProperty("user.home"));
//	    	System.out.println(System.getProperty("user.name"));
	    	String path = System.getProperty("user.dir") + "/uploads";
	    	File Folder = new File(path);
	    	if (!Folder.exists()) {
	    		try{
	    		    Folder.mkdir(); //폴더 생성합니다.
	    		    System.out.println("폴더가 생성되었습니다.");
	    	     } 
	    	     catch(Exception e){
	    		    e.getStackTrace();
	    		}        
	         }else {
	        	 System.out.println("폴더있음");
	         }
	      File newfile = new File(System.getProperty("user.dir") + "/uploads/" + newName);
	      System.out.println("2");
	      file.transferTo(newfile);
	      
//	      System.out.print(newfile.getPath());
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(newfile.getName()));
	    } catch (Exception e) {
	      System.out.print(e);
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("fail"));
	    }
	    
	 }
	 
	 @PostMapping("/upload")
	  public List<String> uploadFile(@RequestParam("file") List<MultipartFile> files) {
	    String message = "";
	    System.out.println("zzz" + files.size());
	    List<String> list = new LinkedList<String>();
	    String path = System.getProperty("user.dir") + "/uploads";
    	File Folder = new File(path);
    	if (!Folder.exists()) {
    		try{
    		    Folder.mkdir(); //폴더 생성합니다.
    		    System.out.println("폴더가 생성되었습니다.");
    	     } 
    	     catch(Exception e){
    		    e.getStackTrace();
    		}        
         }else {
        	 System.out.println("폴더있음");
         }
	    for(MultipartFile file : files) {
	    System.out.println(file + "이다");
	    String name = file.getOriginalFilename();
	    int dot = name.lastIndexOf(".");
	    String ext = name.substring(dot);
	    String now = LocalDateTime.now().toString();
	    String newName = now.replace("-", "").replace(".", "").replace(":", "")+ ext;
//	    System.out.println(newName);
//	    System.out.println(System.getProperty("user.dir"));
	    
	    try {
	    	System.out.println("1");
	      File newfile = new File(System.getProperty("user.dir") + "/uploads/" + newName);
	      System.out.println("2");
	      file.transferTo(newfile);
	      list.add(newfile.getName());
//	      System.out.print(newfile.getPath());
//	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(newfile.getName()));
	    } catch (Exception e) {
	      System.out.print(e);
//	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("fail"));
	    }
	    }
	    return list;
	    
	 }
	 
	 @GetMapping("/read/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = storageService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
}
