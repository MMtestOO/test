package com.proto.mm.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name="POSTER_NO_SEQ_GENERATOR",
		sequenceName = "POSTER_NO_SEQ", // 매핑할 데이터 베이스 시퀀스 이름
		initialValue = 1000,
		allocationSize = 1)
public class Poster {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator ="POSTER_NO_SEQ_GENERATOR")
	@Column(name = "poster_count")
	private BigDecimal posterCount;
	
	@NonNull
	@Column(name = "poster_path")
	private String posterPath;
	
	@NonNull
	@Column(name = "movie_code")
	private BigDecimal movieCode;

}
