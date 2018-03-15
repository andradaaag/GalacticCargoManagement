package test.repository;

import main.domain.Ship;
import main.repository.ShipJSONRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShipJSONRepositoryTest {
    private static ShipJSONRepository repo;

    @Before
    public void setUp() {
        repo = new ShipJSONRepository("./src/test/data/ships_test.json");
    }

    @Test
    public void getAll() {
        Iterable<Ship> ships = repo.getAll();
        Assert.assertEquals(ships.spliterator().getExactSizeIfKnown(), 5);
    }
}