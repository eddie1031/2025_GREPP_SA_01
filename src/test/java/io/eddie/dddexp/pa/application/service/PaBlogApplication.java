package io.eddie.dddexp.pa.application.service;

import io.eddie.dddexp.pa.application.port.in.BlogUseCase;
import io.eddie.dddexp.pa.application.port.out.PaPostPersistencePort;
import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.model.PaPostStatus;
import io.eddie.dddexp.pa.domain.service.PaPostDomainService;
import io.eddie.dddexp.pa.domain.vo.PaPostDescription;

import java.util.List;

public class PaBlogApplication implements BlogUseCase {

    private final PaPostDomainService postDomainService;

    private final PaPostPersistencePort postPersistencePort;

    public PaBlogApplication(PaPostPersistencePort postPersistencePort, PaPostDomainService postDomainService) {
        this.postDomainService = postDomainService;
        this.postPersistencePort = postPersistencePort;
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
    public PaPostDescription viewPost(Long id) {
        PaPost post = getPost(id);

        post.increaseViewCount();
        postPersistencePort.save(post);

        return postDomainService.createPostDescription(post);
    }

}
