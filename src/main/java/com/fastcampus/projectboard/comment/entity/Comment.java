package com.fastcampus.projectboard.comment.entity;

import com.fastcampus.projectboard.article.entity.Article;
import com.fastcampus.projectboard.config.AuditingFields;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Setter
    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    private Comment(Article article, String content){
        this.article = article;
        this.content = content;
    }

    //정적 팩토리 메서드 패턴
   public static Comment of(Article article, String content){
        return new Comment(article,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return commentId != null && commentId.equals(comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
