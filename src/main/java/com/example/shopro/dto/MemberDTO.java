package com.example.shopro.dto;

import com.example.shopro.constant.Role;
import com.example.shopro.endtity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {


    private Long id;

    @NotBlank
    @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하로 작성해주세요")
    private String name;

    @Email(message = "이메일 형식으로 작성해주세요")
    @NotBlank(message = "이메일ㄹ을 작성해주세요")
    @Size(min = 2, max = 20, message = "이메일은 2자 이상 10자 이하로 작성해주세요")
    private String email;

    @NotBlank
    @Size(min = 4, max = 10)
    private String password;

    private String address;

    private Role role; //세팅을 위해서 존재 관리자만 필요할 수 있다.혹시 몰라 일단 넣어둠

    public Member dtoToEntity(MemberDTO memberDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setAddress(memberDTO.getAddress());

        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setRole(Role.ADMIN);

        return member;
    }

}
