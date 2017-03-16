package xyz.ibat.sloth.base.webview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseFragment;
import xyz.ibat.sloth.domain.main.activities.ImagePreviewActivity;
import xyz.ibat.sloth.utils.T;

/**
 * Created by DongJr on 2017/3/16.
 */

public class WebFragment extends BaseFragment {

    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.webview)
    WebView mWebView;
    @Bind(R.id.btn_reload)
    FrameLayout btnReload;
    @Bind(R.id.load_fail_view)
    LinearLayout mLoadFailView;
    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    private View mRootView;

    public static final String URL_TAG = "URL_TAG";

    private String mUrl;
    private boolean progressAnimStart = false;
    private boolean isReceivedError;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_web, container, false);
        ButterKnife.bind(this, mRootView);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mainToolbar);

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mUrl = bundle.getString(URL_TAG);

        if (mUrl != null) {
            mWebView.loadUrl(mUrl);
            initView();
            initWebView();
        }
    }

    private void initView() {
        mProgress.setProgressDrawable(getResources()
                .getDrawable(R.drawable.webview_progress_bar_states));

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload();
                mLoadFailView.setVisibility(View.GONE);
                isReceivedError = false;
            }
        });

    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        //设置与js交互
        webSettings.setJavaScriptEnabled(true);
        //设置是否支持缩放,默认true
        webSettings.setSupportZoom(false);
        //设置是否显示缩放工具
        webSettings.setBuiltInZoomControls(false);


        mWebView.setWebChromeClient(new SlothWebChromeClient());

        mWebView.setWebViewClient(new SlothWebViewClient());


        mWebView.addJavascriptInterface(new JSInterface(getContext()), "webview");

    }

    private void addImageClickListner() {
        // 遍历所有的img节点，并添加onclick函数
        mWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.webview.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }

    private void startProgressAnimation() {
        progressAnimStart = true;
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mProgress, "progress", 0, 20);
        animator1.setDuration(800);
        ObjectAnimator animator2 = ObjectAnimator.ofInt(mProgress, "progress", 20, 80);
        animator2.setDuration(800);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator2).after(animator1);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    final class JSInterface {

        private Context context;

        public JSInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void openImage(String imgUrl) {
            ImagePreviewActivity.startActivity(context, imgUrl, ImagePreviewActivity.SCALETYPE_FITXY);
        }
    }


    class SlothWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress >= 100) {
                progressAnimStart = false;
                mProgress.setProgress(newProgress);
                mProgress.setVisibility(View.GONE);
            } else {
                if (!progressAnimStart) {
                    mProgress.setVisibility(View.VISIBLE);
                    startProgressAnimation();
                }
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }


    }

    class SlothWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            WebActivity.startActivity(getContext(), url);
            //while return false means the current WebView handles the url.
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            mWebView.setVisibility(View.GONE);
            mLoadFailView.setVisibility(View.VISIBLE);
            T.show(R.string.network_error);
            isReceivedError = true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!isReceivedError) {
                mWebView.setVisibility(View.VISIBLE);
            }
            addImageClickListner();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }


    }
}
