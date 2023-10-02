package com.example.gugucon.entity;

import com.example.gugucon.dto.MemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "member_table")

public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;
	
	@Column (unique = true) // unique 제약조건 추가
	private String memberId;
	
	@Column
	private String memberPassword;
	
	public static MemberDto toMemberDto(MemberEntity memberEntity) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setId(memberEntity.getId());
		memberDto.setMemberId(memberEntity.getMemberId());
		memberDto.setMemberPassword(memberEntity.getMemberPassword());
		
		return memberDto;
	}
}
