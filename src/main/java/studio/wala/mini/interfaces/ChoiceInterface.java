package studio.wala.mini.interfaces;

import studio.wala.mini.entities.Choice;

import java.util.List;

public interface ChoiceInterface {

    public List<Choice> index(Integer questionId);
    public Choice show(Integer choiceId);
    public void store(Choice choice);
    public void update(Choice choice);
    public void delete(Integer choiceId);

}
