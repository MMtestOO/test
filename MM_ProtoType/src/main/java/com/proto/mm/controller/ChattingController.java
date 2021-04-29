package com.proto.mm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.SwingWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proto.mm.service.ChattingService;
import com.proto.mm.service.MainService;

@Controller
public class ChattingController {

	@Autowired
	ChattingService chattingService;
	
	@Autowired
	MainService mainService;
	
	@GetMapping("chat")
	@ResponseBody
	public String chat(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		mainService.signInCheck(model, request, response);
		String responseMessage = chattingService.MM_Chat(model, request, response);
		
		return responseMessage;
	}
	
}
