package com.polify.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "otp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTP {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code", nullable = false)
    private int code;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @Getter
    @Setter
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @Getter
    @Setter
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Getter
    @Setter
    @Column(name = "updated_by", nullable = true)
    @LastModifiedBy
    private String updatedBy;


    public void setUser(User user) {
        this.users = user;
    }

}
