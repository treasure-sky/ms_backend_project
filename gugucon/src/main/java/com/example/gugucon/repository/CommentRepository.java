package com.example.gugucon.repository;

import com.example.gugucon.entity.CommentEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	List<CommentEntity> findByArticleId(Long articleId);
}
