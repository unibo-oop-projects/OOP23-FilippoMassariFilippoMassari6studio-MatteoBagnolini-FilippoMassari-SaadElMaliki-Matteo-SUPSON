package supson.view.impl;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;

public class GameFrame extends JFrame implements KeyListener{
    private boolean right, left, jump;


    public GameFrame(){
        super("SuperSonic");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800,600));
        //this.pack();
        this.setVisible(true);
        this.startView();
    }

    public void startView(){
        this.addKeyListener(this);
        this.getRootPane();
    }

    public Vect2d getInput(){
        Vect2d res = new Vect2dImpl(0,0);
        return res;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
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
                left = false;
                break;
            case KeyEvent.VK_D:
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
