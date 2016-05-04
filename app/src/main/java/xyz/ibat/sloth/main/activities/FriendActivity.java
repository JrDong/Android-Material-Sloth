package xyz.ibat.sloth.main.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;

/**
 * create by DongJr 2016/05/04
 */
public class FriendActivity extends AppCompatActivity {

    @Bind(R.id.first)
    View first;
    @Bind(R.id.second)
    View second;


    private float startY;
    private float endY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endY  = event.getY();
                float moveY = endY - startY;
                first.setY(moveY);
                break;
        }
        return true;
    }
}
