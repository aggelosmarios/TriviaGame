package gr.unipi.TriviaGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ResultSceneCreator {
    private VBox root; 
    private Label scoreLabel; 
    private Button restartButton; 
    private Button exitButton; 
    static int highScore = 0; 

    // Κατασκευαστής της οθόνης αποτελεσμάτων.
     
    public ResultSceneCreator(Stage primaryStage, int score, int totalQuestions) {
        root = new VBox(15); // Κάθετη διάταξη με απόσταση 15px μεταξύ των στοιχείων
        root.setAlignment(Pos.CENTER); // Στοίχιση στο κέντρο

        int maxScore = totalQuestions * 10; // Μέγιστο σκορ (κάθε σωστή απάντηση δίνει 10 πόντους)
        double percentage = (double) score / maxScore * 100; // Υπολογισμός ποσοστού επιτυχίας

        // Ενημέρωση του μέγιστου σκορ αν ο παίκτης έκανε νέο ρεκόρ
        boolean isNewHighScore = false;
        if (score > highScore) {
            highScore = score;
            isNewHighScore = true;
        }

        // Δημιουργία ετικέτας για την εμφάνιση του σκορ
        scoreLabel = new Label("Το σκορ σας: " + score + " / " + maxScore);
        Label messageLabel = new Label(getMessageBasedOnScore(percentage, isNewHighScore));

        // Κουμπί για να παίξει ξανά ο παίκτης
        restartButton = new Button("Παίξτε Ξανά");
        restartButton.setOnAction(e -> primaryStage.setScene(new MainSceneCreator(primaryStage).createScene()));

        // Κουμπί εξόδου από την εφαρμογή
        exitButton = new Button("Έξοδος");
        exitButton.setOnAction(e -> primaryStage.close());

        // Προσθήκη όλων των στοιχείων στη σκηνή
        root.getChildren().addAll(scoreLabel, messageLabel, restartButton, exitButton);
    }

    // Επιστρέφει ένα μήνυμα ανάλογα με το ποσοστό επιτυχίας του παίκτη.

    private String getMessageBasedOnScore(double percentage, boolean isNewHighScore) {
        if (isNewHighScore) {
            return "Νέο ρεκόρ! Συγχαρητήρια!";
        } else if (percentage >= 80) {
            return "Συγχαρητήρια! Είστε Trivia Expert!";
        } else if (percentage >= 50) {
            return "Καλή προσπάθεια!";
        }
        return "Δοκιμάστε ξανά!";
    }

    // Δημιουργεί και επιστρέφει τη σκηνή των αποτελεσμάτων.
 
    public Scene createScene() {
        return new Scene(root, 400, 300); // Δημιουργία σκηνής με διαστάσεις 400x300 pixels
    }
}
