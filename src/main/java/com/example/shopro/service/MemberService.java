package com.example.shopro.service;

import com.example.shopro.dto.MemberDTO;
import com.example.shopro.endtity.Member;
import com.example.shopro.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service        //비즈니스 로직을 담당하는 서비스 계층 클래스에
@Transactional   //이 어노테이션을 선언합니다. 로직을 처리하다가
                //에러가 발생하였다면 변겨된 데이터로직을 수행하기
                //이전 상태로 롤백 시켜줍니다.
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    //빈 주입하는 방법으로 @Autowired 어노테이션을 사용하거나 피드 주입
    //setter 사용하거나 생성자를 새로 만들어서 주입하는 방법등이 있다.
    //@RequiredArgsConstructore 어노테이션은 final이나
    //@NonNull이 붙은 필드에 생성자를 생성해줌 빈이라 new~~이거 생성자
    //빈이 생성자가 1개이고 생성자의 파라미터 타입이 빈등록이 가능하다면
    //어노테이션 없이 의존성 주입이 가능

    //로그인


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member=
        memberRepository.findByEmail(email);

        if (member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())  //세션에 저장
                .username(member.getPassword()) //로그인시에 입령한 비번과 비교괎
                .roles(member.getRole().name())
                .build();
    }

    //회원가입
    public Member saverMember(MemberDTO memberDTO){
        //회원가입여부


        //컨트롤러에서 받은 MemverDTO를 member entity로 변경

        Member member =
        memberDTO.dtoToEntity(memberDTO);

        member=
        memberRepository.save(member);

        return member;
    }

        //회원가입시 회원 가입여부 확인하는 메소드
        private void validateDuplicateMember(String email){

            Member member =
            memberRepository.findByEmail(email);
            //member 이 null이라는건 db에 회원가입이 되어있지 않은 email로 회원가입 가능
            //null이 아니라는건 db에 회원가입이 되어있어 가입을 막거나 예외처리등로 수행
            if(member != null){
                throw new IllegalStateException("이미 가입된 회원입니다.");
            }

            // 이 내용은 try{}catch(IllegalStateException e){
            //          model.att~~("msg", e.get 메세지)
            //          return"y/signup";} 처리 가능

         }





    }



