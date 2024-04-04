package de.rembel.Config;

import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.General.PositionFilter;
import de.rembel.General.PositionType;
import org.bukkit.entity.Player;

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

    public boolean rename(Position oldPosition, Position newPosition){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(result[0].equals(oldPosition.getName())){
                    data.add(newPosition.getName()+"->"+result[1]+"->"+result[2]+"->"+result[3]+"->"+result[4]);
                }else{
                    data.add(temp);
                }
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(General.encode(data.get(i).toString())+"\n");
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

    public boolean rename(String oldPositionName, String newPositionName){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(result[0].equals(oldPositionName)){
                    data.add(newPositionName+"->"+result[1]+"->"+result[2]+"->"+result[3]+"->"+result[4]);
                }else{
                    data.add(temp);
                }
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(General.encode(data.get(i).toString())+"\n");
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

    public boolean set(Position position){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            boolean exist = false;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(result[0].equals(position.getName())){
                    data.add(position.getName()+"->"+position.getPositionAsString()+"->"+position.getCreator()+"->"+position.getDimension()+"->"+position.getType());
                    exist = true;
                }else{
                    data.add(temp);
                }
            }
            if(!exist){
                data.add(position.getName()+"->"+position.getPositionAsString()+"->"+position.getCreator()+"->"+position.getDimension()+"->"+position.getType());
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(General.encode(data.get(i).toString())+"\n");
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

    public Position get(String positionName){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(result[0].equals(positionName)){
                    Position position = new Position(positionName, new String[]{result[1].split(" ")[0], result[1].split(" ")[1], result[1].split(" ")[2]}, result[2], result[3], Integer.valueOf(result[4]));
                    reader.close();
                    return position;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Position get(Position newPosition){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(result[0].equals(newPosition.getName())){
                    Position position = new Position(newPosition.getName(), new String[]{result[1].split(" ")[0], result[1].split(" ")[1], result[1].split(" ")[2]}, result[2], result[3], Integer.valueOf(result[4]));
                    reader.close();
                    return position;
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

    public boolean existPosition(Position position){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(result[0].equals(position.getName())){
                    if(result.length>=2){
                        reader.close();
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

    public boolean existPosition(String positionName){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(result[0].equals(positionName)){
                    if(result.length>=2){
                        reader.close();
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

    public Position[] list(){
        File file = new File(path);
        try {
            ArrayList<Position> data = new ArrayList<Position>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                Position position = new Position(temp.split("->")[0], new String[]{temp.split("->")[1].split(" ")[0], temp.split("->")[1].split(" ")[1], temp.split("->")[1].split(" ")[2]}, temp.split("->")[2], temp.split("->")[3], Integer.valueOf(temp.split("->")[4]));

                data.add(position);
            }
            Position[] finalData = new Position[data.size()];
            for(int i = 0;i< data.size();i++){
                finalData[i] = data.get(i);
            }
            reader.close();
            return finalData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Position[] list(PositionFilter filter){
        File file = new File(path);
        try {
            ArrayList<Position> data = new ArrayList<Position>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                Position position = new Position(temp.split("->")[0], new String[]{temp.split("->")[1].split(" ")[0], temp.split("->")[1].split(" ")[1], temp.split("->")[1].split(" ")[2]}, temp.split("->")[2], temp.split("->")[3], Integer.valueOf(temp.split("->")[4]));

                int distance = -1;
                if(filter != null){
                    if(filter.getPlayer().getWorld().getEnvironment().name().equals(position.getLocation().getWorld().getEnvironment().name())){
                        distance = (int) filter.getPlayer().getLocation().distance(position.getLocation());
                    }
                }

                if(filter == null || !filter.hasPlayername() || filter.getPlayername().equals(position.getCreator())){
                    if(filter == null || !filter.hasDimension() || filter.getDimension().equals(position.getDimension())){
                        if(filter == null || !filter.hasDistance() || distance <= filter.getDistance() && distance != -1){
                            data.add(position);
                        }
                    }
                }
            }
            Position[] finalData = new Position[data.size()];
            for(int i = 0;i< data.size();i++){
                finalData[i] = data.get(i);
            }
            reader.close();
            return finalData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Position[] list(PositionFilter filter, Player player){
        File file = new File(path);
        try {
            ArrayList<Position> data = new ArrayList<Position>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                Position position = new Position(temp.split("->")[0], new String[]{temp.split("->")[1].split(" ")[0], temp.split("->")[1].split(" ")[1], temp.split("->")[1].split(" ")[2]}, temp.split("->")[2], temp.split("->")[3], Integer.valueOf(temp.split("->")[4]));
                if(filter == null || !filter.hasPlayername() || filter.getPlayername().equals(position.getCreator())){
                    if(filter == null || !filter.hasDimension() || filter.getDimension().equals(position.getDimension())){
                        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
//                        if(config.getBoolean("showDeathPositionInList") && position.getType() != PositionType.DEATHPOSITION){
//                            data.add(position);
//                        }
                        if(position.getType()==PositionType.DEATHPOSITION){
                            if(config.getBoolean("showDeathPositionInList")){
                                data.add(position);
                            }
                        }else{
                            data.add(position);
                        }
                    }
                }
            }
            Position[] finalData = new Position[data.size()];
            for(int i = 0;i< data.size();i++){
                finalData[i] = data.get(i);
            }
            reader.close();
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
                temp = General.decode(temp);
                if(index>=from&&index<=end){
                    data.add(temp.split("->"));
                }
                index++;
            }
            String[][] finalData = new String[data.size()][2];
            for(int i = 0;i< data.size();i++){
                finalData[i] = data.get(i);
            }
            reader.close();
            return finalData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean remove(Position position){
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            boolean exist = false;
            while((temp = reader.readLine())!=null){
                temp = General.decode(temp);
                String[] result = temp.split("->");
                if(!result[0].equals(position.getName())){
                    data.add(temp);
                }
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(General.encode(data.get(i).toString())+"\n");
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