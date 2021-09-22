package com.oobiliuoo.ticketingsystem.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.adapter.ScenicSpotAdapter;
import com.oobiliuoo.ticketingsystem.data.ScenicSpot;
import com.oobiliuoo.ticketingsystem.utils.LBUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView listView;
    private List<ScenicSpot> scenicSpots = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //initView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {


       // LBUtils.resetOrderInfo();

        initScenicSpots();
        ScenicSpotAdapter adapter = new ScenicSpotAdapter(getContext(),R.layout.home_list_layout,scenicSpots);

        listView = getView().findViewById(R.id.home_lv);
        listView.deferNotifyDataSetChanged();
        listView.setAdapter(adapter);


    }

    private void initScenicSpots() {
        ScenicSpot spot1 = new ScenicSpot("高椅邻景区","AAAAA","开放时间：07：00-22：00",R.drawable.gaoyiling);
        spot1.setPrice("0");
        spot1.setIntro("  高椅岭风景区位于湖南省郴州市苏仙区桥口镇与资兴市交界处，它与飞天山相隔，与永兴县便江接壤，距离著名的风景名胜区东江湖28公里。这里地势以山林为主，风景宜人，森林覆盖率达95%。高椅岭山、水、泉、洞、寨、崖、坦俱全，是一块尚未开发的丹霞地貌处女地，其最大的特点就是丹霞地貌周边有漂亮的水洼点缀，红岩绿水、险寨奇涧，生态自然，美得一塌糊涂。");
        scenicSpots.add(spot1);
        ScenicSpot spot2 = new ScenicSpot("玻璃栈道","游玩","开放时间：08：00-17：00",R.drawable.boliqiao);
        spot2.setPrice("100");
        spot2.setIntro("  高椅岭玻璃栈道长60米，最高处海拔1430米，这条看着就让人腿软的玻璃栈道给人带来的刺激震撼，可与举世闻名的美国大峡谷玻璃走廊“天空之路”媲美。");
        scenicSpots.add(spot2);
        ScenicSpot spot3 = new ScenicSpot("缆车单程","交通","开放时间：08：00-20：00",R.drawable.lanche);
        spot3.setPrice("20");
        spot3.setIntro(" 缆车是由驱动机带动钢丝绳，牵引车厢沿着铺设在地表并有一定坡度的轨道上运行，用以提升或下放人员和货物的运输机械。它多用作工矿区、城市或风景游览区的交通工具。 其利用钢绳牵引，实现人员或货物输送目的之设备的统称或一般称谓。");
        scenicSpots.add(spot3);
        ScenicSpot spot4 = new ScenicSpot("急速滑道","游玩","开放时间：08：00-17：00",R.drawable.huadao);
        spot4.setPrice("30");
        spot4.setIntro("  你的速度你作主，体会不一样的感觉！");
        scenicSpots.add(spot4);
    }
}