package supson.controller.impl;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;
import supson.view.impl.InputManager;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public final class GameControllerImpl implements GameController {

    private static final String WORLD_FILE_PATH = "/world.txt";

    private final World model;
    private final GameView view;
    private final InputManager input;

    /**
     * This is the GameControllerImpl constructor.
     */
    public GameControllerImpl() {
        this.model = new WorldImpl();
        this.view = new GameViewImpl();
        this.input = new InputManager();
    }


    @Override
    public void processInput() {
        this.model.getPlayer().setMoveRight(input.isRight());
        this.model.getPlayer().setMoveLeft(input.isLeft());
    }

    @Override
    public void update(final long elapsed) {
        this.model.updateGame(elapsed);
    }

    @Override
    public void render() {
        this.view.renderView(this.model.getGameEntities(), this.model.getPlayer(), this.model.getHud());
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.initView();
        this.view.getViewComponent().addKeyListener(input);
    }

}
