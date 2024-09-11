package com.phaete.nf44challenge;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private final AsterixService asterixService;

    public AsterixController(AsterixService asterixService) {
        this.asterixService = asterixService;
    }

    @GetMapping
    public List<AsterixCharacter> getAllCharacters() {
        return asterixService.findAll();
    }

    @GetMapping("/{id}")
    public AsterixCharacter getCharacterById(@PathVariable String id) {
        return asterixService.findById(id);
    }

    @PostMapping
    public DTOAsterixCharacter saveCharacter(@RequestBody DTOAsterixCharacter dtoCharacter) {
        AsterixCharacter character = asterixService.save(dtoCharacter);
        return new DTOAsterixCharacter(character.name(), character.age(), character.profession());
    }

    @PutMapping("/{id}")
    public AsterixCharacter updateCharacter(
            @PathVariable String id,
            @RequestBody DTOAsterixCharacter character
    ) {
        return asterixService.updateCharacterById(id, character);
    }

    @DeleteMapping()
    public boolean deleteCharacter(@RequestBody AsterixCharacter character) {
        return asterixService.deleteById(character.id());
    }
}
