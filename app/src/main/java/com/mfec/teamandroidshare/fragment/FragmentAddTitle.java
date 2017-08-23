package com.mfec.teamandroidshare.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.dao.WrapperDao;
import com.mfec.teamandroidshare.manager.SharedPrefUtil;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentAddTitle extends Fragment implements View.OnClickListener {

    public EditText etTitleDis;
    public EditText etTilteName;
    public EditText etTitleLink;
    public TextView tvAlert;
    public Button btnAdd;
    public String cateName;
    public String userId;
    public RelativeLayout relative;
    public LinearLayout linear;
    public Boolean checkAdd;
    SharedPrefUtil sharedPrefUtil;

    public FragmentAddTitle() {
        super();
    }

    public static FragmentAddTitle newInstance(String cateName) {
        FragmentAddTitle fragment = new FragmentAddTitle();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("cateName", cateName);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefUtil = new SharedPrefUtil(getContext());

        if (getArguments() != null)
            cateName = getArguments().getString("cateName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_title, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        etTitleDis = (EditText) rootView.findViewById(R.id.etTitleDis);
        etTilteName = (EditText) rootView.findViewById(R.id.etTilteName);
        etTitleLink = (EditText) rootView.findViewById(R.id.etTitleLink);
        tvAlert = (TextView) rootView.findViewById(R.id.tvAlert);
        relative = (RelativeLayout) rootView.findViewById(R.id.relative);
        linear = (LinearLayout) rootView.findViewById(R.id.linear);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        initHideKeyboard(relative);


        etTitleLink.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    addTopic();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity()
                            .getCurrentFocus()
                            .getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
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

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            addTopic();
        }
    }

    private void addTopic() {
        userId = sharedPrefUtil.getUserId();
        if (!TextUtils.isEmpty(etTilteName.getText().toString()) && !TextUtils.isEmpty(etTitleDis.getText().toString())
                && !TextUtils.isEmpty(etTitleLink.getText().toString())) {
            //Toast.makeText(getActivity(), "mosNaja" + cateName, Toast.LENGTH_SHORT).show();
            WrapperDao wrapper = new WrapperDao();
            CategoryDao categoryDao = new CategoryDao();
            TitleDao titleDao = new TitleDao();
            categoryDao.setCateName(cateName);

            titleDao.setHead(etTilteName.getText().toString());
            titleDao.setLink(etTitleLink.getText().toString());
            titleDao.setDescription(etTitleDis.getText().toString());
            titleDao.setUserId(userId);

            wrapper.setTitleDao(titleDao);
            wrapper.setCategoryDao(categoryDao);

            Call<Boolean> call = HttpManagerNice.getInstance().getService().AddTopic(wrapper);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.isSuccessful()) {

                           checkAdd = response.body();
                        if (checkAdd == true) {
                            Toast.makeText(getActivity(),
                                    "บันทึกสำเร็จ",
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getActivity(),
                                    "ไม่สำเร็จกรุณณาลองอีกครั้ง",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        try {
                            Toast.makeText(getActivity(),
                                    response.errorBody().string(),
                                    Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {

                }
            });

//            etTilteName.setText("");
//            etTitleDis.setText("");
//            etTitleLink.setText("");
//            tvAlert.setText(R.string.text_alert_success);
//            tvAlert.setTextColor(Color.GREEN);
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.text_alert_success);
            builder.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getContext(),
                            R.string.text_alert_success, Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentTitle, FragmentTitle.newInstance(cateName,"LoadTopicList"), "getFragmentTitle")
                            .commit();
                }
            });
            builder.show();
        } else {
//            tvAlert.setText(R.string.text_alert_thisform);
//            tvAlert.setTextColor(Color.RED);
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.text_alert_thisform);
            builder.setNegativeButton(R.string.text_continue, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(),
                            R.string.text_alert_thisform, Toast.LENGTH_SHORT).show();
                    //dialog.dismiss();
                }
            });
            builder.show();


        }
    }

    private void initHideKeyboard(RelativeLayout touchInterceptor) {
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setHideKeyboard(etTilteName, v, event);
                    setHideKeyboard(etTitleDis, v, event);
                    setHideKeyboard(etTitleLink, v, event);
                }
                return false;
            }
        });
    }

    private void setHideKeyboard(final EditText editText, View v, MotionEvent event) {
        if (editText.isFocused()) {
            Rect outRect = new Rect();
            editText.getGlobalVisibleRect(outRect);
            if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                editText.clearFocus();
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

}

