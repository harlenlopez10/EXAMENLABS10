/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author harle
 */
public class HashTable {
    public Entry lista;
    
    public HashTable(){
        lista = null;
    }
    
    public void add(String username, long pos){
        if(lista != null){
            Entry nodito = this.lista;
            while(nodito.siguiente != null){
                nodito = nodito.siguiente;
            }
            nodito.siguiente = new Entry(username, pos);
            System.out.println("Agregado");
        }else{
            lista = new Entry(username, pos);
            System.out.println("Agregado");
        }
    }
    
    public boolean remove(String username){
        if(lista.username.equals(username)){
            lista = lista.siguiente;
            System.out.println("Usuario removido con exito");
            return true;
        }else{
            Entry nodito = this.lista;
            while(nodito.siguiente != null){
                if(username.equals(nodito.siguiente.username)){
                    nodito.siguiente = nodito.siguiente.siguiente;
                    System.out.println("Usuario removido con exito");
                    return true;
                }
                nodito = nodito.siguiente;
            }
            return false;
        }
    }
    
    public long Search(String username){
        if(lista != null){
            Entry nodito = lista;
            while(nodito != null){
                if(username.equals(nodito.username)){
                    return nodito.position;
                }
                nodito = nodito.siguiente;
            }
            return -1;
        }
        return -1;
    }
}
