package supson.model.entity.impl;

import supson.common.api.Pos2d;
import supson.model.entity.api.GameEntity;
import supson.model.hitbox.api.Hitbox;
import supson.model.hitbox.impl.RectHitbox;

/**
 * This abstract class, which implements the interface GameEntity, models a generic game entity.
 * This class is used as a template to create other more specific classes.
 */
public abstract class AbstractGameEntity implements GameEntity {

    private static final int HEIGHT = 2;
    private static final int WIDTH = 2;

    private Pos2d position;
    private Hitbox hitbox;

    public AbstractGameEntity(final Pos2d pos, final int height, final int width) {
        this.position = pos;
        this.hitbox = new RectHitbox(pos, height, width);
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
        return this.hitbox;
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
