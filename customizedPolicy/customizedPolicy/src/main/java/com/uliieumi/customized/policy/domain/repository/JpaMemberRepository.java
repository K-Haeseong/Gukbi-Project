package com.uliieumi.customized.policy.domain.repository;

import com.uliieumi.customized.policy.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaMemberRepository extends JpaRepository<Member, Long> {


}
