/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgsystems.gameoflife;

/**
 *
 * @author domic
 */
public record Cell(int row, int col, char state) {
    static char Live = '@';
    static char Dead = '#';
    static char NotSet = '@';
}
