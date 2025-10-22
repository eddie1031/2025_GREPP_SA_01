package io.eddie.dddexp.pa.domain.vo;

public record PaPostDescription(
        Long id,
        String title,
        String content,
        String author,
        int viewCount
) {
}
