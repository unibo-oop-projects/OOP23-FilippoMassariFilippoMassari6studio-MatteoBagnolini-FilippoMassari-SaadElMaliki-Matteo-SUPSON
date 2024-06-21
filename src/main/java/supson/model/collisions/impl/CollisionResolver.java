package supson.model.collisions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.collisions.CollisionEvent;
import supson.model.collisions.api.CollisionManager;
import supson.model.collisions.api.CollisionObservable;
import supson.model.collisions.api.CollisionObserver;
import supson.model.entity.api.GameEntity;
import supson.model.entity.api.block.TagBlockEntity;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.api.block.finishline.Finishline;
import supson.model.entity.api.block.trap.Trap;
import supson.model.entity.api.moveable.MoveableEntity;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.hitbox.api.Hitbox;
import supson.model.world.api.World;

/**
 * This class is a collision resolver. It is used to check
 * and resolve collisions in the game.
 */
public final class CollisionResolver implements CollisionManager, CollisionObservable {

    private static final int RENDER_DISTANCE = 5;
    private static final double DELTA = 0.000_001;

    private final List<CollisionObserver> observers;

    /**
     * This is the constructor of this class. It initializes the list of observers.
     */
    public CollisionResolver() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void resolvePlatformCollisions(final MoveableEntity entity,
            final List<TagBlockEntity> blocks, final Pos2d startingPos) {

        final Pos2d updatedPos = entity.getPosition();
        double newX = updatedPos.x();
        double newY = updatedPos.y();

        final List<TagBlockEntity> collidingBlocks = getCollidingBlocks(entity, blocks);
        if (!collidingBlocks.isEmpty()) {
            newY = resolveVerticalCollision(entity, blocks, startingPos, newY);
            newX = resolveHorizontalCollision(entity, blocks, updatedPos, newY);
        }

        entity.setPosition(new Pos2dImpl(newX, newY));
    }

    @Override
    public void resolveFinishlineCollision(final Player player, final List<Finishline> finishlines, final World world) {
        finishlines.stream()
        .filter(f -> f.getPosition().getDistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(f -> f.getHitbox().isCollidingWith(player.getHitbox()))
        .forEach(f -> {
            f.endGame(world);
        });
    }

    @Override
    public void resolveTrapCollisions(final Player player, final List<Trap> traps) {
        traps.stream()
        .filter(t -> t.getPosition().getDistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(t -> t.getHitbox().isCollidingWith(player.getHitbox()))
        .forEach(t -> {
            t.activate(player);
            player.setPosition(new Pos2dImpl(getAdjustedOrizontalCoord(player, t), player.getPosition().y()));
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
                player.setPosition(new Pos2dImpl(getAdjustedOrizontalCoord(player, e), player.getPosition().y()));
            });
            return List.of();
        }
    }

    @Override
    public List<Collectible> resolveCollectibleCollisions(final Player player, final List<Collectible> collectibles) {
        return collectibles.stream()
        .filter(collectible -> collectible.getPosition().getDistance(player.getPosition()) <= RENDER_DISTANCE)
        .filter(collectible -> collectible.getHitbox().isCollidingWith(player.getHitbox()))
        .peek(collectible -> collectible.collect(player))
        .collect(Collectors.toList());
    }

    private List<TagBlockEntity> getCollidingBlocks(final MoveableEntity entity, final List<TagBlockEntity> collidingBlocks) {
        return collidingBlocks.stream()
        .filter(b -> b.getPosition().getDistance(entity.getPosition()) <= RENDER_DISTANCE)
        .filter(b -> b.getGameEntityType().equals(GameEntityType.TERRAIN))
        .filter(b -> b.getHitbox().isCollidingWith(entity.getHitbox()))
        .collect(Collectors.toList());
    }

    private double resolveVerticalCollision(final MoveableEntity entity, final List<TagBlockEntity> blocks,
        final Pos2d startingPos, final double updatedY) {
        entity.setPosition(new Pos2dImpl(startingPos.x(), updatedY));
        final List<TagBlockEntity> verticalColliding = getCollidingBlocks(entity, blocks);
        if (!verticalColliding.isEmpty()) {
            return getAdjustedVerticalCoord(entity, verticalColliding.get(0));
        }
        return updatedY;
    }

    private double resolveHorizontalCollision(final MoveableEntity entity, final List<TagBlockEntity> blocks,
        final Pos2d updatedPos, final double adjustedY) {
        entity.setPosition(new Pos2dImpl(updatedPos.x(), adjustedY));
        final List<TagBlockEntity> orizontalColliding = getCollidingBlocks(entity, blocks);
        if (!orizontalColliding.isEmpty()) {
            return getAdjustedOrizontalCoord(entity, orizontalColliding.get(0));
        }
        return updatedPos.x();
    }

    /**
     * This method adjust the orizontal position of the entity colliding with another entity in the x axis.
     * The position is adjusted based on the dimension of the hitbox of the entity and the colliding entity.
     * The entity is moved perfectly to the right (or to the left) of the block, and a 
     * delta is added to have the entity just ot the right (or to the left) of the colliding entity.
     * @param entity the entity which is colliding
     * @param collidingEntity the colliding entity
     * @return the new x coordinate of the entity to be set
     */
    private double getAdjustedOrizontalCoord(final MoveableEntity entity, final GameEntity collidingEntity) {
        final double newXPos;
        if (entity.getPosition().x() < collidingEntity.getPosition().x()) {     //contact from right
            newXPos = entity.getPosition().x()
                + collidingEntity.getHitbox().getLLCorner().x() - entity.getHitbox().getURCorner().x() - DELTA;
                // CHECKSTYLE: EmptyStatement OFF
                // default case is intentionally empty: nothing has to be done if collidingEntity isn't Terrain, Enemy or Trap
                switch (collidingEntity.getGameEntityType()) {
                    case TERRAIN : notifyObservers(CollisionEvent.BLOCK_RIGHT_COLLISION); break;
                    case ENEMY, DAMAGE_TRAP : notifyObservers(CollisionEvent.OBSTACLE_RIGHT_COLLISION); break;
                    default : ;
                }
                // CHECKSTYLE: EmptyStatement ON
        } else {                                                    //contatto from left
            newXPos = entity.getPosition().x()
                + collidingEntity.getHitbox().getURCorner().x() - entity.getHitbox().getLLCorner().x() + DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvent.BLOCK_LEFT_COLLISION);
                }
                // CHECKSTYLE: EmptyStatement OFF
                // default case is intentionally empty: nothing has to be done if collidingEntity isn't Terrain, Enemy or Trap
                switch (collidingEntity.getGameEntityType()) {
                    case TERRAIN : notifyObservers(CollisionEvent.BLOCK_LEFT_COLLISION); break;
                    case ENEMY, DAMAGE_TRAP : notifyObservers(CollisionEvent.OBSTACLE_LEFT_COLLISION); break;
                    default : ;
                }
                // CHECKSTYLE: EmptyStatement ON
        }
        return newXPos;
    }

    /**
     * This method adjust the orizontal position of the entity colliding with another entity in the y axis.
     * The position is adjusted based on the dimension of the hitbox of the entity and the colliding entity.
     * The entity is moved perfectly above (or below) of the block, and a delta is added to have the entity
     * just right over (or under) of the colliding entity.
     * @param entity the entity which is colliding
     * @param collidingEntity the colliding entity
     * @return the new x coordinate of the entity to be set
     */
    private double getAdjustedVerticalCoord(final MoveableEntity entity, final GameEntity collidingEntity) {
        final double newYPos;
        if (entity.getPosition().y() > collidingEntity.getPosition().y()) {                   //contact from above 
            newYPos = entity.getPosition().y()
                + collidingEntity.getHitbox().getURCorner().y() - entity.getHitbox().getLLCorner().y() + DELTA;
                if (entity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                    notifyObservers(CollisionEvent.BLOCK_LOWER_COLLISION);
                }
        } else {                                                                    //contact from below
            newYPos = entity.getPosition().y()
                + collidingEntity.getHitbox().getLLCorner().y() - entity.getHitbox().getURCorner().y() - DELTA;
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
