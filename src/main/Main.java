package main;

import main.controller.*;
import main.repository.CharacterJSONRepository;
import main.repository.PlanetJSONRepository;
import main.repository.ShipJSONRepository;
import main.ui.Console;

public class Main {

    public static void main(String[] args) {
        CharacterJSONRepository characterJSONRepository = new CharacterJSONRepository("./data/characters.json");
        PlanetJSONRepository planetJSONRepository = new PlanetJSONRepository("./data/planets.json");
        ShipJSONRepository shipJSONRepository = new ShipJSONRepository("./data/ships.json");

        Controller ctrl = new Controller(characterJSONRepository, planetJSONRepository, shipJSONRepository);

        Console console = new Console(ctrl);
        console.runConsole();
    }
}
