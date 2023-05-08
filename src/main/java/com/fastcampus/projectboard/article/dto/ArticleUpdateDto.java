package com.fastcampus.projectboard.article.dto;

public class ArticleUpdateDto {
    String title;
    String content;
    String hashtag;

    private ArticleUpdateDto(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static ArticleUpdateDto of(String title, String content, String hashtag){
        return new ArticleUpdateDto(title, content, hashtag);
    }
}
