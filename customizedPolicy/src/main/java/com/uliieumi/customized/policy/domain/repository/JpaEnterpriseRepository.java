package com.uliieumi.customized.policy.domain.repository;

import com.uliieumi.customized.policy.domain.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaEnterpriseRepository extends JpaRepository<Enterprise, Long> {

}
