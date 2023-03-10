package com.teksocial.application.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilePojo {
    private String imageUrl;
    private String profileName;
    private Long profileId;
}
