package supson.view.api;

import supson.model.entity.impl.Player;

public interface PlayerView {

    void renderPlayer(Player player);

    void renderHud(Player player);
}
