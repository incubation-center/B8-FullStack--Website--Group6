package com.polify.repository;

import com.polify.entity.HasVoted;
import com.polify.entity.Poll;
import com.polify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HasVotedRepository extends JpaRepository<HasVoted, Long> {

    HasVoted findByPollAndUser(Poll poll, User user);

}
