package supson.view.impl.common;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * The InputManager class implements KeyListener to manage keyboard input to control player actions.
 */
public final class InputManager implements KeyListener {

    private boolean left, right, jump;

    /**
     * Returns the current state of the left arrow key.
     *
     * @return true if the left arrow key is pressed, false otherwise
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Returns the current state of the right arrow key.
     *
     * @return true if the left arrow key is pressed, false otherwise
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Returns the current state of the space key.
     *
     * @return true if the space key is pressed, false otherwise
     */
    public boolean isJump() {
        return jump;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                jump = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_SPACE:
                jump = false;
                break;
            default:
                break;
        }
    }

}
