package com.polify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "poll_description")
    private String pollDescription;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    public Poll(String pollQuestion, String pollDescription, Community community) {
        this.pollQuestion = pollQuestion;
        this.pollDescription = pollDescription;
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

    public String getPollDescription() {
        return pollDescription;
    }

    public void setPollDescription(String pollDescription) {
        this.pollDescription = pollDescription;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", pollQuestion='" + pollQuestion + '\'' +
                ", pollDescription='" + pollDescription + '\'' +
                ", community=" + community +
                '}';
    }
}
