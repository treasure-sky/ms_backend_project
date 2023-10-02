package com.example.gugucon.dto;

import com.example.gugucon.entity.ArticleEntity;
import com.example.gugucon.entity.CommentEntity;
import com.example.gugucon.entity.MemberEntity;

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
public class CommentDto {
	private Long id;
    private String content;
    private Long articleId;
    private String memberId;

    public CommentEntity toEntity(MemberEntity member, ArticleEntity article) {
        CommentEntity commentEntity = new CommentEntity();
        
        commentEntity.setId(this.id);
        commentEntity.setContent(this.content);
        commentEntity.setMember(member);
        commentEntity.setArticle(article);
        
        return commentEntity;
    }
}
