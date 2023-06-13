package com.training.StartAnnotation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "COMMUNITY_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {

    @Id
    @Column(name = "COMMUNITY_ID", columnDefinition = "serial")
    private int id;

    @Column(name = "COMMUNITY_NAME")
    private String name;

    @Column(name = "COMMUNITY_IMAGE")
    private String image;

    @Column(name = "COMMUNITY_ICON")
    private String icon;

    @Column(name = "POST_ID")
    private int postId;

    @Column(name = "CREATED_AT")
    private Time createdAt;

    @Column(name = "UPDATED_AT")
    private Time updatedAt;

    @Column(name = "DELETED_AT")
    private Time deletedAt;
}
