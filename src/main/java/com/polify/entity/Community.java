package com.polify.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "community")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "community_name", nullable = false)
    private String communityName;

    @Getter
    @Setter
    @Column(name = "image")
    private String image;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @PrePersist
    public void onCreate(){
        dateCreated = LocalDate.now();
    }
    public Community(String communityName) {
        this.communityName = communityName;
    }

    public UUID id() {
        return id;
    }

    public void community_id(UUID id) {
        this.id = id;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

}