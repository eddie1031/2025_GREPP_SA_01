package io.eddie.dddexp.pa.application.port.out;

import io.eddie.dddexp.pa.domain.model.PaPost;

public interface PaPostPersistencePort {

    PaPost save(PaPost paPost);

}
