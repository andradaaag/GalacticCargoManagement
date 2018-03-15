package main.repository;

import com.google.gson.Gson;
import main.domain.BaseEntity;
import main.domain.Planet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlanetJSONRepository {
    private Map<Integer, Planet> entities = new HashMap<>();

    public PlanetJSONRepository(String fileName) {
        loadData(fileName);
    }

    /**
     * Loads the data from a JSON file and saves the Planets in memory
     *
     * @param fileName the name of the JSON file
     */
    private void loadData(String fileName) {
        Gson gson = new Gson();
        try {
            Planet[] planets = gson.fromJson(new FileReader(fileName), Planet[].class);
            for (Planet planet : planets) {
                save(planet);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a Planet in memory
     *
     * @param entity the Planet entity to be saved
     */
    private void save(Planet entity) {
        entities.putIfAbsent(entity.getId(), entity);
    }

    /**
     * Returns the Planet having the given ID
     *
     * @param id the id of the Planet searched for
     * @return an Optional of a Planet
     */
    public Optional<Planet> get(int id) {
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * Returns all the existent Planet
     *
     * @return an Iterable of Planets
     */
    public Iterable<BaseEntity> getAll() {
        return entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }
}
