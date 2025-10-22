package io.eddie.dddexp.tx.domain;

import io.eddie.dddexp.tx.dto.TxPostStatus;
import io.eddie.dddexp.tx.repository.TxCommentRepository;
import io.eddie.dddexp.tx.repository.TxPostRepository;
import io.eddie.dddexp.tx.service.TxBlogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TxApplicationTests {

    TxBlogService txBlogService;

    @BeforeEach
    void init() {
        txBlogService = new TxBlogService(new TxPostRepository());
    }

    @Test
    void create_post() {

        String targetTitle = "제목";
        String targetContent = "내용";
        String targetAuthor = "작성자";

        TxPost createdPost = txBlogService.create(targetTitle, targetContent, targetAuthor);

        // soutv
        System.out.println("createdPost = " + createdPost);

        Assertions.assertNotNull(createdPost.getId());

        Assertions.assertEquals(TxPostStatus.DRAFT, createdPost.getStatus());

        Assertions.assertEquals(targetTitle, createdPost.getTitle());


    }

    @Test
    void comment_test() {

        String targetContent = "댓글 제목";
        String targetAuthor = "작성자1";
        long targetPostId = 1L;

        TxCommentRepository commentRepository = new TxCommentRepository();

//        TxComment comment = TxComment.builder()
//                .postId(targetPostId)
//                .author(targetAuthor)
//                .content(targetContent)
//                .build();

        TxComment comment = new TxComment(targetPostId, targetAuthor, targetContent);

        TxComment createdComment = commentRepository.save(comment);

        Assertions.assertNotNull(createdComment.getId());

    }


}
