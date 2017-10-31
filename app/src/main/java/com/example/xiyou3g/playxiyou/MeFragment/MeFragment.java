package com.example.xiyou3g.playxiyou.MeFragment;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.xiyou3g.playxiyou.Activity.LoginActivity;
import com.example.xiyou3g.playxiyou.Activity.SiliuActivity;
import com.example.xiyou3g.playxiyou.Activity.SiliuActivitySe;
import com.example.xiyou3g.playxiyou.Adapter.HelpAdapter;
import com.example.xiyou3g.playxiyou.Adapter.QueAdpater;
import com.example.xiyou3g.playxiyou.MyView.RoundImageView;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/14.
 */

public class MeFragment extends Fragment implements View.OnClickListener{

    private View view;
    private TextView stuName;
    private TextView stuNum;

    private TextView stuinfo;
    private TextView siliu;
    private TextView project;
    private TextView major;
    private TextView place;
    private TextView help;
    private TextView question;
    private TextView about;
    private TextView exit;
    private RoundImageView logo;

    private static int count = 0;

//    public static class MyLocationListener implements BDLocationListener{
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            if(location.getLocType() == BDLocation.TypeNetWorkLocation || location.getLocType() == BDLocation.TypeGpsLocation){
//                loc = location;
//                navigateTo(location);
//            }
//        }
//
//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
//        }
//    }

//    private static void navigateTo(BDLocation location) {
//        if(isFirst ||isLoc|| count<2){
//            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
//            Log.e("location",location.getLatitude()+"   "+location.getLongitude());
//            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
//            baiduMap.animateMapStatus(update);
//            update = MapStatusUpdateFactory.zoomTo(16f);
//            baiduMap.animateMapStatus(update);
//            isFirst = false;
//            isLoc = false;
//            count++;
//        }
//        MyLocationData myLocationData = new MyLocationData.Builder()
//                .direction(100)
//                .latitude(location.getLatitude())
//                .longitude(location.getLongitude())
//                .build();
//        baiduMap.setMyLocationData(myLocationData);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count = 0;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.me_fragment,container,false);
        initWight(view);
        return view;
    }

    private void initWight(View view) {
        stuName = (TextView) view.findViewById(R.id.stuname);
        stuNum = (TextView) view.findViewById(R.id.stunum);
        stuinfo = (TextView) view.findViewById(R.id.stuinfo);
        siliu = (TextView) view.findViewById(R.id.siliu);

        project = (TextView) view.findViewById(R.id.project);
        major = (TextView) view.findViewById(R.id.major);

        place = (TextView) view.findViewById(R.id.place);
        help = (TextView) view.findViewById(R.id.help);
        question = (TextView) view.findViewById(R.id.question);
        about = (TextView) view.findViewById(R.id.about);
        exit = (TextView) view.findViewById(R.id.exit);
        logo = (RoundImageView) view.findViewById(R.id.logo);

        stuName.setText(stuclass);
        stuNum.setText(stuname);

        stuinfo.setOnClickListener(this);
        siliu.setOnClickListener(this);
        project.setOnClickListener(this);
        major.setOnClickListener(this);
        place.setOnClickListener(this);
        help.setOnClickListener(this);
        question.setOnClickListener(this);
        about.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stuinfo:
                final Dialog mdialog = new Dialog(getContext(),R.style.DialogTheme);
                mdialog.setContentView(R.layout.person_activity);
                ImageView sImag = (ImageView) mdialog.findViewById(R.id.stusex);
                TextView sname = (TextView) mdialog.findViewById(R.id.sname);
                TextView snum = (TextView) mdialog.findViewById(R.id.snum);
                TextView sclass = (TextView) mdialog.findViewById(R.id.sclass);
                TextView sacade = (TextView) mdialog.findViewById(R.id.sacade);
                TextView smajor = (TextView) mdialog.findViewById(R.id.smajor);
                TextView sedu = (TextView) mdialog.findViewById(R.id.sedu);
                TextView syear = (TextView) mdialog.findViewById(R.id.syear);

                if(stuSex.equals("男")){
                    sImag.setImageResource(R.mipmap.man);
                }else{
                    sImag.setImageResource(R.mipmap.women);
                }
                sname.setText(stuname);
                snum.setText(stuid);
                sclass.setText(stuclass);
                sacade.setText(stuacademy);
                smajor.setText(stumajor);
                sedu.setText(stueducation);
                syear.setText(stuYear);

                ImageView back = (ImageView) mdialog.findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdialog.dismiss();
                    }
                });
                mdialog.show();
                break;
            case R.id.siliu:
                Intent intent1 = new Intent(getContext(), SiliuActivitySe.class);
                startActivity(intent1,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case R.id.project:
                Intent proIntent = new Intent(getContext(),ProjectActivity.class);
                startActivity(proIntent);
                break;
            case R.id.major:
                Intent majorIntent = new Intent(getContext(),MajortActivity.class);
                startActivity(majorIntent);
                break;
            case R.id.help:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.help_dialog,null);
                RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.help_recyc);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                List<String> helpList = new ArrayList<>();
                String[] array = new String[]{"APP名称：西邮游"
                        ,"适用范围：西邮童鞋们"
                        ,"主要功能：\n教务处查询（课表查询、成绩查询、培养计划查询、所得学分查询）、邮电大学体育部登录、智慧教室门户（功能还没开发完全，小猿正在努力开发）"
                        ,"其它功能：\n个人信息，四六级成绩查询，更多功能待开发中，敬请期待~"
                        ,"APP诞生地：\n西邮移动应用开发实验室（3G实验室）"
                        ,"教务处官网：\nhttp://222.24.62.120/"
                        ,"体育部官网：\nhttp://yddx.boxkj.com/admin/login"
                        ,"智慧教室官网：\nhttp://jwkq.xupt.edu.cn:8080/"
                        ,"四六级查询（99宿舍）：\nhttp://cet.99sushe.com/"};
                for(int i =0;i<array.length;i++){
                    helpList.add(array[i]);
                }
                HelpAdapter helpAdapter = new HelpAdapter(helpList);
                recyclerView.setAdapter(helpAdapter);
                bottomSheetDialog.setContentView(dialogView);
                bottomSheetDialog.show();
                break;
            case R.id.question:
                BottomSheetDialog bdialog = new BottomSheetDialog(getContext());
                View dview = LayoutInflater.from(getContext()).inflate(R.layout.question_dialog,null);
                RecyclerView questRecyc = (RecyclerView) dview.findViewById(R.id.quedtion);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                questRecyc.setLayoutManager(llm);
                String[] ques = new String[]{"小猿小猿，为什么有时候登录的时候会闪退？"
                        ,"小猿，为什么有时候验证码的图片加载不出来？"
                        ,"小猿，为什么有时候登录进去后，加载课表时会闪退啊？"
                        ,"小猿，为什么登录进去后，有时候课表加载不出来？"
                        ,"小猿，为什么APP首次登录进去后，会有那么多加载框？"
                        ,"小猿，为啥体育部的界面好眼熟啊？！！"
                        ,"小猿，为什么智慧教室里面都暂无内容啊！！"
                        ,"小猿小猿，你的APP又莫名其妙崩溃了！！"};
                String[] answer = new String[]{"有时候登录的时候会闪退可能是因为服务器那边返回数据时，没有返回回来，或许是返回得太慢，导致小猿的程序解析数据时，没有完全解析成功，这个问题，小猿后期计划写个JavaWeb服务器来解决这个问题，敬请期待~"
                        ,"验证码加载不出来，请先检查当前设备是否处于有网状态下，如果有网，可以试着点击验证码图片进行刷新喔"
                        ,"登录进去后，加载课表会闪退，可能解析数据时出了点异常错误，重启一下APP就好了"
                        ,"课表加载不出来，可能课表数据请求时比较慢，可以点击右下角的悬浮按钮进行课表选择刷新一下"
                        ,"首次登陆后，出现那么多加载框，是因为首次登陆在加载数据，小猿比较懒，没有写本地缓存，后期会加以改进的~"
                        ,"体育部。。小猿原本想自己写个界面的，但后来发现微信上的体育部的界面很不错了。。就把它搬过来了。。。不过后期，小猿会尝试写写体育部的界面的"
                        ,"因为小猿写APP的时候，刚好在暑假，智慧教室的数据都清空了，所以现在看到的都暂无数据，开学后，小猿会将数据解析出来的，请耐心等待"
                        ,"小猿正渴望出现一些莫名其妙bug，你可以将你出现bug的操作方式通过邮箱联系小猿，小猿会努力改bug，bug不止，生命不息！"};
                List<String> quesList = new ArrayList<>();
                List<String> answList = new ArrayList<>();
                for(int i =0;i<answer.length;i++){
                    quesList.add(ques[i]);
                    answList.add(answer[i]);
                }
                QueAdpater adpater = new QueAdpater(quesList,answList);
                questRecyc.setAdapter(adpater);
                bdialog.setContentView(dview);
                bdialog.show();
                break;
            case R.id.about:
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.about);
                dialog.create();
                dialog.show();
                break;
            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("温馨提示：");
                builder.setMessage("是否确认切换账号？");
                builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        courseList.clear();
                        scoreBeanList.clear();
                        proList.clear();
                        majorBeanList.clear();
                        ViewStatelist.clear();
                        Intent intent1 = new Intent(getContext(),LoginActivity.class);
                        startActivity(intent1);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("再想想",null).create().show();
                break;
            case R.id.place:
                Intent intent = new Intent(getContext(),GuideActivity.class);
                startActivity(intent);
                break;
        }
    }

}
