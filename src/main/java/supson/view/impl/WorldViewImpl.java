package supson.view.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.GameEntity;
import supson.model.entity.player.Player;
import supson.view.api.WorldView;

public class WorldViewImpl implements WorldView {

    private static final int CAMERA_RANGE = 20;
    private static final int DEFAULT_DIMENSION = 24;
    private final List<GameEntity> cameraGameEntitiesList = new ArrayList<>();

    @Override
    public void renderWorld(final JPanel gamePanel, final List<GameEntity> gameEntitiesList, final Player player) {
        cameraGameEntitiesList.clear();
        int mapWidth = 0;
        for (GameEntity gameEntity : gameEntitiesList) { //TODO : refactor
            if (gameEntity.getPosition().x() > mapWidth) {
                mapWidth = (int) gameEntity.getPosition().x();
            }
        }
        selectGameEntity(gameEntitiesList, player, mapWidth);
        addToPanel(gamePanel, player, mapWidth);
    }

    private void selectGameEntity(final List<GameEntity> gameEntitiesList, final Player player, final int mapWidth) {
        cameraGameEntitiesList.clear();
        int playerX = (int) player.getPosition().x();
        int leftBoundary = playerX - CAMERA_RANGE;
        int rightBoundary = playerX + CAMERA_RANGE;

        if (playerX >= mapWidth - CAMERA_RANGE) {
            leftBoundary = mapWidth - 2 * CAMERA_RANGE;
            rightBoundary = mapWidth;
        }

        if (playerX <= CAMERA_RANGE) {
            leftBoundary = 0;
            rightBoundary = 2 * CAMERA_RANGE;
        }

        for (GameEntity gameEntity : gameEntitiesList) {
            if (gameEntity.getPosition().x() >= leftBoundary
                && gameEntity.getPosition().x() <= rightBoundary) {
                this.cameraGameEntitiesList.add(gameEntity);
            }
        }
        this.cameraGameEntitiesList.add(player);
    }

    private void addToPanel(final JPanel gamePanel, final Player player, final int mapWidth) {
        int centerX = (gamePanel.getWidth() / 2) - (3 * DEFAULT_DIMENSION / 4);//TODO : refactor
        int centerY = gamePanel.getHeight() / 2;
        double playerX = player.getPosition().x();
        double playerY = player.getPosition().y();

        for (GameEntity gameEntity : cameraGameEntitiesList) {
            Optional<ImageIcon> icon;
            if (gameEntity.getGameEntityType().equals(GameEntityType.PLAYER)) {
                icon = getPlayerImage(player);
            } else {
                icon = getEntityImage(gameEntity);
            }
            if (icon.isPresent()) {
                JLabel label = new JLabel(icon.get());
                label.setOpaque(false);
                Pos2d pos = gameEntity.getPosition();
                int x, y;
                if (playerX >= mapWidth - CAMERA_RANGE) {
                    x = (int) Math.round(centerX + (pos.x() - (mapWidth - CAMERA_RANGE)) * DEFAULT_DIMENSION);
                } else if (playerX <= CAMERA_RANGE) {
                    x = (int) Math.round(centerX + (pos.x() - CAMERA_RANGE) * DEFAULT_DIMENSION);
                } else {
                    x = (int) Math.round(centerX + (pos.x() - playerX) * DEFAULT_DIMENSION);
                }
                int gameEntityHeight = DEFAULT_DIMENSION * gameEntity.getHeight();
                if (gameEntity.getGameEntityType().equals(GameEntityType.PLAYER)
                    || gameEntity.getGameEntityType().equals(GameEntityType.ENEMY)
                    || gameEntity.getGameEntityType().equals(GameEntityType.SUBTERRAIN)) {
                    y = (int) Math.round(centerY - (pos.y() - playerY) * DEFAULT_DIMENSION - (DEFAULT_DIMENSION / 2));
                } else {
                    y = (int) Math.round(centerY - (pos.y() - playerY) * gameEntityHeight);
                }
                label.setBounds(x, y, DEFAULT_DIMENSION, gameEntityHeight);
                gamePanel.add(label);
            }
        }
    }

    private Optional<ImageIcon> getEntityImage(final GameEntity gameEntity) {
        final GameEntityType type = gameEntity.getGameEntityType();
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(type.getSpritePath()));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<ImageIcon> getPlayerImage(final Player player) {
        PlayerPathSelector pps = new PlayerPathSelector();
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(pps.selectPath(player)));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
