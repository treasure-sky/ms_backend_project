package com.example.gugucon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gugucon.dto.CommentDto;
import com.example.gugucon.entity.ArticleEntity;
import com.example.gugucon.entity.CommentEntity;
import com.example.gugucon.entity.MemberEntity;
import com.example.gugucon.repository.CommentRepository;

@Service
public class CommentService {
	
    @Autowired
    private CommentRepository commentRepository;
    
    public List<CommentEntity> getCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
    
    public CommentEntity createComment(CommentDto form, MemberEntity member, ArticleEntity article) {
        CommentEntity commentEntity = form.toEntity(member, article);
        return commentRepository.save(commentEntity);
    }
}
