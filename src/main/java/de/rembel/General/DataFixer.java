package de.rembel.General;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.Config.OldNormalConfig;
import de.rembel.Main.PositionatorMain;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;

public class DataFixer {

    private Plugin plugin = PositionatorMain.getPlugin();

    public DataFixer(){
        log("started...");
        if(!new File("plugins//Positionator").exists()) new File("plugins//Positionator").mkdir();
        log("checked for Main Folder");

        if(checkVersion(1)) Fix1();
        if(checkVersion(2)) Fix2();




        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
        config.init();
        if(!config.existdata("enableDeletePositionsFromOtherPlayer")) config.set("enableDeletePositionsFromOtherPlayer","true");
        if(!config.existdata("sendUpdateMessages")) config.set("sendUpdateMessages","true");
        if(!config.existdata("allowOpToTeleport")) config.set("allowOpToTeleport","false");
        if(!config.existdata("allowPlayerToTeleport")) config.set("allowPlayerToTeleport","false");
        log("config initialized");

        log("completed");
    }

    private boolean Fix1(){
        File configFile = new File("plugins//Positionator//config.yml");
        File dataFolder = new File("plugins//Positionator//Data");
        File userFolder = new File("plugins//Positionator//Data//User");

        configFile.delete();
        log("Config deleted");
        dataFolder.mkdir();
        log("Data Folder created");
        userFolder.mkdir();
        log("User Folder created");

        if(new File("plugins//Positionator//public.conf").exists()){
            moveFile(new File("plugins//Positionator//public.conf"),new File("plugins//Positionator//Data//public.conf"));
        }else{
            try {
                new File("plugins//Positionator//Data//public.conf").createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log("new public data File created");
        }

        ArrayList<String> ignoreFiles = new ArrayList<String>();
        ignoreFiles.add("buildVersion.yml");
        ignoreFiles.add("config.yml");
        ignoreFiles.add("public.conf");
        log("set up ignored Files");

        for(File tempFile : new File("plugins//Positionator//").listFiles()){
            if(!ignoreFiles.contains(tempFile.getName()) && tempFile.isFile()){
                File playerFolder = new File("plugins//Positionator//Data//User//"+tempFile.getName().split("\\.")[0]);
                playerFolder.mkdir();
                File playerPositionFile = new File("plugins//Positionator//Data//User//"+tempFile.getName().split("\\.")[0]+"//data.conf");
                try {
                    playerPositionFile.createNewFile();
                    BufferedReader reader = new BufferedReader(new FileReader(tempFile));
                    PrintWriter writer = new PrintWriter(playerPositionFile);
                    String lineData = null;
                    while((lineData = reader.readLine())!= null){
                        writer.write(lineData+"\n");
                    }
                    writer.flush();
                    writer.close();
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                log("converted playerdata for "+tempFile.getName().split("\\.")[0]);
                NormalConfig playerConfig = new NormalConfig("plugins//Positionator//Data//User//"+tempFile.getName().split("\\.")[0]+"//config.yml");
                playerConfig.init();
                if(!playerConfig.existdata("showDeathPositionInList")) playerConfig.set("showDeathPositionInList","true");
                if(!playerConfig.existdata("setDeathPositionInBossbar")) playerConfig.set("setDeathPositionInBossbar","true");
                if(!playerConfig.existdata("enableFilter")) playerConfig.set("enableFilter","true");
                if(!playerConfig.existdata("enableMenuClickSound")) playerConfig.set("enableMenuClickSound","true");
                tempFile.delete();
                log("created playerconfig for "+tempFile.getName().split("\\.")[0]);
            }
        }
        return true;
    }

    private boolean Fix2(){
        try{
            for(File temp : new File("plugins//Positionator//Data//User").listFiles()){
                File dataFile = new File("plugins//Positionator//Data//User//"+temp.getName()+"//data.conf");
                ArrayList<String> data = new ArrayList<String>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                    String tempData = null;
                    while((tempData = reader.readLine()) != null){
                        data.add(tempData);
                    }
                    reader.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    PrintWriter writer = new PrintWriter(dataFile);
                    for(String tempData : data){
                        writer.write(General.encode(tempData)+"\n");
                    }
                    writer.flush();
                    writer.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                log("encoded data for File "+dataFile.toPath().toString());
            }

            File publicFile = new File("plugins//Positionator//Data//public.conf");
            BufferedReader reader = new BufferedReader(new FileReader(publicFile));
            ArrayList<String> publicData = new ArrayList<String>();
            String tempPublicData = null;
            while((tempPublicData = reader.readLine()) != null){
                publicData.add(tempPublicData);
            }

            PrintWriter writer = new PrintWriter(publicFile);
            for(String tempData : publicData){
                writer.write(General.encode(tempData)+"\n");
            }
            log("encoded public data");
            writer.flush();
            writer.close();
        }catch(Exception e){}
        return true;
    }

    private boolean checkVersion(int version){
        File file = new File("plugins//Positionator//buildVersion.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
                log("created buildVersion File");
                PrintWriter writer = new PrintWriter(file);
                writer.write("V: 0\n");
                writer.write("#Do never change this Value!");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        int buildVersion;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            buildVersion = Integer.valueOf(reader.readLine().split(": ")[1]);
            reader.close();
            log("read buildVersion from File");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(buildVersion<version){
            try {
                PrintWriter writer = new PrintWriter(file);
                writer.write("V: "+version+"\n");
                writer.write("#Do never change this Value");
                writer.flush();
                writer.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            log("update buildVersion");
            return true;
        }
        return false;
    }

    private void log(String message){
        plugin.getLogger().log(Level.INFO,"[DataFixer] "+message);
    }

    private void copy(File source, File destination){
        try {
            Files.copy(source.toPath(), destination.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log("copy File "+source.getName()+" to "+destination.toPath().toString());
    }
    private void moveFile(File source, File destination){
        try {
            Files.copy(source.toPath(), destination.toPath());
            source.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log("copy File "+source.getName()+" to "+destination.toPath().toString());
    }

    public static void deleteFolder(File folder) {
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
    }
}
