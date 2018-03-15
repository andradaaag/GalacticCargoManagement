package main.ui;

import main.controller.Controller;
import main.domain.*;

import java.util.Scanner;

import static main.util.NullStringUtil.checkIfNullInput;

public class Console {
    private Controller ctrl;
    private boolean keepAlive = true;

    public Console(Controller ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Prints the given entities also displaying the prompt
     *
     * @param prompt   the message to be displayed before printing the entities
     * @param entities the elements to be printed
     */
    private void printEntities(String prompt, Iterable<BaseEntity> entities) {
        System.out.println(prompt);
        entities.forEach(x -> System.out.println("\t" + x.toString()));
    }

    /**
     * Pauses the execution of the program letting the user decide whether he wants to continue
     */
    private void pressEnterKeyToContinue() {
        System.out.println("\nPress Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an input displaying the given prompt
     *
     * @param prompt the message to be displayed when reading the input
     * @return the String read
     */
    private String readStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        String input = scanner.nextLine();
        return checkIfNullInput(input, "\nInput cannot be null.");
    }

    /**
     * Runs the program
     */
    public void runConsole() {
        while (keepAlive) {
            try {
                printEntities("\nWhich character would you like to use?", ctrl.getCharacters());
                int characterID = Integer.parseInt(readStringInput("\nEnter the character option(only the ID): "));
                long cargoWeight = Long.parseLong(readStringInput("\nEnter the cargo weight(KG): "));
                printEntities("\nWhat is the destination planet?", ctrl.getPlanets());
                int planetID = Integer.parseInt(readStringInput("\nEnter the destination planet option(only the ID): "));

                printEntities("\nThese are the ships options: ", ctrl.getTripDetails(characterID, cargoWeight, planetID));
                pressEnterKeyToContinue();
            } catch (GCMException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\nInput should be a natural number.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
