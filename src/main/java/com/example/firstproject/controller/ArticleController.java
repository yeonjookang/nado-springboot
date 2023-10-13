package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String showArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleDto articleDto){
        // 1. DTO를 엔티티로 변환
        Article article =articleDto.toEntity();
        log.info(article.toString());
        // 2. 레포지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/articles/{articleId}")
    public String showArticle(@PathVariable Long articleId, Model model){
        //1. id를 조회해 데이터 가져오기
        Article findArticle = articleRepository.findById(articleId).orElse(null);
        //2. 모델에 데이터 등록하기
        model.addAttribute("article",findArticle);
        //3. 뷰 페이지 설정하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String showArticles(Model model){
        //1. DB에서 모든 Article 데이터 가져오기
        List<Article> articles = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articles",articles);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

}
