package supson.model.hitbox.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import supson.common.GameEntityType;
import supson.common.api.Observable;
import supson.common.api.Observer;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.block.api.BlockEntity;
import supson.model.block.api.Collectible;
import supson.model.block.api.Trap;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.player.Player;
import supson.model.hitbox.api.Hitbox;

import java.util.logging.Logger;

/**
 * This class is a collision resolver. It is used to check
 * and resolve collisions in the game.
 */
public final class CollisionResolver implements Observable {

    private static final Logger LOGGER = Logger.getLogger("Collision");

    private static final int RENDER_DISTANCE = 5;
    private static final double DELTA = 0.000_001;

    private final List<Observer> observers;

    /**
     * The constructor of this class is final and empty, ensuring it cannot
     * be instantiated.
     */
    public CollisionResolver() {
        this.observers = new ArrayList<>();
     }

    /**
     * This method resolves collisions between a moveable entity and the platform blocks.
     * It modifies the position of the entity in order to avoid the hitbox of the entity
     * and the hitbox of the colliding block to overlap. This create the effect of "solid"
     * blocks.
     * @param entity the entity that is moving
     * @param list the list of blocks in the level
     * @param startingPos the initial position of the entity, before it has move
     */
    public void resolvePlatformCollisions(final MoveableEntity entity,
            final List<BlockEntity> list, final Pos2d startingPos) {
        final Pos2d updatedPos = entity.getPosition();
        double newX = updatedPos.x();
        double newY = updatedPos.y();
        final List<BlockEntity> collidingBlocks = getCollidingBlocks(entity, list);
        if (!collidingBlocks.isEmpty()) {

            //DEBUG---------------
            if (entity instanceof Player) {
                //LOGGER.info(" ENTRY player pos: " + entity.getPosition());
            }
            //DEBUG---------------

            entity.setPosition(new Pos2dImpl(startingPos.x(), updatedPos.y()));
            final List<BlockEntity> verticalColliding = getCollidingBlocks(entity, collidingBlocks);
            if (!verticalColliding.isEmpty()) {
                newY = getAdjustedVerticalCoord(entity, verticalColliding.get(0));
                //DEBUG
                if (entity instanceof Player) LOGGER.info("vert coll pl " + entity.getPosition().y() + "block " + verticalColliding.get(0).getPosition().y());
            }
            entity.setPosition(new Pos2dImpl(updatedPos.x(), newY));
            final List<BlockEntity> orizontalColliding = getCollidingBlocks(entity, collidingBlocks);
            if (!orizontalColliding.isEmpty()) {
                newX = getAdjustedOrizontalCoord(entity, orizontalColliding.get(0));
                //DEBUG
                if (entity instanceof Player) LOGGER.info("oriz coll pl ");
            }
            entity.setPosition(new Pos2dImpl(newX, newY));
            //DEBUG
            //LOGGER.info(" EXIT player pos: " + entity.getPosition());
        }
    }

    public void resolveTrapCollisions(final Player player, final List<Trap> traps) {
        traps.stream()
        .filter(trap -> trap.getPosition().getdistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(trap -> trap.getHitbox().isCollidingWith(player.getHitbox()))
        .forEach(trap -> trap.activate(player));
    }

    /**
     * This method resolves collisions between the player and the enemies.
     * @param player the player
     * @param enemies the list of enemies in the level
     * @return the list of enemy killed
     */
    public List<Enemy> resolveEnemiesCollisions(final Player player, final List<Enemy> enemies) {
        final Hitbox playerHitbox = player.getHitbox();
        if (player.isInvulnerable()) {
             return enemies.stream()
            .filter(e -> playerHitbox.isCollidingWith(e.getHitbox()))
            .collect(Collectors.toList());
        } else {
            enemies.stream()
            .filter(e -> playerHitbox.isCollidingWith(e.getHitbox()))
            .forEach(e -> {
                player.incrementLife(-1);    //TODO: here use the applyDamage method
                notifyObservers(e.getPosition().x() > player.getPosition().x()
                ? CollisionEvents.OBSTACLE_RIGHT_COLLISION : CollisionEvents.OBSTACLE_LEFT_COLLISION);
            });
            return List.of();
        }
    }

    /**
     * This method resolves collisions between the player and the collectible entities.
     * @param player the player
     * @param collectibles the list of collectible entities
     * @return a list of collectible that have been collected and have to be removed
     */
    public List<Collectible> resolveCollectibleCollisions(final Player player, final List<Collectible> collectibles) {
        return collectibles.stream()
        .filter(collectible -> collectible.getPosition().getdistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(collectible -> collectible.getHitbox().isCollidingWith(player.getHitbox()))
        .peek(collectible -> collectible.collect(player))
        .collect(Collectors.toList());
    }

    private List<BlockEntity> getCollidingBlocks(final MoveableEntity entity, final List<BlockEntity> collidingBlocks) {
        return collidingBlocks.stream()
        .filter(b -> b.getPosition().getdistance(entity.getPosition()) <= RENDER_DISTANCE)
        .filter(b -> b.getGameEntityType().equals(GameEntityType.TERRAIN))
        .filter(b -> b.getHitbox().isCollidingWith(entity.getHitbox()))
        .collect(Collectors.toList());
    }

    /**
     * This method adjust the orizontal position of the entity colliding with a block in the x axis.
     * The position is adjusted based on the dimension of the hitbox of the entity and the block it is
     * colliding with. The entity is moved perfectly to the right (or to the left) of the block, and a 
     * delta is added to have the entity just ot the right (or to the left) of the colliding block.
     * @param entity the entity which is colliding
     * @param block one of the block the entity is colliding with
     * @return the new x coordinate of the entity to be set
     */
    private double getAdjustedOrizontalCoord(final MoveableEntity entity, final BlockEntity block) {
        final double newXPos;
        if (entity.getPosition().x() < block.getPosition().x()) {     //contact from right
            newXPos = entity.getPosition().x()
                + block.getHitbox().getLLCorner().x() - entity.getHitbox().getURCorner().x() - DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvents.BLOCK_RIGHT_COLLISION);
                }
        } else {                                                    //contatto from left
            newXPos = entity.getPosition().x()
                + block.getHitbox().getURCorner().x() - entity.getHitbox().getLLCorner().x() + DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvents.BLOCK_LEFT_COLLISION);
                }
        }
        return newXPos;
    }

    /**
     * This method adjust the vertical position of the entity colliding with a block in the y axis.
     * The position is adjusted based on the dimension of the hitbox of the entity and the block it is
     * colliding with. The entity is moved perfectly above (or below) the block, and a delta is added
     * to have the entity right over (or under) the colliding block.
     * @param entity the entity which is colliding
     * @param block one of the block the entity is colliding with
     * @return the new y coordinate of the entity to be set
     */
    private double getAdjustedVerticalCoord(final MoveableEntity entity, final BlockEntity block) {
        final double newYPos;
        if (entity.getPosition().y() > block.getPosition().y()) {                   //contact from above 
            newYPos = entity.getPosition().y()
                + block.getHitbox().getURCorner().y() - entity.getHitbox().getLLCorner().y() + DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvents.BLOCK_LOWER_COLLISION);
                }
        } else {                                                                    //contact from below
            newYPos = entity.getPosition().y()
                + block.getHitbox().getLLCorner().x() - entity.getHitbox().getURCorner().x() - DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvents.BLOCK_UPPER_COLLISION);
                }
        }
        return newYPos;
    }

    @Override
    public void register(final Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(final Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(final CollisionEvents event) {
        observers.forEach(o -> o.onNotify(event));
    }

}
