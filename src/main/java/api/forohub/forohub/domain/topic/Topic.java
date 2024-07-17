package api.forohub.forohub.domain.topic;

import api.forohub.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name= "Topic")
@Table(name="topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "author_id")
    private User author;
    private String course;
    private boolean active;



    public Topic(Long id, String title, String message, LocalDateTime date, Status status, User user, String course) {
        this.id=id;
        this.title=title;
        this.message=message;
        this.date=LocalDateTime.now();
        this.status=status;
        this.author=user;
        this.course=course;

    }


    public void updateData(UpdateTopic updateTopic) {
        if (updateTopic.title() !=null){
            this.title= updateTopic.title();
        }
        if (updateTopic.message() != null){
            this.message=updateTopic.message();
        }
        if (updateTopic.status() != null){
            this.status=updateTopic.status();
        }
        if (updateTopic.course() != null){
            this.course=updateTopic.course();
        }
    }
    public void diactivateTopic(){
        this.active=false;
    }
}
