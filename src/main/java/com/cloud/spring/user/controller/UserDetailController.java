package com.cloud.spring.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cloud.spring.user.entity.UserDetail;
import com.cloud.spring.user.service.UserDetailService;
import com.cloud.spring.user.util.CustomErrorType;
import com.cloud.spring.user.util.SuccessMessage;

@RestController
@CrossOrigin
@RequestMapping("user-service")
public class UserDetailController {
	

	public static Logger logger = LoggerFactory.getLogger(UserDetailController.class);
	@Autowired
	private UserDetailService userDetailService;

	@RequestMapping(value = "/fetchall", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetail>> listAllUsers() {
		List<UserDetail> users = userDetailService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		// ResponseEntity.accepted().body(users);
		// ResponseEntity.ok().body(users);
		return new ResponseEntity<List<UserDetail>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User-----------------------------

	@RequestMapping(value = "/fetchuser/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
		logger.info("Fetching User with id {}", id);
		UserDetail user = userDetailService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		UserDetail user = userDetailService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		userDetailService.deleteUserById(id);
		return new ResponseEntity<String>("User Deleteted Successfully",HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllUser() {
		logger.info("Fetching & Deleting User");
		userDetailService.deleteAllUser();
		return new ResponseEntity<CustomErrorType>(new CustomErrorType("Deleted Successfully....."),HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserDetail user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userDetailService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getFname());
			return new ResponseEntity<>(
					new CustomErrorType("Unable to create. A User with Email/Phone " + user.getEmail() +"/"+user.getPhone() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userDetailService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user-service/fetchuser/{id}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("User with id " + user.getUserId() + " Created"),
				HttpStatus.CREATED);
	}

}
