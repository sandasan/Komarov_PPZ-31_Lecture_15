package HomeWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchFileThread implements Runnable {
    String directoryPath, fileName;
    private Thread thr;
    private boolean stop = false;

    public SearchFileThread(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try {
            searchFileInDir();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stop = true;
    }

    private void searchFileInDir() throws IOException {
        Path startingDir = Paths.get(directoryPath);
        /*Find finder = new Find(fileName);
        Files.walkFileTree(startingDir, finder);
        finder.done();*/
    }
}
