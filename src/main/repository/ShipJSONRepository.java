package main.repository;

import com.google.gson.Gson;
import main.domain.Ship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ShipJSONRepository {
    private Map<Integer, Ship> entities = new HashMap<>();

    public ShipJSONRepository(String fileName) {
        loadData(fileName);
    }

    /**
     * Loads the data from a JSON file and saves the Ships in memory
     *
     * @param fileName the name of the JSON file
     */
    private void loadData(String fileName) {
        Gson gson = new Gson();
        try {
            Ship[] ships = gson.fromJson(new FileReader(fileName), Ship[].class);
            for (Ship ship : ships) {
                save(ship);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a Ship in memory
     *
     * @param entity the Ship entity to be saved
     */
    private void save(Ship entity) {
        entities.putIfAbsent(entity.getId(), entity);
    }

    /**
     * Returns all the existent Ships
     *
     * @return an Iterable of Ships
     */
    public Iterable<Ship> getAll() {
        return entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }
}
