package com.fastcampus.projectboard.article.repository;

import com.fastcampus.projectboard.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//프로젝트의 repository를 rest API로 노출을 시킬것인지의 전략
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article,Long> {

}
