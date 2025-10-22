package io.eddie.dddexp.pa.domain.model;

import java.time.LocalDateTime;

public class PaPost {

    private Long id;

    private String title;
    private String content;

    private String author;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int viewCount;

    public PaPost(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getViewCount() {
        return viewCount;
    }
}
