package io.eddie.dddexp.tx.repository;

import io.eddie.dddexp.tx.domain.TxPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TxPostRepository {

    private final Map<Long, TxPost> db = new HashMap<>();
    private Long sequence = 0L;

    public TxPost save(TxPost post) {

        if ( post.getId() == null ) {
            post.setId(++sequence);
        }

        db.put(post.getId(), post);

        return post;

    }

    public Optional<TxPost> findById(Long id) {
        return db.containsKey(id)
                ? Optional.of(db.get(id))
                : Optional.empty();
    }

    public List<TxPost> findAll() {
        return db.values()
                .stream()
                .toList();
    }

}
