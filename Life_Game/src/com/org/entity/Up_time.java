package com.org.entity;

import com.org.logic.Game_control;

import javax.swing.*;
import javax.xml.stream.events.StartDocument;
import java.awt.event.ActionListener;

public class Up_time {
    private Timer updateTimer;//时间触发对象
    private int ev_delay=1000;//默认周期为1s
    private Game_control control;//控制对象
    private JButton m_pasue,m_evolution,m_next_tip_cycle,m_next_cycle,m_evolution_fast;
    //预报提示更新监听器
    ActionListener updating= e->{
        control.nextTerm();
        //检验是否游戏结束
        if(control.isFlag()) {
            if (control.isOver()) {
                JOptionPane.showMessageDialog(null, "生命游戏结束");
                if(updateTimer.isRunning()){
                    updateTimer.stop();
                }
                //设置相关按钮不可用
                m_pasue.setEnabled(false);
                m_next_tip_cycle.setEnabled(false);
                m_evolution.setEnabled(false);
                m_next_cycle.setEnabled(false);
                m_evolution_fast.setEnabled(false);
            }
        }
    };
    //直接快速更新监听器
    ActionListener fast_updating= e->{
        //检验游戏是否结束
        if(control.isOver(control.getM_map().getVice_map())){
            JOptionPane.showMessageDialog(null, "生命游戏结束");
            if(updateTimer.isRunning()){
                updateTimer.stop();
            }
            //设置相关按钮不可用
            m_pasue.setEnabled(false);
            m_next_tip_cycle.setEnabled(false);
            m_evolution.setEnabled(false);
            m_next_cycle.setEnabled(false);
            m_evolution_fast.setEnabled(false);
        }
        control.fastTerm();
    };
    public  Up_time(){}
    //构造周期对象
    public Up_time(Game_control control)
    {
        this.control=control;
        updateTimer=new Timer(ev_delay,updating);
    }
    //设置演化周期
    public void set_delay(int delay)
    {
        ev_delay=delay;
        updateTimer.setDelay(ev_delay);
    }
    //提示演化
    public void tip_updating()
    {
        Timer uptime=new Timer(ev_delay,updating);
        updateTimer=uptime;
    }
    //快速演化
    public void fast_updating()
    {
        Timer uptime=new Timer(ev_delay,fast_updating);
        updateTimer=uptime;
    }

    public int get_delay()
    {
        return ev_delay;
    }

    public  ActionListener get_updating()
    {
        return updating;
    }

    public boolean judge_running()
    {
        return updateTimer.isRunning();
    }

    public void StartDocument()
    {
        updateTimer.start();
    }

    public void StopDocument()
    {
        updateTimer.stop();
    }

    public  ActionListener get_fast_updating()
    {
        return fast_updating;
    }
    //设置里面涉及的Button按钮
    public void setButton(JButton m_pasue,JButton m_evolution,JButton m_next_tip_cycle,JButton m_next_cycle,JButton m_evolution_fast) {
        this.m_pasue = m_pasue;
        this.m_evolution=m_evolution;
        this.m_next_tip_cycle=m_next_tip_cycle;
        this.m_next_cycle=m_next_cycle;
        this.m_evolution_fast=m_evolution_fast;
    }
}
