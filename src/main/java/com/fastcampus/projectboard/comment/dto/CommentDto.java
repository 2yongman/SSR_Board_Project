package com.fastcampus.projectboard.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {

    private LocalDateTime createdAt;
    private String createBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String content;

    private CommentDto(LocalDateTime createdAt, String createBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        this.createdAt = createdAt;
        this.createBy = createBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.content = content;
    }

    public static CommentDto of(LocalDateTime createdAt, String createBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new CommentDto(createdAt, createBy, modifiedAt, modifiedBy, content);
    }
}
