package supson.view.impl;

import java.util.List;

import javax.swing.JFrame;

import supson.core.api.GameEngine;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.hud.api.Hud;
import supson.view.api.EndGameView;
import supson.view.api.GameView;
import supson.view.api.MenuView;

public class MainView {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final JFrame mainFrame;
    private final MenuView menuView;
    private final GameView gameView;
    private final EndGameView endGameView;

    public MainView() {
        this.mainFrame = new JFrame("SUPER-SONIC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WIDTH, HEIGHT);
        this.mainFrame.setResizable(false);
        this.menuView = new MenuViewImpl(this.mainFrame);
        this.gameView = new GameViewImpl(this.mainFrame);
        this.endGameView = new EndGameViewImpl(this.mainFrame);
    }
    
    public void showMenu(){
        this.menuView.initView();
        this.menuView.renderView();
    }

    public void showGameView() {
        gameView.initView();
    }

    public void renderGameView(final List<GameEntity> gameEntitiesList, final Player player, final Hud hud) {
        this.gameView.renderView(gameEntitiesList, player, hud);
    }

    public void showEndGame(){
        endGameView.initView();
        endGameView.renderView();
    }

    public void addInputManager(InputManager input){
        this.mainFrame.addKeyListener(input);
    }
}
