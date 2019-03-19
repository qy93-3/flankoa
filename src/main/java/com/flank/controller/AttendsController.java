package com.flank.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Attends;
import com.flank.beans.User;
import com.flank.result.Result;
import com.flank.service.AttendsService;
import com.flank.service.StatusService;
import com.flank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 考勤视图层
 * @author Flank
 * @since 2019-03-08
 */
@Controller
@RequestMapping("/attends")
public class AttendsController {
    @Autowired
    AttendsService attendsService;
    @Autowired
    StatusService statusService;
    @Autowired
    UserService userService;
    @Autowired
    TimeController timeController;

    /**
     * 分页显示考勤信息
     * 还可以实现条件的分页查询
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result listByPage(Integer page, Integer limit,@RequestParam(required = false) String date) {
    System.out.println("date"+date);
        Page<Attends> attendsPage=null;
        if (date == null) {
            attendsPage = attendsService.selectPage(new Page<Attends>(page, limit));
        }else {
            attendsPage = attendsService.selectPage(new Page<Attends>(page, limit),
                    new EntityWrapper<Attends>().between("time", date.substring(0, 10),date.substring(13, date.length())));
        }
        ArrayList<Attends> list = new ArrayList<>();
        for (Attends attend : attendsPage.getRecords()) {
            //关联对象的查询
            attend.setStatus(statusService.selectById(attend.getStatusId())).setUser(userService.selectById(attend.getUserId()));
            list.add(attend);
        }
        //将带有关联对象的数据放回page对象中
        attendsPage.setRecords(list);

        //将结果包装成符合layui的格式
        Result<Attends> AttendsResult = new Result<Attends>();
        AttendsResult.setCode(0).setCount(attendsPage.getTotal()).setMsg("success").setData(attendsPage.getRecords());
        return AttendsResult;
    }



    /**
     * 跳转到 考勤管理页面
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return "attends/AttendsList";
    }
    /**
     * 跳转到 考勤报表页面
     * @return
     */
    @GetMapping("/report")
    public String report() {
        return "attends/AttendsListReport";
    }

    /**
     * 签到
     * 12点之前签到是上班，12点之后签到插入的数据是下班
//     * @param count 用来记录点击签到的次数
     * @return
     */
    @GetMapping("/attend")
    @ResponseBody
    public Result attend(HttpSession session){
        Result result = new Result();

        //生成一条签到记录
        Attends attend = new Attends();
        attend.setTime(new Date());
        User user = (User) session.getAttribute("user");
        attend.setUserId(user.getUserId());

        //判断是上班签到还是下班签到
        Calendar ncalendar = Calendar.getInstance();
        //输出当前时间的小时
        int hour = ncalendar.get(Calendar.HOUR_OF_DAY);
        if (hour <= 12) {
            if (hour <= 8) {
                //判断用户是否今天上午已经签到
                if (!judgeShanBan(user)) {
                    //上班准时
                    attend.setStatusId(3);
                    attend.setType("上班");
                    result.setCode(200).setMsg("签到成功");
                } else {
                    result.setCode(0).setMsg("已签到，请勿重复签到");
                    return result;
                }
            }else {
                if (!judgeShanBan(user)) {
                    //上班迟到
                    attend.setType("上班");
                    attend.setStatusId(1);
                    result.setCode(200).setMsg("签到成功");
                } else {
                    result.setCode(0).setMsg("已签到，请勿重复签到");
                    return result;
                }
            }
        } else {
            if (hour >=17) {
                if (!judgeXiaBan(user)) {
                    //下班准时
                    attend.setType("下班");
                    attend.setStatusId(3);
                    result.setCode(200).setMsg("签到成功");
                } else {
                    result.setCode(0).setMsg("已签到，请勿重复签到");
                    return result;
                }
            }else {
                if (!judgeXiaBan(user)) {
                    //下班迟到
                    attend.setType("下班");
                    attend.setStatusId(1);
                    result.setCode(200).setMsg("签到成功");
                } else {
                    result.setCode(0).setMsg("已签到，请勿重复签到");
                    return result;
                }
            }
        }
        attendsService.insert(attend);
        return result;
    }

    /**
     * 判断该用户今天上午是否签到
     * @return
     */
    public boolean judgeShanBan(User user){
        Calendar ncalendar = Calendar.getInstance();
        int year = ncalendar.get(Calendar.YEAR);//当前年
        int mont = ncalendar.get(Calendar.MONTH);//当前月
        int day = ncalendar.get(Calendar.DATE);//当前日
        int hour = ncalendar.get(Calendar.HOUR_OF_DAY);//当前时
        //获取该用户的签到记录
        List<Attends> list = attendsService.selectList(new EntityWrapper<Attends>().eq("user_id", user.getUserId()).eq("type", "上班"));
        for (Attends attend : list) {
            //判断签到时间和今天是否是同一天
            if (isYeaterday(attend.getTime(), null) == -1) {
                //表示已经签到
                return true;
            }
        }
        return false;

    }
    /**
     * 判断该用户今天下午是否签到
     * @return
     */
    public boolean judgeXiaBan(User user){
        Calendar ncalendar = Calendar.getInstance();
        int year = ncalendar.get(Calendar.YEAR);//当前年
        int mont = ncalendar.get(Calendar.MONTH);//当前月
        int day = ncalendar.get(Calendar.DATE);//当前日
        int hour = ncalendar.get(Calendar.HOUR_OF_DAY);//当前时
        //获取该用户的签到记录
        List<Attends> list = attendsService.selectList(new EntityWrapper<Attends>().eq("user_id", user.getUserId()).eq("type", "下班"));
        for (Attends attend : list) {
            //判断签到时间和今天是否是同一天
            if (isYeaterday(attend.getTime(), null) == -1) {
                //表示已经签到
                return true;
            }
        }
        return false;

    }

    /**
     * @author LuoB.
     * @param oldTime 较小的时间
     * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比)
     * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天.
     */
    private int isYeaterday(Date oldTime,Date newTime){
        if(newTime==null){
            newTime=new Date();
        }
        //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(newTime);
        Date today = null;
        try {
            today = format.parse(todayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
