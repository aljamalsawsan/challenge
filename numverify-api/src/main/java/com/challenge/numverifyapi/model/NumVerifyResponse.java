package com.challenge.numverifyapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NumVerifyResponse {
    private boolean valid;

    private String number;

    @JsonProperty("local_format")
    private String localFormat;

    @JsonProperty("international_format")
    private String internationalFormat;

    @JsonProperty("country_prefix")
    private String countryPrefix;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("carrier")
    private String carrier;

}