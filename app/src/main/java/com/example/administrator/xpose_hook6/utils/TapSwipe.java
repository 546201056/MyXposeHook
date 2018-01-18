package com.example.administrator.xpose_hook6.utils;

import java.util.Random;

/**
 * Created by Administrator on 2018/1/5.
 */

public class TapSwipe {
//   private  Random random;
    public static void TapUc()throws Exception {
//        ShellUtils.execCommand("input tap 330 410", true);
//        Thread.sleep(5000);

        Random random = new Random();
        int clickOrSwipe = random.nextInt(100);
        if (clickOrSwipe < 90) {
            int isNeedMockSwipe = 1;// 随机产生是否点击行为
            if (isNeedMockSwipe == 1) {
                int times = random.nextInt(20);
                System.out.println("swipe times:" + times);
                for (int i = 0; i < times; i++) {
                    // if (dumpPage(devices) == true) {
                    // shell("adb -s " + devices
                    // + " shell input tap 330 410");
                    // }
                    System.out.println("swipe :" + i);
                    int x = random.nextInt(1081);
                    if (x < 50) {
                        x = 51;
                    }
                    int y = random.nextInt(1921);
                    if (y < 50) {
                        y = 51;
                    }
                    int y_from = random.nextInt(1120) + 60;
                    if (y_from < 50) {
                        y_from = 51;
                    }
                    int y_to = random.nextInt(1120) + 60;
                    if (y_to < 50) {
                        y_to = 51;
                    }
                    int show = random.nextInt(15);
                    if (show < 10) {
                        if (y_from < y_to) {
                            int temp;
                            temp = y_from;
                            y_from = y_to;
                            y_to = temp;
                        }
                    }

                    System.out.println("swipe:==>from[" + x + "," + y_from
                            + "] to [" + x + "," + y_to + "]");
                    ShellUtils.execCommand(" input swipe  " + x
                            + " " + y_from + " " + x + " " + y_to,true);
                    int swipeSleepTime = new Random().nextInt(1000) + 1000;
                    System.out.println("swipeSleepTime:===>"
                            + swipeSleepTime + "ms");
                    Thread.sleep(swipeSleepTime);
                }
            }

            // int isNeedMockClick = 1;// 随机产生是否点击行为
            // if (isNeedMockClick == 1) {

            int times = random.nextInt(100); //
            if (times == 0) {
                System.err
                        .println("Ramdom ===> 0, Excute Click Action !!!!");
                int x = random.nextInt(1081);
                if (x < 50) {
                    x = 51;
                }
                int y = random.nextInt(1120) + 60;
                if (y < 400) {
                    y = 401 + random.nextInt(1000);
                }
                int clickSleepTime = new Random().nextInt(2000) + 2000;

                ShellUtils.execCommand(" input tap " + x + " "
                        + y,true);

                Thread.sleep(clickSleepTime);
                System.out.println("tap:==>[" + x + ", " + y + "]");
                ShellUtils.execCommand(" input tap " + x + " "
                        + y,true);

                System.out.println("clickSleepTime:===>" + clickSleepTime
                        + "ms");
                Thread.sleep(clickSleepTime);
                ShellUtils.execCommand("input keyevent 4",true);
                // Thread.sleep(1000);
                ShellUtils.execCommand("input tap 330 410",true);
                Thread.sleep(5000);
            } else {
                System.err.println("Ramdom ====>" + times
                        + ",Not excute Click Action !!!!");
            }
            // System.out.println("tap times:" + times);
            // for (int i = 0; i < times; i++) {
            // // if (dumpPage(devices) == true) {
            // // shell("adb -s " + devices
            // // + " shell input tap 330 410");
            // // }
            // System.out.println("tap :" + times);
            //
            // }
            // }
        } else {
            int times = random.nextInt(100);
            if (times == 0) {
                System.err
                        .println("Ramdom ===> 0, Excute Click Action !!!!");
                int x = random.nextInt(1081);
                if (x < 50) {
                    x = 51;
                }
                int y = random.nextInt(1120) + 60;
                if (y < 400) {
                    y = 401 + random.nextInt(1000);
                }
                int clickSleepTime = new Random().nextInt(2000) + 2000;

                ShellUtils.execCommand("input tap " + x + " "
                        + y,true);

                Thread.sleep(clickSleepTime);
                System.out.println("tap:==>[" + x + ", " + y + "]");
                ShellUtils.execCommand("input tap " + x + " "
                        + y,true);

                System.out.println("clickSleepTime:===>" + clickSleepTime
                        + "ms");
                Thread.sleep(clickSleepTime);
                ShellUtils.execCommand("input keyevent 4",true);
                // Thread.sleep(1000);
                ShellUtils.execCommand("input tap 330 410",true);
                Thread.sleep(5000);
            } else {
                System.err.println("Ramdom ====>" + times
                        + ",Not excute Click Action !!!!");
            }
            int isNeedMockSwipe2 = 1;// 随机产生是否点击行为
            if (isNeedMockSwipe2 == 1) {
                int times2 = random.nextInt(30);
                System.out.println("swipe times:" + times2);
                for (int i = 0; i < times2; i++) {
                    // if (dumpPage(devices) == true) {
                    // shell("adb -s " + devices
                    // + " shell input tap 330 410");
                    // }
                    System.out.println("swipe :" + i);
                    int x = random.nextInt(1081);
                    if (x < 50) {
                        x = 51;
                    }
                    int y = random.nextInt(1921);
                    if (y < 50) {
                        y = 51;
                    }
                    int y_from = random.nextInt(1120) + 60;
                    if (y_from < 50) {
                        y_from = 51;
                    }
                    int y_to = random.nextInt(1120) + 60;
                    if (y_to < 50) {
                        y_to = 51;
                    }
                    int show = random.nextInt(15);
                    if (show < 10) {
                        if (y_from < y_to) {
                            int temp;
                            temp = y_from;
                            y_from = y_to;
                            y_to = temp;
                        }
                    }

                    System.out.println("swipe:==>from[" + x + "," + y_from
                            + "] to [" + x + "," + y_to + "]");
                    ShellUtils.execCommand("input swipe  " + x
                            + " " + y_from + " " + x + " " + y_to,true);
                    int swipeSleepTime = new Random().nextInt(500) + 500;
                    System.out.println("swipeSleepTime:===>"
                            + swipeSleepTime + "ms");
                    Thread.sleep(swipeSleepTime);
                }
            }
        }
    }
}
