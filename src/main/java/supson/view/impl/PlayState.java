package supson.view.impl;

import supson.model.world.api.World;
import supson.view.api.GameState;
import supson.view.api.GameStateManager;
import supson.view.api.GameView;

public class PlayState implements GameState{
    GameView view;
    World world;
    InputManager input;
    GameStateManager stateManager;


    public PlayState(GameStateManager s, World m, InputManager i){
        view = new GameViewImpl();
        world = m;
        input = i;
        stateManager = s;
    }

    @Override
    public void enter() {
        view.initView();
        view.getViewComponent().addKeyListener(input);
        this.render();
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
