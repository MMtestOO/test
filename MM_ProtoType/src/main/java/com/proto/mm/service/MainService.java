package com.proto.mm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.proto.mm.model.Member;

@Service
public class MainService {
	
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
}
