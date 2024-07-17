package api.forohub.forohub.domain.topic;

import api.forohub.forohub.domain.user.UserRepository;
import api.forohub.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseTopicData createTopic(TopicData topicData){
        if (!userRepository.findById(topicData.idUser()).isPresent()){
            throw new IntegrityValidation("This user id  ins´t registered on the data base ");
        }
        var title= topicData.title();
        if (title != null && topicRepository.existsByTitleIgnoreCase(title)){
            throw new IntegrityValidation("This title is already present in the database. Please review the existing topic.");
        }
        String message = topicData.message();
        if (message != null && topicRepository.existsByMessageIgnoreCase(message)){
            throw new IntegrityValidation("This message is already present in the database. Please review the existing topic.");
        }
        var user = userRepository.findById(topicData.idUser()).get();
        var topic1= new Topic(null,title,message,topicData.date(),topicData.status(),user,topicData.course());
        topicRepository.save(topic1);
        return new ResponseTopicData(topic1);
    }
}
