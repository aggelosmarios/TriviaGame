package gr.unipi.TriviaGame;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

 // Η κλάση 'MainSceneCreator' δημιουργεί την αρχική σκηνή του Trivia Game.

public class MainSceneCreator {
    private VBox root; 
    private TextField amountField; 
    private ComboBox<String> difficultyBox; 
    private ComboBox<String> typeBox; 
    private Button startButton;
    private Button exitButton; 

    // Κατασκευαστής που αρχικοποιεί την αρχική οθόνη του παιχνιδιού.

    public MainSceneCreator(Stage primaryStage) {
        root = new VBox(15); // Κάθετο layout με απόσταση 15px μεταξύ των στοιχείων
        root.setAlignment(Pos.CENTER); // Στοίχιση στο κέντρο

        // Μήνυμα καλωσορίσματος
        Label welcomeLabel = new Label("Καλωσήρθατε στο Trivia Game");

        // Πεδίο εισαγωγής για τον αριθμό των ερωτήσεων
        amountField = new TextField();
        amountField.setPromptText("Αριθμός ερωτήσεων");

        // Πτυσσόμενο μενού για επιλογή δυσκολίας
        difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("easy", "medium", "hard");
        difficultyBox.setValue("medium"); // Προεπιλεγμένη τιμή

        // Πτυσσόμενο μενού για επιλογή τύπου ερωτήσεων
        typeBox = new ComboBox<>();
        typeBox.getItems().addAll("multiple", "boolean"); // "multiple" για πολλαπλής επιλογής, "boolean" για True/False
        typeBox.setValue("multiple"); // Προεπιλεγμένη τιμή

        // Κουμπί έναρξης παιχνιδιού
        startButton = new Button("Έναρξη Παιχνιδιού");
        startButton.setOnAction(e -> {
            // Έλεγχος αν όλα τα πεδία έχουν συμπληρωθεί
            if (amountField.getText().isEmpty() || difficultyBox.getValue() == null || typeBox.getValue() == null) {
                showAlert("Σφάλμα", "Παρακαλώ συμπληρώστε όλα τα πεδία.");
                return;
            }
            try {
                int amount = Integer.parseInt(amountField.getText()); // Μετατροπή αριθμού ερωτήσεων σε ακέραιο
                // Φόρτωση της επόμενης σκηνής
                primaryStage.setScene(new GameSceneCreator(primaryStage, amount, difficultyBox.getValue(), typeBox.getValue()).createScene());
            } catch (NumberFormatException ex) {
                // Αν ο χρήστης δεν έβαλε αριθμό, εμφάνιση μηνύματος σφάλματος
                showAlert("Σφάλμα", "Παρακαλώ εισάγετε έγκυρο αριθμό ερωτήσεων.");
            }
        });

        // Κουμπί εξόδου από το παιχνίδι
        exitButton = new Button("Έξοδος");
        exitButton.setOnAction(e -> Platform.exit()); 

        // Προσθήκη όλων των στοιχείων στη σκηνή
        root.getChildren().addAll(welcomeLabel, amountField, difficultyBox, typeBox, startButton, exitButton);
    }

    // Δημιουργεί και επιστρέφει τη σκηνή της αρχικής οθόνης
     
    public Scene createScene() {
        return new Scene(root, 400, 300); // Δημιουργία σκηνής με διαστάσεις 400x300 pixels
    }

    //Εμφανίζει ένα μήνυμα σφάλματος

    private void showAlert(String title, String message) {
        System.out.println(title + ": " + message); 
    }
}

