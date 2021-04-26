package com.proto.mm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.proto.mm.model.Cart;
import com.proto.mm.model.Member;
import com.proto.mm.model.Movie;
import com.proto.mm.repository.CartRepository;
import com.proto.mm.repository.MovieRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MovieRepository movieRepository;

	public Model showCartList(Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
	
		
		System.out.println("카트 목록 서비스 호출");
		
		// 세션이 있으면 가져오고 없더라도 생성하지 않음
		HttpSession session = request.getSession(false);
		
		try {
			// 세션에 로그인 된 멤버의 memCount 가져오기
			Member member = (Member) session.getAttribute("member");
			model.addAttribute(member);
			BigDecimal memCount = member.getMemCount();
			System.out.println(member.toString());
			System.out.println("세션 멤버 가져오기 성공");
			
			// 로그인 된 멤버의 memCount를 기준으로 장바구니 목록 조회
			// cartCount로 정렬하여 가져오기
			List<Cart> carts = cartRepository.findByMemCount(memCount, Sort.by(Sort.Direction.ASC, "cartCount"));
			model.addAttribute("carts", carts);
			System.out.println("카트 목록 가져오기 성공");
			
			// cart에 있는 영화 코드를 기준으로 영화 정보 가져오기
			List<Movie> cartMovies = findMovieByMovieCode(carts);
			model.addAttribute("movies", cartMovies);
			System.out.println("카트 내 영화 목록 가져오기 성공");
			
		

		}catch(NullPointerException e) {

		}
		return model;
		
	}
	
	public List<Movie> findMovieByMovieCode (List<Cart> carts){
		
		List<Movie> cartMovies=new ArrayList<Movie>();
		
		for(Cart cart : carts) {
			BigDecimal movieCode = cart.getMovieCode();
			Movie movie = new Movie();
			movie = movieRepository.findByMovieCode(movieCode);
			cartMovies.add(movie);
		}
		return cartMovies;
	}

	public void cartInsert(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("member");
		BigDecimal memCount = member.getMemCount();
		
		String movie_title = request.getParameter("movieTitle");
		Movie movie = movieRepository.findByMovieTitle(movie_title);
		BigDecimal movieCode = movie.getMovieCode();
		BigDecimal cartCount=null;
		Cart cart = new Cart(cartCount, memCount, movieCode);
		
		cartRepository.save(cart);
	}
	
	public Cart cartCheck(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("member");
		BigDecimal memCount = member.getMemCount();

		String movie_title = request.getParameter("movieTitle");
		Movie movie = movieRepository.findByMovieTitle(movie_title);
		BigDecimal movieCode = movie.getMovieCode();
		
		return cartRepository.findByMovieCodeAndMemCount(memCount, movieCode);

		
	}

	public void cartDelete(Cart c) {
		System.out.println("카트 삭제 서비스 호출");
		cartRepository.delete(c);
	}

	public Cart findCartMovie(HttpServletRequest request, HttpServletResponse response) {
		 System.out.println("영화 찾기 서비스 호출");
		 HttpSession session = request.getSession(false);
		 Member member = (Member) session.getAttribute("member");
		 BigDecimal memCount = member.getMemCount();
		 
		 String movieTitle=request.getParameter("movieTitle");
		 Movie movie=movieRepository.findByMovieTitle(movieTitle);
		 BigDecimal movieCode = movie.getMovieCode();
		 Cart cart = cartRepository.findByMovieCodeAndMemCount(movieCode, memCount);

		return cart;
	}

}
