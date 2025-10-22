package io.eddie.dddexp.tx.repository;

import io.eddie.dddexp.tx.domain.TxComment;

import java.util.HashMap;
import java.util.Map;

public class TxCommentRepository {

    private final Map<Long, TxComment> db = new HashMap<>();
    private Long sequence = 0L;

    public TxComment save(TxComment comment) {
        if (comment.getId() == null) comment.setId(++sequence);

        db.put(comment.getId(), comment);

        return comment;
    }

}
