package de.rembel.Config;

import java.io.*;
import java.util.ArrayList;

public class Config {

    private String path;

    public Config(String path){
        this.path=path;
    }

    public void init(){
        File file = new File(path);
        if(!file.exists()){
            String[] pathname = path.split("//");
            StringBuilder resultpath = new StringBuilder();
            for(int i = 0;i<pathname.length-1;i++){
                resultpath.append(pathname[i]+"//");
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

    public void set(String keyword, String value,String author,String dimension,int type){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            boolean exist = false;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split("->");
                if(result[0].equalsIgnoreCase(keyword)){
                    data.add(keyword+"->"+value+"->"+author+"->"+dimension+"->"+type+"\n");
                    exist = true;
                }else{
                    data.add(temp+"\n");
                }
            }
            if(!exist){
                data.add(keyword+"->"+value+"->"+author+"->"+dimension+"->"+type+"\n");
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(data.get(i).toString());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] get(String keyword){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split("->");
                if(result[0].equalsIgnoreCase(keyword)){
                    String[] finalresult = new String[result.length];
                    for(int i = 1;i< result.length;i++){
                        finalresult[i] = result[i];
                    }
                    return finalresult;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exist(){
        File file = new File(path);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public boolean existdata(String keyword){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split("->");
                if(result[0].equalsIgnoreCase(keyword)){
                    if(result.length>=2){
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String[][] list(){
        File file = new File(path);
        try {
            ArrayList<String[]> data = new ArrayList<String[]>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                data.add(temp.split("->"));
            }
            String[][] finalData = new String[data.size()][2];
            for(int i = 0;i< data.size();i++){
                finalData[i] = data.get(i);
            }
            return finalData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String[][] listFormToEnd(int from, int end){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String[]> data = new ArrayList<String[]>();
            String temp = null;
            int index = 0;
            while((temp = reader.readLine())!=null){
                if(index>=from&&index<=end){
                    data.add(temp.split("->"));
                }
                index++;
            }
            String[][] finalData = new String[data.size()][2];
            for(int i = 0;i< data.size();i++){
                finalData[i] = data.get(i);
            }
            return finalData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(String keyword){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            boolean exist = false;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split("->");
                if(!result[0].equalsIgnoreCase(keyword)){
                    data.add(temp+"\n");
                }
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(data.get(i).toString());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}