package com.fastcampus.projectboard.article.service;

import com.fastcampus.projectboard.article.dto.ArticleDto;
import com.fastcampus.projectboard.article.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.article.repository.ArticleRepository;
import com.fastcampus.projectboard.type.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(@Autowired ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional(readOnly = true)
    public Page<ArticleDto> SearchArticles(SearchType title, String search_keyword) {
        //Todo repository 테스트 필요
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long articleId){
        return null;
    }

    public void saveArticle(ArticleDto articleDto) {

    }

    public void updateArticle(long articleId, ArticleUpdateDto of) {
    }

    public void deleteArticle(long articleId, ArticleUpdateDto of) {
    }
}
