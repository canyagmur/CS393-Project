package com.example.canproject.repository;

import com.example.canproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query(nativeQuery = true,value = "select * from member m where m.id=?1")
    Member findByMemberId(Long id);
}
