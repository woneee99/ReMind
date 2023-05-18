package com.example.enjoytrip.dto;

import com.example.enjoytrip.oauth.entity.ProviderType;
import com.example.enjoytrip.oauth.entity.RoleType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userSeq;
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userName;

    private String emailVerifiedYn;
    private String profileImageUrl;

    private ProviderType providerType;
    private RoleType roleType;

    private String createdAt;
    private String updatedAt;

    public UserDto(String userId, String email, String name,  String emailVerifiedYn, String imageUrl, ProviderType providerType, RoleType roleType, String createdAt, String updatedAt) {
        this.userId = userId;
        this.userEmail = email;
        this.userName = name;
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = imageUrl;
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
