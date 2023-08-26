// 206573289 Omri Levi


import biuoop.GUI;
import game.animationAndScreens.AnimationRunner;
import game.levels.gameFunction.GameFlow;
import game.levels.gameFunction.GameLevel;
import game.levels.Bullseye;
import game.levels.LevelInformation;
import game.levels.Prism;
import game.levels.Landscape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * This class is the main entry point for the game.
 * <p>
 * It creates an instance of the Game class, initializes it and starts its execution.
 * </p>
 */
public class Main {

    /**
     * The main method creates an instance of the game, initializes it and starts its execution.
     *
     * @param args specific levels can be accessed via args. if no args were specified, uses the default order.
     */
    public static void main(String[] args) {
        final int numOfFrames = 60;

        // list of all available levels
        List<LevelInformation> listOfLevels = Arrays.asList(new Bullseye(), new Landscape(), new Prism());

        if (args.length > 0) {  // if the order of levels is received as an input from the user
            List<Integer> newOrder = new ArrayList<>();                     // the order of the levels
            List<LevelInformation> newOrderOfLevels = new ArrayList<>();    // level info sorted by the new order
            String range = "[1-" + listOfLevels.size() + "]";               // valid range of levels

            // create a new list of levels with the order that was received as an argument
            for (String arg : args) {
                Pattern pattern = Pattern.compile(range);
                Matcher matcher = pattern.matcher(arg);

                if (matcher.matches()) {        // if the argument is a number in the valid range, add it to the list
                    int currentLevel = Integer.parseInt(arg);
                    newOrder.add(currentLevel);

                    newOrderOfLevels.add(listOfLevels.get(currentLevel - 1));
                }
            }

            if (newOrder.isEmpty()) {
                System.out.println("no valid levels");
                return;
            }
            System.out.println("the game will play with the levels: " + newOrder);

            listOfLevels = newOrderOfLevels;        // replace the default order with a new one from the user
        }

        GUI gui = new GUI("Game", GameLevel.WIDTH, GameLevel.HEIGHT);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, numOfFrames), gui.getKeyboardSensor());
        gameFlow.runLevels(listOfLevels);
        gui.close();
    }
}
