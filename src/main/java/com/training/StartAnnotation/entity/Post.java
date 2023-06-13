package com.training.StartAnnotation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "POST_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @Column(name = "POST_ID", columnDefinition = "serial")
    private int id;

    @Column(name = "POST_TITLE")
    private String title;

    @Column(name = "POST_DESCRIPTION")
    private String description;

    @Column(name = "COMMUNITY_ID")
    private int comId;

    @Column(name = "CREATED_BY")
    private int createdBy;

    @Column(name = "VOTE_LIMIT")
    private int voteLimit;

    @Column(name = "DURATION")
    private Time duration;

    @Column(name = "CREATED_AT")
    private Time createdAt;

    @Column(name = "UPDATED_AT")
    private Time updatedAt;

    @Column(name = "DELETED_AT")
    private Time deletedAt;

    @Column(name = "END_AT")
    private Time endAt;
}
