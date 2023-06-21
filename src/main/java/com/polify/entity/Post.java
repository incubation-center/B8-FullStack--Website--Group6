package com.polify.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post_content", nullable = false)
    private String postContent;

    @Column(name = "post_date", nullable = false)
    private LocalDate postDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @PrePersist
    public void onCreate(){
        postDate = LocalDate.now();
    }

    public Post(String postContent, User users, Community community) {
        this.postContent = postContent;
        this.users = users;
        this.community = community;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
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

}
