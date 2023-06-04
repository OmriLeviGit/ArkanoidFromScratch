package game.gameFunction;

class GameTest {
    GameLevel game = new GameLevel();

/*
    @Test
    public void testGame1() {
        initializeTestWithoutBallAndPaddle(game);

        // initialize an array of balls, the paddle is used to set the y coordinate
        Point start = new Point(255.86772038027058, 163.6202846837666);
        Velocity v = new Velocity(-8.01950746617272, -4.630064794363035);

        Ball newBall = new Ball(start, 5, Color.white);
        newBall.setGameEnvironment(game.getEnvironment());
        newBall.setVelocity(v);
        newBall.addToGame(game);

        game.run();
    }

    @Test
    public void testBallAtPaddleHeightAndParallel() {
        initializeTestWithoutBall(game);

        // initialize an array of balls, the paddle is used to set the y coordinate

        double ballX = (double) Game.WIDTH / 2;                                 // spawn at the center
        double ballY = Game.HEIGHT - ( + Game.BORDER_THICKNESS + 10);      // spawn above the paddle

        Point start = new Point(ballX, ballY);
        Velocity v = new Velocity(5, 0);

        Ball newBall = new Ball(start, 5, Color.white);
        newBall.setGameEnvironment(game.getEnvironment());
        newBall.setVelocity(v);
        newBall.addToGame(game);

        game.run();
    }

    @Test
    public void testGame3() {
        initializeTestWithoutBall(game);

        // initialize an array of balls, the paddle is used to set the y coordinate
        Point start = new Point(775.3350295008578, 525.2702443930356);
        Velocity v = new Velocity(7.068028809364036, -4.0807283357263575);

        Ball newBall = new Ball(start, 5, Color.white);
        newBall.setGameEnvironment(game.getEnvironment());
        newBall.setVelocity(v);
        newBall.addToGame(game);

        game.run();
    }
    
 */



//    public void initializeTestWithoutBallAndPaddle(Game game) {
//        Initializer initialize = new Initializer();             // create an initializer
//        initialize.setGame(this.game);                          // add a reference to the game
//
//        initialize.background(Color.BLACK);
//        initialize.borders();
//        initialize.gameBlocks(new BlockRemover(this.game, new Counter()), new Counter());
//    }
//
//    public void initializeTestWithoutBall(Game game) {
//        Initializer initialize = new Initializer();             // create an initializer
//        initialize.setGame(this.game);                          // add a reference to the game
//
//        initialize.background(Color.BLACK);
//        initialize.borders();
//        initialize.paddle(90, 20, this.game.getGui().getKeyboardSensor());
//        initialize.gameBlocks(new BlockRemover(this.game, new Counter()), new Counter());
//    }
}