package com.example.xiyou3g.playxiyou.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.playxiyou.Content.AttenContent;
import com.example.xiyou3g.playxiyou.DataBean.CheckBean;
import com.example.xiyou3g.playxiyou.DataBean.CheckInforBean;
import com.example.xiyou3g.playxiyou.HttpRequest.RequestAppeal;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Lance on 2017/10/7.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder> {

    private List<CheckBean> checkBeanList  = new ArrayList<>();
    private Context context;
    private ViewGroup parent;

    //申诉返回的boolean;
    private boolean isSucceed = true;

    public CheckAdapter(Context context,List<CheckBean> checkBeen){
        checkBeanList = checkBeen;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(checkBeanList.size() > 0){
            CheckBean checkBean = checkBeanList.get(position);
            holder.WaterDate.setText(checkBean.getWaterDate());
            holder.S_Name.setText(checkBean.getS_Name());
            holder.S_Code.setText(checkBean.getS_Code());
            holder.JT_No.setText(checkBean.getJT_No());
            holder.RoomNum.setText(checkBean.getRoomNum());
            holder.BName.setText(checkBean.getBName());
            if(checkBean.getStatus().equals("1")){
                holder.Status.setTextColor(Color.parseColor("#66da60"));
                holder.Status.setText("正常");
            }else if(checkBean.getStatus().equals("2")){
                holder.Status.setTextColor(Color.parseColor("#dac260"));
                holder.Status.setText("迟到");
            }else{
                holder.Status.setTextColor(Color.parseColor("#da6066"));
                holder.Status.setText("缺勤");
            }
            if(checkBean.getStatus().equals("1")){
                holder.butLayout.setVisibility(View.GONE);
            }else{
                holder.butLayout.setVisibility(View.VISIBLE);
                holder.appeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setAppeal(checkBeanList,position);
                    }
                });
            }
        }
    }

    private void setAppeal(List<CheckBean> checkBeanList, int position) {
        Dialog dialog = new Dialog(context,R.style.DialogTheme);
        dialog.setContentView(R.layout.appeal_dialog);
        initDialogWight(dialog,checkBeanList,position);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void initDialogWight(final Dialog dialog, final List<CheckBean> checkBeanList, final int position) {

        TextView course = (TextView) dialog.findViewById(R.id.appeal_course);
        TextView date = (TextView) dialog.findViewById(R.id.appeal_date);
        final EditText remark = (EditText) dialog.findViewById(R.id.appeal_edit);
        Button appeal = (Button) dialog.findViewById(R.id.appeal_button);

        final CheckBean checkBean = checkBeanList.get(position);

        course.setText(checkBean.getS_Name());
        date.setText(checkBean.getWaterDate());

        appeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,checkBean.getS_Name(),Toast.LENGTH_SHORT).show();
                if(remark.getText().toString().length() < 5){
                    Toast.makeText(context,"申诉理由请大于五个字！！",Toast.LENGTH_SHORT).show();
                }else{
                    RequestAppeal.INSTANCE.requestAppeal(remark.getText().toString(), checkBeanList, position, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String res = response.body().string();
                            LogUtils.INSTANCE.e("appealResponse:",res);
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                isSucceed = jsonObject.getBoolean("IsSucceed");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    AttenContent.attenHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.INSTANCE.e("appealResponse",isSucceed+"");
                            if(isSucceed){
                                Toast.makeText(context,"申诉成功~~",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(context,"该记录已被申请过，请等待审核!",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    },500);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return checkBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView WaterDate;
        private TextView S_Name;
        private TextView S_Code;
        private TextView JT_No;
        private TextView RoomNum;
        private TextView BName;
        private TextView Status;
        private Button appeal;
        private RelativeLayout butLayout;

        public ViewHolder(View view) {
            super(view);
            WaterDate = (TextView) view.findViewById(R.id.WaterDate);
            S_Name = (TextView) view.findViewById(R.id.S_Name);
            S_Code = (TextView) view.findViewById(R.id.S_Code);
            JT_No = (TextView) view.findViewById(R.id.JT_No);
            RoomNum = (TextView) view.findViewById(R.id.RoomNum);
            BName = (TextView) view.findViewById(R.id.BName);
            Status = (TextView) view.findViewById(R.id.Status);
            appeal = (Button) view.findViewById(R.id.appeal);
            butLayout = (RelativeLayout) view.findViewById(R.id.but_layout);
        }
    }
}
