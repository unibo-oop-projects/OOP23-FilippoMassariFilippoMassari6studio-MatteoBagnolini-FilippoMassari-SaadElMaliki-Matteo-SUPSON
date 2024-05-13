package supson.view.api;

import javax.swing.JPanel;

import supson.model.entity.impl.Player;

public interface HudView {
    /**
    * Renders the HUD.
     * @param hudPanel 
     * @param player 
    */
    void renderHud(JPanel hudPanel, Player player);
}
