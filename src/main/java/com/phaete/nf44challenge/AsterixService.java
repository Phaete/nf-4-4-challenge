package com.phaete.nf44challenge;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public AsterixCharacter findById(String id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Character with id " + id + " not found"));
    }

    public AsterixCharacter save(DTOAsterixCharacter character) {
        return repo.save(new AsterixCharacter(idService.generateId(), character.name(), character.age(), character.profession()));
    }

    public AsterixCharacter updateCharacterById(String id, DTOAsterixCharacter dtoAsterixCharacter) {
        AsterixCharacter characterToBeUpdated = findById(id); // if exception, get out of update
        return repo.save(new AsterixCharacter(characterToBeUpdated.id(), dtoAsterixCharacter.name(), dtoAsterixCharacter.age(), dtoAsterixCharacter.profession()));
    }

    public boolean deleteById(String id) {
        AsterixCharacter characterToBeDeleted = findById(id); // if exception, get out of delete
        repo.deleteById(characterToBeDeleted.id());
        return true;
    }
}
