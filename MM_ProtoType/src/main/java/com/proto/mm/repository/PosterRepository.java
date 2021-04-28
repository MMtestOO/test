package com.proto.mm.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proto.mm.model.Poster;

public interface PosterRepository extends JpaRepository<Poster, BigDecimal>{

	public Poster findByMovieCode(BigDecimal movieCode);

}
