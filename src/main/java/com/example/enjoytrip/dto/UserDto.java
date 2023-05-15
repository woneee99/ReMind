package com.example.enjoytrip.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userId;
    private String userEmail;
    private String userPassword;
    private String userName;

    private String emailVerifiedYn;
    private String profileImageUrl;

    private String createdAt;
    private String updatedAt;
}
