package com.example.gugucon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gugucon.dto.MemberDto;
import com.example.gugucon.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/login")
	public String login() {
		return "login"; // -> 로그인 화면으로
	}
	@GetMapping("/signin")
	public String signin() {
		return "signin"; // -> 회원가입 화면으로
	}
	
	//로그인 요청을 받음
	@PostMapping("/login_request")
	public String login_request(@ModelAttribute MemberDto memberDto, HttpSession session) {
		
		MemberDto loginResult = memberService.login(memberDto);
		if(loginResult != null) {
			
			// login 성공
			session.setAttribute("loginId", loginResult.getMemberId());
			return "redirect:/main"; // -> main 페이지 이동
		}
		else {
			// login 실패
			return "redirect:/login";
		}
	}
	// 회원가입 요청을 받음
	@PostMapping("/signin_request")
	public String signin_request(@ModelAttribute MemberDto memberDto) {
		
		String checkResult = memberService.IdCheck(memberDto.getMemberId());
		if(checkResult!=null && checkResult.equals("ok")) {
			memberService.signin(memberDto);
			return "redirect:/login"; // 회원가입 성공, 로그인 페이지로 이동
		}
		else {
			// 중복아이디가 이미 존재하는 경우에 대한 처리
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/login";
	}
}
