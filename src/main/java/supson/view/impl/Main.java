package supson.view.impl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import supson.view.api.GameView;
import supson.model.entity.api.GameEntity;
import supson.model.world.impl.WorldImpl;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                GameView gameView = new GameViewImpl();
                WorldImpl world = new WorldImpl();

                gameView.initView();
                List<GameEntity> gameEntities = new ArrayList<>();

                world.loadWorld("/world.txt");

                gameEntities.addAll(world.getBlocks());
                gameEntities.addAll(world.getEnemies());
                gameEntities.add(world.getPlayer());

                gameView.renderView(gameEntities, world.getPlayer());
            }
        });
    }
}

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            // Ottenere un InputStream per il file di risorse
            InputStream inputStream = Main.class.getResourceAsStream("/world.txt");

            // Creare un BufferedReader per leggere i dati dall'InputStream
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Leggere il file riga per riga
            String fileContent = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            
            // Chiudere il BufferedReader
            reader.close();

            // Stampare il contenuto del file
            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/

