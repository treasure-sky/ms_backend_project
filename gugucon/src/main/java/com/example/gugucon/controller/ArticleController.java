package com.example.gugucon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gugucon.dto.ArticleDto;
import com.example.gugucon.entity.MemberEntity;
import com.example.gugucon.entity.ArticleEntity;
import com.example.gugucon.entity.CommentEntity;
import com.example.gugucon.service.ArticleService;
import com.example.gugucon.service.CommentService;
import com.example.gugucon.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleController {
	
	@Autowired
	private MemberService memberService;

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CommentService commentService;
	
	@GetMapping("/articleForm")
	public String articleForm() {
		return "articleForm"; // -> 게시글 작성
	}

	@PostMapping("/articleCreate")
	public String articleCreate(ArticleDto form, HttpSession session) {
		
		String loggedInUser = (String) session.getAttribute("loginId");
		ArticleEntity saved = articleService.createArticle(form, loggedInUser);
		
		return "redirect:/main/article/" + saved.getId();
	}

	@GetMapping("/main/article/{id}")
	public String articleShow(@PathVariable Long id, Model model, MemberEntity memberEntity, HttpSession session) {
		
		ArticleEntity articleEntity = articleService.getArticle(id);
		
		String loggedInUser = (String) session.getAttribute("loginId");
		MemberEntity loggedInUserId = memberService.findByMemberId(loggedInUser);
		
		List<CommentEntity> comments = commentService.getCommentsByArticleId(id);
	    model.addAttribute("comments", comments);
		
		model.addAttribute("article", articleEntity);
		model.addAttribute("member", loggedInUserId);
		
		return "article"; // -> 해당 id 번호의 게시글 읽기
	}

	@GetMapping("/main/article/{id}/edit")
	public String articleEdit(@PathVariable Long id, Model model) {
		
		ArticleEntity articleEntity = articleService.getArticle(id);
		model.addAttribute("article", articleEntity);
		
		return "articleEdit"; // -> 수정 페이지 이동
	}

	@PostMapping("/main/article/{id}/update")
	public String articleUpdate(ArticleDto form, HttpSession session) {
		
		String loggedInUser = (String) session.getAttribute("loginId");
		articleService.updateArticle(form, loggedInUser);
		
		return "redirect:/main/article/" + form.getId(); // -> 수정된 해당 id 번호의 게시글 읽기
	}

	@GetMapping("/main/article/{id}/delete")
	public String articleDelete(@PathVariable Long id, RedirectAttributes rttr) {
		
		articleService.deleteArticle(id);
		rttr.addFlashAttribute("msg", "Deletion is complete");
		
		return "redirect:/main";
	}
}
