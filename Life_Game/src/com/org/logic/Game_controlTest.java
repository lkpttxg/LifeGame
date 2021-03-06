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
    public void testState1(){//?????????????????????????????????
        int i=1,j=1;
        boolean flag=false;
        //????????????????????????????????????
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
        //?????????
        if(control.getM_map().getMap()[i][j].getBackground().equals(Color.gray)){
            flag=true;
        }
        assertEquals(true,flag);
        flag=false;
        //???????????????????????????????????????
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
        //???????????????
        if(control.getM_map().getMap()[i][j].getBackground().equals(Color.blue)){
            flag=true;
        }
        assertEquals(true,flag);
    }
    @Test
    public void testState2(){//?????????????????????????????????
        int i=1,j=1;
        boolean flag=false;
        int[][] vice=control.getM_map().getVice_map();
        //????????????????????????????????????
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
        //?????????
        if(control.getM_map().getVice_map()[i][j]==2){
            flag=true;
        }
        assertEquals(true,flag);
        flag=false;
        //???????????????????????????????????????
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
        //?????????
        if(control.getM_map().getVice_map()[i][j]==3){
            flag=true;
        }
        assertEquals(true,flag);
    }
    @Test
    public void testCountSurround1(){//?????????????????????????????????
        int i=1,j=1;
        int count=0;
        //????????????8??????????????????
        control.getM_map().getMap()[i-1][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j].setBackground(Color.white);
        control.getM_map().getMap()[i-1][j+1].setBackground(Color.white);
        control.getM_map().getMap()[i][j-1].setBackground(Color.gray);
        control.getM_map().getMap()[i][j+1].setBackground(Color.orange);
        control.getM_map().getMap()[i+1][j-1].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j].setBackground(Color.white);
        control.getM_map().getMap()[i+1][j+1].setBackground(Color.white);
        count=control.countSurround(i,j);
        //?????????
        assertEquals(2,count);

    }
    @Test
    public void testCountSurround2(){//?????????????????????????????????
        int i=1,j=1;
        int count=0;
        int[][] vice=control.getM_map().getVice_map();
        //????????????8??????????????????
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
    public void testIsalived1() {//?????????????????????????????????
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
    public void testIsalived2() {//?????????????????????????????????
        int flag=0;
        int i=1,j=1;
        int[][] vice=control.getM_map().getVice_map();
        control.getM_map().getVice_map()[i][j]=3;//3????????????
        flag=control.isalived(i,j,vice);
        assertEquals(0,flag);
        i=1;
        j=2;

        control.getM_map().getVice_map()[i][j]=1;//1???????????????
        flag=control.isalived(i,j,vice);
        assertEquals(1,flag);

        i=1;
        j=3;
        control.getM_map().getVice_map()[i][j]=2;//2??????????????????
        flag=control.isalived(i,j,vice);
        assertEquals(1,flag);

        i=1;
        j=4;

        control.getM_map().getVice_map()[i][j]=0;//0?????????
        flag=control.isalived(i,j,vice);
        assertEquals(0,flag);

    }
    @Test
    public void testAdd_remove1(){//?????????????????????????????????
        int i=1,j=1;
        control.getM_map().getMap()[i][j].setBackground(Color.blue);//???orange
        control.add_remove(i,j);//????????????
        assertEquals(Color.orange,control.getM_map().getMap()[i][j].getBackground());//????????????

        i=1;
        j=2;
        control.getM_map().getMap()[i][j].setBackground(Color.gray);//???white
        control.add_remove(i,j);//????????????
        assertEquals(Color.white,control.getM_map().getMap()[i][j].getBackground());//????????????
    }
    @Test
    public void testAdd_remove2(){//?????????????????????????????????
        int i=1,j=1;
        int[][] vice=control.getM_map().getVice_map();
        control.getM_map().getVice_map()[i][j]=3;//????????????orange
        control.add_remove_without_tip(i,j,vice);//????????????
        assertEquals(Color.orange,control.getM_map().getMap()[i][j].getBackground());//????????????

        i=1;
        j=2;
        control.getM_map().getVice_map()[i][j]=2;//????????????white
        control.add_remove_without_tip(i,j,vice);//????????????
        assertEquals(Color.white,control.getM_map().getMap()[i][j].getBackground());//????????????
    }
    @Test
    public void testIsOver1() {//?????????????????????????????????
        control.getM_map().reset();
        //????????????????????????
        assertEquals(true,control.isOver());
    }

    @Test
    public void testIsOver2() {//?????????????????????????????????
        control.getM_map().reset();
        int[][] vice=control.getM_map().getVice_map();
        //????????????????????????
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