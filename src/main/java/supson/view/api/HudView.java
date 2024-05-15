package supson.view.api;

import supson.model.hud.api.Hud;

public interface HudView {
    /**
    * Renders the HUD.
    * 
    * @param player the player object to render the HUD for
    */
    void renderHud(Hud hud);
}
