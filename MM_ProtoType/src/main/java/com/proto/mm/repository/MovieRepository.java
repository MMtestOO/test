package com.proto.mm.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proto.mm.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, BigDecimal>{
	
	public Movie findByMovieTitle(String movieTitle);
	
	public List<Movie> findByMovieTitle(String movieTitle, Sort sort);
	
	public Movie findByMovieCode(BigDecimal movieCode);
	
	public List<Movie> findByMovieTitleContains(String movieTitle, Sort sort);
	
	public List<Movie> findTop6ByMovieGenreContains(String movieGenre, Sort sort);

	public List<Movie> findByMovieTitleStartsWith(String searchValue, Sort sort);
	
	public List<Movie> findByMovieGenre(String movieGenre, Sort sort);
	
	public List<Movie> findByMovieGenreAndMovieRatingGreaterThanEqual(String movieGenre, BigDecimal movieRating, Sort sort);
	
	public List<Movie> findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqual(String movieGenre, BigDecimal movieRating, BigDecimal moviePrice, Sort sort);
	
	public List<Movie> findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqualAndMovieRdateContains(String movieGenre, BigDecimal movieRating, BigDecimal moviePrice, String movieRdate, Sort sort);
	
	public List<Movie> findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqualAndMovieRdateContainsAndMovieRtimeLessThanEqual(String movieGenre, BigDecimal movieRating, BigDecimal moviePrice, String movieRdate, BigDecimal movieRtime, Sort sort);
	
	public List<Movie> findByMovieGenreOrMovieRatingGreaterThanEqual(String movieGenre, BigDecimal movieRating, Sort sort);
	
	public List<Movie> findByMovieGenreAndMovieRatingGreaterThanEqualOrMoviePriceLessThanEqual(String movieGenre, BigDecimal movieRating, BigDecimal moviePrice, Sort sort);
	
	public List<Movie> findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqualOrMovieRdateContains(String movieGenre, BigDecimal movieRating, BigDecimal moviePrice, String movieRdate, Sort sort);
	
	public List<Movie> findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqualAndMovieRdateContainsOrMovieRtimeLessThanEqual(String movieGenre, BigDecimal movieRating, BigDecimal moviePrice, String movieRdate, BigDecimal movieRtime, Sort sort);
	
}
