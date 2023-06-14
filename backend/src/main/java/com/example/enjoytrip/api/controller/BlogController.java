package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.*;
import com.example.enjoytrip.api.service.BlogService;
import com.example.enjoytrip.api.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    private String uploadFolder = "C:\\upload";

    @GetMapping()
    public ResponseEntity<List<BlogListDto>> blogList(@RequestParam(required = false) String hashTag, int offset) throws IOException {
        List<BlogListDto> blogListDto = null;
        if(hashTag != null && hashTag.length() > 0) { // 해시태그가 있는 경우
            System.out.println(" here1 ");

        }
        else { // 없는 경우
            System.out.println(" here2 ");
            blogListDto = blogService.blogList(offset);
            int size = blogListDto.size();
            System.out.println("size = " + size);
            for(int i=0; i<size; i++) {
                BlogListDto listDto = blogListDto.get(i);
                InputStream inputStream = new FileInputStream(uploadFolder + "/" + listDto.getFileName());
                byte[] images = IOUtils.toByteArray(inputStream);
                byte[] byteEnc64 = Base64.encodeBase64(images);
                String imgStr = new String(byteEnc64 , "UTF-8");

                blogListDto.get(i).setImages(imgStr);
                inputStream.close();
            }

        }
        return ResponseEntity.ok().body(blogListDto);
    }

//    @GetMapping("/myList")
//    public ResponseEntity<List<BlogListDto>> myBlogList() {
//        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//        String username = principal.getName();
//        UserDto user = userService.getUser(username);
//        List<Blog> userBlogList = blogService.findByUserSeq(user.getUserSeq());
//
//        List<BlogListDto> myList = BlogListDto.getMyList(userBlogList);
//
//        return ResponseEntity.ok().body(myList);
//    }
//
    @GetMapping("/{blogId}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable(name = "blogId") int blogId) throws IOException {
        BlogDto blogDto = blogService.blogDetail(blogId);
        System.out.println("blogDto.getFileDto() = " + blogDto.getFileDto());
        return ResponseEntity.ok().body(blogDto);
    }

    @PostMapping
    public ResponseEntity<Integer> blogInsert(BlogDto blogDto) {

        System.out.println("blog = " + blogDto.getContent());
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        UserDto user = userService.getUser(username);

        blogDto.setUserSeq(user.getUserSeq());
        int ret = blogService.blogInsert(blogDto);

        for( MultipartFile file : blogDto.getFileList()) {
            String uploadFileName = file.getOriginalFilename();
            File saveFile = new File(uploadFolder, uploadFileName);

            try {
                file.transferTo(saveFile);
                BlogFileDto fileDto = new BlogFileDto();
                fileDto.setFileName(uploadFileName);
                fileDto.setBlogId(blogDto.getBlogId());
                blogService.fileInsert(fileDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok().body(ret);
    }
}
