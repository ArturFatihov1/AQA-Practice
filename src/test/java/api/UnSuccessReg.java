package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnSuccessReg {
    private String error;

    public UnSuccessReg(String error) {
        this.error = error;
    }

    public UnSuccessReg() {
    }

    public String getError() {
        return error;
    }
}
