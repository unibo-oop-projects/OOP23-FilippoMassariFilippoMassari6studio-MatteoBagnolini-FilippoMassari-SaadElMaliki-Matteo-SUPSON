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
import supson.model.block.api.CollectibleFactory;
import supson.model.block.impl.CollectibleFactoryImpl;
import supson.model.entity.impl.Enemy;
import supson.model.world.api.World;
import supson.model.world.api.WorldLoader;

public final class WorldLoaderImpl implements WorldLoader {

    private static final String EMPTY = "0";
    private final CollectibleFactory collectibleFactory;

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

    private void processLine(List<String> lines, int y, World world) {
        String[] tokens = lines.get(world.getMapWidth() - y).split(" ");
        IntStream.range(0, tokens.length)
                .filter(x -> !tokens[x].equals(EMPTY))
                .forEach(x -> processToken(tokens[x], x, y, world));
    }

    private void processToken(String token, int x, int y, World world) {
        int worldElement = Integer.parseInt(token);
        Pos2d pos = new Pos2dImpl(x, y);
        Optional.ofNullable(GameEntityType.getType(worldElement))
                .ifPresent(type -> addEntityToWorld(type, pos, world));
    }

    private void addEntityToWorld(final GameEntityType type, final Pos2d pos, World world) {
        if (type.equals(GameEntityType.ENEMY)) {
            world.addEnemy(new Enemy(pos));
        } else if (type.equals(GameEntityType.TERRAIN) 
                   || type.equals(GameEntityType.DAMAGE_TRAP) 
                   || type.equals(GameEntityType.SUBTERRAIN)) {
            world.addBlock(type, pos);
        } else {
            world.addCollectible(collectibleFactory.createCollectible(type, pos));
        }
    }
}
