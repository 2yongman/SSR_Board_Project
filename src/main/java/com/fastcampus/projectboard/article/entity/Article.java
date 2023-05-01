package com.fastcampus.projectboard.article.entity;

import com.fastcampus.projectboard.comment.entity.Comment;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false) @Setter
    private String title;

    @Column(nullable = false, length = 10000) @Setter
    private String content;

    @Column @Setter
    private String hashtag;

    @ToString.Exclude
    @OrderBy("commentId")
    @OneToMany(mappedBy = "article", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(nullable = false,length = 100)
    private String modifiedBy;

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    //new 키워드를 쓰지 않도록
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    //중복 요소를 제거, 정렬하기 위한 로직
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return articleId != null && articleId.equals(article.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId);
    }
}
