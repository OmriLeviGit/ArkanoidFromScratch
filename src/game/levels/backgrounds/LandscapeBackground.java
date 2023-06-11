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
        Color skyColor = new Color(135, 206, 250);
        d.setColor(skyColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // grass
        Color groundColor = new Color(0, 175, 0);
        d.setColor(groundColor);
        d.fillRectangle(0, 500, d.getWidth(), 150);

        // left tree
        Color trunkColor = new Color(139, 69, 19);
        Color leavesColor = new Color(34, 139, 34);
        d.setColor(trunkColor);
        d.fillRectangle(50, 350, 30, 150); // trunk of the tree on the left
        d.setColor(leavesColor);
        d.fillCircle(40, 350, 40);      // first circle of leaves on the left tree
        d.fillCircle(70, 300, 40);      // second circle of leaves on the left tree
        d.fillCircle(100, 350, 40);     // third circle of leaves on the left tree

        // left right
        d.setColor(trunkColor);
        d.fillRectangle(730, 300, 35, 200); // trunk of the tree on the right
        d.setColor(leavesColor);
        d.fillCircle(705, 300, 50);     // first circle of leaves on the right tree
        d.fillCircle(740, 250, 50);     // second circle of leaves on the right tree
        d.fillCircle(785, 300, 50);     // third circle of leaves on the right tree

        // birds
        d.setColor(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            int birdX = 500 + i * 15;
            int birdY = 300 + i * 10;

            d.drawLine(birdX, birdY, birdX - 10 + i, birdY - 5 + i);     // left wing
            d.drawLine(birdX, birdY, birdX + 10 - i, birdY - 5 + i);     // right wing
        }

        // cloud 1
        d.setColor(new Color(220, 220, 220));
        d.fillOval(100, 110, 70, 30);
        d.fillOval(130, 100, 70, 30);

        // cloud 2
        d.fillOval(450, 75, 70, 30);
        d.fillOval(480, 85, 70, 30);

        // sun
        d.setColor(new Color(230, 230, 150));
        d.fillCircle(700, 100, 25);
        d.setColor(Color.YELLOW);
        d.fillCircle(700, 100, 20);
        d.setColor(new Color(255, 255, 170));
        d.fillCircle(700, 100, 15);
    }
}
