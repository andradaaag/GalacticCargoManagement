package test.controller;

import main.controller.Controller;
import main.domain.*;
import main.domain.Character;
import main.repository.CharacterJSONRepository;
import main.repository.PlanetJSONRepository;
import main.repository.ShipJSONRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTest {
    private Controller ctrl;

    private CharacterJSONRepository mockedCharacterRepo;
    private PlanetJSONRepository mockedPlanetRepo;
    private ShipJSONRepository mockedShipRepo;

    private Character character1, character2;
    private Planet planet;
    private Ship ship1;
    private Ship ship2;

    @Before
    public void setUp() throws Exception {
        //Mock the Character Repository
        mockedCharacterRepo = mock(CharacterJSONRepository.class);
        character1 = new Character(1, "Han Solo", Arrays.asList("C"));
        character2 = new Character(2, "Joda", new ArrayList<>());
        when(mockedCharacterRepo.getAll()).thenReturn(Arrays.asList(character1, character2));
        when(mockedCharacterRepo.get(1)).thenReturn(Optional.ofNullable(character1));
        when(mockedCharacterRepo.get(2)).thenReturn(Optional.ofNullable(character2));

        //Mock the Planet Repository
        mockedPlanetRepo = mock(PlanetJSONRepository.class);
        planet = new Planet(5, "Jakku", 30000);
        when(mockedPlanetRepo.getAll()).thenReturn(Arrays.asList(planet));
        when(mockedPlanetRepo.get(5)).thenReturn(Optional.ofNullable(planet));

        //Mock the Ship Repository
        mockedShipRepo = mock(ShipJSONRepository.class);
        ship1 = new Ship(1, 70000, "Star Destroyer", 750, "C");
        ship2 = new Ship(2, 25000, "Millenium Falcon", 1500, "C");
        when(mockedShipRepo.getAll()).thenReturn(Arrays.asList(ship1, ship2));

        //Create the controller
        ctrl = new Controller(mockedCharacterRepo, mockedPlanetRepo, mockedShipRepo);
    }

    @Test
    public void getCharacters() {
        Iterable<BaseEntity> characters = ctrl.getCharacters();
        Assert.assertEquals(characters.spliterator().getExactSizeIfKnown(), 2);
    }

    @Test
    public void getPlanets() {
        Iterable<BaseEntity> planets = ctrl.getPlanets();
        Assert.assertEquals(planets.spliterator().getExactSizeIfKnown(), 1);
    }

    @Test
    public void getShipsByType() {
        Collection<Ship> ships = ctrl.getShipsByType("C");
        Assert.assertEquals(ships.size(), 2);
    }

    @Test
    public void getShipsByCharacter() {
        Collection<Ship> ships1 = ctrl.getShipsByCharacter(character1);
        Assert.assertEquals(ships1.size(), 2);
        Collection<Ship> ships2 = ctrl.getShipsByCharacter(character2);
        Assert.assertEquals(ships2.size(), 0);
    }

    @Test
    public void getTripDetailsShouldWorkFineWhenCharacterCanDriveShips() {
        List<BaseEntity> tripDetails1 = ctrl.getTripDetails(1, 50000, 5);
        Assert.assertEquals(tripDetails1.size(), 2);
        TripDetails tripDetails = new TripDetails(2, "Millenium Falcon", 60, 2);
        Assert.assertEquals(tripDetails1.get(1), tripDetails);
    }

    @Test(expected = GCMException.class)
    public void getTripDetailsShouldRaiseExceptionWhenCharacterCanNotDriveAnyShip() {
        ctrl.getTripDetails(2, 50000, 5);
    }
}