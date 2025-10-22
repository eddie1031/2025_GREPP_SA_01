package io.eddie.dddexp.pa.domain.model;

import java.time.LocalDateTime;

public class PaPost {

    private Long id;

    private String title;
    private String content;

    private String author;

    private PaPostStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int viewCount;

    private void validate(String title, String content, String author) {

        if ( title == null || title.trim().isEmpty() ) {
            throw new IllegalArgumentException("제목은 필수로 입력되어야 합니다.");
        }

        if ( content == null || content.trim().isEmpty() ) {
            throw new IllegalArgumentException("내용은 필수로 입력되어야 합니다.");
        }

        if ( author == null || author.trim().isEmpty() ) {
            throw new IllegalArgumentException("작성자는 필수로 입력되어야 합니다.");
        }

    }


    public PaPost(String title, String content, String author) {

        validate(title, content, author);

        this.title = title;
        this.content = content;
        this.author = author;

        this.status = PaPostStatus.DRAFT;

        this.viewCount = 0;

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }

    public void publish() {

        if ( this.status != PaPostStatus.DRAFT) {
            throw new IllegalStateException("초안 상태의 포스트만 발행할 수 있습니다.");
        }

        this.status = PaPostStatus.PUBLISHED;
        this.updatedAt = LocalDateTime.now();

    }

    public void increaseViewCount() {

        if ( this.status != PaPostStatus.PUBLISHED ) {
            throw new IllegalStateException("발행 상태의 포스트만 조회할 수 있습니다.");
        }

        this.viewCount++;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PaPostStatus getStatus() {
        return status;
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
