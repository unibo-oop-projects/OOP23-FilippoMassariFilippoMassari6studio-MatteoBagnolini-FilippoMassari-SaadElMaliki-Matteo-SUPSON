package supson.view.api;

import supson.model.entity.impl.Player;

public interface HudView {
    /**
    * Renders the HUD.
    * 
    * @param player the player object to render the HUD for
    */
    void renderHud(Player player);
}
