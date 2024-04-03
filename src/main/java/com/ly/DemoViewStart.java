package com.ly;

        import com.ly.utils.SpringContextUtils;
        import com.ly.view.DemoGui;

        import javax.swing.*;
        import java.awt.EventQueue;

/**
 * 主页启动
 * @author LGX
 *
 */
public class DemoViewStart {

    public static void run() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //设置样式
                    UIManager.put("swing.boldMetal",Boolean.FALSE);
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    //获取界面实例并显示
                    JFrame frame = new JFrame("ToolGui");
                    frame.setContentPane(SpringContextUtils.getBean(DemoGui.class).getPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
