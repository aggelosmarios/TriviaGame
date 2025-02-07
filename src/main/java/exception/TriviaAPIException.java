package exception;

/**
 * Η κλάση 'TriviaAPIException' είναι μια προσαρμοσμένη εξαίρεση (custom exception),
 * η οποία χρησιμοποιείται για να διαχειρίζεται σφάλματα που σχετίζονται με την επικοινωνία με το API.
 * Επεκτείνει την Exception και παρέχει δύο κατασκευαστές:
 * - Έναν που δέχεται μόνο ένα μήνυμα σφάλματος.
 * - Έναν που δέχεται μήνυμα σφάλματος και την αιτία του σφάλματος.
 */
public class TriviaAPIException extends Exception {


    private static final long serialVersionUID = 1L;


    public TriviaAPIException(String message) {
        super(message);
    }


    public TriviaAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}



