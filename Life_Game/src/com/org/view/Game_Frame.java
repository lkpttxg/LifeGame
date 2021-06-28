package com.org.view;

import com.org.entity.GameMap;
import com.org.entity.Up_time;
import com.org.logic.Game_control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Game_Frame extends JFrame{
    private int Rows=50;//行数
    private int Cols=70;//列数
    private GameMap m_map;//地图对象
    private Game_control control;//控制对象
    private Up_time upTime;//时间对象
    /*位于地图右侧的按钮*/
    private final JButton m_apply=new JButton("应用变化周期");
    private final JButton m_finish=new JButton("布置完成");
    private final JButton m_random=new JButton("随机布置");
    private final JButton m_next_tip_cycle=new JButton("(提示)单步演化");
    private final JButton m_next_cycle=new JButton("(无提示）单步演化");
    private final JButton m_evolution = new JButton("(提示)连续演化");
    private final JButton m_evolution_fast = new JButton("(无提示）迅速演化");
    private final JButton m_pause = new JButton("停止演化");
    /*地图上方提示框*/
    private final JTextField m_tip = new JTextField(80);
    private final JTextField m_text=new JTextField("1000");
    /*界面面板*/
    private final JPanel m_center=new JPanel(new GridLayout(Rows, Cols, 1, 1));// 细胞皿面板;
    private final JPanel m_north = new JPanel(new FlowLayout(FlowLayout.LEFT));//上方提示面板
    private final JPanel m_east = new JPanel(new GridLayout(10,1));//右侧总面板
    //右侧网格布局的10个面板
    private final JPanel j_alive=new JPanel(new GridLayout(1,2));//右侧第一行，提示当前细胞
    private final JPanel j_willAlive=new JPanel(new GridLayout(1,2));//右侧第二行，提示将死细胞
    private final JPanel j_willDie=new JPanel(new GridLayout(1,2));//右侧第三行，提示将活细胞
    private final JPanel j_cycle_tip=new JPanel(new GridLayout(1,2));//右侧第四行，设置周期
    private final JPanel j_apply=new JPanel(new GridLayout(1,1));//右侧第五行，设置周期
    private final JPanel j_finish=new JPanel(new GridLayout(1,1));//右侧第六行，设置布置完成按钮
    private final JPanel j_random=new JPanel(new GridLayout(1,1));//右侧第七行，设置随机布置按钮
    private final JPanel j_next_cycle=new JPanel(new GridLayout(1,2));//右侧第八行，设置单步演化
    private final JPanel j_evolution=new JPanel(new GridLayout(1,2));//右侧第九行，设置连续演化
    private final JPanel j_pause=new JPanel(new GridLayout(1,1));   //右侧第十行，设置暂停演化
    /*private final JPanel m_east1=new JPanel(new GridLayout(3,3));
    private final JPanel m_east2=new JPanel(new GridLayout(2,2));
    private final JPanel m_east3=new JPanel(new GridLayout(3,1));
    private final JPanel m_east4=new JPanel(new GridLayout(3,1));*/

    //构造函数，构造界面
    public Game_Frame()
    {
        //初始化属性信息
        m_map=new GameMap(Rows,Cols,m_center);     //传入面板参数，将地图添加到面板
        control=new Game_control(m_map);
        upTime =new Up_time(control);
        upTime.setButton(m_pause,m_evolution,m_next_tip_cycle,m_next_cycle,m_evolution_fast);
        //初始化框架边框信息，布局信息
        initFrameBorder();
        initConfig();

    }

    //初始化界面边界信息
    public void initFrameBorder(){
        setTitle("生命游戏");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 1800, 1500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    //初始化其中组件的信息
    public void initConfig(){
       // m_center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        initButton();//初始化按钮信息
        m_tip.setFont(new Font("黑体", Font.PLAIN, 18));
        m_tip.setEditable(false);
        m_tip.setText("点击下面的格子以放置细胞,然后按下《布置完成》按钮开始游戏");
        m_tip.setSize(10,80);
        m_north.add(m_tip, BorderLayout.NORTH);
        add(m_north, BorderLayout.NORTH);
        add(m_center, BorderLayout.CENTER);
        add(m_east, BorderLayout.EAST);
        setVisible(true);
    }

    //初始化按钮的信息
    public void initButton(){
        //设置右边细胞提示按钮
        Font font=new Font("黑体", Font.PLAIN, 20);
        JLabel table1=new JLabel("---当前细胞");
        JLabel table2=new JLabel("---将成为活细胞");
        JLabel table3=new JLabel("---将成为死细胞");
        JLabel table4=new JLabel("设置演变周期为(毫秒):");
        //设置文字字体
        table1.setFont(font);
        table2.setFont(font);
        table3.setFont(font);
        table4.setFont(font);
        //黄色方格，表示当前细胞
        JButton alive=new JButton();
        alive.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        alive.setBackground(Color.orange);
        alive.setSize(1,1);
        alive.setEnabled(false);
        //蓝色方格，表示将成为活细胞的细胞
        JButton willAlive=new JButton();
        willAlive.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        willAlive.setBackground(Color.BLUE);
        willAlive.setSize(1,1);
        willAlive.setEnabled(false);
        //灰色方格，表示将成为死细胞
        JButton willDie=new JButton();
        willDie.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        willDie.setBackground(Color.GRAY);
        willDie.setSize(1,1);
        willDie.setEnabled(false);
        //嵌入布局，放置按钮
        //布置右侧细胞提示的按钮与标签
       /* m_east1.add(alive);
        m_east1.add(table1);
        m_east1.add(willAlive);
        m_east1.add(table2);
        m_east1.add(willDie);
        m_east1.add(table3);*/
        j_alive.add(alive);
        j_alive.add(table1);
        j_willAlive.add(willAlive);
        j_willAlive.add(table2);
        j_willDie.add(willDie);
        j_willDie.add(table3);
        //布置设置演化周期的按钮与标签
        /*m_east2.add(table4);
        m_east2.add(m_text);*/
        j_cycle_tip.add(table4);
        j_cycle_tip.add(m_text);
        //布置功能按钮
        /*m_east3.add(m_apply);
        m_east3.add(m_finish);
        m_east3.add(m_random);
        m_east4.add(m_next_cycle);
        m_east4.add(m_evolution);
        m_east4.add(m_pause);*/
        j_apply.add(m_apply);
        j_finish.add(m_finish);
        j_random.add(m_random);
        j_next_cycle.add(m_next_tip_cycle);
        j_next_cycle.add(m_next_cycle);
        j_evolution.add(m_evolution);
        j_evolution.add(m_evolution_fast);
        j_pause.add(m_pause);
        //布置子面板
        m_east.add(j_alive);
        m_east.add(j_willAlive);
        m_east.add(j_willDie);
        m_east.add(j_apply);
        m_east.add(j_cycle_tip);
        m_east.add(j_random);
        m_east.add(j_finish);
        m_east.add(j_next_cycle);
        m_east.add(j_evolution);
        m_east.add(j_pause);

        //按钮注册对应事件
        m_finish.addActionListener(finish);
        m_random.addActionListener(random);
        m_next_tip_cycle.addActionListener(upTime.get_updating());
        m_next_cycle.addActionListener(upTime.get_fast_updating());
        m_evolution.addActionListener(evolution);
        m_evolution_fast.addActionListener(evolution_fast);
        m_pause.addActionListener(pause);
        m_apply.addActionListener(apply_delay);
        //修改子面板边界信息
        /*m_east1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 50));
        m_east2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        m_east3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        m_east4.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));*/
        //设置初始按钮状态
        m_next_tip_cycle.setEnabled(false);//设置有提示的下一周期不可用
        m_next_cycle.setEnabled(false);//设置下一周期不可用
        m_evolution.setEnabled(false);//设置演化按钮不可用
        m_evolution_fast.setEnabled(false);//快速演化按钮不可用
        m_pause.setEnabled(false);//停止演化按钮不可用
    }


    //点击按钮监听器，布置细胞
    public  static  ActionListener click= e->{
        if(((AbstractButton)e.getSource()).getBackground().equals(Color.orange)){
            ((AbstractButton)e.getSource()).setBackground(Color.white);
        }else{
            ((AbstractButton)e.getSource()).setBackground(Color.orange);
        }
    };
    //创建一个时间变更执行的Timer对象
    //Timer updateTimer=new Timer(time_set.get_delay(),time_set.get_updating());

    //《布置完成》按钮监听器
    public  ActionListener finish=e->{
        if (((AbstractButton) e.getSource()).getText().equals("布置完成")) {
            control.setNotClick();
            m_tip.setText("按下《下一周期》或《开始演化》开始一个周期或者按指定周期演化");
            m_next_tip_cycle.setEnabled(true);//开启（有提示）下一周期按钮
            m_next_cycle.setEnabled(true);//开启下一周期按钮
            m_evolution.setEnabled(true);//开启(有提示)演化按钮
            m_evolution_fast.setEnabled(true);//开启快速演化
            m_random.setEnabled(false);//随机布局关闭
            ((AbstractButton) e.getSource()).setText("重新布置");
        } else {		//重新布置
            control.clear_map();
            control.setFlag(false);
            m_next_tip_cycle.setEnabled(false);//设置下一周期不可用
            m_next_cycle.setEnabled(false);//下一周期按钮不可用
            m_evolution.setEnabled(false);//设置演化按钮不可用
            m_evolution_fast.setEnabled(false);//快速演化按钮不可用
            m_pause.setEnabled(false);//暂停按钮不可用
            m_random.setEnabled(true);//随机布局打开
            m_tip.setText("点击下面的格子以放置细胞,然后按下《布置完成》按钮开始游戏");
            if(upTime.judge_running()) upTime.StopDocument();//如果正在细胞周期，则停止更新
            ((AbstractButton) e.getSource()).setText("布置完成");
        }
    };

    //《随机布置》按钮监听器
    public  ActionListener random=e->{
        if(((AbstractButton)e.getSource()).getText().equals("随机布置")){
            control.randSet();
        }
    };
    //《有提示连续演化按钮》监听器
    public ActionListener evolution=e->{
        double delay=Double.valueOf(upTime.get_delay())/1000.0;//获得周期对象的周期大小
        m_tip.setText("周期演化为每周期"+delay+"秒。");//设置提示信息
        upTime.tip_updating();           //设置有提示连续演化
        upTime.StartDocument();           //时钟开始
        ((AbstractButton)e.getSource()).setEnabled(false);//当前按钮不可用
        m_pause.setEnabled(true);//暂停演化可用
        m_next_tip_cycle.setEnabled(false);//有提示的一次演化不可用
        m_next_cycle.setEnabled(false);//无提示的一次演化不可用
        m_evolution_fast.setEnabled(false);//有提示的快速演化不可用
    };
    //《快速演化》按钮监听器
    public ActionListener evolution_fast=e -> {
        double delay=Double.valueOf(upTime.get_delay())/1000.0;
        m_tip.setText("周期演化为每周期"+delay+"秒。");
        upTime.fast_updating();           //设置无提示快速演化
        upTime.StartDocument();           //时钟开始
        ((AbstractButton)e.getSource()).setEnabled(false);//当前按钮不可用
        m_pause.setEnabled(true);//暂停演化可用
        m_next_tip_cycle.setEnabled(false);//有提示的一次演化不可用
        m_next_cycle.setEnabled(false);//无提示的一次演化不可用
        m_evolution.setEnabled(false);//无提示的连续演化不可用
    };
    //《停止演化》按钮监听器
    public ActionListener pause=e->{
        upTime.StopDocument();            //暂停
        ((AbstractButton)e.getSource()).setEnabled(false);//停止演化不可用
        m_next_tip_cycle.setEnabled(true);//提示的一次演化可用
        m_next_cycle.setEnabled(true);//无提示的一次演化可用
        m_evolution.setEnabled(true);//连续演化可用
        m_evolution_fast.setEnabled(true);//快速演化不可用
    };
    //《应用》按钮监听器
    public ActionListener apply_delay=e->{
        if(m_text.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请输入有效时间");
            return;//空文本不触发
        }
        int delay=Integer.parseInt(m_text.getText());//得到文本内容
        upTime.set_delay(delay);//设置周期大小
        m_tip.setText("周期演化为每周期"+(double)delay/1000.0+"秒。");//显示新的周期
    };

}
