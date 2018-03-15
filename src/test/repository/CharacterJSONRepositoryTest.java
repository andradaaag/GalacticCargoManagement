package test.repository;

import main.domain.BaseEntity;
import main.domain.Character;
import main.repository.CharacterJSONRepository;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Optional;

public class CharacterJSONRepositoryTest {
    private static CharacterJSONRepository repo;
    private static Character testCharacter;

    @org.junit.Before
    public void setUp() throws Exception {
        repo = new CharacterJSONRepository("./src/test/data/characters_test.json");
        testCharacter = new Character(1, "Han Solo", Arrays.asList("A", "B", "C"));
    }

    @org.junit.Test
    public void get() {
        Optional<Character> characterOptional = repo.get(1);
        Assert.assertTrue(characterOptional.isPresent());
        Character character = characterOptional.get();
        Assert.assertEquals(character, testCharacter);
    }

    @org.junit.Test
    public void getAll() {
        Iterable<BaseEntity> characters = repo.getAll();
        Assert.assertEquals(characters.spliterator().getExactSizeIfKnown(), 7);
    }
}