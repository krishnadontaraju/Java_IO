import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;


public class FileApiTest {
    private static String HOME_LOCATION = System.getProperty("Path","C:\\Users\\manik\\Desktop\\new\\Program_tester\\Donut\\test");
    private static String NIO_FILE = "TemporaryFile";

    @Test
    public void givenFileName_WhenChecked_thenConfirm() throws IOException {

        Path homeLocation = Paths.get(HOME_LOCATION);
        Assertions.assertTrue(Files.exists(homeLocation));

        /**
         * Deleting the file if already exists
         */
        Path testPath = Paths.get(HOME_LOCATION+"\\"+NIO_FILE);
        if (Files.exists(testPath))
            FileUtilities.deleteFile(testPath.toFile());
        Assertions.assertTrue(Files.notExists(testPath));

        Files.createDirectory(testPath);
        Assertions.assertTrue(Files.exists(testPath));

        /**
         * Creating 15 files
         */
        IntStream.range(1,15).forEach(entry -> {
            Path temporaryFile = Paths.get(testPath+"\\temporaryFile"+entry);
            Assertions.assertTrue(Files.notExists(temporaryFile));

            try {
                Files.createFile(temporaryFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assertions.assertTrue(Files.exists(temporaryFile));
        });

        Files.list(testPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(testPath).forEach(System.out::println);
        Files.newDirectoryStream(testPath , newPath -> newPath.toFile().isFile() &&
                                                        newPath.toString().startsWith("temporaryFile"))
                                .forEach(System.out::println);
    }

    @Test
    public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException {
        Path dir = Paths.get(HOME_LOCATION+"\\"+NIO_FILE);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new WatchService(dir).processEvents();
    }
}
