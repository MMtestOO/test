package com.proto.mm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proto.mm.model.Cart;
import com.proto.mm.model.Movie;
import com.proto.mm.service.CartService;
import com.proto.mm.service.MainService;
import com.proto.mm.service.MovieService;
import com.proto.mm.service.OrdersService;

@Controller
public class CartController {
	
	@Autowired
	MainService mainService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	OrdersService orderService;
	

	@GetMapping("cart")
	public String cart(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		mainService.signInCheck(model, request, response);
		cartService.showCartList(model, request, response);
		
		return "cart";
	}
	
	@RequestMapping(value = "cartInsert", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String cartInsert(Model model,HttpServletRequest request,
			HttpServletResponse response) {
			try {
				if (orderService.orderCheck(request, response) != null) {
					return "이미 구매한 영화 입니다.";
				}else if(cartService.cartCheck(request, response) == null) {
				cartService.cartInsert(request, response);
				String movieTitle = request.getParameter("movieTitle");
				
				return movieTitle + " 이(가) 장바구니에 담겼습니다.";
				}else {
					return "이미 장바구니에 담겨있습니다.";
				}
			}catch(Exception e) {
				return e.getMessage();
			}
		}
	
	@RequestMapping(value = "cartDelete", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String cartDelete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Cart cart = cartService.findCartMovie(request, response);
			cartService.cartDelete(cart);
			String movieTitle = request.getParameter("movieTitle");
			
			return movieTitle + "삭제 되었습니다.";
			
		}catch(Exception e) {
			return e.getMessage();
		}
		
		
	}
	
	@RequestMapping(value = "cartMovieDetail", 
			method= {RequestMethod.GET})
	public String showCartMovieDetail(Model model, HttpServletRequest request,
							  HttpServletResponse response) {
		String movieTitle=request.getParameter("movieTitle");

		mainService.signInCheck(model, request, response);
		movieService.showMovieDetail(model, movieTitle);
		System.out.println("영화 자세히 보기 정보 : "+model+"\n"+movieTitle);
		
		return "cartMovieDetail";
	}
	
}
