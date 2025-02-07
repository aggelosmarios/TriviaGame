package trivia.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.TriviaAPIException;
import trivia.model.ResponseWrapper;

public class TriviaAPIClient {

    // Βασική διεύθυνση URL του Trivia API 
    private static final String BASE_URL = "https://opentdb.com/api.php";

    // Αντικείμενο HTTP client για την αποστολή αιτημάτων προς το API.
    private final CloseableHttpClient httpClient;

    // ObjectMapper για τη μετατροπή των JSON αποκρίσεων σε Java αντικείμενα.
    private final ObjectMapper objectMapper;

    // Κατασκευαστής που αρχικοποιεί τον HTTP Client και τον ObjectMapper.
    public TriviaAPIClient() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    // Με βάση τις παραμέτρους που δίνονται, καλείται το API για την ανάκτηση των ερωτήσεων.
     
    public ResponseWrapper fetchQuestions(int amount, String category, String difficulty, String type) throws TriviaAPIException {
        try {
            // Δημιουργία URI με τις παραμέτρους αναζήτησης
            URIBuilder uriBuilder = new URIBuilder(BASE_URL)
                    .addParameter("amount", String.valueOf(amount));

            if (category != null && !category.isEmpty()) {
                uriBuilder.addParameter("category", category);
            }
            if (difficulty != null && !difficulty.isEmpty()) {
                uriBuilder.addParameter("difficulty", difficulty);
            }
            if (type != null && !type.isEmpty()) {
                uriBuilder.addParameter("type", type);
            }

            URI uri = uriBuilder.build();

            // Δημιουργία και αποστολή HTTP GET αιτήματος
            HttpGet getRequest = new HttpGet(uri);
            CloseableHttpResponse response = httpClient.execute(getRequest);

            // Έλεγχος αν το API επιστρέφει επιτυχές status code (200 OK)
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new TriviaAPIException("API Error: " + response.getStatusLine().getReasonPhrase());
            }

            // Ανάγνωση του response body και μετατροπή σε αντικείμενο ResponseWrapper
            HttpEntity entity = response.getEntity();
            return objectMapper.readValue(entity.getContent(), ResponseWrapper.class);

        } catch (URISyntaxException e) {
            throw new TriviaAPIException("Invalid API URL", e);
        } catch (ClientProtocolException e) {
            throw new TriviaAPIException("HTTP Protocol error", e);
        } catch (IOException e) {
            throw new TriviaAPIException("Error processing the response", e);
        }
    }
}
