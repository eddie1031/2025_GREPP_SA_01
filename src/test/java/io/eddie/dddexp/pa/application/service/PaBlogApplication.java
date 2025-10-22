package io.eddie.dddexp.pa.application.service;

import io.eddie.dddexp.pa.application.port.in.BlogUseCase;
import io.eddie.dddexp.pa.application.port.out.PaPostPersistencePort;
import io.eddie.dddexp.pa.domain.model.PaPost;

public class PaBlogApplication implements BlogUseCase {

    private final PaPostPersistencePort postPersistencePort;

    public PaBlogApplication(PaPostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    @Override
    public PaPost writePost(String title, String content, String author) {

        PaPost paPost = new PaPost(title, content, author);

        return postPersistencePort.save(paPost);
    }

}
