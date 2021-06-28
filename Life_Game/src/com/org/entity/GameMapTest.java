package com.org.entity;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class GameMapTest extends GameMap {
    JPanel center=new JPanel(new GridLayout(10,10));
    GameMap gameMap=new GameMap(10,10,center);

    @Test
    public void testReset() {
        for(int i=0;i<gameMap.getRow();i++)
        {
            for(int j=0;j<gameMap.getCol();j++)
            {
                gameMap.getMap()[i][j].setBackground(Color.white);
            }
        }
        for(int i=0;i<gameMap.getRow();i++)
        {
            for(int j=0;j<gameMap.getCol();j++)
            {
                assertEquals(Color.white,gameMap.getMap()[i][j].getBackground());
            }
        }
    }

    @Test
    public void testUpdateViceMap() {
        for(int i=0;i<gameMap.getRow();i++)
            for(int j=0;j<gameMap.getCol();j++){
                if(gameMap.getMap()[i][j].getBackground().equals(Color.orange)){
                    gameMap.getVice_map()[i][j]=1;
                }else{
                    gameMap.getVice_map()[i][j]=0;
                }
            }
    }


}