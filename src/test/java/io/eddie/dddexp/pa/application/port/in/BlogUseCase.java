package io.eddie.dddexp.pa.application.port.in;

import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.vo.PaPostDescription;

import java.util.List;

public interface BlogUseCase {

    PaPost writePost(String title, String content, String author);

    List<PaPost> getPublishedPosts();

    PaPost getPost(Long id);

    PaPostDescription viewPost(Long id);

}
