package gr.unipi.TriviaGame;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import trivia.api.TriviaAPIClient;
import trivia.model.Question;
import trivia.model.ResponseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση 'GameSceneCreator' διαχειρίζεται τη σκηνή του παιχνιδιού Trivia Game.
 * Περιλαμβάνει τη φόρτωση ερωτήσεων από το API, την εμφάνιση ερωτήσεων και
 * την αξιολόγηση των απαντήσεων του χρήστη.
 */
public class GameSceneCreator {
    private VBox root; 
    private Label questionLabel; 
    private ToggleGroup optionsGroup; 
    private List<RadioButton> optionButtons; 
    private Button nextButton; 
    private Stage primaryStage; 
    private List<Question> questions; 
    private int currentQuestionIndex = 0; 
    private int score = 0; 
    private static String lastDifficulty = ""; 
    private static String lastType = ""; 

    // Κατασκευαστής της σκηνής παιχνιδιού.
    
    public GameSceneCreator(Stage primaryStage, int amount, String difficulty, String type) {
        this.primaryStage = primaryStage;
        root = new VBox(15);
        root.setAlignment(Pos.CENTER);

        // Ετικέτα για τη φόρτωση των ερωτήσεων
        questionLabel = new Label("Φόρτωση ερωτήσεων...");
        optionsGroup = new ToggleGroup();
        optionButtons = new ArrayList<>();

        // Κουμπί για μετάβαση στην επόμενη ερώτηση
        nextButton = new Button("Επόμενη Ερώτηση");
        nextButton.setOnAction(e -> checkAnswer());

        // Προσθήκη των στοιχείων στη σκηνή
        root.getChildren().addAll(questionLabel, nextButton);

        // Αν αλλάξουν οι παράμετροι του παιχνιδιού, διαγράφουμε το μέγιστο σκορ
        if (!difficulty.equals(lastDifficulty) || !type.equals(lastType)) {
            ResultSceneCreator.highScore = 0;
        }

        lastDifficulty = difficulty;
        lastType = type;

        // Κλήση της μεθόδου για ανάκτηση ερωτήσεων από το API
        fetchQuestions(amount, difficulty, type);
    }

    // Ανάκτηση ερωτήσεων από το Trivia API.

    private void fetchQuestions(int amount, String difficulty, String type) {
        new Thread(() -> {
            try {
                TriviaAPIClient apiClient = new TriviaAPIClient();
                ResponseWrapper response = apiClient.fetchQuestions(amount, null, difficulty, type);

                // Μεταφορά των αποτελεσμάτων στη JavaFX interface
                Platform.runLater(() -> {
                    questions = response.getResults();
                    if (questions == null || questions.isEmpty()) {
                        questionLabel.setText("Δεν βρέθηκαν ερωτήσεις.");
                    } else {
                        loadQuestion();
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> questionLabel.setText("Αποτυχία φόρτωσης ερωτήσεων."));
            }
        }).start();
    }

    // Φορτώνει και εμφανίζει την τρέχουσα ερώτηση στη σκηνή.

    private void loadQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.getQuestion());

        // Αφαίρεση παλιών επιλογών από τη σκηνή
        root.getChildren().removeIf(node -> node instanceof RadioButton);
        optionsGroup.getToggles().clear();

        // Δημιουργία λίστας με σωστή και λανθασμένες απαντήσεις
        List<String> options = new ArrayList<>(currentQuestion.getIncorrectAnswers());
        options.add(currentQuestion.getCorrectAnswer());
        java.util.Collections.shuffle(options); // Ανακάτεμα των απαντήσεων

        // Δημιουργία κουμπιών επιλογής για κάθε απάντηση
        for (String option : options) {
            RadioButton rb = new RadioButton(option);
            rb.setToggleGroup(optionsGroup);
            optionButtons.add(rb);
            root.getChildren().add(rb);
        }
    }

    /**
     * Ελέγχει αν η απάντηση που επέλεξε ο χρήστης είναι σωστή.
     * Αυξάνει ή μειώνει το σκορ και φορτώνει την επόμενη ερώτηση.
     */
    private void checkAnswer() {
        RadioButton selectedButton = (RadioButton) optionsGroup.getSelectedToggle();
        if (selectedButton != null && selectedButton.getText().equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
            score += 10; // Σωστή απάντηση: +10 πόντοι
        } else {
            score -= 5; // Λάθος απάντηση: -5 πόντοι
        }

        currentQuestionIndex++;

        // Αν υπάρχουν ακόμα ερωτήσεις, φόρτωσε την επόμενη
        if (currentQuestionIndex < questions.size()) {
            loadQuestion();
        } else {
            // Αν τελειώσουν οι ερωτήσεις, πήγαινε στην οθόνη αποτελεσμάτων
            primaryStage.setScene(new ResultSceneCreator(primaryStage, score, questions.size()).createScene());
        }
    }

    // Δημιουργεί και επιστρέφει τη σκηνή του παιχνιδιού.
     
    public Scene createScene() {
        return new Scene(root, 400, 300);
    }
}
