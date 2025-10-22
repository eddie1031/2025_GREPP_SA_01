package io.eddie.dddexp.pa.application.service;

import io.eddie.dddexp.pa.application.port.in.BlogUseCase;
import io.eddie.dddexp.pa.application.port.out.PaCommentPersistencePort;
import io.eddie.dddexp.pa.application.port.out.PaPostPersistencePort;
import io.eddie.dddexp.pa.domain.model.PaComment;
import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.model.PaPostStatus;
import io.eddie.dddexp.pa.domain.service.PaPostDomainService;
import io.eddie.dddexp.pa.domain.vo.PaPostDescription;

import java.util.List;

public class PaBlogApplication implements BlogUseCase {

    private final PaPostDomainService postDomainService;

    private final PaPostPersistencePort postPersistencePort;
    private final PaCommentPersistencePort commentPersistencePort;

    public PaBlogApplication(PaPostPersistencePort postPersistencePort
            , PaPostDomainService postDomainService
            , PaCommentPersistencePort commentPersistencePort) {
        this.postDomainService = postDomainService;
        this.postPersistencePort = postPersistencePort;
        this.commentPersistencePort = commentPersistencePort;
    }

    @Override
    public PaPost writePost(String title, String content, String author) {

        PaPost paPost = new PaPost(title, content, author);

        return postPersistencePort.save(paPost);
    }

    @Override
    public List<PaPost> getPublishedPosts() {
        return postPersistencePort.findByStatus(PaPostStatus.PUBLISHED);
    }

    @Override
    public PaPost getPost(Long id) {
        return postPersistencePort.findById(id);
    }

    @Override
    public void publishPost(Long id) {

        PaPost post = findPostById(id);
        post.publish();

        postPersistencePort.save(post);

    }

    @Override
    public PaComment addComment(Long postId, String author, String content) {

        PaComment comment = new PaComment(content, author, postId);
        return commentPersistencePort.save(comment);

    }

    private PaPost findPostById(Long postId) {
        PaPost findPost = postPersistencePort.findById(postId);

        if ( findPost == null ) {
            throw new IllegalArgumentException("%d번 포스트를 찾을 수 없습니다".formatted(postId));
        }

        return findPost;
    }

    @Override
    public PaPostDescription viewPost(Long id) {
        PaPost post = getPost(id);

        post.increaseViewCount();
        postPersistencePort.save(post);

        commentPersistencePort.findAllByPostId(post.getId());

        return postDomainService.createPostDescription(post);
    }

}
