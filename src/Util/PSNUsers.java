/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author harle
 */
public class PSNUsers {
    RandomAccessFile RAF, Troph;
    public HashTable users;
    
    public PSNUsers(){
        try{
            File file = new File("usuarios.psn");
            if(!file.exists()){
                file.createNewFile();
            }
            RAF = new RandomAccessFile("usuarios.psn","rw");
            
            file = new File("trofeos.psn");
            if(!file.exists()){
                file.createNewFile();
            }
            Troph = new RandomAccessFile("trofeos.psn","rw");
            
            reloadHashTable();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void reloadHashTable() throws IOException, FileNotFoundException{
        users = new HashTable();
        RAF.seek(0);
        while(RAF.getFilePointer() < RAF.length()){
            long largo = RAF.getFilePointer();
            String user = RAF.readUTF();
            RAF.readInt();
            RAF.readInt();
            if(RAF.readBoolean()){
                System.out.println("Agregado " + user);
                users.add(user, largo);
            }
        }
        System.out.println("Carga completa...");
    }
    
    public boolean addUser(String username) throws IOException{
        if(users.Search(username) == -1){
            RAF.seek(RAF.length());
            long largo = RAF.length();
            RAF.writeUTF(username);
            RAF.writeInt(0);
            RAF.writeInt(0);
            RAF.writeBoolean(true);
        
            users.add(username, largo);
            System.out.println("Agregado con exito...");
            return true;
        }
        return false;
    }
    
    public boolean deactivateUser(String Username) throws IOException{
        long largo = users.Search(Username);
        if(largo == -1){
            return false;
        }else{
            RAF.seek(largo);
            RAF.readUTF();
            RAF.readInt();
            RAF.readInt();
            RAF.writeBoolean(false);
            users.remove(Username);
            return true;
        }
    }
    
    public boolean addTrophieTo(String Username, String Game, String trophyName, Trophy type) throws IOException{
        long largo = users.Search(Username);
        if(largo == -1){
            return false;
        }else{
            
            
            RAF.seek(largo);
            RAF.readUTF();
            long position = RAF.getFilePointer();
            int value = RAF.readInt() + type.points;
            RAF.seek(position);
            RAF.writeInt(value);
            position = RAF.getFilePointer();
            value = RAF.readInt() + 1;
            RAF.seek(position);
            RAF.writeInt(value);
            
            
            Troph.seek(Troph.length());
            Troph.writeUTF(Username);
            Troph.writeUTF(Game);
            Troph.writeUTF(trophyName);
            Troph.writeUTF(type.name());
            Troph.writeLong(Calendar.getInstance().getTimeInMillis());
            return true;
        }
    }
    
    public String playerInfo(String Username) throws IOException{
        long user = users.Search(Username);
        if(user != -1){
            RAF.seek(user);
            String txt = "";
            txt += "Nombre del Usuario: " + RAF.readUTF() + "\n";
            txt += "Puntos por Trofeos: " +RAF.readInt() + "\n";
            txt += "Numero de Trofeos: " + RAF.readInt() + "\n";
            
            Troph.seek(0);
            while(Troph.getFilePointer() != Troph.length()){
                if(Troph.readUTF().equals(Username)){
                    String game= Troph.readUTF();
                    String trophyname = Troph.readUTF();
                    String type = Troph.readUTF();
                    long time = Troph.readLong();
                    Calendar today = Calendar.getInstance();
                    today.setTimeInMillis(time);
                    txt += "Fecha: " + new SimpleDateFormat("dd/MM/yy").format(today.getTime());
                    txt += " Tipo: " + type;
                    txt += " Juego: " + game;
                    txt += " Descripcion: "+ trophyname +"\n";
                }else{
                    Troph.readUTF();
                    Troph.readUTF();
                    Troph.readUTF();
                    Troph.readLong();
                }
            }
            return txt;
        }
        return "No existe dicho Usuario.";
    }

    
}
