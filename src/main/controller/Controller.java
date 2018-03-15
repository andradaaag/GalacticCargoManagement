package main.controller;

import main.domain.*;
import main.domain.Character;
import main.repository.CharacterJSONRepository;
import main.repository.PlanetJSONRepository;
import main.repository.ShipJSONRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Math.ceil;

public class Controller {
    private CharacterJSONRepository characterRepo;
    private PlanetJSONRepository planetRepo;
    private ShipJSONRepository shipRepo;

    public Controller(CharacterJSONRepository characterRepo, PlanetJSONRepository planetRepo, ShipJSONRepository shipRepo) {
        this.characterRepo = characterRepo;
        this.planetRepo = planetRepo;
        this.shipRepo = shipRepo;
    }

    /***
     * Returns all the characters
     * @return an Iterable of Characters
     */
    public Iterable<BaseEntity> getCharacters() {
        return characterRepo.getAll();
    }

    /**
     * Returns all the planets
     *
     * @return an Iterable of Planets
     */
    public Iterable<BaseEntity> getPlanets() {
        return planetRepo.getAll();
    }

    /***
     * Returns all the ships which have the specified type
     * @param type the specified type for the ships
     * @return a Collection of Ships
     */
    public Collection<Ship> getShipsByType(String type) {
        return StreamSupport.stream(shipRepo.getAll().spliterator(), false)
                .filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    /***
     * Returns all the ships which can be driven by the specified character
     * @param character the given character
     * @return a Collection of Ships
     */
    public Collection<Ship> getShipsByCharacter(Character character) {
        List<Ship> ships = new ArrayList<>();
        List<String> shipTypes = character.getShipsType();
        for (String shipType : shipTypes) {
            ships.addAll(getShipsByType(shipType));
        }
        return ships;
    }

    /**
     * Return the list of trip details regarding each ship which can transport the specified cargo
     *
     * @param characterID the ID of the character who was chosen
     * @param cargoWeight the weight of the cargo to be transported
     * @param planetID    the ID of the destination planet
     * @return a List of TripDetails
     */
    public List<BaseEntity> getTripDetails(int characterID, long cargoWeight, int planetID) {
        if (cargoWeight < 0) {
            throw new GCMException("\nCargo weight should be a positive value.");
        }
        Character character = characterRepo.get(characterID).orElseThrow(() -> new GCMException("\nThere is no character with this ID."));
        Planet planet = planetRepo.get(planetID).orElseThrow(() -> new GCMException("\nThere is no planet with this ID."));

        List<TripDetails> tripDetailsList = new ArrayList<>();
        for (Ship ship : getShipsByCharacter(character)) {
            int noOfTrips = (int) ceil((double) cargoWeight / ship.getMaxCargoWeight());
            int hours = (int) planet.getDistance() / ship.getSpeed();
            if (noOfTrips > 1) {
                hours = hours * (noOfTrips * 2 - 1);
            }
            tripDetailsList.add(new TripDetails(ship.getId(), ship.getName(), hours, noOfTrips));
        }
        if (tripDetailsList.isEmpty()) {
            throw new GCMException("\nThis character cannot drive any ship.");
        }
        Collections.sort(tripDetailsList);

        return new ArrayList<>(tripDetailsList);
    }
}
