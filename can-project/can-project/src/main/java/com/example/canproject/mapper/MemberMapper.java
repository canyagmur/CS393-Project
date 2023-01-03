package com.example.canproject.mapper;


import com.example.canproject.DTO.MemberDTO;
import com.example.canproject.entity.Member;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface MemberMapper {
    MemberMapper INSTANCE= Mappers.getMapper(MemberMapper.class);

    List<MemberDTO> toMemberDTOS(List<Member> members);
    List<Member> toMembers(List<MemberDTO> memberDTOS);

    Member toMember(MemberDTO memberDTO);
    MemberDTO toMemberDTO(Member member);
}
