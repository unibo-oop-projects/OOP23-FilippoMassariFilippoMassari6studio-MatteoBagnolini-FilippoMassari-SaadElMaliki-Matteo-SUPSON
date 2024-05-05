package supson.model.hitbox.impl;

import java.util.List;

import supson.common.api.Pos2d;
import supson.model.block.api.BlockEntity;
import supson.model.block.api.Collectible;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.impl.Player;

/**
 * This class is an utility class which act as collision resolver. It is used to check
 * and resolve collisions in the game.
 */
public final class CollisionResolver {

    private static final int RENDER_DISTANCE = 50;

    /**
     * The constructor of this class is final and empty, ensuring it cannot
     * be instantiated.
     */
    private CollisionResolver() { }

    /**
     * This method resolves collisions between a moveable entity and the platform blocks.
     * It modifies the position of the entity in order to avoid the hitbox of the entity
     * and the hitbox of the colliding block to overlap. This create the effect of "solid"
     * blocks.
     * @param entity the entity that is moving
     * @param blocks the list of blocks in the level
     * @param startingPos the initial position of the entity, before it has move
     */
    public static void resolvePlatformCollisions(final MoveableEntity entity,
        final List<BlockEntity> blocks, final Pos2d startingPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolvePlatformCollisions'");
    }

    /**
     * This method resolves collisions between the player and the enemies.
     * @param player the player
     * @param enemies the list of enemies in the level
     */
    public static void resolveEnemiesCollisions(final Player player, final List<MoveableEntity> enemies) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveEnemiesCollisions'");
    }

    /**
     * This method resolves collisions between the player and the collectible entities.
     * @param player the player
     * @param collectibles the list of collectible entities
     */
    public static void resolveCollectibleCollisions(final Player player, final List<Collectible> collectibles) {
        collectibles.stream()
        .filter(collectible -> collectible.getPosition().getdistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(collectible -> collectible.getHitbox().isCollidingWith(player.getHitbox()))
        .forEach(collectible -> collectible.collect(player));
        // bisogna gestire l'eliminazione dei collezionabili che si rimuovono (anelli)
    }

}
