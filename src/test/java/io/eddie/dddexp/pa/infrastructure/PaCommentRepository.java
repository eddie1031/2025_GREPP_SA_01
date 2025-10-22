package io.eddie.dddexp.pa.infrastructure;

import io.eddie.dddexp.pa.application.port.out.PaCommentPersistencePort;
import io.eddie.dddexp.pa.domain.model.PaComment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaCommentRepository implements PaCommentPersistencePort {

    private final Map<Long, PaComment> db = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public PaComment save(PaComment paComment) {

        if ( paComment.getId() == null )
            paComment.setId(++sequence);

        db.put(paComment.getId(), paComment);

        return paComment;

    }

    @Override
    public List<PaComment> findAllByPostId(Long postId) {


        return db.values()
                .stream()
                .filter(c -> c.getPostId().equals(postId))
                .toList();
    }
}
