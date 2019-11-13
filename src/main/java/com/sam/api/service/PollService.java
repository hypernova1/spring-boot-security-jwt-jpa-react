package com.sam.api.service;

import com.sam.api.model.Poll;
import com.sam.api.payload.PagedResponse;
import com.sam.api.payload.PollRequest;
import com.sam.api.payload.PollResponse;
import com.sam.api.payload.VoteRequest;
import com.sam.api.security.UserPrincipal;

public interface PollService {

    PagedResponse<PollResponse> getAllPolls(UserPrincipal currentUser, int page, int size);

    Poll createPoll(PollRequest pollRequest);

    PollResponse getPollById(Long pollId, UserPrincipal currentUser);

    PollResponse castVoteAndGetUpdatedPoll(Long pollId, VoteRequest voteRequest, UserPrincipal currentUser);
}
