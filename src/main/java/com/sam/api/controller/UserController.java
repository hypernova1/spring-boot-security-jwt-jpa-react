package com.sam.api.controller;

import com.sam.api.exception.ResourceNotFoundException;
import com.sam.api.model.User;
import com.sam.api.model.Vote;
import com.sam.api.payload.*;
import com.sam.api.repository.PollRepository;
import com.sam.api.repository.UserRepository;
import com.sam.api.repository.VoteRepository;
import com.sam.api.security.CurrentUser;
import com.sam.api.security.UserPrincipal;
import com.sam.api.service.PollService;
import com.sam.api.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return UserSummary.builder().id(currentUser.getId()).username(currentUser.getUsername()).build();
    }

    @GetMapping("user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam String username) {
         Boolean isAvailable = !userRepository.existsByUsername(username);
         return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }

    @GetMapping("users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int page,
                                                       @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }

}
