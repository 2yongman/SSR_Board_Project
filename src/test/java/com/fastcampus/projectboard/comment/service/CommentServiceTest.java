package com.fastcampus.projectboard.comment.service;

import com.fastcampus.projectboard.article.entity.Article;
import com.fastcampus.projectboard.article.repository.ArticleRepository;
import com.fastcampus.projectboard.article.service.ArticleService;
import com.fastcampus.projectboard.comment.dto.CommentDto;
import com.fastcampus.projectboard.comment.entity.Comment;
import com.fastcampus.projectboard.comment.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트 반환")
    @Test
    void returnComments(){
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(
                Optional.of(Article.of("title", "content", "hashtag")));

        List<CommentDto> commentDtoList = commentService.searchComment(articleId);
        assertThat(commentDtoList).isNotNull();
        then(articleRepository).should().findById(articleId);
    }


    @DisplayName("댓글 정보를 입력하면, 댓글을 저장")
    @Test
    void saveComments(){
        CommentDto commentDto = CommentDto.of(LocalDateTime.now(),"createdBy"
        ,LocalDateTime.now(),"modifiedBy","content");
        given(commentRepository.save(Mockito.any())).willReturn(Comment.class);

        commentService.saveComment(commentDto);

        then(commentRepository).should().save(any(Comment.class));
    }

    @DisplayName("댓글 수정")
    @Test
    void updateComment(){
        given(commentRepository.save(any(Comment.class))).willReturn(null);

    }


}