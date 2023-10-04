package com.example.gugucon.dto;

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
public class MemberDto {
	private long id;
	private String memberId;
	private String memberPassword;
	
	public static MemberEntity toMemberEntity(MemberDto memberDto) {
		MemberEntity memberEntity = new MemberEntity();
		
		memberEntity.setId(memberDto.getId());
		memberEntity.setMemberId(memberDto.getMemberId());
		memberEntity.setMemberPassword(memberDto.getMemberPassword());
		
		return memberEntity;
	}
}
