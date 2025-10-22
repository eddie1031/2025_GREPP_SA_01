package io.eddie.dddexp.pa.application.port.out;

import io.eddie.dddexp.pa.domain.model.PaComment;

import java.util.List;

public interface PaCommentPersistencePort {

    PaComment save(PaComment paComment);
    List<PaComment> findAllByPostId(Long postId);

}
