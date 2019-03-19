package myoatest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OaTest.class)
public class OaTest {

    @Test
    public void contextLoads() {
        String str = "2019-03-21 - 2019-04-17";
        System.out.println(str.substring(0,10));
        System.out.println(str.substring(13,str.length()));
    }
    @Test
    public void show(){
        Calendar ncalendar = Calendar.getInstance();
//小时
        System.out.println(ncalendar.get(Calendar.HOUR_OF_DAY));
//分
        System.out.println(ncalendar.get(Calendar.MINUTE));
    }

    //用来判断一个时间是不是同一天
    @Test
    public void show2() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2019-3-18");

        System.out.println(isYeaterday(parse,null));
    }

    /**
     * @author LuoB.
     * @param oldTime 较小的时间
     * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比)
     * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天.
     */
    private int isYeaterday(Date oldTime,Date newTime) throws ParseException {
        if(newTime==null){
            newTime=new Date();
        }
        //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(newTime);
        Date today = format.parse(todayStr);
        //昨天 86400000=24*60*60*1000 一天
        if((today.getTime()-oldTime.getTime())>0 && (today.getTime()-oldTime.getTime())<=86400000) {
            return 0;
        }
        else if((today.getTime()-oldTime.getTime())<=0){ //至少是今天
            return -1;
        }
        else{ //至少是前天
            return 1;
        }

    }

}

