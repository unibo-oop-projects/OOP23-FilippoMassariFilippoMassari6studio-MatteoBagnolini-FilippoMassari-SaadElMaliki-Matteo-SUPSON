package supson.model.entity.impl;

import supson.common.api.Vect2d;
import supson.model.entity.api.MoveableEntity;

/**
 * This abstract class, which extends AbstractGame and implements the interface MoveableEntity,
 *  models a generic moveable entity of the game.
 * This class is used as a template to create other more specific classes, such as enemies and the player.
 */
public abstract class AbstractMoveableEntity extends AbstractGameEntity implements MoveableEntity {

    private Vect2d vel;
    private int life;


    @Override
    public final Vect2d getVelocity() {
        return this.vel;
    }

    @Override
    public final void setVelocity(final Vect2d vel) {
        this.vel = vel;
    }

    @Override
    public final int getLife() {
        return this.life;
    }

    @Override
    public final void setLife(final int numLife) {
        this.life = numLife;
    }

    /**
     * This method shall be overrided.
     */
    @Override
    public abstract void move(long timeDelta);

}
