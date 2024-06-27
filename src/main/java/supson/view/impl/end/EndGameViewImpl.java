package supson.view.impl.end;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import supson.view.api.end.EndGameView;
import supson.view.impl.common.ImagePanel;
import supson.view.impl.common.MenuButton;

public class EndGameViewImpl implements EndGameView {

    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;
    private static final String BG_PATH = "sprite/endgamebackground.jpg";

    private final JFrame frame;
    private final JPanel mainPanel;
    private final ImagePanel backgroundPanel;

    private final JLabel scoreLabel;
    private final JLabel timeLabel;
    private final JLabel resultLabel;

    private final MenuButton quitButton;
    private final MenuButton replayButton;
    private final MenuButton mainMenuButton;

    public EndGameViewImpl(JFrame frame, ActionListener listener){
        this.frame = frame;
        this.mainPanel = new JPanel();
        this.backgroundPanel = new ImagePanel(BG_PATH);
        this.scoreLabel = new JLabel();
        this.timeLabel = new JLabel();
        this.resultLabel = new JLabel();
        this.mainMenuButton = new MenuButton("Menu", listener);
        this.replayButton = new MenuButton("Retry", listener);
        this.quitButton = new MenuButton("Quit", listener);
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

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.insets.set(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(resultLabel, gbc);
        
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START; // Align right
        //gbc.insets.set(10, 10, 10, 10); // Padding
        mainPanel.add(scoreLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_END; // Align left
        //gbc.insets.set(10, 10, 10, 10); // Padding
        mainPanel.add(timeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets.set(0, 0, 0, 0); // Top and bottom padding
        gbc.anchor = GridBagConstraints.LINE_END;

        mainPanel.add(mainMenuButton, gbc);
        mainPanel.add(replayButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets.set(20, 0, 20, 0); // Top and bottom padding
        mainPanel.add(quitButton, gbc);


        frame.setContentPane(layeredPane);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void renderView(int score, double time, boolean isWon) {

        this.scoreLabel.setText("Score: " + score);
        this.timeLabel.setText("Time: " + String.format("%.2f",time));

        this.scoreLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        this.scoreLabel.setForeground(Color.YELLOW);

        this.timeLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        this.timeLabel.setForeground(Color.YELLOW);

        this.resultLabel.setFont(new Font("Verdana", Font.BOLD, 75));

        if(isWon){
            this.resultLabel.setText("You Won!");
            this.resultLabel.setForeground(Color.YELLOW);
            this.mainPanel.remove(replayButton);
        } else {
            this.resultLabel.setText("You Lost!");
            this.resultLabel.setForeground(Color.BLACK);
            this.mainPanel.remove(mainMenuButton);
        }

        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }
    
}
