package gr.unipi.TriviaGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Η κλάση 'App' αποτελεί το σημείο εκκίνησης της εφαρμογής Trivia Game.
 * Κληρονομεί από την 'Application' του JavaFX και διαχειρίζεται το κύριο παράθυρο (stage).
 */
public class App extends Application {
    // Στατική αναφορά στο primaryStage για να μπορεί να προσπελαστεί από άλλες κλάσεις
    static Stage primaryStage;

    /**
     * Μέθοδος εκκίνησης της εφαρμογής.
     * Αρχικοποιεί το κύριο παράθυρο, δημιουργεί και εμφανίζει την αρχική σκηνή του παιχνιδιού.
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Trivia Game"); 

        // Δημιουργία της αρχικής σκηνής του παιχνιδιού
        MainSceneCreator mainSceneCreator = new MainSceneCreator(primaryStage);
        Scene mainScene = mainSceneCreator.createScene();

        // Ορισμός και εμφάνιση της αρχικής σκηνής
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Κύρια μέθοδος εκκίνησης της εφαρμογής.
     * Καλεί τη μέθοδο 'launch(args)', η οποία εκκινεί το JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

