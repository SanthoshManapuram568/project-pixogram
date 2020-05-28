package com.pixogram.mediaservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity 
@Table(name = "media")
public class Media {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer userId;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String tags;
	
	@Column
	private String mimeType;
	
	@Column
	private String fileUrl;
	
	@CreationTimestamp
	@Column
	private LocalDateTime createdOn;
	@UpdateTimestamp
	@Column
	private LocalDateTime updatedOn;
	
}
