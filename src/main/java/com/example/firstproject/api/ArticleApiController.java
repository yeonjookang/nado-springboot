package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleApiDto;
import com.example.firstproject.dto.ArticleCreateDto;
import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.dto.ArticleUpdateDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleApiController {
    private final ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> getArticles(){
        return articleService.getAll();
    }

    @GetMapping("/api/articles/{articleId}")
    public Article getArticle(@PathVariable Long articleId){
        return articleService.getOneById(articleId);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDto articleDto){
        Article created = articleService.create(articleDto);
        //컨트롤러의 역할은 요청과 응답
        return (created!=null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //PATCH
    @PatchMapping("/api/articles/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long articleId,
                                        @RequestBody ArticleDto articleDto){
        Article updated = articleService.update(articleId, articleDto);
        return (updated!=null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //DELETE
    @DeleteMapping("/api/articles/{articleId}")
    public ResponseEntity<Article> delete(@PathVariable Long articleId){
        Article deleted =  articleService.delete(articleId);
        return (deleted!=null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
