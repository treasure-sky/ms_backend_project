package com.example.gugucon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gugucon.dto.CommentDto;
import com.example.gugucon.entity.ArticleEntity;
import com.example.gugucon.entity.MemberEntity;
import com.example.gugucon.service.ArticleService;
import com.example.gugucon.service.CommentService;
import com.example.gugucon.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private ArticleService articleService;

    @PostMapping("/article/{articleId}/addComment")
    public String addComment(@PathVariable Long articleId, CommentDto commentDto, HttpSession session) {
        
        String loggedInUserId = (String) session.getAttribute("loginId");
        MemberEntity loggedInUser = memberService.findByMemberId(loggedInUserId);
        ArticleEntity article = articleService.getArticle(articleId);

        commentService.createComment(commentDto, loggedInUser, article);
        
        return "redirect:/main/article/" + articleId;
    }
}
