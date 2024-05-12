package com.parth.challengeapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenge = challengeService.getAllChallenges();

        if (challenge.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT
            );
        }
        return new ResponseEntity<>(
                challenge, HttpStatus.OK
        );
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(
            @PathVariable String month
    ) {
        /*
        if (challengeService.getAllChallenges().isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT
            );
        } else if (challengeService.getChallenge(month) == null) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                challengeService.getChallenge(month),
                HttpStatus.OK
        );
         */
        Challenge challenge = challengeService.getChallenge(month);

        if (challengeService.getAllChallenges().isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT
            );
        } else if (challenge == null) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                challenge, HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addChallenges(
            @RequestBody Challenge challenge
    ) {
        boolean isAdded = challengeService.addChallenges(challenge);
        Map<String, String> response = new HashMap<>();

        if (isAdded) {
            response.put("message", "Challenge Added");
            return new ResponseEntity<>(
                    response, HttpStatus.CREATED
            );
        } else {
            response.put("message", "Couldn't add challenge");
            return new ResponseEntity<>(
                    response, HttpStatus.BAD_REQUEST
            );
        }
    }

//    @PostMapping
//    public ResponseEntity<String> addChallenges(
//            @RequestBody Challenge challenge
//    ) {
//        boolean isAdded = challengeService.addChallenges(challenge);
//        Map<String, String> response = new HashMap<>();
//
//        if (isAdded) {
//            return new ResponseEntity<>(
//                    "Challenge Added", HttpStatus.CREATED
//            );
//        } else {
//            return new ResponseEntity<>(
//                   "Couldn't add challenge", HttpStatus.BAD_REQUEST
//            );
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(
            @RequestBody Challenge updatedChallenge,
            @PathVariable Long id
    ) {
        boolean isUpdated = challengeService.updateChallenge(updatedChallenge, id);

        if (isUpdated) {
            return new ResponseEntity<>(
                    "Challenge Updated", HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "Couldn't update challenge", HttpStatus.NOT_FOUND
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(
            @PathVariable Long id
    ) {
        boolean isDeleted = challengeService.deleteChallenge(id);

        if (isDeleted) {
            return new ResponseEntity<>(
                    "Challenge Deleted", HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "Couldn't delete challenge", HttpStatus.NOT_FOUND
        );
    }
}
