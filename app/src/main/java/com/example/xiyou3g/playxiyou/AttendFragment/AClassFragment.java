package com.example.xiyou3g.playxiyou.AttendFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.Adapter.AClassAdapter;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendClass;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.HandleAttClass;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance
 * on 2017/7/20.
 */

public class AClassFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.class_area)
    TextView textView;

    @BindView(R.id.classroom)
    RecyclerView classroom;

    @BindView(R.id.isselect)
    TextView isSelectClass;

    private AClassAdapter aClassAdapter;
    public static AClassHandler aClassHandler;

    private int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.atten_class_fragment,container,false);
        ButterKnife.bind(this, view);
        initWight();
        aClassHandler = new AClassHandler(isSelectClass, classroom, aClassAdapter);
        return view;
    }

    public static class AClassHandler extends Handler {
        private WeakReference<TextView> selctClass;
        private WeakReference<RecyclerView> classroom;
        private WeakReference<AClassAdapter> adapter;

        AClassHandler(TextView textView, RecyclerView recyclerView, AClassAdapter aClassAdapter) {
            selctClass = new WeakReference<>(textView);
            classroom = new WeakReference<>(recyclerView);
            adapter = new WeakReference<>(aClassAdapter);
        }

        @Override
        public void handleMessage(Message msg) {
            TextView isSelectClass = selctClass.get();
            RecyclerView classr = classroom.get();
            AClassAdapter aClassAdapter = adapter.get();
            if (isSelectClass != null && classr != null && aClassAdapter != null) {
                switch (msg.what){
                    case 9:
                        isSelectClass.setVisibility(View.GONE);
                        classr.setVisibility(View.VISIBLE);
                        aClassAdapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    }

    private void initWight() {
        textView.setText(classname);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classroom.setLayoutManager(linearLayoutManager);
        aClassAdapter = new AClassAdapter(classroomBeanList);
        classroom.setAdapter(aClassAdapter);

        if(classroomBeanList.size() == 0){
            isSelectClass.setVisibility(View.VISIBLE);
            classroom.setVisibility(View.GONE);
        }else {
            isSelectClass.setVisibility(View.GONE);
            classroom.setVisibility(View.VISIBLE);
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
