// 206573289 Omri Levi


package game.levels.backgrounds;

import biuoop.DrawSurface;
import game.gameFunction.environment.Sprite;

import java.awt.Color;

/**
 * The background of the level Landscape of type Sprite.
 */
public class LandscapeBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        // sky
        Color skyColor = new Color(135, 206, 250); // Sky Blue
        d.setColor(skyColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // grass
        Color groundColor = new Color(0, 175, 0); // Green
        d.setColor(groundColor);
        d.fillRectangle(0, 450, d.getWidth(), 150);

        // trees
        Color trunkColor = new Color(139, 69, 19); // Brown
        d.setColor(trunkColor);
        d.fillRectangle(50, 300, 30, 150); // Trunk of the tree on the left
        d.fillRectangle(730, 250, 35, 200); // Trunk of the tree on the right

        // leaves
        Color leavesColor = new Color(34, 139, 34);
        d.setColor(leavesColor);
        d.fillCircle(40, 300, 40);      // First circle of leaves on the left tree
        d.fillCircle(70, 250, 40);      // Second circle of leaves on the left tree
        d.fillCircle(100, 300, 40);     // Third circle of leaves on the left tree

        d.fillCircle(705, 250, 50);     // First circle of leaves on the right tree
        d.fillCircle(740, 200, 50);     // Second circle of leaves on the right tree
        d.fillCircle(785, 250, 50);     // Third circle of leaves on the right tree

        // sun
        d.setColor(Color.YELLOW);
        d.fillCircle(700, 100, 25);
        d.setColor(new Color(255, 255, 170));
        d.fillCircle(700, 100, 20);
    }
}
