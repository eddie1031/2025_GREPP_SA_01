package io.eddie.dddexp.pa.infrastructure;

import io.eddie.dddexp.pa.application.port.out.PaPostPersistencePort;
import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.model.PaPostStatus;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<PaPost> findByStatus(PaPostStatus status) {
        return db.values()
                .stream()
                .filter(post -> post.getStatus() == status)
                .toList();
    }

    @Override
    public PaPost findById(Long id) {
        return db.get(id);
    }

}
