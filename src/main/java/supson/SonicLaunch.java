package supson;

import supson.core.api.GameEngine;
import supson.core.impl.GameEngineImpl;

public final class SonicLaunch {
    public static void main(String[] args) {
        GameEngine ge = new GameEngineImpl();
        ge.mainLoop();
    }
}