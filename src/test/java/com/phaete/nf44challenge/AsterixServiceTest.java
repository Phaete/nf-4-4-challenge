package com.phaete.nf44challenge;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    AsterixCharacterRepo repo = mock(AsterixCharacterRepo.class);
    IdService idService = mock(IdService.class);
    AsterixService service = new AsterixService(repo, idService);

    @Test
    void findAll() {
        AsterixCharacter character = new AsterixCharacter(UUID.randomUUID().toString(), "Asterix", 30, "Warrior");
        when(repo.findAll()).thenReturn(List.of(character));
        List<AsterixCharacter> characters = service.findAll();
        verify(repo, times(1)).findAll();
        assertEquals(List.of(character), characters);
    }

    @Test
    void findById() {
        AsterixCharacter character = new AsterixCharacter(UUID.randomUUID().toString(), "Obelix", 30, "Sculptor");
        when(repo.findById("2")).thenReturn(Optional.of(character));
        AsterixCharacter optionalCharacter = service.findById("2");
        verify(repo, times(1)).findById("2");
        assertEquals(character, optionalCharacter);
    }

    @Test
    void updateCharacterById() {
        AsterixCharacter characterToBeUpdated = new AsterixCharacter(UUID.randomUUID().toString(), "Idefix", 7, "Canine Ecologist");
        DTOAsterixCharacter update = new DTOAsterixCharacter("Indix", 30, "Sculptor");

        when(repo.findById(characterToBeUpdated.id())).thenReturn(Optional.of(characterToBeUpdated));
        when(repo.save(any(AsterixCharacter.class))).thenReturn(
                new AsterixCharacter(characterToBeUpdated.id(), update.name(), update.age(), update.profession())
        );

        AsterixCharacter actual = service.updateCharacterById(characterToBeUpdated.id(), update);
        verify(repo, times(1)).save(any(AsterixCharacter.class));
        assertEquals(
                new AsterixCharacter(characterToBeUpdated.id(), update.name(), update.age(), update.profession()),
                actual
        );
    }

    @Test
    void deleteById() {
        AsterixCharacter character = new AsterixCharacter(UUID.randomUUID().toString(), "Assurancetourix", 30, "Bard");
        when(repo.findById(character.id())).thenReturn(Optional.of(character));

        //verify(repo, times(1)).deleteById(any(String.class));
        assertTrue(service.deleteById(character.id()));
    }

    @Test
    void save() {
        when(idService.generateId()).thenReturn("5");
        AsterixCharacter character = new AsterixCharacter(idService.generateId(), "Abraracourcix", 50, "Herbalist");
        when(repo.save(character)).thenReturn(character);
        assertEquals(character.id(), service.save(new DTOAsterixCharacter(character.name(), character.age(), character.profession())).id());
        verify(repo, times(1)).save(character);

    }

    @Test
    void randomUUID() {
        UUID randomId = UUID.randomUUID();
        try(MockedStatic<UUID> mockUUID = mockStatic(UUID.class)) {
            mockUUID.when(UUID::randomUUID).thenReturn(randomId);
            assertEquals(randomId.toString(), UUID.randomUUID().toString());
        }
    }
}