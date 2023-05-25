package com.example.enjoytrip.api.dto;

import com.example.enjoytrip.api.entity.Blog;
import com.example.enjoytrip.api.entity.BlogFile;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder
@AllArgsConstructor
public class BlogListDto {
    private String thumbNail;
    private int blogId;
    private String content;
    private String hashTag;

    public static Page<BlogListDto> toDtoList(Page<Blog> postList) {
        Page<BlogListDto> postDtoList = postList.map( p -> BlogListDto.builder()
                .thumbNail(p.getThumbNail())
                .blogId(p.getBlogId())
                .content(p.getContent())
                .hashTag(p.getHashTag())
                .build());
        return postDtoList;
    }

    public static List<BlogListDto> getMyList(List<Blog> postList) {
        List<BlogListDto> listDto = postList.stream().map(p -> BlogListDto.builder()
                .thumbNail(p.getThumbNail())
                .blogId(p.getBlogId())
                .content(p.getContent())
                .hashTag(p.getHashTag())
                .build()).collect(Collectors.toList());

        return listDto;
    }

}
