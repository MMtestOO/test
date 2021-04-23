package com.proto.mm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.proto.mm.model.Member;
import com.proto.mm.model.Movie;
import com.proto.mm.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public Model showMovieList(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("영화목록 서비스 호출");
		// 모든 영화 목록 조회 후 Movie List에 저장 후 model에 값 저장 후 반환
		List<Movie> movies = movieRepository.findAll(Sort.by(Sort.Direction.ASC, "movieCode"));
		model.addAttribute("movies", movies);
		model.addAttribute("searched", "searched"); //model.addAttribute("key","value")
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
			System.out.println(movies.toString());
		}catch(NullPointerException e) {
			System.out.println(e.getStackTrace());
		}
		return model;
	}
	
}
