package api.forohub.forohub.domain.response;

import api.forohub.forohub.domain.topic.Topic;
import api.forohub.forohub.domain.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseData(
        @NotBlank
        String solution,
        @NotNull
        @Valid
        Long idUser,
        @NotNull
        @Valid
        Long idTopic,
        LocalDateTime creationdate) {
}
