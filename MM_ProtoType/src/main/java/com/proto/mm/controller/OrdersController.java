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
import com.proto.mm.service.CartService;
import com.proto.mm.service.MainService;
import com.proto.mm.service.OrdersService;



@Controller
public class OrdersController {

	@Autowired
	OrdersService orderService;
	
	@Autowired
	MainService mainService;
	
	@Autowired
	CartService cartService;
	
	@RequestMapping(value = "movieOrder", 
			method= {RequestMethod.GET})
	public String cart(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		orderService.showOrder(model, request, response);
	
		return "movieOrder";
	}
	
	@GetMapping("orderList")
	public String order(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		orderService.showOrderList(model, request, response);
		return "orderList";
	}

	
	
	@RequestMapping(value = "orderInsert", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String cartInsert(Model model,HttpServletRequest request,
			HttpServletResponse response) {
			try {
				
				if(orderService.orderCheck(request, response) == null) {
					String movieTitle = request.getParameter("movieTitle");
					
					Cart cart = cartService.findCartMovie(request, response);
					cartService.cartDelete(cart);
				
					return movieTitle + " 구매를 진행합니다.";
				}else {
					Cart cart = cartService.findCartMovie(request, response);
					cartService.cartDelete(cart);
					return "이미 구매한 영화 입니다.";
				}
			}catch(Exception e) {
				return e.getMessage();
			}
	}
	
	@RequestMapping(value = "orderDown", 
	method= {RequestMethod.POST},
	produces = "application/text; charset=utf8")
	@ResponseBody
	public String orderDownload(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		orderService.updateOrder(model, request, response);
		String movie_title = request.getParameter("movieTitle");
		return movie_title + " 다운로드 완료되었습니다.";
	}
	
	@RequestMapping(value = "orderDelete", 
	method= {RequestMethod.POST},
	produces = "application/text; charset=utf8")
	@ResponseBody
	public String orderDelete(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		orderService.deleteOrder(model, request, response);
		cartService.cartInsert(request, response);
		String movie_title = request.getParameter("movieTitle");
		return movie_title + " 환불되었습니다.";
	}
}
