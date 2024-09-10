package com.phaete.nf44challenge;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AsterixCharacterRepo extends MongoRepository<AsterixCharacter, String> {
}
