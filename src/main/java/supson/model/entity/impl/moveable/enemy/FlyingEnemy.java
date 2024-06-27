package supson.model.entity.impl.moveable.enemy;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.api.enemy.Enemy;
import supson.model.entity.impl.moveable.AbstractMoveableEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.physics.impl.PhysicsImpl;

public class FlyingEnemy extends AbstractMoveableEntity implements Enemy{

    private static final int MAX_SPEED = 2;
    private static final double ACC_SPEED = 0;
    private static final Vect2d VELOCITYUP = new Vect2dImpl(0, 2);
    private static final Vect2d VELOCITYDOWN = new Vect2dImpl(0, -2);
    private static final double FRICTION = 0;
    private static final int JUMP_FORCE = 0;
    private static final double GRAVITY = 0.05;
    
    private static final int LIFE = 1;
    private static final int RANGE = 3;

    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;

    private static final GameEntityType TYPE = GameEntityType.FLYINGENEMY;

    private boolean upward;
    private final Pos2d initialPos;
    private double range;


    /**
     * The constructor of the enemy class.
     * @param pos the starting positon of the enemy
     * @param vel the starting velocity of the enemy
     * @param life the number of life of the enemy
     * @param range the range of movement of the enemy
     */
    public FlyingEnemy(Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE, VELOCITYUP, LIFE, new PhysicsImpl(MAX_SPEED, ACC_SPEED, ACC_SPEED,
                                                    FRICTION, JUMP_FORCE, GRAVITY));
        this.upward = true;
        this.initialPos = pos;
        this.range = RANGE;
    }


    @Override
    protected void updateVelocity() {
        if (this.upward) {
            this.setVelocity(VELOCITYUP);
            if (this.getPosition().y() - this.initialPos.y() >= range) {
                this.upward = false;
            }
        } else {
            this.setVelocity(VELOCITYDOWN);
            if (this.getPosition().y() - this.initialPos.y() <= 0) {
                this.upward = true;
            }
        }
    }

    public void applyDamage(Player player){
        player.setLife(player.getLife()-1);
    }

}
