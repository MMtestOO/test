package com.proto.mm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proto.mm.service.MainService;
import com.proto.mm.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@Autowired
	MainService mainService;
	
<<<<<<< HEAD
	@GetMapping("home")
	public String movie(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		movieService.showMovieList(model);
		return "home";
	}
=======

>>>>>>> upstream/develop
	
	// Show movie detail
	@RequestMapping(value = "movieDetail", 
			method= {RequestMethod.GET})
	public String showMovieDetail(Model model, HttpServletRequest request,
							  HttpServletResponse response) {
		String movieTitle=request.getParameter("movieTitle");

		mainService.signInCheck(model, request, response);
		movieService.showMovieDetail(model, movieTitle);
		System.out.println("영화 자세히 보기 정보 : "+model+"\n"+movieTitle);
		
		return "home";
	}

	@RequestMapping(value = "movieList", 
	method= {RequestMethod.GET})
	public String movieList(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		movieService.showMovieList(model, request, response);
		System.out.println(model.getAttribute("searched"));
		
		return "home";
	}
	
	
	@RequestMapping(value = "movieSearch", 
	method= {RequestMethod.GET})
	public String movieSearch(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		
		String movieTitle=request.getParameter("movieTitle");
		if(movieTitle != "") {
			movieService.showMovieByMovieTitle(model,request, response);
		}else {
			movieService.showMovieList(model, request, response);
		}
		
		
		System.out.println(model.getAttribute("searched"));
		
		return "home";
	}


	
	
}
