package supson.controller.impl;

import javax.swing.JFrame;

import supson.controller.api.GameState;
import supson.model.world.api.World;
import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;

public class PlayState implements GameState{
    JFrame gameFrame;
    GameView view;
    World world;


    public PlayState(JFrame gameFrame, World world){
        this.world = world;
        this.gameFrame = gameFrame;
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
