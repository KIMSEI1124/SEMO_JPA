package com.multi.semo.member.domain;

import com.multi.semo.member.domain.embedded.Email;
import com.multi.semo.member.domain.embedded.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(Email email);

    boolean existsByPhone(Phone email);
}
