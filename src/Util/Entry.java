/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author harle
 */
public class Entry {
    String username;
    long position;
    Entry siguiente;

    public Entry(String username, long position) {
        this.username = username;
        this.position = position;
        siguiente = null;
    } 
}
