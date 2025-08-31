package com.library.library.member;

import com.library.library.borrow.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
