package xyz.ibat.sloth.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import xyz.ibat.sloth.R;

/**
 * Created by DongJr on 2017/1/16.
 */

public class NewLoadMoreWrapper extends LoadMoreWrapper {

    private LoadState mState = LoadState.LOAD;

    private View mFooterView;

    private ProgressBar loadProgress;
    private TextView loadText;

    private LayoutInflater mInflater;

    public NewLoadMoreWrapper(Context context, RecyclerView.Adapter adapter,RecyclerView parent) {
        super(adapter);

        mInflater = LayoutInflater.from(context);
        mFooterView = mInflater.inflate(R.layout.default_loading,parent, false);
        loadProgress = (ProgressBar) mFooterView.findViewById(R.id.load_progress);
        loadText = (TextView) mFooterView.findViewById(R.id.loading_text);
        setLoadMoreView(mFooterView);
    }

    public void setLoadState(LoadState state) {
        this.mState = state;
        switch (state) {
            case LOAD:
                showLoadView();
                setShouldLoadMore(true);
                break;
            case ERROR:
                setShouldLoadMore(false);
                showLoadErrorView();
                break;
            case NOMORE:
                setShouldLoadMore(false);
                showLoadNoMoreView();
                break;
            default:
                throw new IllegalArgumentException("please input correct argument!");
        }
    }

    private void showLoadView() {
        loadProgress.setVisibility(View.VISIBLE);
        loadText.setText("正在加载...");
    }

    private void showLoadErrorView() {
        loadProgress.setVisibility(View.GONE);
        loadText.setText("加载失败啦...");
    }

    private void showLoadNoMoreView() {
        loadProgress.setVisibility(View.GONE);
        loadText.setText("已经到底啦");
    }

    public LoadState getCurrentState() {
        return mState;
    }

    public enum LoadState {
        LOAD,
        ERROR,
        NOMORE
    }

}
