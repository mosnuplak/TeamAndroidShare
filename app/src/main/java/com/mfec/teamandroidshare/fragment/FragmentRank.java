package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.RankDao;
import com.mfec.teamandroidshare.view.RankAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.mfec.teamandroidshare.R.id.rvRank;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentRank extends Fragment {

    RecyclerView rv_rank;
    RankAdapter adapter;
    private List<RankDao> rankList;

    public FragmentRank() {
        super();
    }

    public static FragmentRank newInstance() {
        FragmentRank fragment = new FragmentRank();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rank, container, false);
        initInstances(rootView);

        getRankItem();


        return rootView;
    }



    private void getRankItem(){
        rankList = new ArrayList<>();
        rankList.add(new RankDao("1","mos"));
        rankList.add(new RankDao("2","fah"));
        rankList.add(new RankDao("3","game"));
        rankList.add(new RankDao("5","pop"));
        rankList.add(new RankDao("6","pop1"));
        rankList.add(new RankDao("7","pop2"));
        rankList.add(new RankDao("8","pop3"));
        rankList.add(new RankDao("9","pop4"));
        rankList.add(new RankDao("10","pop5"));
        rankList.add(new RankDao("11","pop6"));
        rankList.add(new RankDao("12","pop7"));
        rankList.add(new RankDao("13","pop8"));
        rankList.add(new RankDao("14","pop9"));
        rankList.add(new RankDao("15","pop10"));
        rankList.add(new RankDao("16","pop11"));
        rankList.add(new RankDao("17","pop12"));
        rankList.add(new RankDao("18","pop13"));
        rankList.add(new RankDao("19","pop14"));
        rankList.add(new RankDao("20","pop15"));
        rankList.add(new RankDao("2100","เป็นแค่ลูกแมวตัวขาวๆกลมๆเล็กๆไม่ใหญ่ๆชอบที่กลิ้งๆหลุนๆมาบรรจบพบดวงอาทิตย์ริมหาดทราย"));

    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        rv_rank = (RecyclerView) rootView.findViewById(rvRank);

        getRankItem();

        GridLayoutManager manager = new GridLayoutManager(getContext().getApplicationContext(), 1);
        rv_rank.setLayoutManager(manager);

        adapter = new RankAdapter(this ,rankList ,FragmentRank.this);
        rv_rank.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
