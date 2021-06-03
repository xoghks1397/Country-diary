package com.farm.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farm.model.user.SignupRequest;
import com.farm.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/signup")
	public Object Signup(@Valid @RequestBody SignupRequest request, BindingResult bindingResult) {
		return userService.Signup(request, bindingResult);
	}

	@PostMapping("/login")
	public Object Login(@RequestParam(required = true) final String id,
			@RequestParam(required = true) final String password) {
		return userService.Login(id, password);
	}

	@PutMapping(value = "/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object Update(@Valid @RequestBody SignupRequest request, BindingResult bindingResult) {

		return userService.Update(request, bindingResult);
	}

	@DeleteMapping("/delete")
	public Object Delete(@RequestParam(required = true) final String id) {
		return userService.Delete(id);
	}

}
