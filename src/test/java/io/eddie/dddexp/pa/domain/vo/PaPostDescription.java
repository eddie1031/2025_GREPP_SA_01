package io.eddie.dddexp.pa.domain.vo;

import java.util.List;

public record PaPostDescription(
        Long id,
        String title,
        String content,
        String author,
        int viewCount,
        List<PaCommentDescription> comments
) {
}
