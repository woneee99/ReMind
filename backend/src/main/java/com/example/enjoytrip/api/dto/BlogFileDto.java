package com.example.enjoytrip.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogFileDto {
    private int blogFileId;
    private String fileName;
    private int blogId;
    private byte[] images;
}
