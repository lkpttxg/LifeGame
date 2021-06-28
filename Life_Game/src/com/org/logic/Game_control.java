package com.org.logic;


import com.org.entity.GameMap;
import com.org.view.Game_Frame;

import javax.swing.*;
import java.awt.*;
import java.security.KeyStore;

public class Game_control {
    private GameMap m_map;
    private boolean flag = false;
    public  Game_control(){}
    public Game_control(GameMap map) {
        this.m_map = map;
    }

    //地图不可以点击
    public void setNotClick() {
        for (int i = 0; i < m_map.getRow(); i++) {
            for (int j = 0; j < m_map.getCol(); j++) {
                m_map.getMap()[i][j].setEnabled(false);
            }
        }
    }

    //重置地图
    public void clear_map() {
        for (int i = 0; i < m_map.getRow(); i++) {
            for (int j = 0; j < m_map.getCol(); j++) {
                m_map.getMap()[i][j].setBackground(Color.white);//设置背景颜色
                m_map.getMap()[i][j].setEnabled(true);
            }
        }
    }

    //预报改变一周期的信息
    public void nextTerm() {
        if (flag == false) {
            for (int i = 0; i < m_map.getRow(); i++)
                for (int j = 0; j < m_map.getCol(); j++) {
                    setState(i, j);          //下一周期预报
                }
            flag = true;

        } else {
            for (int i = 0; i < m_map.getRow(); i++)
                for (int j = 0; j < m_map.getCol(); j++) {
                    add_remove(i, j);       //下一周期
                }
            flag = false;
        }
    }
    //直接一周期的信息
    public void fastTerm() {
        if (flag) {//存在渐变细胞时
            for (int i = 0; i < m_map.getRow(); i++)
                for (int j = 0; j < m_map.getCol(); j++) {
                    add_remove(i, j);       //有提示的下一周期
                }
            flag = false;
        }//不存在渐变细胞时
        else {
            m_map.updateViceMap();
            for (int i = 0; i < m_map.getRow(); i++)
                for (int j = 0; j < m_map.getCol(); j++) {
                    setState(i, j, m_map.getVice_map());          //辅助数组的下一周期预报
                }
            for (int i = 0; i < m_map.getRow(); i++)
                for (int j = 0; j < m_map.getCol(); j++) {
                    add_remove_without_tip(i, j, m_map.getVice_map());//没有提示的下一周期
                }
        }
    }
    //随机布局
    public void randSet(){
        clear_map();
        //生成活细胞的随机个数
        int size= m_map.getCol()* m_map.getRow();
        int num=(int)(size/5+Math.random()*100+1);

        //根据活细胞随机个数，像地图上随机撒种子
        for(int i=0;i<num;i++) {
            int row = (int) (0 + Math.random() * m_map.getRow());
            int col = (int) (0 + Math.random() * m_map.getCol());
            if (m_map.getMap()[row][col].getBackground().equals(Color.orange)) continue;
            else {
                m_map.getMap()[row][col].setBackground(Color.orange);
            }
        }
    }
    /**
     * 设置某个位置的状态
     */
    public void setState(int i, int j) {
        switch (countSurround(i, j)) {
            case 0:
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                if(m_map.getMap()[i][j].getBackground().equals(Color.orange)) {
                    m_map.getMap()[i][j].setBackground(Color.gray);     //黄变灰，即将要死亡
                }
                break;
            case 3:
                if(m_map.getMap()[i][j].getBackground().equals(Color.white)) {
                    m_map.getMap()[i][j].setBackground(Color.blue);//白变蓝，要变活
                }
                break;
            default:
                break;
        }
    }
    /**
     * 设置辅助地图某个位置的状态
     */
    public void setState(int i, int j,int[][] vice_map) {
        switch (countSurround(i, j,vice_map)) {
            case 0:
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                if(vice_map[i][j]==1) {//当前细胞表示为1
                    vice_map[i][j]=2;     //2表示即将要死亡的细胞
                }
                break;
            case 3:
                if(vice_map[i][j]==0) {//0表示为已死亡细胞
                    vice_map[i][j]=3;//3表示即将活的细胞
                }
                break;
            default:
                break;
        }
    }
    /**
     * 计算对应位置周围的数量
     */
    public int countSurround(int i, int j) {
        //获得周围8个位置细胞数量
        return isalived(i - 1, j - 1) + isalived(i - 1, j) + isalived(i - 1, j + 1) + isalived(i, j - 1) + isalived(i, j + 1)
                + isalived(i + 1, j - 1) + isalived(i + 1, j) + isalived(i + 1, j + 1);
    }
    /**
     * 计算辅助地图数组对应位置周围的数量
     */
    public int countSurround(int i,int j,int[][] vice_map){
        return isalived(i - 1, j - 1,vice_map) + isalived(i - 1, j,vice_map) + isalived(i - 1, j + 1,vice_map) + isalived(i, j - 1,vice_map) + isalived(i, j + 1,vice_map)
                + isalived(i + 1, j - 1,vice_map) + isalived(i + 1, j,vice_map) + isalived(i + 1, j + 1,vice_map);
    }
    /**
     * 计算某个位置是否存在细胞
     * 如果有返回1，没有返回0
     */
    public int isalived(int i, int j) {
        //int Row= m_map.getRow();
        //int Col= m_map.getCol();
        /*int m=i%Row,n=j%Col;        //定义地图两边相连
        if(m<0)
        {
            m+=Row;
        }
        if(n<0)
        {
            n+=Col;
        }*/
        if(i<0||i>=m_map.getRow()||j<0||j>= m_map.getCol())//如果下标超出边界，则记为0
            return 0;
        if (m_map.getMap()[i][j].getBackground().equals(Color.orange)||m_map.getMap()[i][j].getBackground().equals(Color.gray))
            return 1;        //黄和灰都属于上一周期的活细胞
        else
            return 0;
    }
    /**
     * 计算辅助地图数组某个位置是否存在细胞
     * 如果有返回1，没有返回0
     */
    public int isalived(int i, int j,int[][] vice_map) {
        /*int Row= m_map.getRow();
        int Col= m_map.getCol();
        int m=i%Row,n=j%Col;             //定义地图两边相连
        if(m<0)
        {
            m+=Row;
        }
        if(n<0)
        {
            n+=Col;
        }*/
        if(i<0||i>=m_map.getRow()||j<0||j>= m_map.getCol())//如果下标超出边界，则记为0
              return 0;
        if (vice_map[i][j]==1||vice_map[i][j]==2)
            return 1;        //1和2都是属于活细胞
        else
            return 0;
    }
    /**
     * 更新细胞皿，新增复活细胞，移除死亡细胞
     */
    public void add_remove(int i,int j) {
        if(m_map.getMap()[i][j].getBackground().equals(Color.blue)) {
            m_map.getMap()[i][j].setBackground(Color.orange);
        }else if(m_map.getMap()[i][j].getBackground().equals(Color.gray)) {
            m_map.getMap()[i][j].setBackground(Color.white);
        }else {
            return;
        }
    }
    /**
     * 更新细胞皿，一次更新
     */
    public void add_remove_without_tip(int i,int j,int[][] vice_map)
    {
        //根据辅助数组来直接变换
        if(vice_map[i][j]==3){//3表示将要活的细胞
            m_map.getMap()[i][j].setBackground(Color.orange);
        }else if(vice_map[i][j]==2) {//2表示将要死的细胞
            m_map.getMap()[i][j].setBackground(Color.white);
        }else {
            return;
        }
    }

    /**
     * 判断游戏是否结束
     */
    public boolean isOver(){
        //如果细胞没有死也没有生，则游戏已结束
        for(int i=0;i< m_map.getRow();i++){
            for(int j=0;j< m_map.getCol();j++){
                if(m_map.getMap()[i][j].getBackground().equals(Color.blue)||m_map.getMap()[i][j].getBackground().equals(Color.gray)){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 判断游戏是否结束
     */
    public boolean isOver(int[][] vice_map){
        //如果下一次辅助数组进行演化状态不变
        m_map.updateViceMap();//将地图信息复制进去
        //进行一次模拟改变
        for (int i = 0; i < m_map.getRow(); i++)
            for (int j = 0; j < m_map.getCol(); j++) {
                setState(i, j, m_map.getVice_map());          //辅助数组的下一周期预报
            }
        //如果细胞没有死也没有生，则游戏已结束
        for(int i=0;i< m_map.getRow();i++){
            for(int j=0;j< m_map.getCol();j++){
                if(vice_map[i][j]==2||vice_map[i][j]==3){
                    return false;
                }
            }
        }
        return true;
    }
    public GameMap getM_map() {
        return m_map;
    }

    public void setM_map(GameMap m_map) {
        this.m_map = m_map;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
