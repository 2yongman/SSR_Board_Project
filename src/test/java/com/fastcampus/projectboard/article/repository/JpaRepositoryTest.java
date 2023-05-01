package com.fastcampus.projectboard.article.repository;

import com.fastcampus.projectboard.article.entity.Article;
import com.fastcampus.projectboard.comment.repository.CommentRepository;
import com.fastcampus.projectboard.config.JpaConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public JpaRepositoryTest(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_trueWorkFine() {
        //Given

        //When
        List<Article> articles = articleRepository.findAll();

        //Then
        assertThat(articles)
                .isNotNull()
                .hasSize(0);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_trueWorkFine() {
        //Given
        Article article = Article.of("new article", "new content", "#spring");

        //When
        Article savedArticle = articleRepository.save(article);

        //Then
        assertThat(savedArticle.getArticleId()).isNotNull();
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenUpdating_trueWorkFine() {
        //Given
        long previousCount = articleRepository.count();

        Article article = Article.of("new article", "new content", "#spring");
        articleRepository.save(article);

        //When
        articleRepository.findById(article.getArticleId())
                .orElseThrow(IllegalArgumentException::new);

        //Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("update 테스트")
    @Test
    void updateTest() {
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashTag = "#springboot";
        article.setHashtag(updateHashTag);

        //when
        Article savedArticle = articleRepository.saveAndFlush(article);

        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashTag);

    }

    @DisplayName("delete 테스트")
    @Test
    void deleteTest(){

    }
}
