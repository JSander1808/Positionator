package de.rembel.General;

import com.google.common.base.Stopwatch;
import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static de.rembel.Main.PositionatorMain.plugin;

public class BackUpManager {

    public BackUpManager(){
        log("started...");

        File backUpFolder = new File("plugins//Positionator_BackUp");
        if(!backUpFolder.exists()){
            backUpFolder.mkdir();
            log("Backup Folder created");
        }

        log("initialized");
    }

    private void log(String message){
        plugin.getLogger().log(Level.INFO,"[BackUpManager] "+message);
    }

    public boolean createBackUp(String creator, String reason) {
        log("start creating BackUp");
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String backUpName = timeStamp + "_" + plugin.getDescription().getVersion();
        File backUpFolder = new File("plugins//Positionator_BackUp//" + backUpName);
        backUpFolder.mkdir();
        log("created BackUp folder with id " + backUpName);
        File backUpFile = new File("plugins//Positionator_BackUp//" + backUpName + "//" + backUpName + ".zip");
        try {
            backUpFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log("created BackUp File");
        try {
            FileOutputStream fos = new FileOutputStream("plugins//Positionator_BackUp//" + backUpName + "//" + backUpName + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            addDirToZipArchive(zos, new File("plugins//Positionator"), null);
            zos.flush();
            fos.flush();
            zos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NormalConfig config = new NormalConfig("plugins//Positionator_BackUp//" + backUpName + "//config.yml");
        config.init();
        config.set("version", plugin.getDescription().getVersion());
        config.set("date", timeStamp);
        config.set("creator", creator);
        config.set("reason", reason);
        config.set("size", ((int) new File("plugins//Positionator_BackUp//" + backUpName + "//" + backUpName + ".zip").length() / 1024)+ " KB");
        log("config created");

        log("creating BackUp completed");
        return true;
    }

    public boolean loadBackUp(String backUpId){
        log("load BackUp: "+backUpId);
        for(Player player : Bukkit.getOnlinePlayers()){
            LanguageManager language = new LanguageManager(player);
            player.kickPlayer(language.transalte(131)+ChatColor.GOLD+language.transalte(132));
        }
        deleteFolder(new File("plugins//Positionator//"));
        unzip("plugins//Positionator_BackUp//"+backUpId+"//"+backUpId+".zip", new File("plugins//"));
        log("loading BackUp completed");
        new DataFixer();
        return true;
    }

    public boolean deleteBackUp(String backUpId){
        deleteFolder(new File("plugins//Positionator_BackUp//"+backUpId));
        log("deleted BackUp "+backUpId);
        return true;
    }

    public void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName){
        try{
            if (fileToZip == null || !fileToZip.exists()) {
                return;
            }

            String zipEntryName = fileToZip.getName();
            if (parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) {
                zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
            }

            if (fileToZip.isDirectory()) {
                log("+" + zipEntryName);
                for (File file : fileToZip.listFiles()) {
                    addDirToZipArchive(zos, file, zipEntryName);
                }
            } else {
                log("   " + zipEntryName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = new FileInputStream(fileToZip);
                zos.putNextEntry(new ZipEntry(zipEntryName));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
        return true;
    }

    public boolean unzip(String fileZip, File destDir) {
        try {
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                log("+ "+zipEntry.getName());
                File newFile = new File(destDir, String.valueOf(zipEntry));
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
