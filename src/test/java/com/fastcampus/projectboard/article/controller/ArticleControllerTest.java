package com.fastcampus.projectboard.article.controller;


import com.fastcampus.projectboard.config.SecurityConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    private final MockMvc mockMvc;

    public ArticleControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    //실패하는 테스트가 있을 경우 빌드가 안되기 때문에 @Disable 어노테이션을 통해 빌드가 되게끔 한다.
    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        //given

        //when then
        mockMvc.perform(
                get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));
    }

    @Disabled("구현중")
    @DisplayName("[view][GET] 해당 게시글 상세 페이지 조회 - 정상 호출")
    @Test
    public void returnArticleView() throws Exception {
        mockMvc.perform(
                get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("comments"));

    }

    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void searchView() throws Exception {
        mockMvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("articles/search"))
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }

    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 - 정상 호출")
    @Test
    public void hashTagSearchView() throws Exception {
        mockMvc.perform(
                get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("article/search-hashtag"))
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }


}