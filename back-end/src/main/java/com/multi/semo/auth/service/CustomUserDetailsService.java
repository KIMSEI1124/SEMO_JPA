package com.multi.semo.auth.service;

import com.multi.semo.auth.exception.AuthErrorCode;
import com.multi.semo.auth.exception.AuthException;
import com.multi.semo.member.domain.Member;
import com.multi.semo.member.domain.MemberRepository;
import com.multi.semo.member.domain.embedded.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return memberRepository.findByEmail(Email.of(email))
                .map(this::createUserDetails)
                .orElseThrow(() -> new AuthException(AuthErrorCode.MEMBER_EMAIL_IS_NOT_EXIST));
    }

    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole().getValue());

        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
