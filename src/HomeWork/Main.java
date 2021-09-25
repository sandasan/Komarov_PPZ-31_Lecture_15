package HomeWork;

import java.io.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Предлагаем пользователю ввести путь (папку) поиска и шаблон имени файла
        Scanner in = new Scanner(System.in);
        System.out.print("Please specify a path to search file in: ");
        String path = in.nextLine();
        System.out.println("path: " + path);
        System.out.print("Please specify a filename pattern you would search: ");
        String pattern = in.nextLine();
        System.out.println("pattern: " + pattern);

        // Запускаем поиск файла в указанной папке по указанному шаблону
        FileSearcher.find(path, pattern);
    }
}
