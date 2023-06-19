package com.polify.repository;

import com.polify.entity.CommunityMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityMembersRepository extends JpaRepository<CommunityMembers, Long> {

    List<CommunityMembers> findByCommunity(Long community);
}
