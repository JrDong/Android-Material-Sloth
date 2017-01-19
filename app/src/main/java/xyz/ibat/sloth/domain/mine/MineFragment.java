package xyz.ibat.sloth.domain.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.base.BaseFragment;
import xyz.ibat.sloth.base.webview.WebActivity;
import xyz.ibat.sloth.network.ImageLoader;

/**
 * Created by DongJr on 2016/4/15.
 */
public class MineFragment extends BaseFragment {


    @Bind(R.id.user_head)
    ImageView userHead;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.tv_thanks)
    TextView tvThanks;
    @Bind(R.id.tv_blog)
    TextView tvBlog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        collapsingToolbar.setTitle("小董同学");
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ImageLoader.loadCircle(R.mipmap.icon_head, userHead);



        setTextLink();
    }

    private void setTextLink() {
        interceptLink(tvThanks);
        interceptLink(tvBlog);
    }

    private void interceptLink(TextView view) {

        view.setMovementMethod(LinkMovementMethod.getInstance());

        CharSequence text = view.getText();

        if (text instanceof Spannable) {

            Spannable spannable = (Spannable) text;
            URLSpan[] spans = spannable.getSpans(0, text.length(), URLSpan.class);
            if (spans.length == 0) {
                return;
            }
            SpannableStringBuilder ssb = new SpannableStringBuilder(text);

            for (URLSpan urlSpan : spans) {
                final String url = urlSpan.getURL();
                if (URLUtil.isNetworkUrl(url)) {

                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            WebActivity.startActivity(getActivity(), url);
                        }
                    };
                    ssb.setSpan(clickableSpan, spannable.getSpanStart(urlSpan)
                            , spannable.getSpanEnd(urlSpan), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            view.setText(ssb);
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
