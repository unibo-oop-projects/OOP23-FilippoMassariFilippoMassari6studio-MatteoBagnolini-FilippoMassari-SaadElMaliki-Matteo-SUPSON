package supson.view;

/**
 * Those enums represent all the types of events that the view
 * can generate. It should be used to allow the view to communicate
 * with the controller via {@link supson.core.api.GameEngine#onNotifyFromView(ViewEvent event)}.
 */
public enum ViewEvent {

    /**
     * This enum represent the start of the game.
     */
    START_GAME,

    /**
     * This enum represent the closing of the game.
     */
    CLOSE_GAME,

    /**
     * This enum represents the restart of the game.
     */
    RESTART,

    /**
     * This enum represents the exit from the app.
     */
    EXIT

}
