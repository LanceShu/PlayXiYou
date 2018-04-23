package com.example.xiyou3g.playxiyou.MeFragment;

/**
 * Created by Lance
 * on 2017/8/1.
 */

public class XiyouGuide {

    public static String getResult(String classroom){
        String result;
        if(classroom.equalsIgnoreCase("ff203")||classroom.equalsIgnoreCase("ff205")){
            result = "(｡ì _ í｡)你竟然不知道教室是从D入口，直接上二楼，或者从大屏幕右侧楼梯上二楼，在走廊南北方向!";
        }else if(classroom.equalsIgnoreCase("ff207")){
            result = "你猜(ฅ>ω<*ฅ）猜对我就告诉你教室从C入口上二楼，或者从大屏幕右侧楼梯上二楼，教室在东北角，走廊南北方向";
        }else if(classroom.equalsIgnoreCase("ff302")){
            result = "<(￣3￣)>哼！人家才不告诉你教室在从D入口进，走楼梯或乘电梯到三楼，在走廊东西方向，教室位于东南角！";
        }else if(classroom.equalsIgnoreCase("ff303")){
            result = "(｡•ˇ‸ˇ•｡)你竟然不知道教室从D入口进，上楼梯或乘电梯到三楼，在走廊南北方向！";
        }else if(classroom.equalsIgnoreCase("ff305")){
            result = "<(￣3￣)>哼！人家才不告诉你有两条路可以到达！\n" +
                    "1.从大屏幕右（东）侧楼梯，上到二楼平台，从东侧楼梯上到3楼即可！\n" +
                    "2.从D入口进，到三楼，走廊南北方向！";
        }else if(classroom.equalsIgnoreCase("ff307")){
            result = "什么？<(｀^´)>你竟然不知道教室必须从C入口进，走楼梯或乘电梯到三楼，在走廊南北方向，位于东北角！";
        }else if(classroom.equalsIgnoreCase("ff403")||classroom.equalsIgnoreCase("ff405")||classroom.equalsIgnoreCase("ff410")){
            result = "(｡•ˇ‸ˇ•｡)你竟然不知道教室从D入口进，走楼梯或乘电梯到四楼，在走廊南北方向！";
        }else if(classroom.equalsIgnoreCase("ff503")){
            result = "你猜(ฅ>ω<*ฅ）猜对我就告诉你教室从D入口进，上楼梯或乘电梯到6楼（你没有看错，就是6楼/可爱），在走廊南北方向！";
        }else if(classroom.equalsIgnoreCase("ff505")||classroom.equalsIgnoreCase("ff507")){
            result = "(｡•ˇ‸ˇ•｡)你竟然不知道有两条路可以通到教室！\n" +
                    "1.从大屏幕右侧楼梯上二楼平台，从平台东侧楼梯上五楼，在走廊南北方向！ \n" +
                    "2.从D入口进，到六楼走廊南北向！(有时候走廊门会上锁，所以优先推荐路线1)";
        }else if(classroom.equalsIgnoreCase("ff510")){
            result = "(｡•ˇ‸ˇ•｡)你竟然不知道教室从C入口进，上楼梯或乘电梯到6楼（你没有看错，就是6楼），在走廊南北方向，教室位于东北角！";
        }else if(classroom.equalsIgnoreCase("fz203")||classroom.equalsIgnoreCase("fz205")||classroom.equalsIgnoreCase("fz208")||classroom.equalsIgnoreCase("fz209")){
            result = "<(￣3￣)>哼！人家才不告诉你教室从A入口进，上楼梯或乘电梯到二楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz211")||classroom.equalsIgnoreCase("fz214")||classroom.equalsIgnoreCase("fz215")||classroom.equalsIgnoreCase("fz218")){
            result = "什么？<(｀^´)>你竟然不知道教室从C入口进，上楼梯或坐电梯到二楼，在走廊东西方向";
        }else if(classroom.equalsIgnoreCase("fz233")||classroom.equalsIgnoreCase("fz236")||classroom.equalsIgnoreCase("fz239")||classroom.equalsIgnoreCase("fz241")){
            result = "<(￣3￣)>哼！人家才不告诉你教室从B入口进，上楼梯或乘电梯到二楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz303")||classroom.equalsIgnoreCase("fz305")||classroom.equalsIgnoreCase("fz306")||classroom.equalsIgnoreCase("fz309")||classroom.equalsIgnoreCase("fz310")){
            result = "<(￣3￣)>哼！人家才不告诉你教室从A入口进，上楼梯或坐电梯到三楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz312")||classroom.equalsIgnoreCase("fz315")||classroom.equalsIgnoreCase("fz316")||classroom.equalsIgnoreCase("fz319")||classroom.equalsIgnoreCase("fz320")){
            result = "什么？<(｀^´)>你竟然不知道教室从C入口进，走楼梯或乘电梯到3楼，在走廊东西方向";
        }else if(classroom.equalsIgnoreCase("fz338")||classroom.equalsIgnoreCase("fz343")){
            result = "小猿才不是特意来告诉你教室从B入口进，上楼梯或乘电梯到三楼，在走廊东西方向！⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄";
        }else if(classroom.equalsIgnoreCase("fz403")||classroom.equalsIgnoreCase("fz405")||classroom.equalsIgnoreCase("fz406")||classroom.equalsIgnoreCase("fz409")||classroom.equalsIgnoreCase("fz410")){
            result = "(｡•ˇ‸ˇ•｡)你竟然不知道教室从A入口进，上楼梯或坐电梯到四楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz412")||classroom.equalsIgnoreCase("fz415")||classroom.equalsIgnoreCase("fz416")||classroom.equalsIgnoreCase("fz419")||classroom.equalsIgnoreCase("fz420")){
            result = "小猿才不是特意来告诉你教室从C入口进，走楼梯或乘电梯到4楼，在走廊东西方向!⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄";
        }else  if(classroom.equalsIgnoreCase("fz441")||classroom.equalsIgnoreCase("fz443")){
            result = "你猜(ฅ>ω<*ฅ）猜对我就告诉你教室从B入口进，走楼梯或乘电梯到四楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz505")||classroom.equalsIgnoreCase("fz507")||classroom.equalsIgnoreCase("fz508")||classroom.equalsIgnoreCase("fz511")||classroom.equalsIgnoreCase("fz512")
                ||classroom.equalsIgnoreCase("fz514")||classroom.equalsIgnoreCase("fz516")||classroom.equalsIgnoreCase("fz518")||classroom.equalsIgnoreCase("fz521")||classroom.equalsIgnoreCase("fz522")
                ||classroom.equalsIgnoreCase("fz525")||classroom.equalsIgnoreCase("fz526")){
            result = "小猿才不是特意来告诉你教室从A入口进，上楼梯或坐电梯到5楼，在走廊东西方向⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄";
        }else if(classroom.equalsIgnoreCase("fz605")||classroom.equalsIgnoreCase("fz607")){
            result = "(｡•ˇ‸ˇ•｡)你竟然不知道教室从A入口进，上楼梯或乘电梯到六楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz608")){
            result = "你猜(ฅ>ω<*ฅ）猜对我就告诉你教室从D入口进，到七楼（七楼哈）教室在东南角";
        }else if(classroom.equalsIgnoreCase("fz612")){
            result = "(｡•ˇ‸ˇ•｡)你竟然不知道教室从A入口进，上楼梯或乘电梯到六楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz614")||classroom.equalsIgnoreCase("fz618")){
            result = "小猿才不是特意来告诉你教室从A或C入口进，上楼梯或乘电梯到6楼，在走廊东西方向！⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄";
        }else if(classroom.equalsIgnoreCase("fz621")||classroom.equalsIgnoreCase("fz622")||classroom.equalsIgnoreCase("fz625")||classroom.equalsIgnoreCase("fz626")){
            result = "你猜(ฅ>ω<*ฅ）猜对我就告诉你教室从C入口进，上楼梯或乘电梯到6楼，在走廊东西方向！";
        }else if(classroom.equalsIgnoreCase("fz633")||classroom.equalsIgnoreCase("fz634")||classroom.equalsIgnoreCase("fz635")||classroom.equalsIgnoreCase("fz637")){
            result = "什么？<(｀^´)>你竟然不知道教室从D入口进，走楼梯或乘电梯到6楼，在走廊东西方向！";
        }else {
            result = "主人还没给我设置这类话题的回复，你帮我悄悄的告诉他吧~";
        }
        return result;
    }
}
