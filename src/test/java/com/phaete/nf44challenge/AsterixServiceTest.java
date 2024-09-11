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
        assertEquals(List.of(character), service.findAll());
        verify(repo, times(1)).findAll();
    }

    @Test
    void findById() {
        AsterixCharacter character = new AsterixCharacter(UUID.randomUUID().toString(), "Obelix", 30, "Sculptor");
        when(repo.findById("2")).thenReturn(Optional.of(character));
        assertEquals(Optional.of(character), service.findById("2"));
        verify(repo, times(1)).findById("2");
    }

    @Test
    void updateCharacter() {
        AsterixCharacter character = new AsterixCharacter(UUID.randomUUID().toString(), "Idefix", 7, "Canine Ecologist");
        when(repo.existsById(character.id())).thenReturn(true);
        assertTrue(service.updateCharacter(character));
        verify(repo, times(1)).save(character);
    }

    @Test
    void deleteById() {
        AsterixCharacter character = new AsterixCharacter(UUID.randomUUID().toString(), "Assurancetourix", 30, "Bard");
        when(repo.existsById(character.id())).thenReturn(true);
        assertTrue(service.deleteById(character.id()));
        verify(repo, times(1)).deleteById(character.id());
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