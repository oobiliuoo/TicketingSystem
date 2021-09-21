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

        initScenicSpots();
        ScenicSpotAdapter adapter = new ScenicSpotAdapter(getContext(),R.layout.home_list_layout,scenicSpots);

        listView = getView().findViewById(R.id.home_lv);
        listView.deferNotifyDataSetChanged();
        listView.setAdapter(adapter);


    }

    private void initScenicSpots() {
        ScenicSpot spot1 = new ScenicSpot("高椅邻景区","AAAAA","开放时间：07：00～22：00",R.drawable.gaoyiling);
        scenicSpots.add(spot1);
        ScenicSpot spot2 = new ScenicSpot("玻璃栈道","游玩","开放时间：08：00～17：00",R.drawable.boliqiao);
        scenicSpots.add(spot2);
        ScenicSpot spot3 = new ScenicSpot("缆车单程","交通","开放时间：08：00～20：00",R.drawable.lanche);
        scenicSpots.add(spot3);
        ScenicSpot spot4 = new ScenicSpot("急速滑道","游玩","开放时间：08：00～17：00",R.drawable.huadao);
        scenicSpots.add(spot4);
    }
}