package com.polify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor()
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Getter
	@Setter
	@Column(name = "username", nullable = false, unique = true)
	private String username;
    
	@Setter
	@Column(name = "password", nullable = false)
	private String password;
    
	@Getter
	@Setter
	@Column(name = "first_name", nullable = false)
	private String firstName;
    
	@Getter
	@Setter
	@Column(name = "last_name", nullable = false)
	private String lastName;
    
	@Getter
	@Setter
	@Column(name = "email_address", nullable = false)
	private String email;

//	@Getter
//	@Setter
//	@Column(name = "image")
//	private String image;
    
	@Getter
	@Setter
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;
    
	@Getter
	@Setter
	@Column(name = "created_by", nullable = false)
	@CreatedBy
	private String createdBy;
    
	@Getter
	@Setter
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;
    
	@Getter
	@Setter
	@Column(name = "updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public User(String username, String password, String firstName, String lastName, String email) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@PrePersist
	public void onCreate(){
		updatedAt = new Date();
		createdAt = new Date();
	}

}
