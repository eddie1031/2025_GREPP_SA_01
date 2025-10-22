package io.eddie.dddexp.tx.service;

import io.eddie.dddexp.tx.domain.TxPost;
import io.eddie.dddexp.tx.dto.TxPostStatus;
import io.eddie.dddexp.tx.repository.TxCommentRepository;
import io.eddie.dddexp.tx.repository.TxPostRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class TxBlogService {

    private final TxPostRepository repository;
    private final TxCommentRepository commentRepository;

    public TxPost create(String title, String content, String author) {

        if ( title == null || title.trim().isEmpty() ) {
            throw new IllegalArgumentException("제목은 반드시 입력해 주세요!");
        }

        if ( content == null || content.trim().isEmpty() ) {
            throw new IllegalArgumentException("내용은 반드시 입력해 주세요!");
        }

        if ( author == null || author.trim().isEmpty() ) {
            throw new IllegalArgumentException("작성자는 반드시 입력해 주세요!");
        }

        TxPost post = TxPost.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        return repository.save(post);

    }

    public TxPost getPost(Long id) {

        TxPost findPost = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        findPost.setViewCount(findPost.getViewCount() + 1);

        repository.save(findPost);

        return findPost;

    }

    public void publish(Long id) {

        TxPost findPost = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        if ( findPost.getStatus() != TxPostStatus.DRAFT ) {
            throw new IllegalStateException("초안 상태의 게시물만 발행할 수 있습니다.");
        }

        findPost.setStatus(TxPostStatus.PUBLISHED);
        findPost.setUpdatedAt(LocalDateTime.now());

        repository.save(findPost);

    }

}
