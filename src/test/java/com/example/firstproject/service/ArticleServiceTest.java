package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void 게시글_전체조회(){
        //given
        Article article1 = new Article(1L, "title 1", "content 1");
        Article article2 = new Article(2L, "title 2", "content 2");
        Article article3 = new Article(3L, "title 3", "content 3");
        ArrayList<Article> expected = new ArrayList<>(Arrays.asList(article1, article2, article3));
        //when
        List<Article> articles = articleService.getAll();
        //then
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void 게시글_단건조회_성공(){
        //given
        Long id = 2L;
        Article expected = new Article(2L, "title 2", "content 2");
        //when
        Article article = articleService.getOneById(id);
        //then
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void 게시글_단건조회_실패_존재하지_않는_id_입력(){
        //given
        Long id = -1L;
        Article expected = null;
        //when
        Article article = articleService.getOneById(id);
        //then
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void 게시글_생성_성공(){
        //given
        String title = "title 4";
        String content = "content 4";
        ArticleDto articleDto = new ArticleDto(null, title, content);
        Article expected = new Article(4L, title, content);
        //when
        Article article = articleService.create(articleDto);
        //then
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void 게시글_생성_실패(){ //dto에 id가 포함된 오류
        //given
        String title = "title 4";
        String content = "content 4";
        ArticleDto articleDto = new ArticleDto(4L, title, content);
        Article expected = null;
        //when
        Article article = articleService.create(articleDto);
        //then
        assertEquals(expected,article);
    }
}