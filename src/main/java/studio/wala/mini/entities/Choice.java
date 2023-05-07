package studio.wala.mini.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "choices")
public class Choice {

    @Id
    @Column(name = "uid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "correct", nullable = false)
    private Boolean correct = false;

    @ManyToOne
    private Question question;

    public Choice() {
    }

    public Choice(String content, Boolean isCorrect, Question question) {
        this.content = content;
        this.correct = isCorrect;
        this.question = question;
    }
    public Choice(Integer id, String content, Boolean isCorrect, Question question) {
        this.id = id;
        this.content = content;
        this.correct = isCorrect;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCorrect() {
        return correct;
    }
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
}
