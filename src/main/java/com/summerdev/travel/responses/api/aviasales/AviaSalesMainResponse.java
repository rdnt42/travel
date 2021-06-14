package com.summerdev.travel.responses.api.aviasales;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesMainResponse {

    private boolean success;

    @JsonProperty("data")
    private AviaSalesDataResponse aviaSalesDataResponse;
    private String error;
    private String currency;

    public AviaSalesMainResponse(boolean result, AviaSalesDataResponse aviaSalesDataResponse, String error, String currency) {
        this.success = result;
        this.aviaSalesDataResponse = aviaSalesDataResponse;
        this.error = error;
        this.currency = currency;
    }

    public AviaSalesMainResponse() {
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AviaSalesDataResponse getAviaSalesDataResponse() {
        return aviaSalesDataResponse;
    }

    public void setAviaSalesDataResponse(AviaSalesDataResponse aviaSalesDataResponse) {
        this.aviaSalesDataResponse = aviaSalesDataResponse;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AviaSalesMainResponse)) return false;
        AviaSalesMainResponse that = (AviaSalesMainResponse) o;
        return success == that.success && Objects.equals(aviaSalesDataResponse, that.aviaSalesDataResponse) && Objects.equals(error, that.error) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, aviaSalesDataResponse, error, currency);
    }

    @Override
    public String toString() {
        return "AviaSalesMainResponse{" +
                "result=" + success +
                ", aviaSalesDataResponse=" + aviaSalesDataResponse +
                ", errorFromResponse='" + error + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static void parse(String json) throws JsonProcessingException {
        JsonFactory factory = new JsonFactory();

        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);

        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {

            Map.Entry<String,JsonNode> field = fieldsIterator.next();
            System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
        }
    }
}
