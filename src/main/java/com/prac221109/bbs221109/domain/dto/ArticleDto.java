package com.prac221109.bbs221109.domain.dto;

import com.prac221109.bbs221109.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ArticleDto {
    private String id;
    private String title;
    private String content;


    public Article toEntity(){
        return new Article(this.title, this.content);
    }
}
