package io.eddie.dddexp.pa.application.port.in;

import io.eddie.dddexp.pa.domain.model.PaPost;

public interface BlogUseCase {

    PaPost writePost(String title, String content, String author);

}
