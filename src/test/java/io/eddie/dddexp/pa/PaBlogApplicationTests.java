package io.eddie.dddexp.pa;


import io.eddie.dddexp.pa.application.port.out.PaCommentPersistencePort;
import io.eddie.dddexp.pa.application.port.out.PaPostPersistencePort;
import io.eddie.dddexp.pa.application.service.PaBlogApplication;
import io.eddie.dddexp.pa.domain.model.PaComment;
import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.model.PaPostStatus;
import io.eddie.dddexp.pa.domain.service.PaPostDomainService;
import io.eddie.dddexp.pa.domain.vo.PaCommentDescription;
import io.eddie.dddexp.pa.domain.vo.PaPostDescription;
import io.eddie.dddexp.pa.infrastructure.PaCommentRepository;
import io.eddie.dddexp.pa.infrastructure.PaPostRepository;
import org.junit.jupiter.api.*;

import java.util.List;

class PaBlogApplicationTests {

    PaBlogApplication application;

    @BeforeEach
    void init() {

        PaPostDomainService domainService = new PaPostDomainService();

        PaPostPersistencePort postRepository = new PaPostRepository();
        PaCommentPersistencePort paCommentRepository = new PaCommentRepository();

        application = new PaBlogApplication(postRepository, domainService, paCommentRepository);

    }

    @Test
    void write_post() {

        // given
        String givenTitle = "제목1";
        String givenContent = "내용1";
        String givenAuthor = "작성자1";

        // when
        PaPost whenSaved = application.writePost(givenTitle, givenContent, givenAuthor);

        // then
        Assertions.assertNotNull(whenSaved.getId());

        Assertions.assertEquals(PaPostStatus.DRAFT, whenSaved.getStatus());
        Assertions.assertEquals(givenTitle, whenSaved.getTitle());

    }

    @Nested
    public class Context_with_post {

        PaPost post;

        String givenTitle = "제목1";
        String givenContent = "내용1";
        String givenAuthor = "작성자1";

        @BeforeEach
        void initPost() {
            post = application.writePost(givenTitle, givenContent, givenAuthor);
        }

        @Test
        void publish_post() {

            application.publishPost(post.getId());

            PaPost findPost = application.getPost(post.getId());

            Assertions.assertNotNull(findPost);

            Assertions.assertEquals(PaPostStatus.PUBLISHED, findPost.getStatus());

        }

        @Test
        void get_published_posts() {

            application.publishPost(post.getId());

            List<PaPost> publishedPosts = application.getPublishedPosts();
            PaPost first = publishedPosts.getFirst();

            Assertions.assertEquals(1, publishedPosts.size());

            Assertions.assertEquals(PaPostStatus.PUBLISHED, first.getStatus());

        }

        @Test
        void add_comment() {

            application.publishPost(post.getId());

            String given_comment_author = "댓글_작성자_1";
            String given_comment_content = "댓글_내용_1";

            PaComment comment = application.addComment(
                    post.getId(),
                    given_comment_author,
                    given_comment_content
            );

            Assertions.assertNotNull(comment.getId());

        }

        @Test
        void view_post() {

            application.publishPost(post.getId());

            String comment_author = "댓글_작성자_2";
            String comment_content = "댓글_내용_2";

            application.addComment(
                    post.getId(),
                    comment_author,
                    comment_content
            );


            PaPostDescription findPost = application.viewPost(post.getId());

            Assertions.assertNotNull(findPost);

            Assertions.assertEquals(1, findPost.comments().size());

            PaCommentDescription firstComment = findPost.comments().getFirst();

            Assertions.assertEquals(comment_author, firstComment.author());
            Assertions.assertEquals(comment_content, firstComment.content());

            System.out.println("findPost = " + findPost);

        }

    }


}