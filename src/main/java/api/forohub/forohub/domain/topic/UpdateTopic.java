package api.forohub.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateTopic(@NotNull Long id,
                          String title,
                          String message,
                          Status status,
                          @NotNull Long idUser,
                          String course) {
}
