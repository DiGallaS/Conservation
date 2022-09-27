package Conservation;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress progress = new GameProgress(250, 190, 23, 4);
        GameProgress progress2 = new GameProgress(500, 160, 30, 1);
        GameProgress progress3 = new GameProgress(150, 250, 16, 2);

        saveGame(progress, "D:/Games/savegames//save.dat");
        saveGame(progress2, "D:/Games/savegames//save2.dat");
        saveGame(progress3, "D:/Games/savegames//save3.dat");

        String[] list = new String[]{"D:/Games/savegames//save.dat", "D:/Games/savegames//save2.dat", "D:/Games/savegames//save3.dat"};


        zipFiles("D:/Games/savegames.zip", list);

        deleteFile(list);


    }

    public static void saveGame(GameProgress progress, String address) {
        try (FileOutputStream fos = new FileOutputStream(address);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String address, String[] list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(address))) {
            for (String file : list) {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(String[] list) {
        for (String name:list) {
            File file = new File(name);
            file.delete();
        }
    }
}