package supson.view.impl;

import supson.model.entity.impl.Player;
import supson.view.api.GameEntityView;

public class PlayerViewImpl implements GameEntityView<Player>{

    @Override
    public void renderGameEntity(Player player, GamePanel gamePanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderPlayer'");
    }

    public void renderHud(Player player, GamePanel gamePanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderHud'");
    }

}
