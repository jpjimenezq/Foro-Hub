package api.forohub.forohub.domain.topic;

import java.time.LocalDateTime;

public record ResponseTopicData(Long id, String title, String message, Status status, Long idUser, String course,
                                LocalDateTime date) {
    public ResponseTopicData(Topic topic1) {
        this(topic1.getId(), topic1.getTitle(), topic1.getMessage(),topic1.getStatus(),topic1.getAuthor().getId(),topic1.getCourse(),topic1.getDate());
    }


}
