package com.example.enjoytrip.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    private int id;
    private String tagName;
    private double lat;
    private double ma;
    private int blogId;
}
