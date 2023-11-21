package com.example.dgbackend.database.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServiceNamesDto(
        @JsonProperty("name") String name) {}
