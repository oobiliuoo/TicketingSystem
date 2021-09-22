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
       NotifyText n1 = new NotifyText("景区入园通知","2021-9-19 9:53","一、高椅岭景区入园须知( 4月至10月营业期):\n" +
               "\n" +
               "1、高椅岭门票免费\n" +
               "\n" +
               "2、入园时间为8:00—17:00，入园时请出示门票，每票只限一人当日一次入园使用，门票一经使用即作废，不设门票退换、退款及离园再进服务。\n" +
               "\n" +
               "3、敬请游客遵守园内秩序，听从工作人员指引,以免发生意外。\n" +
               "\n" +
               "4、为了保持园内的环境卫生及游客安全,游客不可携带宠物、食品、饮料、玻璃器皿及其它危险物品入园;不可携带任何酒类入园;请游客在指定区域吸烟。\n" +
               "\n" +
               "5、禁止携带宠物入园。\n" +
               "\n" +
               "6、请游客爱护园内游乐设备，损坏者照价赔偿。\n" +
               "\n" +
               "7、在工作人员的指导下游玩；如在园内发生意外,请及时与现场工作人员联系。\n" +
               "\n" +
               "8、园内项目因天气原因需进行补雪可能造成游乐项目运行时间延迟或暂停开放。\n" +
               "\n" +
               "9、在不能满足园区安全运行的恶劣天气及其它自然灾害等情况下，园内项目将关闭或部分关闭，本园无须事先告知。\n" +
               "\n" +
               "10、根据项目截止运行时间及景区闭园时间，景区会预先告知停止排队时间，届时将提前终止游客排队或入园，请您谅解。\n" +
               "\n" +
               "11、请勿有以下行为：持危险物品入园、贩卖或陈列物品、散发传单、集会或演讲、妨碍本园及相关设施运营的一切行为。\n" +
               "\n" +
               "12、为了您的安全，我们特别提醒您园内外部分项目不适合高血压、心脏病、颈椎腰椎等患者、孕妇、酒醉者及有癫痫病史等游客游玩；12岁以下儿童及60周岁以上老人需持票监护人陪同，并视其他状况等因素参与相关游乐。导致不良后果，本园不承担任何责任。\n" +
               "\n" +
               "13、对于因游客自身的疏忽、过失所引起的财产损失或不遵守《雁栖湖景区入园须知》所引起的事故，景区概不负责。\n" +
               "\n" +
               "14、疫情期间防控措施\n" +
               "\n" +
               "（1）为确保游客安全，所有进入景区人员需佩戴口罩、配合测量体温，未佩戴口罩、体温异常的游客将禁止入园。\n" +
               "\n" +
               "（2）游客须主动出示“健康码”及“通信大数据行程卡”。凡近14天内去过国内中高风险地区所在设区市（直辖市为县区，非中高风险地区）的人员，应持有48小时内核酸检测阴性证明。健康码异常、去过中高风险所在设区市但无法出示核酸检测阴性证明的游客将禁止入园。\n" +
               "\n" +
               "（3）景区实行实名制购票，游客可进行网络购票或扫码购票，景区提倡无现金、无接触支付。购票成功后凭二维码或自助机取票入园。\n" +
               "\n" +
               "以上条款最终解释权归高椅岭开发有限公司所有");
       notifyTexts.add(n1);

        NotifyText n2 = new NotifyText("景区常问问题指南","2021-9-20 9:53","问：每7天可预约三个景区，7天是按照自然周来计算的吗？ \n" +
                "\n" +
                "　　答：不是的，是从第一次预约成功的那天开始算，往后推6天即为一周。\n" +
                "\n" +
                "　　比如，小A在8月11日成功预约8月15日黄鹤楼的入园名额，8月18日重新恢复预约3个景区的权利。\n" +
                "\n" +
                "　　问：欢乐谷现在还是每次只能预约1人、每日仅能预约次日门票吗？ \n" +
                "\n" +
                "　　答：从明日起，欢乐谷恢复每次预约可预约三人、每次可预约当日起7日内景区门票的规则。\n" +
                "\n" +
                "　　但由于欢乐谷属于热门景区，入园名额相对抢手，今日投放名额预约完毕明日还可以预约，还请保持平常心，我们一直免费到12月31日的。\n" +
                "\n" +
                "　　问：今日想预约后天的门票但显示“今日已约完”，明天还可以继续预约吗？ \n" +
                "\n" +
                "　　答：可以继续预约的。每个景区每天的门票按计划投放7天，今天投放的约完了，明天还会继续投放当日起7日内的门票。所以不是今天约完了，7天的入园名额都没有了。\n" +
                "\n" +
                "　　比如，今天8月13日，我想预约东湖海洋世界8月16日的门票，发现显示今日已约完，14日、15日、16日都还是有机会预约8月16日的。\n" +
                "\n" +
                "　　问：我预约成功了，必须要带身份证才能进去吗？ \n" +
                "\n" +
                "　　答：对的，预约成功后请一定记得携带身份证入园，身份证是本次活动最重要的凭证。\n" +
                "\n" +
                "　　问：如果预约成功因为时间来不及没去，会有什么后果吗？对后续预约有影响吗？ \n" +
                "\n" +
                "　　答：暂时不会影响继续参加活动，但是占用了个人每周可预约3个景区、以及活动期内该景区总共可预约3次的预约次数。\n" +
                "\n" +
                "　　问：重复预订同一个景区，是否影响使用？ \n" +
                "\n" +
                "　　答：活动期内，每人仅可成功预约同一景区3次，并且不可更改和退订，请大家慎重预约。\n" +
                "\n" +
                "　　自8月8日武汉23家A级付费景区免票以来，黄鹤楼、欢乐谷、海昌极地海洋公园等旅游景区接待游客量大幅上升，为了更好的出游体验，建议大家错峰出游。");
        notifyTexts.add(n2);

        NotifyText n3 = new NotifyText("景区电话","2021-9-23 11:59","高椅岭预约电话： 0957-6666123\n"+ "高椅岭投诉电话： 0957-5466123\n"+ "高椅岭缆车电话： 0957-62356123\n"+ "高椅岭滑道电话： 0957-61251123\n");
        notifyTexts.add(n3);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notify, container, false);
    }



}