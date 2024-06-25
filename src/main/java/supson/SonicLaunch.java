package supson;

import supson.controller.api.GameController;
import supson.controller.impl.GameControllerImpl;

/**
 * This class is the entry point of the application.
 */
public final class SonicLaunch {

    private SonicLaunch() { }

    /**
     * This is the main method to start the game.
     * @param args the arguments
     */
    public static void main(final String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        final GameController gc = new GameControllerImpl();
        gc.initGame();
    }

}
