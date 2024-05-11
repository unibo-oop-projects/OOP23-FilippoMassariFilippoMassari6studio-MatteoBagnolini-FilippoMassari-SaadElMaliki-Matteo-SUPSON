package supson.model.hitbox.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;
import supson.model.block.api.BlockEntity;
import supson.model.block.api.Collectible;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.model.hitbox.api.Hitbox;

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
     * @param list the list of blocks in the level
     * @param startingPos the initial position of the entity, before it has move
     */
    public static void resolvePlatformCollisions(final MoveableEntity entity,
            final List<BlockEntity> list, final Pos2d startingPos) {

        final Pos2d actualPos = entity.getPosition();

        final List<BlockEntity> collidingBlocks = getCollidingBlocks(entity, list);

        if (!collidingBlocks.isEmpty()) {

            entity.setPosition(new Pos2dImpl(actualPos.x(), startingPos.y()));

            final List<BlockEntity> collidingOrizontalBlocks = getCollidingBlocks(entity, collidingBlocks);

            if (!collidingOrizontalBlocks.isEmpty()) {

                adjustOrizontalPos(entity, collidingOrizontalBlocks.get(0));

            }

            entity.setPosition(new Pos2dImpl(entity.getPosition().x(), actualPos.y()));

            final List<BlockEntity> collidingVerticalBlocks = getCollidingBlocks(entity, collidingBlocks);

            if (!collidingVerticalBlocks.isEmpty()) {

                adjustVerticalPos(entity, collidingOrizontalBlocks.get(0));   //possiamo prendere per semplicità il primo blocco

            }

        }

    }

    /**
     * This method resolves collisions between the player and the enemies.
     * @param player the player
     * @param enemies the list of enemies in the level
     * @return the list of enemy killed
     */
    public static List<Enemy> resolveEnemiesCollisions(final Player player, final List<Enemy> enemies) {
        Hitbox playerHitbox = player.getHitbox();
        boolean playerInvulnerable = player.getIsJumping();
        List<Enemy> killed = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (playerHitbox.isCollidingWith(enemy.getHitbox())) {
                if (playerInvulnerable) {
                    killed.add(enemy);
                } else {
                    player.setLife(player.getLife()-1);
                }
            }
        }
        return killed;
    }

    /**
     * This method resolves collisions between the player and the collectible entities.
     * @param player the player
     * @param collectibles the list of collectible entities
     * @return a list of collectible that have been collected and have to be removed
     */
    public static List<Collectible> resolveCollectibleCollisions(final Player player, final List<Collectible> collectibles) {
        return collectibles.stream()
        .filter(collectible -> collectible.getPosition().getdistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(collectible -> collectible.getHitbox().isCollidingWith(player.getHitbox()))
        .peek(collectible -> collectible.collect(player))
        .collect(Collectors.toList());
    }

    private static List<BlockEntity> getCollidingBlocks(final MoveableEntity entity, final List<BlockEntity> collidingBlocks) {
        return collidingBlocks.stream()
        .filter(b -> b.getPosition().getdistance(entity.getPosition()) <= RENDER_DISTANCE)
        .filter(b -> b.getGameEntityType().equals(GameEntityType.TERRAIN))
        .filter(b -> b.getHitbox().isCollidingWith(entity.getHitbox()))
        .collect(Collectors.toList());
    }

    private static void adjustOrizontalPos(final MoveableEntity entity, final BlockEntity block) {
        final double newXPos;
        if (entity.getPosition().x() < block.getPosition().x()) {     //contatto da destra
            newXPos = entity.getPosition().x()
                + block.getHitbox().getLLCorner().x() - entity.getHitbox().getURCorner().x();
        } else {                                                    //contatto da sinistra
            newXPos = entity.getPosition().x()
                - block.getHitbox().getURCorner().x() + entity.getHitbox().getLLCorner().x();
        }
        entity.setPosition(new Pos2dImpl(newXPos, entity.getPosition().y()));
    }

    private static void adjustVerticalPos(final MoveableEntity entity, final BlockEntity block) {
        final double newYPos;
        if (entity.getPosition().y() > block.getPosition().y()) {     //contatto da sopra 
            newYPos = entity.getPosition().y()
                + block.getHitbox().getURCorner().y() - entity.getHitbox().getLLCorner().y();
        } else {                                                    //contatto da sotto
            newYPos = entity.getPosition().y()
                + block.getHitbox().getLLCorner().x() - entity.getHitbox().getURCorner().x();
            entity.setVelocity(new Vect2dImpl(entity.getVelocity().x(), 0));        //velY è 0 perchè tocca il soffitto
        }
        entity.setPosition(new Pos2dImpl(entity.getPosition().x(), newYPos));
    }

}
