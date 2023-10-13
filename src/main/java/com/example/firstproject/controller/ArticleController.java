package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleCreateDto;
import com.example.firstproject.dto.ArticleUpdateDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String createArticle(ArticleCreateDto articleCreateDto){
        // 1. DTO를 엔티티로 변환
        Article article = articleCreateDto.toEntity();
        log.info(article.toString());
        // 2. 레포지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
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

    @GetMapping("/articles/{articleId}/edit")
    public String showEditForm(@PathVariable Long articleId, Model model){
        Article findArticle = articleRepository.findById(articleId).orElse(null);
        model.addAttribute("article",findArticle);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String updateArticle(ArticleUpdateDto articleUpdateDto){
        Article article = articleUpdateDto.toEntity();
        Article findArticle = articleRepository.findById(article.getId()).orElse(null);

        if(findArticle!=null){
          //findArticle.setTitle(article.getTitle());
          //findArticle.setContent(article.getContent());
            articleRepository.save(article);
        }

        return "redirect:/articles/"+article.getId();
    }

    @GetMapping("/articles/{articleId}/delete")
    public String deleteArticle(@PathVariable Long articleId, RedirectAttributes rttr){
        //1. 삭제할 대상 가져오기
        Article findArticle = articleRepository.findById(articleId).orElse(null);
        //2. 대상 엔티티 삭제하기
        if(findArticle!=null){
            articleRepository.delete(findArticle);
            rttr.addFlashAttribute("msg","삭제되었습니다");
        }
        //3. 결과 페이지로 리다이렉트 하기
        return "redirect:/articles";
    }

}
