package supson.model.entity.impl;

import supson.common.api.Pos2d;
import supson.model.entity.api.GameEntity;
import supson.model.hitbox.api.Hitbox;

/**
 * This abstract class, which implements the interface GameEntity, models a generic game entity.
 * This class is used as a template to create other more specific classes.
 */
public abstract class AbstractGameEntity implements GameEntity {

    private Pos2d position;
    private Hitbox hitbox;

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
    public final boolean isColliding(final GameEntity otherGameEntity) {
        //TODO : real implementation
        return true;
    }

    @Override
    public final void notifyCollision(final GameEntity collidingGameEntity) {
        //TODO : real implementation
    }

}
