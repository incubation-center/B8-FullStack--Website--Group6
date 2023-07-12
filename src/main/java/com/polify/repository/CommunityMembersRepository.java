package com.polify.repository;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommunityMembersRepository extends JpaRepository<CommunityMembers, Long> {

    List<CommunityMembers> findByUsersId(Long user_id);

    List<CommunityMembers> findByCommunityId(UUID community_id);

    CommunityMembers findByCommunityAndUsers(Community community, User user);
}
