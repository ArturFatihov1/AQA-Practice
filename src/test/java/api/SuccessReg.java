package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessReg {
    private Integer id;
    private String token;

    public SuccessReg(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessReg() {
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
