package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentWebTitle extends Fragment {
    WebView myWebView;
    public FragmentWebTitle() {
        super();
    }

    public static FragmentWebTitle newInstance() {
        FragmentWebTitle fragment = new FragmentWebTitle();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_webview_title, container, false);
        initInstances(rootView);

        myWebView.getSettings().setJavaScriptEnabled(true);
        Fragment fragment = this;

        myWebView.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }
        });

        myWebView.loadUrl("http://www.youtube.com");

        return rootView;
    }

    private void initInstances(View rootView) {
        myWebView = (WebView) rootView.findViewById(R.id.webview);
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
