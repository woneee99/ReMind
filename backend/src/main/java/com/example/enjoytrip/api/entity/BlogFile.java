package com.example.enjoytrip.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogFileId;
    private String blogUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Blog blogId;
}
