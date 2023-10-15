package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleApiDto;
import com.example.firstproject.dto.ArticleCreateDto;
import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article getOneById(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }


    public Article create(ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        if(article.getId()!=null)
            //ID를 함께 보내면 수정이 되므로 안됨
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long articleId, ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        Article findArticle = articleRepository.findById(articleId).orElse(null);
        if(findArticle==null || articleId!= findArticle.getId()){
            //400 잘못된 요청 응답
            return null;
        }
        findArticle.patch(article);
        Article updated = articleRepository.save(findArticle);
        return updated;
    }

    public Article delete(Long articleId) {
        Article findArticle = articleRepository.findById(articleId).orElse(null);
        if(findArticle==null){
            return null;
        }
        articleRepository.delete(findArticle);
        //삭제된 Article 객체는 findArticle에 여전히 메모리에 저장되어 있으므로 이를 반환
        return findArticle;
    }
}
