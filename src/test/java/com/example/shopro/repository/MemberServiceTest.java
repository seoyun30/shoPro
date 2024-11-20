package com.example.shopro.repository;


import com.example.shopro.dto.MemberDTO;
import com.example.shopro.endtity.Member;
import com.example.shopro.service.MemberService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.description.ByteCodeElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.spi.ValueReader;
import org.modelmapper.spi.ValueWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입테스트")
    public void saveMemberTest(){

        MemberDTO memberDTO = MemberDTO.builder()
                .address("경남 사이타마현")
                .email("sin@a.a")
                .password("1234")
                .name("신짱구")
                .build();

        try{
            Member member =
                    memberService.saverMember(memberDTO);
            log.info(member);

        }catch (IllegalStateException e){
            log.info(e.getMessage());

        }


    }

}