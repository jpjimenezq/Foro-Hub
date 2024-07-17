package api.forohub.forohub.domain.response;

import api.forohub.forohub.domain.topic.Topic;
import api.forohub.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name="Response")
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationdate;
    private String solution;
    @OneToOne
    @JoinColumn(name="author", referencedColumnName="id")
    private User author;
    @OneToOne
    @JoinColumn(name="topic", referencedColumnName="id")
    private Topic topic;
    private boolean active;

    public Response(Long id, String solution, User user, Topic topic, LocalDateTime creationdate) {
        this.id=id;
        this.solution=solution;
        this.author=user;
        this.topic=topic;
        this.creationdate=LocalDateTime.now();
    }

    public void responseUpdate(UpdateResponseDetails updateResponseDetails) {
        if (updateResponseDetails.solution() != null){
            this.solution=updateResponseDetails.solution();
        }
    }
    public void diactivateResponse(){
        this.active=false;
    }
}
