package supson.view.api;

import supson.view.impl.GamePanel;

public interface GameEntityView<E> {
    
    void renderGameEntity(E gameEntity, GamePanel gamePanel);
}
