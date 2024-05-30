package supson.view.impl;

import supson.model.entity.player.Player;
import supson.model.entity.player.PlayerState;

public final class PlayerPathSelector {

    public String selectPath(final Player player) {
        PlayerState ps = player.getState();
        String playerPath = "sprite/player_sprite_"+ps.left()+"_"+ps.right()+"_"+ps.isJumping()+"_"+ps.isInvulnerable()+".jpg";
        return playerPath;
    }
}
