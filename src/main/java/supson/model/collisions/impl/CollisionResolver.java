package supson.model.collisions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.block.api.Collectible;
import supson.model.block.api.Finishline;
import supson.model.block.api.Trap;
import supson.model.collisions.CollisionEvent;
import supson.model.collisions.api.CollisionManager;
import supson.model.collisions.api.CollisionObservable;
import supson.model.collisions.api.CollisionObserver;
import supson.model.entity.api.GameEntity;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.player.Player;
import supson.model.hitbox.api.Hitbox;
import supson.model.world.api.World;

/**
 * This class is a collision resolver. It is used to check
 * and resolve collisions in the game.
 */
public final class CollisionResolver implements CollisionManager, CollisionObservable {

    private static final int RENDER_DISTANCE = 10;
    private static final double DELTA = 0.000_001;

    private final List<CollisionObserver> observers;

    /**
     * The constructor of this class is final and empty, ensuring it cannot
     * be instantiated.
     */
    public CollisionResolver() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void resolvePlatformCollisions(final MoveableEntity entity,
            final List<GameEntity> blocks, final Pos2d startingPos) {

        final Pos2d updatedPos = entity.getPosition();
        double newX = updatedPos.x();
        double newY = updatedPos.y();

        final List<GameEntity> collidingBlocks = getCollidingBlocks(entity, blocks);
        if (!collidingBlocks.isEmpty()) {
            newY = resolveVerticalCollision(entity, blocks, startingPos, newY);
            newX = resolveHorizontalCollision(entity, blocks, updatedPos, newY);
        }

        entity.setPosition(new Pos2dImpl(newX, newY));
    }

    public void resolveFinishlineCollision(final Player player, final List<Finishline> finishlines, World world) {
        finishlines.stream()
        .filter(f -> f.getPosition().getdistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(f -> f.getHitbox().isCollidingWith(player.getHitbox()))
        .forEach(f -> {
            f.endGame(world);
            /*notifyObservers(f.getPosition().x()>player.getPosition().x()
                ? CollisionEvent.OBSTACLE_RIGHT_COLLISION : CollisionEvent.OBSTACLE_LEFT_COLLISION);*/
        });
    }

    @Override
    public void resolveTrapCollisions(final Player player, final List<Trap> traps) {
        traps.stream()
        .filter(t -> t.getPosition().getdistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(t -> t.getHitbox().isCollidingWith(player.getHitbox()))
        .forEach(t -> {
            t.activate(player);
            notifyObservers(t.getPosition().x() > player.getPosition().x()
                ? CollisionEvent.OBSTACLE_RIGHT_COLLISION : CollisionEvent.OBSTACLE_LEFT_COLLISION);
        });
    }

    @Override
    public List<Enemy> resolveEnemiesCollisions(final Player player, final List<Enemy> enemies) {
        final Hitbox playerHitbox = player.getHitbox();
        if (player.getState().isInvulnerable() || player.getState().isJumping()) {
            return enemies.stream()
            .filter(e -> playerHitbox.isCollidingWith(e.getHitbox()))
            .collect(Collectors.toList());
        } else {
            enemies.stream()
            .filter(e -> playerHitbox.isCollidingWith(e.getHitbox()))
            .forEach(e -> {
                e.applyDamage(player);
                notifyObservers(e.getPosition().x() > player.getPosition().x()
                ? CollisionEvent.OBSTACLE_RIGHT_COLLISION : CollisionEvent.OBSTACLE_LEFT_COLLISION);
            });
            return List.of();
        }
    }

    @Override
    public List<Collectible> resolveCollectibleCollisions(final Player player, final List<Collectible> collectibles) {
        return collectibles.stream()
        .filter(collectible -> collectible.getPosition().getdistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(collectible -> collectible.getHitbox().isCollidingWith(player.getHitbox()))
        .peek(collectible -> collectible.collect(player))
        .collect(Collectors.toList());
    }

    private List<GameEntity> getCollidingBlocks(final MoveableEntity entity, final List<GameEntity> collidingBlocks) {
        return collidingBlocks.stream()
        .filter(b -> b.getPosition().getdistance(entity.getPosition()) <= RENDER_DISTANCE)
        .filter(b -> b.getGameEntityType().equals(GameEntityType.TERRAIN))
        .filter(b -> b.getHitbox().isCollidingWith(entity.getHitbox()))
        .collect(Collectors.toList());
    }

    private double resolveVerticalCollision(final MoveableEntity entity, final List<GameEntity> blocks,
        final Pos2d startingPos, final double updatedY) {
        entity.setPosition(new Pos2dImpl(startingPos.x(), updatedY));
        final List<GameEntity> verticalColliding = getCollidingBlocks(entity, blocks);
        if (!verticalColliding.isEmpty()) {
            return getAdjustedVerticalCoord(entity, verticalColliding.get(0));
        }
        return updatedY;
    }

    private double resolveHorizontalCollision(final MoveableEntity entity, final List<GameEntity> blocks,
        final Pos2d updatedPos, final double adjustedY) {
        entity.setPosition(new Pos2dImpl(updatedPos.x(), adjustedY));
        final List<GameEntity> orizontalColliding = getCollidingBlocks(entity, blocks);
        if (!orizontalColliding.isEmpty()) {
            return getAdjustedOrizontalCoord(entity, orizontalColliding.get(0));
        }
        return updatedPos.x();
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
    private double getAdjustedOrizontalCoord(final MoveableEntity entity, final GameEntity block) {
        final double newXPos;
        if (entity.getPosition().x() < block.getPosition().x()) {     //contact from right
            newXPos = entity.getPosition().x()
                + block.getHitbox().getLLCorner().x() - entity.getHitbox().getURCorner().x() - DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvent.BLOCK_RIGHT_COLLISION);
                }
        } else {                                                    //contatto from left
            newXPos = entity.getPosition().x()
                + block.getHitbox().getURCorner().x() - entity.getHitbox().getLLCorner().x() + DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvent.BLOCK_LEFT_COLLISION);
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
    private double getAdjustedVerticalCoord(final MoveableEntity entity, final GameEntity block) {
        final double newYPos;
        if (entity.getPosition().y() > block.getPosition().y()) {                   //contact from above 
            newYPos = entity.getPosition().y()
                + block.getHitbox().getURCorner().y() - entity.getHitbox().getLLCorner().y() + DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvent.BLOCK_LOWER_COLLISION);
                }
        } else {                                                                    //contact from below
            newYPos = entity.getPosition().y()
                + block.getHitbox().getLLCorner().y() - entity.getHitbox().getURCorner().y() - DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvent.BLOCK_UPPER_COLLISION);
                }
        }
        return newYPos;
    }

    @Override
    public void register(final CollisionObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(final CollisionObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(final CollisionEvent event) {
        observers.forEach(o -> o.onNotify(event));
    }

}
