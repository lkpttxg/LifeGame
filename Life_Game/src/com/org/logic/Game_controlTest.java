package com.org.logic;

import com.org.entity.GameMap;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class Game_controlTest extends Game_control {
    JPanel center=new JPanel(new GridLayout(10,10));
    GameMap gameMap=new GameMap(10,10,center);
    Game_control control=new Game_control(gameMap);
    @Test
    public void testSetNotClick() {
        control.setNotClick();
        for (int i = 0; i < control.getM_map().getRow(); i++) {
            for (int j = 0; j < control.getM_map().getCol(); j++) {
                assertEquals(false, control.getM_map().getMap()[i][j].isEnabled());

            }
        }
    }

    @Test
    public void testClear_map() {
        control.clear_map();
        for (int i = 0; i < control.getM_map().getRow(); i++) {
            for (int j = 0; j < control.getM_map().getCol(); j++) {
                assertEquals(true, control.getM_map().getMap()[i][j].isEnabled());
            }
        }
    }

    @Test
    public void testNextTerm() {
        boolean flag=false;
        control.randSet();
        control.nextTerm();
        if(control.isFlag()){
           a: for (int i = 0; i < control.getM_map().getRow(); i++) {
                for (int j = 0; j < control.getM_map().getCol(); j++) {
                    if(control.getM_map().getMap()[i][j].getBackground().equals(Color.blue)||control.getM_map().getMap()[i][j].getBackground().equals(Color.gray)){
                        flag=true;
                        break a;
                    }
                }
            }
            assertEquals(true,flag);
        }
        else{
            flag=true;
            b: for (int i = 0; i < control.getM_map().getRow(); i++) {
                for (int j = 0; j < control.getM_map().getCol(); j++) {
                    if(control.getM_map().getMap()[i][j].getBackground().equals(Color.blue)||control.getM_map().getMap()[i][j].getBackground().equals(Color.gray)){
                        flag=false;
                        break b;
                    }
                }
            }
            assertEquals(false,flag);
        }
    }

    @Test
    public void testFastTerm() {
        boolean flag=true;
        control.randSet();
        control.fastTerm();
        b: for (int i = 0; i < control.getM_map().getRow(); i++) {
            for (int j = 0; j < control.getM_map().getCol(); j++) {
                if(control.getM_map().getMap()[i][j].getBackground().equals(Color.blue)||control.getM_map().getMap()[i][j].getBackground().equals(Color.gray)){
                    flag=false;
                    break b;
                }
            }
        }
        assertEquals(true,flag);
    }

    @Test
    public void testRandSet() {
        control.randSet();
        int size= control.getM_map().getCol()* control.getM_map().getRow();
        int count=0;
        int rangeLow=size/5;
        int rangeUp=rangeLow+100;
        boolean flag=false;
        b: for (int i = 0; i < control.getM_map().getRow(); i++) {
            for (int j = 0; j < control.getM_map().getCol(); j++) {
                if(control.getM_map().getMap()[i][j].getBackground().equals(Color.orange)){
                    count++;
                }
            }
        }
        if(count>rangeLow&&count<rangeUp){
            flag=true;
        }
        assertEquals(true,flag);
    }
    @Test
    public void testState1(){//重载方法测试，两个参数
        int i=1,j=1;
        boolean flag=false;
        //本身为黄色，周围无活细胞
        control.getM_map().getMap()[i][j].setBackground(Color.orange);
        control.getM_map().getMap()[i-1][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j+1].setBackground(Color.white);
        control.getM_map().getMap()[i][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i][j+1].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j+1].setBackground(Color.white);
        control.setState(i,j);
        //变灰色
        if(control.getM_map().getMap()[i][j].getBackground().equals(Color.gray)){
            flag=true;
        }
        assertEquals(true,flag);
        flag=false;
        //本身为白色，周围三个活细胞
        control.getM_map().getMap()[i][j].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j-1].setBackground(Color.orange);
        control.getM_map().getMap()[i-1][j].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j+1].setBackground(Color.orange);
        control.getM_map().getMap()[i][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i][j+1].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j-1].setBackground(Color.orange);
        control.getM_map().getMap()[i+1][j].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j+1].setBackground(Color.white);
        control.setState(i,j);
        //白色变蓝色
        if(control.getM_map().getMap()[i][j].getBackground().equals(Color.blue)){
            flag=true;
        }
        assertEquals(true,flag);
    }
    @Test
    public void testState2(){//重载方法测试，三个参数
        int i=1,j=1;
        boolean flag=false;
        int[][] vice=control.getM_map().getVice_map();
        //本身为黄色，周围无活细胞
        control.getM_map().getVice_map()[i][j]=1;
        control.getM_map().getVice_map()[i-1][j-1]=0;
        control.getM_map().getVice_map()[i-1][j]=0;
        control.getM_map().getVice_map()[i-1][j+1]=0;
        control.getM_map().getVice_map()[i][j-1]=0;
        control.getM_map().getVice_map()[i][j+1]=0;
        control.getM_map().getVice_map()[i+1][j-1]=0;
        control.getM_map().getVice_map()[i+1][j]=0;
        control.getM_map().getVice_map()[i+1][j+1]=0;
        control.setState(i,j,vice);
        //变灰色
        if(control.getM_map().getVice_map()[i][j]==2){
            flag=true;
        }
        assertEquals(true,flag);
        flag=false;
        //本身为白色，周围三个活细胞
        control.getM_map().getVice_map()[i][j]=0;
        control.getM_map().getVice_map()[i-1][j-1]=1;
        control.getM_map().getVice_map()[i-1][j]=1;
        control.getM_map().getVice_map()[i-1][j+1]=1;
        control.getM_map().getVice_map()[i][j-1]=0;
        control.getM_map().getVice_map()[i][j+1]=0;
        control.getM_map().getVice_map()[i+1][j-1]=0;
        control.getM_map().getVice_map()[i+1][j]=0;
        control.getM_map().getVice_map()[i+1][j+1]=0;
        control.setState(i,j,vice);
        //变蓝色
        if(control.getM_map().getVice_map()[i][j]==3){
            flag=true;
        }
        assertEquals(true,flag);
    }
    @Test
    public void testCountSurround1(){//重载方法测试，两个参数
        int i=1,j=1;
        int count=0;
        //设置周围8个细胞的状态
        control.getM_map().getMap()[i-1][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j+1].setBackground(Color.white);
        control.getM_map().getMap()[i][j-1].setBackground(Color.gray);
        control.getM_map().getMap()[i][j+1].setBackground(Color.orange);
        control.getM_map().getMap()[i+1][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j+1].setBackground(Color.white);
        count=control.countSurround(i,j);
        //得数量
        assertEquals(2,count);

    }
    @Test
    public void testCountSurround2(){//重载方法测试，三个参数
        int i=1,j=1;
        int count=0;
        int[][] vice=control.getM_map().getVice_map();
        //设置周围8个细胞的状态
        control.getM_map().getVice_map()[i-1][j-1]=3;
        control.getM_map().getVice_map()[i-1][j]=0;
        control.getM_map().getVice_map()[i-1][j+1]=0;
        control.getM_map().getVice_map()[i][j-1]=1;
        control.getM_map().getVice_map()[i][j+1]=0;
        control.getM_map().getVice_map()[i+1][j-1]=0;
        control.getM_map().getVice_map()[i+1][j]=2;
        control.getM_map().getVice_map()[i+1][j+1]=0;
        count=control.countSurround(i,j,vice);
        assertEquals(2,count);
    }

    @Test
    public void testIsalived1() {//重载方法测试，两个参数
        int flag=0;
        int i=1,j=1;
        control.getM_map().getMap()[i][j].setBackground(Color.BLUE);
        flag=control.isalived(i,j);
        assertEquals(0,flag);
        i=1;
        j=2;
        control.getM_map().getMap()[i][j].setBackground(Color.orange);
        flag=control.isalived(i,j);
        assertEquals(1,flag);
        i=1;
        j=3;
        control.getM_map().getMap()[i][j].setBackground(Color.gray);
        flag=control.isalived(i,j);
        assertEquals(1,flag);
        i=1;
        j=4;
        control.getM_map().getMap()[i][j].setBackground(Color.white);
        flag=control.isalived(i,j);
        assertEquals(0,flag);

    }
    @Test
    public void testIsalived2() {//重载方法测试，三个参数
        int flag=0;
        int i=1,j=1;
        int[][] vice=control.getM_map().getVice_map();
        control.getM_map().getVice_map()[i][j]=3;//3表示复活
        flag=control.isalived(i,j,vice);
        assertEquals(0,flag);
        i=1;
        j=2;

        control.getM_map().getVice_map()[i][j]=1;//1表示无变化
        flag=control.isalived(i,j,vice);
        assertEquals(1,flag);

        i=1;
        j=3;
        control.getM_map().getVice_map()[i][j]=2;//2表示将于死亡
        flag=control.isalived(i,j,vice);
        assertEquals(1,flag);

        i=1;
        j=4;

        control.getM_map().getVice_map()[i][j]=0;//0死亡的
        flag=control.isalived(i,j,vice);
        assertEquals(0,flag);

    }
    @Test
    public void testAdd_remove1(){//重载方法测试，两个参数
        int i=1,j=1;
        control.getM_map().getMap()[i][j].setBackground(Color.blue);//变orange
        control.add_remove(i,j);//进行测试
        assertEquals(Color.orange,control.getM_map().getMap()[i][j].getBackground());//比较结果

        i=1;
        j=2;
        control.getM_map().getMap()[i][j].setBackground(Color.gray);//变white
        control.add_remove(i,j);//进行测试
        assertEquals(Color.white,control.getM_map().getMap()[i][j].getBackground());//比较结果
    }
    @Test
    public void testAdd_remove2(){//重载方法测试，三个参数
        int i=1,j=1;
        int[][] vice=control.getM_map().getVice_map();
        control.getM_map().getVice_map()[i][j]=3;//活的，变orange
        control.add_remove_without_tip(i,j,vice);//进行测试
        assertEquals(Color.orange,control.getM_map().getMap()[i][j].getBackground());//比较结果

        i=1;
        j=2;
        control.getM_map().getVice_map()[i][j]=2;//要死，变white
        control.add_remove_without_tip(i,j,vice);//进行测试
        assertEquals(Color.white,control.getM_map().getMap()[i][j].getBackground());//比较结果
    }
    @Test
    public void testIsOver1() {//重载方法测试，两个参数
        control.getM_map().reset();
        //检验是否游戏结束
        assertEquals(true,control.isOver());
    }

    @Test
    public void testIsOver2() {//重载方法测试，三个参数
        control.getM_map().reset();
        int[][] vice=control.getM_map().getVice_map();
        //检验是否游戏结束
        assertEquals(true,control.isOver(vice));
    }

    @Test
    public void testGetM_map() {
        assertEquals(gameMap,control.getM_map());
    }


    @Test
    public void testIsFlag() {
        control.setFlag(true);
        assertEquals(true,control.isFlag());
    }


}