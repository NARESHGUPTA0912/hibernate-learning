package com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// Owning Side


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "subjects")
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private int studentId;
	private String studentName;
	
	@ManyToMany(
		cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    }
	)
	@JoinTable(
		name = "student_subject",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
	private Set<Subject> subjects;
}
