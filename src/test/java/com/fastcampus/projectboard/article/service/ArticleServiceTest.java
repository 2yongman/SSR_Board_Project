package com.fastcampus.projectboard.article.service;

import com.fastcampus.projectboard.article.dto.ArticleDto;
import com.fastcampus.projectboard.article.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.article.entity.Article;
import com.fastcampus.projectboard.article.repository.ArticleRepository;
import com.fastcampus.projectboard.type.SearchType;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("검색 기능")
    @Test
    void searchTest() {
        //given

        //when
        Page<ArticleDto> articles = sut.SearchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그

        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("검색을 조회하면, 게시글 반환")
    @Test
    void returnArticle() {
        //given

        //when
        ArticleDto articleDto = sut.searchArticle(1L);
        //then
        assertThat(articleDto).isNotNull();

    }

    @DisplayName("게시글 정보를 입력하면 게시글 생성")
    @Test
    void saveArticle() {
        //given
        ArticleDto articleDto = ArticleDto.of("title", "content", "hashtag",
                LocalDateTime.now(), "createdBy");
        given(articleRepository.save(Mockito.any())).willReturn(Article.class);

        //when
        sut.saveArticle(articleDto);

        //then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 정보르 입력하면, 수정")
    @Test
    void updateArticle() {
        given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "hashtag"));

        then(articleRepository).should().save(any(Article.class));

    }
    @DisplayName("게시글 정보를 입력하면, 삭제")
    @Test
    void deleteArticle() {
        willDoNothing().given(articleRepository).delete(any(Article.class));

        sut.deleteArticle(1L, ArticleUpdateDto.of("title", "content", "hashtag"));

        then(articleRepository).should().delete(any(Article.class));
    }



}