package supson.model.world.api;

/*
 * Represents a world loader that loads a world from a file.
 */
public interface WorldLoader {

    /**
     * Loads a world from the specified file path.
     *
     * @param filePath the file path of the world to load
     * @param world    the world to load
     */
    void loadWorld(String filePath, World world);

}