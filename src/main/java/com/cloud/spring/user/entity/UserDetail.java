package com.cloud.spring.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "UserDetail")
@Table(name = "USR_DETAIL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USR_ID")
	private Long userId;

	@Column(name = "USR_NAME")
	private String fname;

	@Column(name = "USR_PASSWORD")
	private String password;

	@Column(name = "USR_EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "USR_PHONE", unique = true, nullable = false)
	private String phone;

	@JsonManagedReference
	@OneToMany(mappedBy = "userDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Address> addresses = new HashSet<Address>();

}
