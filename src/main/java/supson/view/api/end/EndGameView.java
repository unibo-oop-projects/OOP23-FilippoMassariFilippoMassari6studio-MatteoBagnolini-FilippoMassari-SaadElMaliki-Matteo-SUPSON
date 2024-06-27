package supson.view.api.end;

public interface EndGameView {
    void initView();
    void renderView(int score, double time, boolean isWon);
}
