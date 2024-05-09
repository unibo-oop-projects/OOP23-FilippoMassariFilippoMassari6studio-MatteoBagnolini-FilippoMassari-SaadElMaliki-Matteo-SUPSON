package supson.model.entity.impl;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.GameEntity;
import supson.model.hitbox.api.Hitbox;
import supson.model.hitbox.impl.RectHitbox;

/**
 * This abstract class, which implements the interface GameEntity, models a generic game entity.
 * This class is used as a template to create other more specific classes.
 */
public abstract class AbstractGameEntity implements GameEntity {

    private Pos2d position;
    private final int height;
    private final int width;
    private final GameEntityType type;

    /**
     * The constructor of a generic game entity.
     * @param pos the position of the entity
     * @param height the height of the entity
     * @param width the width of the entity
     */
    public AbstractGameEntity(final Pos2d pos, final int height, final int width, final GameEntityType type) {
        this.position = pos;
        this.height = height;
        this.width = width;
        this.type = type;
    }

    @Override
    public final Pos2d getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(final Pos2d pos) {
        this.position = pos;
    }

    @Override
    public final Hitbox getHitbox() {
        return new RectHitbox(this.position, height, width);
    }

    @Override
    public final GameEntityType getGameEntityType() {
        return this.type;
    }

    @Override
    public final boolean isCollidingWith(final GameEntity otherGameEntity) {
        return this.getHitbox().isCollidingWith(otherGameEntity.getHitbox());
    }

    @Override
    public final void notifyCollision(final GameEntity collidingGameEntity) {
        //TODO : real implementation
    }

}
