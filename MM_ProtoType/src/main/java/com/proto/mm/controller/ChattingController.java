package com.proto.mm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.SwingWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proto.mm.model.Movie;
import com.proto.mm.service.ChattingService;
import com.proto.mm.service.MainService;

@Controller
public class ChattingController {

	@Autowired
	ChattingService chattingService;
	
	@Autowired
	MainService mainService;
	
	@GetMapping("chat")
	public String chat(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		mainService.signInCheck(model, request, response);
		String responseMessage = chattingService.MM_Chat(model, request, response);
		
		model.addAttribute("response",responseMessage);
		
		HttpSession session=request.getSession();
		session.setAttribute("chat",responseMessage);
		
		return "home";
	}
}
