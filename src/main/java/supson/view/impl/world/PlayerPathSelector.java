package supson.view.impl.world;

import supson.model.entity.impl.moveable.player.Player;
import supson.model.entity.impl.moveable.player.PlayerState;

/*
 * this class procuce the tring that rapresent the sprite player
 */
public final class PlayerPathSelector {

    private String lastPlayerPath;

    /**
     * Constructs a new instance of {@code PlayerPathSelector}.
     */
    public PlayerPathSelector() {
        this.lastPlayerPath = "";
    }

    /**
     * Selects the path of the player sprite based on the player's state.
     *
     * @param player the player entity
     * @return the path of the player sprite
     */
    public String selectPath(final Player player) {
        PlayerState ps = player.getState();
        lastPlayerPath = "sprite/player/player_sprite_" 
                          + ps.left() + "_" 
                          + ps.right() + "_" 
                          + ps.isJumping() + "_" 
                          + ps.isInvulnerable() 
                          + ".png";
        return lastPlayerPath;
    }
}
