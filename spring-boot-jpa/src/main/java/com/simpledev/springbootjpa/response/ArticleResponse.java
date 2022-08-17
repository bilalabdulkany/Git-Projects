package com.simpledev.springbootjpa.response;

import com.simpledev.springbootjpa.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ArticleResponse {
    private String email;
    private List<Comment> comment;
    private String title;
    private String content;
    private LocalDate date;
    private Boolean published;
}
