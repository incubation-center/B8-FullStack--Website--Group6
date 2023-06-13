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
@Table(name = "POLL_OPTION_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollOption {

    @Id
    @Column(name = "POLL_OPTION_ID", columnDefinition = "serial")
    private int id;

    @Column(name = "POLL_OPTION_TITLE")
    private String title;

    @Column(name = "POLL_TYPE")
    private String pollType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "POST_ID")
    private int postId;

    @Column(name = "PERCENTAGE")
    private float percentage;

    @Column(name = "CREATED_AT")
    private Time createdAt;

    @Column(name = "UPDATED_AT")
    private Time updatedAt;

    @Column(name = "DELETED_AT")
    private Time deletedAt;
}
