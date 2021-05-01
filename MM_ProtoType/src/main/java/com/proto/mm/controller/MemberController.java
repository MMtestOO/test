  
package com.proto.mm.controller;




import java.math.BigDecimal;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proto.mm.model.Member;
import com.proto.mm.service.MainService;
import com.proto.mm.service.MemberService;
import com.proto.mm.service.OrdersService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MainService mainService;
	
	@Autowired
	OrdersService orderService;
	
	
	@GetMapping("memberInsertForm")
	public String signUp() {
		return "memberInsertForm";
	}
	
	
	@RequestMapping(value = "memberInsert", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String memberInsert(HttpServletRequest request,
			HttpServletResponse response, String[] prefer) {
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String temp = Arrays.toString(prefer);
		String preference = temp.replaceAll("[\\s\\[\\]]", "");
		BigDecimal mem_count = null;
		try {
			Member m=new Member(mem_count,id,pw,name,preference); 
			Member result = memberService.idCheck(id);
			if(result == null) {
				memberService.memberInsert(m);
				return name+"님 회원가입 되셨습니다.";	
			}else {
				return "아이디가 중복입니다.";
			}
			
		}catch(Exception e) {
			return e.getMessage();
		}	
	}	
	
	
	@GetMapping("memberList")
	public String member(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		memberService.showList(model);
		return "memberList";
	}
	
	@GetMapping("memberInfo")
	public String memberInfo(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		HttpSession session=request.getSession();
		memberService.showMemberInfo(model,session);
		// orderService에서 정보 가져오기
		orderService.showOrderList(model, request, response);
		
		return "memberInfo";
	}

	@GetMapping("memberUpdateForm")
	public String update(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("회원정보수정 서비스 호출");
		return "memberUpdateForm";
	}
	
	@RequestMapping(value = "memberUpdate",
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String memberUpdate(Model model,HttpServletRequest request,
			HttpServletResponse response, String[] prefer) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		HttpSession session=request.getSession();
		Member member = (Member)session.getAttribute("member");
		Member result = memberService.idCheck(member.getId());
		String pw=request.getParameter("pw");
		if(pw.equals(member.getPw()))
			result.setPw(pw);
		else
			return "비밀번호가 일치하지 않습니다.";
		String name=request.getParameter("name");
		String temp = Arrays.toString(prefer);
		String preference = temp.replaceAll("[\\s\\[\\]]", "");
		
		if(!name.isEmpty() && !preference.equals("null"))
		{
			result.setName(name);
			result.setPreference(preference);
		}else if(!name.isEmpty())
			result.setName(name);
		else if(!preference.equals("null"))
			result.setPreference(preference);
		else
			return "수정된 정보가 없습니다.";
		
		System.out.println(result);
		memberService.memberInsert(result);
		session.setAttribute("member", result);
		
		return result.getName()+"님 회원 정보 수정 되셨습니다.";	
	}
	
	@RequestMapping(value = "memberDelete",
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String memberDelete(Model model,HttpServletRequest request,
			HttpServletResponse response, String[] prefer) {
		// 세션을 체크해서 로그인 상태인지 확인
		mainService.signInCheck(model, request, response);
		HttpSession session=request.getSession();
		Member member = (Member)session.getAttribute("member");
		memberService.memberDelete(member);
		session.invalidate();
		
		return member.getName()+"님 회원 탈퇴되셨습니다.";	
	}
}