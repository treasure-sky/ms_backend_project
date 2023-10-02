package com.example.gugucon.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gugucon.entity.ArticleEntity;
import com.example.gugucon.service.ArticleService;

@RestController
public class ArticleApi {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/api/articleList")
    public ResponseEntity<List<ArticleEntity>> index(){
        List<ArticleEntity> articles = articleService.index();
        return ResponseEntity.ok(articles);
    }
}
