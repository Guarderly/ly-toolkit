package com.ly;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppStarter {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //初始化Spring
        new SpringApplicationBuilder(AppStarter.class).headless(false).run(args);
        //显示界面
        ToolMainViewStart.run();

        long end = System.currentTimeMillis();
        System.out.println("time:"+(end-start)+"ms");
    }


//    private static void banner(){
//        Splash splash = new Splash();
//        splash.setVisible(true);
//        //监听进度并更新
//        ProgressBeanPostProcessor.observe().subscribe(integer -> splash.getProgress().setValue(integer)
//                ,e->{}
//                ,() ->splash.setVisible(false));
//    }
}
