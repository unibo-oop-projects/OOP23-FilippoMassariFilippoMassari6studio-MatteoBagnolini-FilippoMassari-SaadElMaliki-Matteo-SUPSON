package supson.view.api;

import javax.swing.JFrame;

import supson.model.hud.api.Hud;

public interface HudView {
    /**
    * Renders the HUD.
    * 
    * @param gameFrame the frame to render the HUD on
    * @param player the player object to render the HUD for
    */
    void renderHud(JFrame gameFrame, Hud hud);
}
