package io.eddie.dddexp.pa.infrastructure;

import io.eddie.dddexp.pa.application.port.out.PaPostPersistencePort;
import io.eddie.dddexp.pa.domain.model.PaPost;

import java.util.HashMap;
import java.util.Map;

public class PaPostRepository implements PaPostPersistencePort {

    private final Map<Long, PaPost> db = new HashMap<>();
    private Long sequence = 0L;

    public PaPost save(PaPost post) {

        if ( post.getId() == null ) {
            post.setId(++sequence);
        }

        db.put(post.getId(), post);

        return post;

    }

}
