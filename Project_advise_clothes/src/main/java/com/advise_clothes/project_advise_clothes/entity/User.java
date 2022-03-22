package com.advise_clothes.project_advise_clothes.entity;

import com.advise_clothes.project_advise_clothes.entity.config.AuditingEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@ToString
public class User extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String account;
    public String password;
    public String nickname;
    public String email;
    public String phoneNumber;
    public Integer gender;
    public String area;
    public Integer height;
    public Integer weight;

    @CreatedDate
    public LocalDateTime createdAt;
    @CreatedBy
    public String createdBy;
    @LastModifiedDate
    public LocalDateTime updatedAt;
    @LastModifiedBy
    public String updatedBy;
    public Integer deletedReason;
}
