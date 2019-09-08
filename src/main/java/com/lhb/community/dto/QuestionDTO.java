package com.lhb.community.dto;

import com.lhb.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;

}
