package io.eddie.dddexp.pa.application.port.in;

import io.eddie.dddexp.pa.domain.model.PaComment;
import io.eddie.dddexp.pa.domain.model.PaPost;
import io.eddie.dddexp.pa.domain.vo.PaPostDescription;

import java.util.List;

public interface BlogUseCase {

    PaPost writePost(String title, String content, String author);

    List<PaPost> getPublishedPosts();
    PaPost getPost(Long id);

    void publishPost(Long id);

    PaComment addComment(Long postId, String author, String content);

    PaPostDescription viewPost(Long id);

}
