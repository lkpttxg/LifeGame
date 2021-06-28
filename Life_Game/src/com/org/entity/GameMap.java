package com.org.entity;

import com.org.logic.Game_control;
import com.org.view.Game_Frame;

import javax.swing.*;
import java.awt.*;

public class GameMap {
    private JButton[][] map;//地图数组
    private int[][] vice_map;//辅助数组
    private int row;
    private int col;


    public GameMap() {
    }

    //地图二位数组构造方法
    public GameMap(int row, int col,JPanel map_center) {
        this.row = row;
        this.col = col;
        this.map=new JButton[row][col];
        this.vice_map=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map[i][j]=new JButton();
                map[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));//设置边框颜色
                map[i][j].setBackground(Color.white);//设置背景颜色,默认为白色
                vice_map[i][j]=0;//0为存在细胞，1为不存在细胞
                map[i][j].addActionListener(Game_Frame.click);//添加点击监听
                map_center.add(map[i][j]);
            }
        }
    }
    //重置地图方法
    public void reset(){
    for(int i=0;i<row;i++)
    {
        for(int j=0;j<col;j++)
        {
            map[i][j].setBackground(Color.white);
        }
    }
    }
    //更新辅助数组
    public void updateViceMap(){
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++){
                if(map[i][j].getBackground().equals(Color.orange)){
                    vice_map[i][j]=1;
                }else{
                    vice_map[i][j]=0;
                }
            }
    }

    public JButton[][] getMap() {
        return map;
    }

    public void setMap(JButton[][] map) {
        this.map = map;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public int[][] getVice_map() {
        return vice_map;
    }

    public void setVice_map(int[][] vice_map) {
        this.vice_map = vice_map;
    }
}
