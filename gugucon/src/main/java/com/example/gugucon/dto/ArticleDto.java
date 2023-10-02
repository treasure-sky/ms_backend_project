package com.example.gugucon.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.gugucon.entity.ArticleEntity;
import com.example.gugucon.entity.MemberEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleDto {
	private Long id;
    private String title;
    private String content;
    
    @JsonProperty("logged_in_member_id")
    private MemberEntity writer;
    
    private List<String> stack = new ArrayList<>();
    
    public ArticleEntity toEntity(ArticleDto articleDto) {
    	
    	ArticleEntity articleEntity = new ArticleEntity();
    	
    	articleEntity.setId(articleDto.getId());
    	articleEntity.setTitle(articleDto.getTitle());
    	articleEntity.setContent(articleDto.getContent());
    	articleEntity.setStack(articleDto.getStack());
    	
    	return articleEntity;
    }
}
