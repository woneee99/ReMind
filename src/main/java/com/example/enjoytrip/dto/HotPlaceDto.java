package com.example.enjoytrip.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceDto {
    private int hotplaceId;
    private int userId;
    private String hotplaceName;
    private String hotplaceMap;
    private int type;
    private String hotplaceImg;
}
