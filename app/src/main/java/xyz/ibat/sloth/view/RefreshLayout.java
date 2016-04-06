package xyz.ibat.sloth.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import xyz.ibat.sloth.R;

/**
 * Created by DongJr on 2016/4/5.
 */
public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    private OnLoadListener mOnloadListener;

    private ListView mListView;

    private int mLastVisiblePosition;

    //是否正在加载
    private boolean isLoading = false;

    private View mFootView;

    private ProgressBar mProgress;
    private TextView mTextView;

    public RefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public enum LoadState {
        ONLOADING,
        NOMORE
    }

    public void setLoadState(LoadState loadState) {
        switch (loadState) {
            case ONLOADING:
                mTextView.setText("正在刷新");
                mProgress.setVisibility(VISIBLE);
                break;
            case NOMORE:
                mTextView.setText("没有更多了");
                mProgress.setVisibility(GONE);
                break;

        }
    }

    private void init(Context context) {
        mFootView = View.inflate(context, R.layout.item_refresh_foot, null);
        mProgress = (ProgressBar) mFootView.findViewById(R.id.load_progress);
        mTextView = (TextView) mFootView.findViewById(R.id.loadmore_text);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mListView == null) {
            getView();
        }
    }

    //绑定当前listView
    private void getView() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                if (childView instanceof ListView) {
                    mListView = (ListView) childView;
                    setScrollListener();
                }
            }
        }
    }

    private void setScrollListener() {
        if (mListView != null) {
            mListView.setOnScrollListener(this);
        }
    }

    private void loadData() {
        if (mOnloadListener != null) {
            setLoading(true);
            mOnloadListener.onLoad();
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            //防止重绘
            if (mListView.getFooterViewsCount() == 0) {
                mListView.addFooterView(mFootView);
            }
        } else {
            mListView.removeFooterView(mFootView);
        }
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        mOnloadListener = onLoadListener;
    }

    public void setListener(OnRefreshListener onRefreshListener,OnLoadListener onLoadListener){
        setOnRefreshListener(onRefreshListener);
        mOnloadListener = onLoadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        int visibleItemCount = view.getChildCount();
        int totalItemCount = mListView.getAdapter().getCount();
        mLastVisiblePosition = mListView.getLastVisiblePosition();

        if ((visibleItemCount > 0 && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE &&
                (mLastVisiblePosition) == totalItemCount - 1)) {
            if (!isLoading) {
                loadData();
                isLoading = false;
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    public interface OnLoadListener {
        void onLoad();
    }
}
