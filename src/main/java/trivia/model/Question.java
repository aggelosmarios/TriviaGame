package trivia.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "difficulty",
    "category",
    "question",
    "correct_answer",
    "incorrect_answers"
})
public class Question {

    // Ο τύπος της ερώτησης ("multiple" για πολλαπλής επιλογής και "boolean" για true/false)
    @JsonProperty("type")
    private String type;

    // Επίπεδο δυσκολίας της ερώτησης ("easy", "medium" και "hard")
    @JsonProperty("difficulty")
    private String difficulty;

    // Κατηγορία ερώτησης 
    @JsonProperty("category")
    private String category;

    // Κείμενο ερώτησης
    @JsonProperty("question")
    private String question;

    // Σωστή απάντηση 
    @JsonProperty("correct_answer")
    private String correctAnswer;

    // Λίστα με τις λανθασμένες απαντήσεις
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;

    // Επιπλέον ιδιότητες που μπορεί να επιστραφούν από το API και δεν ορίζονται ρητά στην κλάση.
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    // Getters και Setters για κάθε ιδιότητα

    // Επιστρέφει τον τύπο της ερώτησης.
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    // Ορίζει τον τύπο της ερώτησης. 
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    // Επιστρέφει τη δυσκολία της ερώτησης.
    @JsonProperty("difficulty")
    public String getDifficulty() {
        return difficulty;
    }

    // Ορίζει τη δυσκολία της ερώτησης.
    @JsonProperty("difficulty")
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Επιστρέφει την κατηγορία της ερώτησης.

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    // Ορίζει την κατηγορία της ερώτησης.

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    // Επιστρέφει το κείμενο της ερώτησης.

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    // Ορίζει το κείμενο της ερώτησης.

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }

    // Επιστρέφει τη σωστή απάντηση.

    @JsonProperty("correct_answer")
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // Ορίζει τη σωστή απάντηση.

    @JsonProperty("correct_answer")
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    // Επιστρέφει τη λίστα με τις λανθασμένες απαντήσεις.

    @JsonProperty("incorrect_answers")
    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    // Ορίζει τη λίστα των λανθασμένων απαντήσεων.

    @JsonProperty("incorrect_answers")
    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    // Επιστρέφει οποιαδήποτε επιπλέον ιδιότητα που μπορεί να υπάρχει στην απόκριση του API.

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    //Προσθέτει μια επιπλέον ιδιότητα στον χάρτη 'additionalProperties'Σ.

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

