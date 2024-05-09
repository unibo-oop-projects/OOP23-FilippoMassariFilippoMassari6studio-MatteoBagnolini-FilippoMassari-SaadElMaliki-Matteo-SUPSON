package supson.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import supson.common.api.Pos2d;
import supson.controller.api.GameController;
import supson.model.entity.impl.Enemy;
import supson.model.hitbox.impl.CollisionResolver;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;
import supson.model.block.api.Collectible;
import supson.model.entity.api.MoveableEntity;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public final class GameControllerImpl implements GameController {

    private static final String WORLD_FILE_PATH = "src\\resources\\world.txt";

    private final World model;
    private final GameView view;

    /**
     * This is the GameControllerImpl constructor.
     */
    public GameControllerImpl() {
        this.model = new WorldImpl();
        this.view = new GameViewImpl();
    }


    @Override
    public void processInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processInput'");
    }

    @Override
    public void update(final long elapsed) {
        final List<MoveableEntity> movEntities = List.copyOf(model.getEnemies());
        movEntities.add(model.getPlayer());

        movEntities.stream()
        .forEach(e -> {
            Pos2d oldPos = e.getPosition();
            e.move(elapsed);
            CollisionResolver.resolvePlatformCollisions(e, model.getBlocks(), oldPos);
        });

        final List<Enemy> killed = CollisionResolver.resolveEnemiesCollisions(model.getPlayer(), model.getEnemies());
        killed.forEach(k -> model.removeEnemy(k));

        final List<Collectible> activated = CollisionResolver.resolveCollectibleCollisions(model.getPlayer(),
            model.getBlocks().stream().filter(k -> k instanceof Collectible).map(Collectible.class::cast)
            .collect(Collectors.toList()));
        activated.forEach(k -> model.removeBlock(k));
    }

    @Override
    public void render() {
        this.view.renderGame(model.getBlocks(), model.getEnemies(), model.getPlayer());
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.renderStartGame();
    }

}
