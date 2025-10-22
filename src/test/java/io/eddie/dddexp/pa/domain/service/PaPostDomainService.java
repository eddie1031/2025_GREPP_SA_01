package io.eddie.dddexp.pa.domain.service;

import io.eddie.dddexp.pa.domain.model.PaComment;
import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.vo.PaCommentDescription;
import io.eddie.dddexp.pa.domain.vo.PaPostDescription;

import java.util.List;

public class PaPostDomainService {

    public PaPostDescription createPostDescription(PaPost post, List<PaComment> comments) {

        return new PaPostDescription(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getViewCount(),
                comments.stream()
                        .map(c -> new PaCommentDescription(c.getAuthor(), c.getContent(),c.getCreatedAt()))
                        .toList()

        );

    }

}
