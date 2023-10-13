package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleCreateDto {
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(null,title, content);
    }
}
