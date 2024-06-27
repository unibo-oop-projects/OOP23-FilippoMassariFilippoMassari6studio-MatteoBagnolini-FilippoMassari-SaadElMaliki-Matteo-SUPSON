package supson.view.impl.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Customized JButton for menu and endgame views.
 */
public class MenuButton extends JButton {

    private static final long serialVersionUID = 7L;
    private static final int TEXTSIZE  = 50;

    /**
     * Constructs a MenuButton with specified text and ActionListener.
     *
     * @param text the text to display on the button.
     * @param listener the ActionListener to handle button clicks.
     */
    public MenuButton(final String text, final ActionListener listener) {
        super(text);
        init(text, listener);
    }

    private void init(final String text, final ActionListener listener) {
        this.setFont(new Font("Verdana", Font.BOLD, TEXTSIZE));
        this.setForeground(Color.WHITE);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setActionCommand(text);
        this.addActionListener(listener);
    }
}
