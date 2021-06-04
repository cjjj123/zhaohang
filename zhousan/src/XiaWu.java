import sk.*;

import java.util.*;

public class XiaWu {
    public static void main(String[] args) {
        //下午三点
        //定时器
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //new Qiu().start();
                //new jj.Qiu().start();
                //new skt.Qiu().start();
                //new erbin.Qiu().start();
                //SK
                new sk.Mei().start();
                new sk.Shi().start();
                //new XiaBu().start();
                //二饼
                new erbin.Mei().start();
                new erbin.Shi().start();
                new erbin.XiaBu().start();
                new erbin.LongYi().start();
                //JJ
                new jj.Mei().start();
                new jj.Shi().start();
                new jj.XiaBu().start();
                new jj.LongYi().start();
                //SKT
                new skt.Mei().start();
                new skt.Shi().start();
                new skt.XiaBu().start();
            }
        };

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,10);
        calendar.set(Calendar.MINUTE,02);
        calendar.set(Calendar.SECOND,50);
        calendar.set(Calendar.MILLISECOND,000);

        Date date = calendar.getTime();

        Timer timer = new Timer();
        timer.schedule(timerTask,date);

    }
}
