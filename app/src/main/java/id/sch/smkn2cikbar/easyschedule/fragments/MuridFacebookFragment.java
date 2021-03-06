package id.sch.smkn2cikbar.easyschedule.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import id.sch.smkn2cikbar.easyschedule.MuridActivity;
import id.sch.smkn2cikbar.easyschedule.R;
import id.sch.smkn2cikbar.easyschedule.models.progressDialogModel;

import static id.sch.smkn2cikbar.easyschedule.adapters.DetectConnection.isNetworkStatusAvialable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MuridFacebookFragment extends Fragment {

    private Toolbar toolbar;
    private MuridActivity muridActivity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String baseURl = "https://facebook.com";

    private static final String widgetInfo = "<div id=\"fb-root\"></div>\n" +
            "<script>(function(d, s, id) {\n" +
            "  var js, fjs = d.getElementsByTagName(s)[0];\n" +
            "  if (d.getElementById(id)) return;\n" +
            "  js = d.createElement(s); js.id = id;\n" +
            "  js.src = \"//connect.facebook.net/id_ID/sdk.js#xfbml=1&version=v2.7\";\n" +
            "  fjs.parentNode.insertBefore(js, fjs);\n" +
            "}(document, 'script', 'facebook-jssdk'));</script>" +
            "<div class=\"fb-page\" data-href=\"https://www.facebook.com/smkn2cikbar/\" data-tabs=\"timeline\" data-small-header=\"false\" data-adapt-container-width=\"true\" data-hide-cover=\"false\" data-show-facepile=\"true\"><blockquote cite=\"https://www.facebook.com/smkn2cikbar/\" class=\"fb-xfbml-parse-ignore\"><a href=\"https://www.facebook.com/smkn2cikbar/\">SMK Negeri 2 Cikarang Barat</a></blockquote></div>";

    public MuridFacebookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        muridActivity = (MuridActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        final WebView webView = (WebView) view.findViewById(R.id.timeline_webview);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isNetworkStatusAvialable (getActivity().getApplicationContext())) {
                    webView.reload();
                    webView.getSettings().setDomStorageEnabled(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebViewClient(new MyWebViewClient());
                    webView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);
                    progressDialogModel.pdMenyiapkanDataLogin(getActivity());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection!!", Toast.LENGTH_LONG).show();
                    webView.loadDataWithBaseURL(null, "<html><body><img width=\"100%\" height=\"100%\" src=\"file:///android_res/drawable/offline.png\"></body></html>", "text/html", "UTF-8", null);
                    progressDialogModel.hideProgressDialog();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        setupToolbar();

        if(isNetworkStatusAvialable (getActivity().getApplicationContext())) {
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new MyWebViewClient());
            webView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);
            progressDialogModel.pdMenyiapkanDataLogin(getActivity());
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection!!", Toast.LENGTH_LONG).show();
            webView.loadDataWithBaseURL(null, "<html><body><img width=\"100%\" height=\"100%\" src=\"file:///android_res/drawable/offline.png\"></body></html>", "text/html", "UTF-8", null);
            progressDialogModel.hideProgressDialog();
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(isNetworkStatusAvialable (getActivity().getApplicationContext())) {
                view.loadUrl(url);
                progressDialogModel.pdMenyiapkanDataLogin(getActivity());
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection!!", Toast.LENGTH_LONG).show();
                view.loadDataWithBaseURL(null, "<html><body><img width=\"100%\" height=\"100%\" src=\"file:///android_res/drawable/offline.png\"></body></html>", "text/html", "UTF-8", null);
                progressDialogModel.hideProgressDialog();
                swipeRefreshLayout.setRefreshing(false);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialogModel.hideProgressDialog();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        muridActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(R.string.facebook_fragment_title);
        toolbar.setSubtitle(R.string.facebook_fragment_subtitle);
        muridActivity.setSupportActionBar(toolbar);
    }

}
