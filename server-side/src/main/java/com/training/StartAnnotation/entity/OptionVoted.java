package com.training.StartAnnotation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Table(name = "OPTION_VOTED_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionVoted {

    @Id
    @Column(name = "OPTION_VOTED_ID", columnDefinition = "serial")
    private int id;

    @Column(name = "POLL_OPTION_ID")
    private int pollOptionId;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "VOTED_AT")
    private Time votedAt;

}
