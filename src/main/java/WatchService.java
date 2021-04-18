import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchService {

    private final java.nio.file.WatchService watcher;
    private final Map<WatchKey, Path> directoryWatchers;

    public WatchService(Path directoryPath) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.directoryWatchers = new HashMap<>();
        scanAndRegisterDirectories(directoryPath);
    }


    private void registerDirectoryWatchers(Path directoryPath) throws IOException {
        WatchKey key = directoryPath.register( watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY );
        directoryWatchers.put(key, directoryPath);
    }


    private void scanAndRegisterDirectories(final Path startPath) throws IOException {

        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectoryWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }


    @SuppressWarnings({"rawTypes", "unchecked"})
    void processEvents() {

        while (true) {

            WatchKey testKey = null;
            try {
                testKey = watcher.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Path directory = directoryWatchers.get(testKey);
            if (directory == null) continue;
            for (WatchEvent<?> event : testKey.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = directory.resolve(name);
                System.out.format("%s: %s\n", event.kind().name(), child);


                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) scanAndRegisterDirectories(child);
                    } catch (IOException x) {
                    }
                } else if (kind.equals(ENTRY_DELETE)) {
                    if (Files.isDirectory(child)) directoryWatchers.remove(testKey);
                }
            }

            boolean valid = testKey.reset();
            if (!valid) {
                directoryWatchers.remove(testKey);
                if (directoryWatchers.isEmpty()) break;
            }
        }
    }
}

