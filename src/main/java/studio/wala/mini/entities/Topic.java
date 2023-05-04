package studio.wala.mini.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @Column(name ="uid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="topic_name", nullable = false, unique = true)
    private String topicName;

    @Column(name ="topic_description", nullable = false)
    private String topicDescription;

    @Column(name ="topic_image", nullable = true)
    private String topicImage;

    /* constructors */
    public Topic() {}
    public Topic(String topicName, String topicDescription, String topicImage) {
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.topicImage = topicImage;
    }
    public Topic(Integer id, String topicName, String topicDescription, String topicImage) {
        this.id = id;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.topicImage = topicImage;
    }

    /* getters and setters */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }
    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getTopicImage() {
        return topicImage;
    }
    public void setTopicImage(String topicImage) {
        this.topicImage = topicImage;
    }
}
