package com.phaete.nf44challenge;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
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

    @DeleteMapping("/{id}")
    public boolean deleteCharacterById(@PathVariable String id) {
        return asterixService.deleteById(id);
    }
}
