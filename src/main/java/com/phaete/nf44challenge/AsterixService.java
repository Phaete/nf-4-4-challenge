package com.phaete.nf44challenge;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsterixService {

    private final AsterixCharacterRepo repo;
    private final IdService idService;

    public AsterixService(AsterixCharacterRepo repo, IdService idService) {
        this.idService = idService;
        this.repo = repo;
    }

    public List<AsterixCharacter> findAll() {
        return repo.findAll();
    }

    public Optional<AsterixCharacter> findById(String id) {
        return repo.findById(id);
    }

    public AsterixCharacter save(DTOAsterixCharacter character) {
        return repo.save(new AsterixCharacter(idService.generateId(), character.name(), character.age(), character.profession()));
    }

    public boolean updateCharacter(AsterixCharacter character) {
        if (!repo.existsById(character.id())) {
            return false;
        } else {
            repo.save(character);
            return true;
        }
    }

    public boolean deleteById(String id) {
        if (!repo.existsById(id)) {
            return false;
        } else {
            repo.deleteById(id);
            return true;
        }
    }
}
