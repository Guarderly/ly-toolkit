package com.ly.view;

import com.ly.App;
import com.ly.handler.DemoHandler;
import com.ly.utils.SpringContextUtils;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class DemoGui {
    private JButton button1;
    private JPanel panel1;

    private DemoHandler testComponent= SpringContextUtils.getBean(DemoHandler.class);


    public DemoGui() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testComponent.sayHi();
            }
        });
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //启动springboot
        new SwingWorker(){
            @Override
            protected Object doInBackground() {
                ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class)
                        .headless(false)
                        .run(args);

//                MainFrameController bean = context.getBean(MainFrameController.class);
//                bean.prepareAndOpenFrame();
//                ToolGuiController controller = context.getBean(ToolGuiController.class);
//                controller.prepareAndOpenFrame();
                //ToolGui toolGui = context.getBean(ToolGui.class);
                //toolGui.prepareAndOpenFrame();

                long end = System.currentTimeMillis();
                System.out.println("time:"+(end-start)+"ms");
                return null;
            }
        }.execute();

        JFrame frame = new JFrame("ToolGui");
        frame.setContentPane(new DemoGui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void show(String[] args) {
        long start = System.currentTimeMillis();

        JFrame frame = new JFrame("ToolGui");
        frame.setContentPane(new DemoGui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanel(){
        return panel1;
    }

//    public static void prepareAndOpenFrame() {
//        JFrame frame = new JFrame("ToolGui");
//        frame.setContentPane(new ToolGui().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

}
