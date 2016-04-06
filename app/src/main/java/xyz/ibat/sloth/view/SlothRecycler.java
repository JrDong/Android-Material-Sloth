package xyz.ibat.sloth.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import xyz.ibat.sloth.R;

/**
 * Created by DongJr on 2016/4/6.
 */
public class SlothRecycler extends RecyclerView {

    private boolean mIsLoading = false;
    private OnLoadListener mOnLoadListener;
    private int mLastVisiblePosition;

    private View mFootView;
    private ProgressBar mProgress;
    private TextView mTextView;

    public SlothRecycler(Context context) {
        super(context);
        init(context);
    }

    public SlothRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlothRecycler(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (!mIsLoading && getLastVisiblePosition() == layoutManager.getItemCount() - 1
                        && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadData();
                    mIsLoading = false;
                }

            }
        });
    }

    public int getLastVisiblePosition() {
        int totalItemCount = getLayoutManager().getItemCount();
        if (getLayoutManager() instanceof LinearLayoutManager) {
            mLastVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            mLastVisiblePosition = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else {
            mLastVisiblePosition = totalItemCount - 1;
        }
        return mLastVisiblePosition;
    }

    private void loadData() {
        if (mOnLoadListener != null) {
            setOnLoading(true);
            mOnLoadListener.onLoad();
        }
    }

    public void setOnLoading(boolean isLoading) {
        mIsLoading = isLoading;

    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        mOnLoadListener = onLoadListener;
    }

    public interface OnLoadListener {
        void onLoad();
    }


    public static abstract class LoadMoreAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;
        private List list;

        public LoadMoreAdaper(Context context, List list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == list.size()) {
                return 0;
            } else {
                return getItemType();
            }
        }

        public int getItemType() {
            return 1;
        }

        @Override
        public int getItemCount() {
            return list.size() + 1;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                View footView = LayoutInflater.from(context).inflate(R.layout.item_refresh_foot, parent, false);
                return new FootHolder(footView);
            } else {
                return onCreateHolder(parent, viewType);
            }
        }

        public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType);

        @Override
        public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);


    }

    static class FootHolder extends RecyclerView.ViewHolder {

        public FootHolder(View itemView) {
            super(itemView);
        }

    }
}