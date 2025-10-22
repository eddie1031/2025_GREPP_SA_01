package io.eddie.dddexp.pa.domain.service;

import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.vo.PaPostDescription;

public class PaPostDomainService {

    public PaPostDescription createPostDescription(PaPost post) {

        return new PaPostDescription(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getViewCount()
        );

    }

}
