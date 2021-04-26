package com.proto.mm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
public class MainService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Model signInCheck(Model model,
			HttpServletRequest request, HttpServletResponse response) {
			
		try {
			// 세션이 있으면 가져오고 없으면 생성
			HttpSession session=request.getSession();
			if(session.getAttribute("member") != null) {
				// 세션에 member 속성이 있으면 'logined' 속성을 추가
				model.addAttribute("logined", "logined");
				Member member = (Member) session.getAttribute("member");
				model.addAttribute(member);
			}else {
			}
		}catch(NullPointerException e) {
			e.getStackTrace();
		}
		 
		return model;
	}

	public Model showSelectedGenre(Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		
		Member member = (Member)session.getAttribute("member");
		String temp = member.getPreference();
		String[] movieGenre = temp.split(",");
		
		int selectedGenreCount = new Random().nextInt(movieGenre.length);
		//System.out.println(selectedGenreCount+"번 째로 고른 장르");
		
		List<Movie> movies = movieRepository.findTop9ByMovieGenreContains(movieGenre[selectedGenreCount], Sort.by(Sort.Direction.DESC, "movieRating"));
		model.addAttribute("movies", movies);

		System.out.println("영화 취향 선택에 따른 영화 목록 서비스");
		
		return model;
	}
	
}
