package com.example.xiyou3g.playxiyou.AttendFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.Adapter.AClassAdapter;
import com.example.xiyou3g.playxiyou.DataBean.ClassroomBean;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendClass;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.HandleAttClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance on 2017/7/20.
 */

public class AClassFragment extends Fragment {

    private View view;
    private Toolbar toolbar;
    private TextView textView;
    private RecyclerView classroom;
    private TextView isSelectClass;
    private LinearLayoutManager linearLayoutManager;
    private AClassAdapter aClassAdapter;

    private int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.atten_class_fragment,container,false);
        initWight(view);
        attenHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 9:
                        Log.e("attend chage","success");
                        isSelectClass.setVisibility(View.GONE);
                        classroom.setVisibility(View.VISIBLE);
                        classroom = (RecyclerView) view.findViewById(R.id.classroom);
                        linearLayoutManager = new LinearLayoutManager(getContext());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        classroom.setLayoutManager(linearLayoutManager);
                        aClassAdapter = new AClassAdapter(classroomBeanList);
                        classroom.setAdapter(aClassAdapter);
                        break;
                }
            }
        };
        return view;
    }

    private void initWight(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        textView = (TextView) view.findViewById(R.id.class_area);
        isSelectClass = (TextView) view.findViewById(R.id.isselect);
        classroom = (RecyclerView) view.findViewById(R.id.classroom);

        textView.setText(classname);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classroom.setLayoutManager(linearLayoutManager);

        if(classroomBeanList.size() == 0){
            isSelectClass.setVisibility(View.VISIBLE);
            classroom.setVisibility(View.GONE);
        }else {
            isSelectClass.setVisibility(View.GONE);
            classroom.setVisibility(View.VISIBLE);
            AClassAdapter aClassAdapter = new AClassAdapter(classroomBeanList);
            classroom.setAdapter(aClassAdapter);
        }

        toolbar.inflateMenu(R.menu.class_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.class1:
                        id = 164;
                        break;
                    case R.id.class2:
                        id = 166;
                        break;
                    case R.id.class3:
                        id = 167;
                        break;
                    case R.id.class4:
                        id = 163;
                        break;
                    case R.id.class5:
                        id = 165;
                        break;
                    case R.id.class6:
                        id = 162;
                        break;
                    case R.id.class7:
                        id = 181;
                        break;
                }
                requestClass(id);
                classname = item.getTitle()+"";
                textView.setText(classname);
                return true;
            }
        });
    }

    private void requestClass(int id) {
        classroomBeanList.clear();
        try {
            GetAttendClass.getAttendClass(id, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("attend classroom failure",e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    HandleAttClass.INSTANCE.handleAttClass(response);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
