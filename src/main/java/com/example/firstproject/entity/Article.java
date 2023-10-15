package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article){
        if (article.title!=null)
            this.title=article.title;
        if (article.content!=null)
            this.content=article.content;
    }

}
