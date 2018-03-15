package test.repository;

import main.domain.BaseEntity;
import main.domain.Planet;
import main.repository.PlanetJSONRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PlanetJSONRepositoryTest {
    private static PlanetJSONRepository repo;
    private static Planet testPlanet;


    @Before
    public void setUp() {
        repo = new PlanetJSONRepository("./src/test/data/planets_test.json");
        testPlanet = new Planet(5, "Jakku", 30000);
    }

    @Test
    public void get() {
        Optional<Planet> planetOptional = repo.get(5);
        Assert.assertTrue(planetOptional.isPresent());
        Planet planet = planetOptional.get();
        Assert.assertEquals(planet, testPlanet);
    }

    @Test
    public void getAll() {
        Iterable<BaseEntity> characters = repo.getAll();
        Assert.assertEquals(characters.spliterator().getExactSizeIfKnown(), 6);
    }
}