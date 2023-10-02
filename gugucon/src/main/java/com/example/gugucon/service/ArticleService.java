package com.example.gugucon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gugucon.dto.ArticleDto;
import com.example.gugucon.entity.ArticleEntity;
import com.example.gugucon.entity.MemberEntity;
import com.example.gugucon.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
    private MemberService memberService;

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleEntity createArticle(ArticleDto form, String loggedInUser) {
    	
        MemberEntity loggedInUserId = memberService.findByMemberId(loggedInUser);
        
        ArticleEntity articleEntity = form.toEntity(form);
        articleEntity.setWriter(loggedInUserId);
        
        return articleRepository.save(articleEntity);
    }

    public ArticleEntity getArticle(Long id) {
    	
        return articleRepository.findById(id).orElse(null);
    }

    public ArticleEntity updateArticle(ArticleDto form, String loggedInUser) {
        MemberEntity loggedInUserId = memberService.findByMemberId(loggedInUser);
        
        ArticleEntity articleEntity = form.toEntity(form);
        articleEntity.setWriter(loggedInUserId);

        ArticleEntity target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null) {
            return articleRepository.save(articleEntity);
        }
        
        return null;
    }

    public void deleteArticle(Long id) {
    	
        ArticleEntity target = articleRepository.findById(id).orElse(null);
        
        if(target != null) {
            articleRepository.delete(target);
        }
    }
    
    public List<ArticleEntity> index(){
		
		return articleRepository.findAll();
	}
}
