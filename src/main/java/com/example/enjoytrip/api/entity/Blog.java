package com.example.enjoytrip.api.entity;

import com.example.enjoytrip.api.dto.BlogPostDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private int blogId;
    private int userSeq;
    private int planId;
    private int tripPlanSpotId;
    private String content;
    private String thumbNail;
    private String hashTag;
    private int likeCount;
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "blogId")
    @JsonIgnore
    private List<BlogFile> fileList = new ArrayList<>();

    public Blog(BlogPostDto blogPostDto) {
        this.planId = blogPostDto.getPlanId();
        this.tripPlanSpotId = blogPostDto.getTripPlanSpotId();
        this.content = blogPostDto.getContent();
        this.hashTag = blogPostDto.getHashTag();
    }
}
