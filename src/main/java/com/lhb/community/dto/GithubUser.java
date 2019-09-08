package com.lhb.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Integer id;
    private String bio;
    private String avatar_url;
}
