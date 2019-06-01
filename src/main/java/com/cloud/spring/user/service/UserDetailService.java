package com.cloud.spring.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cloud.spring.user.entity.UserDetail;
import com.cloud.spring.user.repository.UserDetailRepository;

@Service
public class UserDetailService {

	@Autowired
	private UserDetailRepository userDetailRepository;

	public List<UserDetail> findAllUsers() {
		return userDetailRepository.findAll();
	}

	public UserDetail findById(Long id) {
		Optional<UserDetail> user = userDetailRepository.findById(id);
		if (user.isPresent()) 
		    return user.get();
		return null;
	}

	public void saveUser(UserDetail user) {
		userDetailRepository.save(user);
	}

	public void deleteUserById(Long id) {
		userDetailRepository.deleteById(id);
	}

	public void deleteUser(UserDetail user) {
		userDetailRepository.delete(user);
	}

	
	public void deleteAllUser() {
		userDetailRepository.deleteAll();
	}
	
	public boolean isUserExist(UserDetail user) {

		boolean isUserAvail = false;
		
		if(user.getUserId() != null) {
			return isUserAvail;
		}

		UserDetail phoneUser = new UserDetail();
		phoneUser.setPhone(user.getPhone());

		UserDetail userEmail = new UserDetail();
		userEmail.setPhone(user.getEmail());

		Example<UserDetail> phoneEx = Example.of(phoneUser);
		Example<UserDetail> emailEx = Example.of(userEmail);

		Optional<UserDetail> emailUsr = userDetailRepository.findOne(phoneEx);
		Optional<UserDetail> phoneUsr = userDetailRepository.findOne(emailEx);

		if (emailUsr.isPresent() || phoneUsr.isPresent()) {
			isUserAvail = true;
		}

		return isUserAvail;
	}

}
