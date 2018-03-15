package main.repository;

import com.google.gson.Gson;
import main.domain.BaseEntity;
import main.domain.Character;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CharacterJSONRepository {
    private Map<Integer, Character> entities = new HashMap<>();

    public CharacterJSONRepository(String fileName) {
        loadData(fileName);
    }

    /**
     * Loads the data from a JSON file and saves the Characters in memory
     *
     * @param fileName the name of the JSON file
     */
    private void loadData(String fileName) {
        Gson gson = new Gson();
        try {
            Character[] characters = gson.fromJson(new FileReader(fileName), Character[].class);
            for (Character character : characters) {
                save(character);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a Character in memory
     *
     * @param entity the Character entity to be saved
     */
    private void save(Character entity) {
        entities.putIfAbsent(entity.getId(), entity);
    }

    /**
     * Returns the Character having the given ID
     *
     * @param id the id of the Character searched for
     * @return an Optional of a Character
     */
    public Optional<Character> get(int id) {
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * Returns all the existent Character
     *
     * @return an Iterable of Characters
     */
    public Iterable<BaseEntity> getAll() {
        return entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }
}
