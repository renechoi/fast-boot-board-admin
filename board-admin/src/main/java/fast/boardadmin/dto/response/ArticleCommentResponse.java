package fast.boardadmin.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import fast.boardadmin.dto.ArticleCommentDto;
import fast.boardadmin.dto.UserAccountDto;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ArticleCommentResponse(
        Long id,
        UserAccountDto userAccount,
        String content,
        LocalDateTime createdAt
) {

    public static ArticleCommentResponse of(Long id, UserAccountDto userAccount, String content, LocalDateTime createdAt) {
        return new ArticleCommentResponse(id, userAccount, content, createdAt);
    }

    public static ArticleCommentResponse of(ArticleCommentDto dto) {
        return ArticleCommentResponse.of(dto.id(), dto.userAccount(), dto.content(), dto.createdAt());
    }

}