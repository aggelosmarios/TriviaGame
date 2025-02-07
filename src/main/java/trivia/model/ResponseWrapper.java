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
    "response_code",
    "results"
})
public class ResponseWrapper {

    /** 
     * Κωδικός απόκρισης του API που δηλώνει την κατάσταση του αιτήματος.
     * Πιθανές τιμές:
     * 0 - Επιτυχής επιστροφή δεδομένων.
     * 1 - Δεν υπάρχουν διαθέσιμες ερωτήσεις.
     * 2 - Παράμετροι αιτήματος δεν είναι έγκυρες.
     * 3 - Το μέγιστο όριο αιτήσεων έχει ξεπεραστεί.
     */
    @JsonProperty("response_code")
    private Integer responseCode;

    //Λίστα με τις ερωτήσεις που επιστράφηκαν από το API.
    @JsonProperty("results")
    private List<Question> results;

    //Επιπλέον ιδιότητες που μπορεί να περιέχονται στην απόκριση του API, αλλά δεν αντιστοιχούν στα βασικά πεδία της κλάσης.
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    // Επιστρέφει τον κωδικό απόκρισης του API.
    @JsonProperty("response_code")
    public Integer getResponseCode() {
        return responseCode;
    }

    // Ορίζει τον κωδικό απόκρισης του API.
    @JsonProperty("response_code")
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    // Επιστρέφει τη λίστα των ερωτήσεων που επιστράφηκαν από το API.
    @JsonProperty("results")
    public List<Question> getResults() {
        return results;
    }

    // Ορίζει τη λίστα των ερωτήσεων που επιστράφηκαν από το API.
    @JsonProperty("results")
    public void setResults(List<Question> results) {
        this.results = results;
    }

    // Επιστρέφει οποιαδήποτε επιπλέον ιδιότητα υπάρχει στην απόκριση του API.
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    // Προσθέτει μια επιπλέον ιδιότητα στον χάρτη `additionalProperties`.
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
