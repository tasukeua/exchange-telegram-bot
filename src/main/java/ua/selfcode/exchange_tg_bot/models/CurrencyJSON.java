package ua.selfcode.exchange_tg_bot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CurrencyJSON {
    @JsonProperty("cc")
    private String currency;
    @JsonProperty("rate")
    private double rate;
    @JsonProperty("txt")
    private String txt;

}
