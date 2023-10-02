package com.example.gugucon.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.gugucon.dto.ArticleDto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article_table")
public class ArticleEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne // 다대일 관계 설정
    @JoinColumn(name = "logged_in_member_id") // 외래키 설정
    private MemberEntity writer;  // 로그인한 사용자 ID
    
    @ElementCollection
    @CollectionTable(name = "article_stack", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "stack")
    private List<String> stack = new ArrayList<>();
    
    public ArticleDto toDto(ArticleEntity articleEntity) {
    	
    	ArticleDto articleDto = new ArticleDto();
    	
    	articleDto.setId(articleEntity.getId());
    	articleDto.setTitle(articleEntity.getTitle());
    	articleDto.setContent(articleEntity.getContent());
    	articleDto.setStack(articleEntity.getStack());
    	
    	return articleDto;
    }
}
