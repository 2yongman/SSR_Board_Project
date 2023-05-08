package com.fastcampus.projectboard.comment.service;

import com.fastcampus.projectboard.article.repository.ArticleRepository;
import com.fastcampus.projectboard.comment.dto.CommentDto;
import com.fastcampus.projectboard.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public List<CommentDto> searchComment(Long articleId) {
        return List.of();
    }

    public void saveComment(CommentDto commentDto) {
    }
}
