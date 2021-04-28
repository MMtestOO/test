package com.proto.mm.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.proto.mm.model.Movie;
import com.proto.mm.repository.MovieRepository;
import com.proto.mm.repository.PosterRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private PosterService posterService;
	
	public Model showMovieList(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("영화목록 서비스 호출");
		// 모든 영화 목록 조회 후 Movie List에 저장 후 model에 값 저장 후 반환
		List<Movie> movies = movieRepository.findAll(Sort.by(Sort.Direction.ASC, "movieCode"));
		
		model.addAttribute("movies", movies);
		model.addAttribute("searched", "searched"); //model.addAttribute("key","value")
		posterService.showPosterResult(model);
		/*for(Movie movie : movies) {
			System.out.println(movie.toString());
		}*/
		
		return model;
	}
	
	public Model showMovieDetail(Model model, String movieTitle) {
		System.out.println("영화 자세히 보기 서비스 호출");
		Movie movie = movieRepository.findByMovieTitle(movieTitle);
		//System.out.println(movieTitle);
		model.addAttribute("movie", movie);
		model.addAttribute("detail", "detail");
		//System.out.println(movie.toString());
		posterService.showDetailPoster(model, movieTitle);
		return model;
	}

	public Model showMovieByMovieTitle(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("영화검색 서비스 호출");
			String movieTitle = request.getParameter("movieTitle");
			List<Movie> movies = movieRepository.findByMovieTitleContains(movieTitle, Sort.by(Sort.Direction.ASC, "movieCode"));
			model.addAttribute("searched", "searched"); //model.addAttribute("key","value")
			model.addAttribute("movies", movies);
			posterService.showPosterResult(model);
			System.out.println(movies.toString());
		}catch(NullPointerException e) {
			System.out.println(e.getStackTrace());
		}
		return model;
	}
	
	public Model movieFilter(Model model, String movieGenre, int movieRating, int moviePrice,String movieRdate,int movieRtime) {
		try {
			System.out.println("영화 필터 호출");
			List<Movie> movies = null;
			if(!movieGenre.equals("") && movieRating == 0 && moviePrice == 0 && movieRdate.equals("") && movieRtime == 0) {
				movies = movieRepository.findByMovieGenre(movieGenre, Sort.by(Sort.Direction.ASC, "movieCode"));
			}else if(!movieGenre.equals("") && movieRating != 0 && moviePrice == 0 && movieRdate.equals("") && movieRtime == 0) {
				movies = movieRepository.findByMovieGenreAndMovieRatingGreaterThanEqual(movieGenre, new BigDecimal(movieRating), Sort.by(Sort.Direction.ASC, "movieCode"));
			}else if(!movieGenre.equals("") && movieRating != 0 && moviePrice != 0 && movieRdate.equals("") && movieRtime == 0) {
				movies = movieRepository.findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqual(movieGenre, new BigDecimal(movieRating), new BigDecimal(moviePrice), Sort.by(Sort.Direction.ASC, "movieCode"));
			}else if(!movieGenre.equals("") && movieRating != 0 && moviePrice != 0 && !movieRdate.equals("") && movieRtime == 0) {
				movies = movieRepository.findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqualAndMovieRdateContains(movieGenre, new BigDecimal(movieRating), new BigDecimal(moviePrice), movieRdate, Sort.by(Sort.Direction.ASC, "movieCode"));
			}else if(!movieGenre.equals("") && movieRating != 0 && moviePrice != 0 && !movieRdate.equals("") && movieRtime != 0) {
				movies = movieRepository.findByMovieGenreAndMovieRatingGreaterThanEqualAndMoviePriceLessThanEqualAndMovieRdateContainsAndMovieRtimeLessThanEqual(movieGenre, new BigDecimal(movieRating), new BigDecimal(moviePrice), movieRdate, new BigDecimal(movieRtime), Sort.by(Sort.Direction.ASC, "movieCode"));
			}
			if(movies != null)
			{
				model.addAttribute("movies", movies);
				for(Movie movie : movies) {
					System.out.println(movie.toString());
				}
			}
			
		}catch(NullPointerException e) {
			System.out.println(e.getStackTrace());
		}
		return model;
	}
	
}
