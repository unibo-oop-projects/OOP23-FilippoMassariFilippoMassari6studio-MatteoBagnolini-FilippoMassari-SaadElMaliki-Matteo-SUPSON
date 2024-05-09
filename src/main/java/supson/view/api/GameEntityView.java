package supson.view.api;

import supson.model.entity.api.GameEntity;
import supson.view.impl.GamePanel;

public interface GameEntityView {
    
    void renderGameEntity(GameEntity gameEntity, GamePanel gamePanel);
}
