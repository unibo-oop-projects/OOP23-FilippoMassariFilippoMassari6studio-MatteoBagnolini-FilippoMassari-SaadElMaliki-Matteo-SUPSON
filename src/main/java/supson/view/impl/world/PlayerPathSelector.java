package supson.view.impl.world;

import supson.model.entity.player.Player;
import supson.model.entity.player.PlayerState;

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
        /*if (!ps.left() && !ps.right()) { //TODO : non funziona perche questo oggetto viene dealocato tutte le volte
            return lastPlayerPath;
        }*/
        lastPlayerPath = "sprite/player/player_sprite_"+ps.left()+"_"+ps.right()+"_"+ps.isJumping()+"_"+ps.isInvulnerable()+".png";
        return lastPlayerPath;
    }
}
