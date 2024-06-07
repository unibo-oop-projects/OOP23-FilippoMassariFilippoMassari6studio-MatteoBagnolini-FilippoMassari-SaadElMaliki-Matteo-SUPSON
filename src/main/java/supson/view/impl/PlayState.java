package supson.view.impl;

import javax.swing.JFrame;

import supson.model.world.api.World;
import supson.view.api.GameState;
import supson.view.api.GameStateManager;
import supson.view.api.GameView;

public class PlayState implements GameState{
    GameView view;
    World world;
    InputManager input;
    GameStateManager stateManager;


    public PlayState(JFrame gameFrame, World world, InputManager input){
        this.world = world;
        gameFrame.addKeyListener(input);
        view = new GameViewImpl(gameFrame);
    }

    @Override
    public void enter() {
        view.initView();
        
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }

    @Override
    public void render() {
        view.renderView(world.getGameEntities(),world.getPlayer(), world.getHud());
    }

}
