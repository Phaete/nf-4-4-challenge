package com.phaete.nf44challenge;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private final AsterixCharacterRepo repo;

    public AsterixController(AsterixCharacterRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<AsterixCharacter> getAssortedCharacters() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AsterixCharacter> getCharacterById(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping
    public void saveCharacter(@RequestBody AsterixCharacter character) {
        repo.save(character);
    }

    @PutMapping
    public void updateCharacter(@RequestBody AsterixCharacter character) {
        if (repo.existsById(character.id())) {
            repo.save(character);
        }
    }

    @DeleteMapping()
    public void deleteCharacter(@RequestBody AsterixCharacter character) {
        repo.deleteById(character.id());
    }
}
