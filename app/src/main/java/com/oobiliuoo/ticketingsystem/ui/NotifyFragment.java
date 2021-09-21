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
import com.oobiliuoo.ticketingsystem.adapter.NotifyTextAdapter;
import com.oobiliuoo.ticketingsystem.adapter.ScenicSpotAdapter;
import com.oobiliuoo.ticketingsystem.data.NotifyText;
import com.oobiliuoo.ticketingsystem.data.ScenicSpot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private List<NotifyText> notifyTexts = new ArrayList<>();
    public NotifyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifyFragment newInstance(String param1, String param2) {
        NotifyFragment fragment = new NotifyFragment();
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
    public void onStart() {
        super.onStart();
       // initView();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        initScenicSpots();
        NotifyTextAdapter adapter = new NotifyTextAdapter(getContext(),R.layout.notify_list_layout,notifyTexts);

        listView = getView().findViewById(R.id.notify_lv);
        listView.deferNotifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    private void initScenicSpots() {
       NotifyText n1 = new NotifyText("景区入园通知","2021-9-19 9:53","入园免费");
       notifyTexts.add(n1);

        NotifyText n2 = new NotifyText("景区常问问题指南","2021-9-20 9:53","入园免费");
        notifyTexts.add(n2);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notify, container, false);
    }



}