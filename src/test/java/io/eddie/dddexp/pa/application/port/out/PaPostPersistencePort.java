package io.eddie.dddexp.pa.application.port.out;

import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.model.PaPostStatus;

import java.util.List;

public interface PaPostPersistencePort {

    PaPost save(PaPost paPost);

    List<PaPost> findByStatus(PaPostStatus status);

    PaPost findById(Long id);

}
