package com.fastcampus.projectboard.comment.entity;

import java.time.LocalDateTime;

public class Comment {
    private Long commentId;
    private Long articleId;
    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

}
