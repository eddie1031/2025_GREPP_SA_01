package io.eddie.dddexp.pa.application.service;

import io.eddie.dddexp.pa.application.port.in.BlogUseCase;
import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.infrastructure.PaPostRepository;

public class PaBlogApplication implements BlogUseCase {

    private final PaPostRepository paPostRepository;

    public PaBlogApplication(PaPostRepository paPostRepository) {
        this.paPostRepository = paPostRepository;
    }

    @Override
    public PaPost writePost(String title, String content, String author) {

        PaPost paPost = new PaPost(title, content, author);

        return null;
    }

}
