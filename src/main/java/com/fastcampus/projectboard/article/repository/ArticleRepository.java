package com.fastcampus.projectboard.article.repository;

import com.fastcampus.projectboard.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {

}
