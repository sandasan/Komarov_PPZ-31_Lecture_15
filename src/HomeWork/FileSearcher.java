package HomeWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FileSearcher implements Runnable {
    static int foundFilesCount = 0;
    static boolean filesNotFoundPrinted = false;
    String directoryPath, fileName;
    private Thread thr;
    private boolean stop = false;

    public FileSearcher(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        thr = new Thread(this);
        thr.start();
    }

    public static void find(String path, String pattern) throws IOException {
        // Если в указанной папке нет папок, то создаём поток и запускаем поиск файла в этой папке, иначе для каждой папки действуем так же
//        System.out.println("path: " + path);
        // Отфильтровываем папки и ищем файл в каждой из них
            try (Stream<Path> directories = Files.walk(Paths.get(path), 1).filter(currentPath -> currentPath.toFile().isDirectory())) {
                AtomicInteger i = new AtomicInteger();
                directories.forEach(directory -> {
                    if (i.get() > 0) {
                        // Создаём для поиска в новой папке новый поток
                        FileSearcher searcher = new FileSearcher(directory.toString(), pattern);
                    }
                    i.getAndIncrement();
                });
            }
            // Отфильтровываем файлы и ищем файл среди них
            try (Stream<Path> filesTest = Files.walk(Paths.get(path), 1).filter(currentPath -> currentPath.toFile().isFile())) {
                filesTest.forEach(fileTest -> {
                    if (fileTest.getFileName().toString().equals(pattern)) {
                        foundFilesCount++;
                        System.out.println("Files found: " + fileTest);
                    }
                });
            }
            if (foundFilesCount == 0 && !filesNotFoundPrinted) {
                System.out.println("Files not found");
                filesNotFoundPrinted = true;
            }
    }

    @Override
    public void run() {
        try {
            find(directoryPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stop = true;
    }
}
