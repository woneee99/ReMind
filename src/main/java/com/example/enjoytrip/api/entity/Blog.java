package com.example.enjoytrip.api.entity;

import com.example.enjoytrip.api.dto.BoardFileDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    private int userSeq;
    private String title;
    private String content;
    private int likeCount;

    @OneToMany
    private List<BlogFile> blogFile;

    private LocalDateTime createdAt;
}
