package supson.view.impl;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import supson.core.impl.GameEngineImpl;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.hud.api.Hud;
import supson.view.ViewEvent;
import supson.view.api.end.EndGameView;
import supson.view.api.game.GameView;
import supson.view.api.start.MenuView;
import supson.view.impl.common.InputManager;
import supson.view.impl.end.EndGameViewImpl;
import supson.view.impl.game.GameViewImpl;
import supson.view.impl.start.MenuViewImpl;

/**
 * Represents the main view of the game, managing different the different menu, game, and end game views.
 */
public class MainView {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final JFrame mainFrame;
    private final MenuView menuView;
    private final GameView gameView;
    private final EndGameView endGameView;

    /**
     * Contructs a new MainView with the specified GameEngine.
     * 
     * @param ge the GameEngine istance to notify of view events.
     */
    public MainView(final GameEngineImpl ge) {
        this.mainFrame = new JFrame("SUPER-SONIC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WIDTH, HEIGHT);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setResizable(false);

        final ActionListener listener = e -> {
            switch (e.getActionCommand()) {
                case "Play" -> ge.onNotifyFromView(ViewEvent.START_GAME);
                case "Quit" -> ge.onNotifyFromView(ViewEvent.EXIT);
                case "Retry" -> ge.onNotifyFromView(ViewEvent.RESTART);
                case "Menu" -> ge.onNotifyFromView(ViewEvent.MENU);
                default -> { }
            }
        };

        this.menuView = new MenuViewImpl(this.mainFrame, listener);
        this.gameView = new GameViewImpl(this.mainFrame);
        this.endGameView = new EndGameViewImpl(this.mainFrame, listener);
    }

    /**
     * Displays the menu view.
     */
    public void showMenu() {
        this.resetView();
        this.menuView.initView();
        this.menuView.renderView();
    }

    /**
     * Displays the game view.
    */
    public void showGameView() {
        this.resetView();
        this.gameView.initView();
        this.mainFrame.requestFocus();
    }

    /**
     * Renders the game view while playing.
     * 
     * @param gameEntitiesList the list of game entities to render
     * @param player the player object to render
     * @param hud the hud object to render
     */
    public void renderGameView(final List<GameEntity> gameEntitiesList, final Player player, final Hud hud) {
        this.gameView.renderView(gameEntitiesList, player, hud);
    }

    /**
     * Displays the end game view.
     * 
     * @param score the final score
     * @param time the final time
     * @param isWon wether the game was won or lost
     */
    public void showEndGame(final int score, final double time, final boolean isWon) {
        this.resetView();
        endGameView.initView();
        endGameView.renderView(score, time, isWon);
    }

    /**
     * Adds input manager to the main frame.
     * 
     * @param input the input manager to be added
     */
    public void addInputManager(final InputManager input) {
        this.mainFrame.addKeyListener(input);
    }

    /**
     * Clears the main view.
     */
    public void resetView() {
        mainFrame.getContentPane().removeAll();
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
