package com.multi.semo.member.service;

import com.multi.semo.member.domain.Member;
import com.multi.semo.member.domain.MemberRepository;
import com.multi.semo.member.domain.embedded.Email;
import com.multi.semo.member.domain.embedded.Name;
import com.multi.semo.member.domain.embedded.Password;
import com.multi.semo.member.domain.embedded.Phone;
import com.multi.semo.member.dto.request.MemberSignupRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signup(MemberSignupRequest request) {
        /* TODO: 중복가입 방지(이메일, 전화번호 검증) */
        log.info("birth : [{}], email : [{}], name : [{}], password : [{}], phone : [{}]"
                , request.getBirth(), request.getEmail(), request.getName(), request.getPassword(), request.getPhone());
        Member saveMember = Member.builder()
                .birth(request.getBirth())
                .email(Email.of(request.getEmail()))
                .name(Name.of(request.getName()))
                .password(Password.encode(request.getPassword(), passwordEncoder))
                .phone(Phone.of(request.getPhone()))
                .build();
        return memberRepository.save(saveMember);
    }
}
