package com.training.StartAnnotation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import java.util.List;

@Entity
@Table(name = "STUDENT_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@Column(name = "STUDENT_ID", columnDefinition = "serial")
	private int id;

	@Column(name = "STUDENT_NAME")
	private String name;

	@Column(name = "ROLL_NO")
	private int rollNo;

	@Column(name = "DEPT")
	private String dept;

}
