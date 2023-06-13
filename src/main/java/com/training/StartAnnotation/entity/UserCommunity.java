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
@Table(name = "USER_COMMUNITY_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommunity {

    @Id
    @Column(name = "USER_COMMUNITY_ID", columnDefinition = "serial")
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "COMMUNITY_ID")
    private int comId;

    @Column(name = "CREATED_AT")
    private Time createdAt;

    @Column(name = "UPDATED_AT")
    private Time updatedAt;

    @Column(name = "DELETED_AT")
    private Time deletedAt;

    @Column(name = "JOINED_AT")
    private Time joinedAt;

    @Column(name = "LEAVED_AT")
    private Time leavedAt;

}
