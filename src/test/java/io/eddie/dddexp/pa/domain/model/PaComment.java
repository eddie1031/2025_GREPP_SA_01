package io.eddie.dddexp.pa.domain.model;

import java.time.LocalDateTime;

public class PaComment {

    private Long id;

    private String content;
    private String author;

    private Long postId;

    private LocalDateTime createdAt;

    public PaComment(String content, String author, Long postId) {

        validate(author, content);

        this.content = content;
        this.author = author;
        this.postId = postId;
    }

    private void validate(String author, String content) {


        if ( author == null || author.trim().isEmpty() ) {
            throw new IllegalArgumentException("작성자는 필수로 입력되어야 합니다.");
        }

        if ( content == null || content.trim().isEmpty() ) {
            throw new IllegalArgumentException("내용은 필수로 입력되어야 합니다.");
        }

    }

    /*
    Getter and Setter
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Long getPostId() {
        return postId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
