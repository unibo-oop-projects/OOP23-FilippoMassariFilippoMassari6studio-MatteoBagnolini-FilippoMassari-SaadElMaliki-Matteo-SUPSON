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

public class MainView {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final JFrame mainFrame;
    private final MenuView menuView;
    private final GameView gameView;
    private final EndGameView endGameView;

    public MainView(GameEngineImpl ge) {
        this.mainFrame = new JFrame("SUPER-SONIC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WIDTH, HEIGHT);
        this.mainFrame.setResizable(false);

        ActionListener listener = e -> {
            switch (e.getActionCommand()) {
                case "Play" -> ge.onNotifyFromView(ViewEvent.START_GAME);
                case "Quit" -> ge.onNotifyFromView(ViewEvent.EXIT);
                case "Retry" -> ge.onNotifyFromView(ViewEvent.RESTART);
                case "Menu" -> ge.onNotifyFromView(ViewEvent.MENU);
            }
        };

        this.menuView = new MenuViewImpl(this.mainFrame, listener);
        this.gameView = new GameViewImpl(this.mainFrame);
        this.endGameView = new EndGameViewImpl(this.mainFrame, listener);
    }
    
    public void showMenu(){
        this.resetView();
        this.menuView.initView();
        this.menuView.renderView();
    }

    public void showGameView() {
        this.resetView();
        this.gameView.initView();
        this.mainFrame.requestFocus();
    }

    public void renderGameView(final List<GameEntity> gameEntitiesList, final Player player, final Hud hud) {
        this.gameView.renderView(gameEntitiesList, player, hud);
    }

    public void showEndGame(int score, double time, boolean isWon){
        this.resetView();
        endGameView.initView();
        endGameView.renderView(score, time, isWon);
    }

    public void addInputManager(InputManager input){
        this.mainFrame.addKeyListener(input);
    }

    public void resetView(){
        mainFrame.getContentPane().removeAll();
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
