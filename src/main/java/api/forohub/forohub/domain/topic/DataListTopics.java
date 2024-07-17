package api.forohub.forohub.domain.topic;

import java.time.LocalDateTime;

public record DataListTopics(Long id,
                             String title,
                             String message,
                             Status status,
                             Long idUser,
                             String course,
                             LocalDateTime date) {

    public DataListTopics (Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatus(),topic.getAuthor().getId(), topic.getCourse(),topic.getDate());

    }
}
