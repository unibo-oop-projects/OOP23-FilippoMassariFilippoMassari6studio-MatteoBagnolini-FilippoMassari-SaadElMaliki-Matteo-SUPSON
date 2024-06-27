package supson.view.impl.start;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import supson.view.api.start.MenuView;
import supson.view.impl.common.ImagePanel;
import supson.view.impl.common.MenuButton;

public class MenuViewImpl implements MenuView {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;
    private static final String BG_PATH = "sprite/menubackground.jpg";

    private final JFrame frame;
    private final JPanel mainPanel;
    private final ImagePanel backgroundPanel;
    private final MenuButton startButton;
    private final MenuButton quitButton;

    public MenuViewImpl(JFrame frame, ActionListener listener){
        this.frame = frame;
        this.mainPanel = new JPanel();
        this.backgroundPanel = new ImagePanel(BG_PATH);
        startButton = new MenuButton("Play",listener);
        quitButton = new MenuButton("Quit",listener);
    }

    @Override
    public void initView() {

        backgroundPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        backgroundPanel.setOpaque(false);
        backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);

        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, WIDTH, HEIGHT);
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(30, 30, 30, 30);

        mainPanel.add(this.startButton, gbc);
        gbc.gridy++;
        mainPanel.add(quitButton, gbc);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        frame.setContentPane(layeredPane);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void renderView() {
        frame.setVisible(true);
    }
}
