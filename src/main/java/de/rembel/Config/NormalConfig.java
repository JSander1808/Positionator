package de.rembel.Config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;

public class NormalConfig {
    private String path;
    private String prefix = ": ";

    public NormalConfig(String path){
        this.path=path;
    }

    public void init(){
        File file = new File(path);
        if(!file.exists()){
            String[] pathname = path.split("/");
            StringBuilder resultpath = new StringBuilder();
            for(int i = 0;i<pathname.length-1;i++){
                resultpath.append(pathname[i]+"/");
                File tempfile = new File(resultpath.toString());
                tempfile.mkdir();
            }
            try {
                File tempfile = new File(path);
                tempfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void set(String keyword, String value){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            boolean exist = false;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split(prefix);
                if(result[0].equalsIgnoreCase(keyword)){
                    data.add(keyword+prefix+value+"\n");
                    exist = true;
                }else{
                    data.add(temp+"\n");
                }
            }
            if(!exist){
                data.add(keyword+prefix+value+"\n");
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(data.get(i).toString());
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String keyword){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split(prefix);
                if(result[0].equalsIgnoreCase(keyword)){
                    reader.close();
                    return result[1];
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exist(){
        try{
            File file = new File(path);
            if(file.exists()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public boolean existdata(String keyword){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split(prefix);
                if(result[0].equalsIgnoreCase(keyword)){
                    if(result.length==2){
                        return true;
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setBoolean(String key, Boolean value){
        set(key, String.valueOf(value));
    }

    public Boolean getBoolean(String key){
        return Boolean.valueOf(get(key));
    }

    public void clearFile(){
        try {
            PrintWriter writer = new PrintWriter(new File(path));
            writer.write("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean remove(String keyword){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split(prefix);
                if(!result[0].equalsIgnoreCase(keyword)){
                    data.add(temp+"\n");
                }
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(data.get(i).toString());
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
