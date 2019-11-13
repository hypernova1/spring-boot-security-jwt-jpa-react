package com.sam.api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollResponse {
    private Long id;
    private String question;
    private List<ChoiceResponse> choices;
    private UserSummary createdBy;
    private Instant creationDateTime;
    private Instant expirationDateTime;
    private Boolean isExpired;

    @JsonInclude(JsonInclude.Include.NON_NULL) //제이슨 생성시 null 값은 제외
    private Long selectedChoice;
    private Long totalVotes;
}
