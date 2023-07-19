package com.polify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "poll_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "option_text", nullable = false)
    private String optionText;

    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @Column(name = "percentage")
    private float percentage;

    @Column(name = "option_voted")
    private Long optionVoted;

    @OneToMany(mappedBy = "pollOption", cascade = CascadeType.ALL)
    private List<HasVoted> hasVoteds;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @PrePersist
    public void onCreate(){
        optionVoted = 0L;
    }

    public PollOption(String optionText, Poll poll) {
        this.optionText = optionText;
        this.poll = poll;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Long getOptionVoted() {
        return optionVoted;
    }

    public void setOptionVoted(Long optionVoted) {
        this.optionVoted = optionVoted;
    }

}
