package com.parth.challengeapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    private final ChallengeRepository repository;

    public ChallengeService(ChallengeRepository repository) {
        this.repository = repository;
    }

    public List<Challenge> getAllChallenges() {
        return repository.findAll();
    }

    public boolean addChallenges(
            Challenge challenge
    ) {
        if (challenge != null) {
            repository.save(challenge);
            return true;
        } else {
            return false;
        }
    }

    public Challenge getChallenge(String month) {
        Optional<Challenge> challenge = repository.findByMonthIgnoreCase(month);
        return challenge.orElse(null);
    }

    public boolean updateChallenge(Challenge updatedChallenge, Long id) {
        Optional<Challenge> challenge = repository.findById(id);

            if (challenge.isPresent()) {
                Challenge challengeToUpdate = challenge.get();

                challengeToUpdate.setMonth(updatedChallenge.getMonth());
                challengeToUpdate.setDescription(updatedChallenge.getDescription());

                repository.save(challengeToUpdate);
                return true;
            }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
