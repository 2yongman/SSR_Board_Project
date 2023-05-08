package com.fastcampus.projectboard.article.dto;

import java.time.LocalDateTime;

public class ArticleDto {
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String createBy;

    private ArticleDto(String title, String content, String hashtag, LocalDateTime createdAt, String createBy) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.createBy = createBy;
    }

    public static ArticleDto of(String title,
                                String content,
                                String hashtag,
                                LocalDateTime createdAt,
                                String createBy){
        return new ArticleDto(title,content, hashtag, createdAt, createBy);
    }

}
