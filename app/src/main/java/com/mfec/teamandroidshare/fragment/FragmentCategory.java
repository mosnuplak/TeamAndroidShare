package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.mfec.teamandroidshare.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentCategory extends Fragment {

    OptionsFabLayout fabWithOptions;

    public FragmentCategory() {
        super();
    }

    public static FragmentCategory newInstance() {
        FragmentCategory fragment = new FragmentCategory();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initInstances(rootView);

        fabWithOptions = (OptionsFabLayout) rootView.findViewById(R.id.fab_l);

        //Set mini fab's colors.
        fabWithOptions.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.green_fab);

        //Set main fab clicklistener.
        fabWithOptions.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Main fab clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        //Set mini fabs clicklisteners.
        fabWithOptions.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {
                switch (fabItem.getItemId()) {

                    case R.id.fab_addCategory:
                        Toast.makeText(getContext(),
                                fabItem.getTitle() + "clicked!",
                                Toast.LENGTH_SHORT).show();
                    case R.id.fab_add:
                        Toast.makeText(
                                getContext(),
                                fabItem.getTitle() + " clicked!",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        return rootView;


    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
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
