package supson.view.impl;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputManager implements KeyListener {
    public boolean left,right,jump;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
        System.out.println(left+" "+jump+" "+right);
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
        System.out.println(left+" "+jump+" "+right);
    }


}
