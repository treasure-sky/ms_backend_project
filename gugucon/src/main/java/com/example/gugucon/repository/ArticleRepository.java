package com.example.gugucon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gugucon.entity.ArticleEntity;


public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>{

}
