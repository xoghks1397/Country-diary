package com.farm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.farm.dao.UserDao;
import com.farm.model.user.BasicResponse;
import com.farm.model.user.SignupRequest;
import com.farm.model.user.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public Object Signup(SignupRequest request, BindingResult bindingResult) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		if (bindingResult.hasErrors()) {// @valid가 틀렸을 경우 에러와 같이 보내줌
			List<ObjectError> errorList = bindingResult.getAllErrors();
			result.status = false;
			result.data = "조건에 맞게 작성하여 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
		if (userDao.getUserByEmail(request.getEmail()).isPresent()) { // userDao 에서 이메일 통해 찾아낸 user가 있으면 중복된거
			result.status = false;
			result.data = "이메일이 중복됩니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else if (userDao.getUserById(request.getId()).isPresent()) { // userDao 에서 id를 통해 찾아낸 user가 있으면 중복된거
			result.status = false;
			result.data = "아이디가 중복됩니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			userDao.save(User.builder().id(request.getId()).password(request.getPassword()).name(request.getName())
					.email(request.getEmail()).build());
			result.status = true;
			result.data = request.getName() + "님, 회원가입을 축하드립니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object Login(String id, String password) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Map<String, String> map = new HashMap<String, String>();

		Optional<User> user = userDao.getUserByIdAndPassword(id, password);
		if (user.isPresent()) {
			map.put("id", user.get().getId());
			map.put("name", user.get().getName());
			map.put("email", user.get().getEmail());
			result.status = true;
			result.data = "true";// jwt토큰넣어야한다.
			result.object = map;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "아이디와 비밀번호를 확인 후 로그인 해주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object Update(SignupRequest request, BindingResult bindingResult) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		if (bindingResult.hasErrors()) {// @valid가 틀렸을 경우 에러와 같이 보내줌
			List<ObjectError> errorList = bindingResult.getAllErrors();
			result.status = false;
			result.data = "조건에 맞게 작성하여 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
		Optional<User> user = userDao.getUserById(request.getId());
		if (user.isPresent()) {
			user.ifPresent(UpdateUser -> {
				UpdateUser.setId(request.getId());
				UpdateUser.setPassword(request.getPassword());
				UpdateUser.setName(request.getName());
				UpdateUser.setEmail(request.getEmail());
				User newUser = userDao.save(UpdateUser);
			});
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "false";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

	public Object Delete(String id) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Optional<User> user = userDao.getUserById(id);
		if (user.isPresent()) {
			userDao.deleteById(id);
			result.status = true;
			result.data = user.get().getName();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "존재하지 않는 아이디입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		return response;
	}

}
