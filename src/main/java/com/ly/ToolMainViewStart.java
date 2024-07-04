package com.ly;

import com.ly.utils.SpringContextUtils;
import com.ly.view.LogGui;
import com.ly.view.ToolMainGui;

import javax.swing.*;
import java.awt.*;

/**
 * 主页启动
 * @author LGX
 *
 */
public class ToolMainViewStart {

    public static void run() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //设置样式
                    UIManager.put("swing.boldMetal",Boolean.FALSE);
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    //获取界面实例并显示
                    JFrame frame = new JFrame("平台工具");
                    frame.setBounds(100,100,480,600);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.getContentPane().add(SpringContextUtils.getBean(ToolMainGui.class).getPanel(),BorderLayout.CENTER);
                    frame.getContentPane().add(SpringContextUtils.getBean(LogGui.class).getPanel(),BorderLayout.SOUTH);


                    frame.pack();

                    JMenuBar jMenuBar = new JMenuBar();
                    frame.setJMenuBar(jMenuBar);

                    JMenu aboutMenu = new JMenu("关于");
                    jMenuBar.add(aboutMenu);

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
