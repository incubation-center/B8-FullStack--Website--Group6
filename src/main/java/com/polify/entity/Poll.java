package com.polify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "poll")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "poll_question", nullable = false)
    private String pollQuestion;

    @Column(name = "limit_vote", nullable = false)
    private int limitVote;

    @Column(name = "duration", nullable = false)
    private String duration;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @Column(name = "poll_date")
    private Date pollDate;

    @Column(name = "total_vote")
    private Long totalVote;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<PollOption> pollOptions;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<HasVoted> hasVoteds;

    @PrePersist
    public void onCreate(){
        pollDate = new Date();
    }

    public Poll(String pollQuestion, int limitVote, String duration, Community community) {
        this.pollQuestion = pollQuestion;
        this.limitVote = limitVote;
        this.duration = duration;
        this.community = community;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public int getLimitVote() {
        return limitVote;
    }

    public void setLimitVote(int limitVote) {
        this.limitVote = limitVote;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Date getPollDate() {
        return pollDate;
    }

    public void setPollDate(Date pollDate) {
        this.pollDate = pollDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(Long totalVote) {
        this.totalVote = totalVote;
    }
}
