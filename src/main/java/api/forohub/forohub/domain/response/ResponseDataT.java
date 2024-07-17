package api.forohub.forohub.domain.response;

import java.time.LocalDateTime;

public record ResponseDataT(Long id, String solution, Long idUser, Long idTopic, LocalDateTime creationdate) {
    public ResponseDataT(Response rVerified) {
        this(rVerified.getId(),rVerified.getSolution(),rVerified.getAuthor().getId(),rVerified.getTopic().getId(),rVerified.getCreationdate());
    }
}
