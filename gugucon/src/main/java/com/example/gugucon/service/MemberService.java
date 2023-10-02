package com.example.gugucon.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gugucon.dto.MemberDto;
import com.example.gugucon.entity.MemberEntity;
import com.example.gugucon.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberDto login(MemberDto memberDto) {
		
		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDto.getMemberId());
		if(byMemberId.isPresent()) {
			// 조회 결과가 있다(해당 아이디를 가진 회원 정보가 있다)
			MemberEntity memberEntity = byMemberId.get();
			if(memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())) {
				// 비밀번호가 일치
				MemberDto dto = MemberEntity.toMemberDto(memberEntity);
				return dto;
			}
			else {
				// 비밀번호 불일치(로그인 실패)
				return null;
			}
		}
		else {
			// 조회 결과가 없다(해당 아이디을 가진 회원이 없다)
			return null;
		}
	}
	
	public String IdCheck(String memberId) {
		
		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
		if(byMemberId.isPresent()) {
			// 조회결과가 있다 -> 중복된 memberId이다
			return null;
		}
		else {
			// 조회결과가 있다 -> 사용할 수 있다
			return "ok";
		}
	}
	
	public void signin(MemberDto memberDto) {
		MemberEntity memberEntity = MemberDto.toMeberEntity(memberDto);
		memberRepository.save(memberEntity);
	}
	
	public MemberEntity findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId).get();
	}
}
