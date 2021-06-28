package com.org.entity;

import com.org.logic.Game_control;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class Up_timeTest extends Up_time {
    JPanel center=new JPanel(new GridLayout(10,10));
    GameMap gameMap=new GameMap(10,10,center);
    Game_control control=new Game_control(gameMap);
    Up_time up_time=new Up_time(control);



}