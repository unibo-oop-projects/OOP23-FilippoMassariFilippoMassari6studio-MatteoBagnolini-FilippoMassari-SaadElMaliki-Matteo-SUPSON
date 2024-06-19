package supson.model.collisions.api;

import java.util.List;

import supson.common.api.Pos2d;
import supson.model.entity.api.block.TagBlockEntity;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.api.block.trap.Trap;
import supson.model.entity.api.moveable.MoveableEntity;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.entity.impl.moveable.player.Player;

/**
 * This interface represent a collision manager. It is used to manage the collisions
 * occuring in the game.
 */
public interface CollisionManager {

    /**
     * This method resolves collisions between a moveable entity and the platform blocks.
     * It modifies the position of the entity in order to avoid the hitbox of the entity
     * and the hitbox of the colliding block to overlap. This create the effect of "solid"
     * blocks.
     * @param entity the entity that is moving
     * @param blocks the list of blocks in the level
     * @param startingPos the initial position of the entity, before it has move
     */
    void resolvePlatformCollisions(MoveableEntity entity, List<TagBlockEntity> blocks, Pos2d startingPos);

    /**
     * This method resolves collisions between the player and the enemies.
     * @param player the player
     * @param enemies the list of enemies in the level
     * @return the list of enemy killed
     */
    List<Enemy> resolveEnemiesCollisions(Player player, List<Enemy> enemies);

    /**
     * This method resolve collisions between the player and the traps.
     * @param player the player
     * @param traps a list containing the traps in the level
     */
    void resolveTrapCollisions(Player player, List<Trap> traps);

    /**
     * This method resolves collisions between the player and the collectible entities.
     * @param player the player
     * @param collectibles the list of collectible entities
     * @return a list of collectible that have been collected and have to be removed
     */
    List<Collectible> resolveCollectibleCollisions(Player player, List<Collectible> collectibles);

}
