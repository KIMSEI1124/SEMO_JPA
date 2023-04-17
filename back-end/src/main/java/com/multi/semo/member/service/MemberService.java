package com.multi.semo.member.service;

import com.multi.semo.member.domain.Member;
import com.multi.semo.member.domain.MemberRepository;
import com.multi.semo.member.domain.embedded.Email;
import com.multi.semo.member.domain.embedded.Name;
import com.multi.semo.member.domain.embedded.Password;
import com.multi.semo.member.domain.embedded.Phone;
import com.multi.semo.member.dto.request.MemberSignupRequest;
import com.multi.semo.member.exception.MemberErrorCode;
import com.multi.semo.member.exception.MemberException;
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
        Email email = Email.of(request.getEmail());
        validateEmailIsNotDuplicated(email);

        Phone phone = Phone.of(request.getPhone());
        validatePhoneIsNotDuplicated(phone);

        Member saveMember = Member.builder()
                .birth(request.getBirth())
                .email(email)
                .name(Name.of(request.getName()))
                .password(Password.encode(request.getPassword(), passwordEncoder))
                .phone(phone)
                .build();

        return memberRepository.save(saveMember);
    }

    private void validateEmailIsNotDuplicated(Email email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException(MemberErrorCode.SIGNUP_INVALID_EMAIL);
        }
    }

    private void validatePhoneIsNotDuplicated(Phone phone) {
        if (memberRepository.existsByPhone(phone)) {
            throw new MemberException(MemberErrorCode.SIGNUP_INVALID_PHONE);
        }
    }
}
