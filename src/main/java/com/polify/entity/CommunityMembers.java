package com.polify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "community_members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @Column(name = "date_joined", nullable = false)
    private LocalDate dateJoined;

    @PrePersist
    public void onCreate(){
        dateJoined = LocalDate.now();
    }

    public CommunityMembers(User users, Community community) {
        this.users = users;
        this.community = community;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public String toString() {
        return "CommunityMembers{" +
                "id=" + id +
                ", users=" + users +
                ", community=" + community +
                ", dateJoined=" + dateJoined +
                '}';
    }
}
