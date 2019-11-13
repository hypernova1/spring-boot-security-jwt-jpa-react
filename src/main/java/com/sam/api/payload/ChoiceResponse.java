package com.sam.api.payload;

import lombok.*;

@Getter @Setter
public class ChoiceResponse {
    private long id;
    private String text;
    private long voteCount;
}
