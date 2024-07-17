package api.forohub.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicData(
        @NotNull(message = "The title cannot be repeated ")
        String title,
        @NotNull(message = "Use appropriate language in the message that does not exceed 700 characters in length ")
        String message,
        @NotNull(message = "Select one of the Status ´OPEN´ or ´CLOSED´")
        Status status,
        @NotNull(message = "Use your Author idUser")
        Long idUser,
        @NotNull(message = "Remember use the appropriate course  for you post  ")
        String course,
        LocalDateTime date ) {


}
