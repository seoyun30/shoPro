package com.example.shopro.dto;

import com.example.shopro.constant.Role;
import com.example.shopro.endtity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {


    private Long id;

    private String name;

    private String email;

    private String password;

    private String address;

    private Role role;


}
