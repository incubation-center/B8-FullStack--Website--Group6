package com.polify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "vote")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private PollOption option;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @Column(name = "date_voted", nullable = false)
    private LocalDate dateVoted;

    @PrePersist
    public void onCreate(){
        dateVoted = LocalDate.now();
    }

    public Vote(PollOption option, User users) {
        this.option = option;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PollOption getOption() {
        return option;
    }

    public void setOption(PollOption option) {
        this.option = option;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public LocalDate getDateVoted() {
        return dateVoted;
    }

    public void setDateVoted(LocalDate dateVoted) {
        this.dateVoted = dateVoted;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", option=" + option +
                ", users=" + users +
                ", dateVoted=" + dateVoted +
                '}';
    }
}
