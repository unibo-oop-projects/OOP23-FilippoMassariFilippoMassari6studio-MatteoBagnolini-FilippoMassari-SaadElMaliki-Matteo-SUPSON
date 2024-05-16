package supson.view.api;

import javax.swing.JFrame;

import supson.model.hud.api.Hud;

/**
 * The HudView interface represents the view of the Hud.
 * It provides methods to render the Hud.
 */
public interface HudView {

    /**
    * Renders the HUD.
    * 
    * @param gameFrame the frame to render the HUD on
    * @param hud the hud object to render the HUD for
    */
    void renderHud(JFrame gameFrame, Hud hud);
}
