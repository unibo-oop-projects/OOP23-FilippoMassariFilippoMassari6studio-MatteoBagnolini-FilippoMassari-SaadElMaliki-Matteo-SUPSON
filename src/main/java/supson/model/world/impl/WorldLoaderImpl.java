package supson.model.world.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.entity.api.block.collectible.CollectibleFactory;
import supson.model.entity.impl.block.collectible.CollectibleFactoryImpl;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.world.api.World;
import supson.model.world.api.WorldLoader;

/**
 * Implementation of the WorldLoader interface.
 * Loads the game world from a file.
 */
public final class WorldLoaderImpl implements WorldLoader {

    private static final String EMPTY = "0";
    private final CollectibleFactory collectibleFactory;

    /**
     * Constructs a new WorldLoaderImpl instance.
     */
    public WorldLoaderImpl() {
        this.collectibleFactory = new CollectibleFactoryImpl();
    }

    @Override
    public void loadWorld(final String filePath, final World world) {
        try (InputStream inputStream = getClass().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            Optional<Integer> mapWidth = Optional.of(lines.size() - 1);
            world.setMapWidth(mapWidth);
            IntStream.rangeClosed(0, mapWidth.get())
                    .map(y -> mapWidth.get() - y)
                    .forEach(y -> processLine(lines, y, world));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes a line from the file and adds corresponding entities to the world.
     *
     * @param lines the list of lines from the file
     * @param y the y-coordinate of the line
     * @param world the world to which entities are to be added
     */
    private void processLine(final List<String> lines, final int y, final World world) {
        String[] tokens = lines.get(world.getMapWidth() - y).split(" ");
        IntStream.range(0, tokens.length)
                .filter(x -> !tokens[x].equals(EMPTY))
                .forEach(x -> processToken(tokens[x], x, y, world));
    }

    /**
     * Processes a token from the line and adds the corresponding entity to the world.
     *
     * @param token the token representing an entity type
     * @param x the x-coordinate of the entity
     * @param y the y-coordinate of the entity
     * @param world the world to which the entity is to be added
     */
    private void processToken(final String token, final int x, final int y, final World world) {
        int worldElement = Integer.parseInt(token);
        Pos2d pos = new Pos2dImpl(x, y);
        Optional.ofNullable(GameEntityType.getType(worldElement))
                .ifPresent(type -> addEntityToWorld(type, pos, world));
    }

    /**
     * Adds an entity of the specified type to the world at the given position.
     *
     * @param type the type of the entity
     * @param pos the position of the entity
     * @param world the world to which the entity is to be added
     */
    private void addEntityToWorld(final GameEntityType type, final Pos2d pos, final World world) {
        if (type.equals(GameEntityType.ENEMY)) {
            world.addEnemy(new Enemy(pos));
        } else if (type.equals(GameEntityType.TERRAIN) 
                   || type.equals(GameEntityType.DAMAGE_TRAP) 
                   || type.equals(GameEntityType.SUBTERRAIN)
                   || type.equals(GameEntityType.FINISHLINE)) {
            world.addBlock(type, pos);
        } else {
            world.addCollectible(collectibleFactory.createCollectible(type, pos));
        }
    }
}
