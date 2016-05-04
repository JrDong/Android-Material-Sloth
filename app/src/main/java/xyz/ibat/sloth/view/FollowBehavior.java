package xyz.ibat.sloth.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import xyz.ibat.sloth.R;

/**
 * Created by DongJr on 2016/5/4.
 */
public class FollowBehavior extends CoordinatorLayout.Behavior {

    private int targetId;

    public FollowBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
        //AttributeSet和TypedArray区别。都可以获取属性值，但TypedArray更方便些，如果属性引用了
        //values文件下的值，用AttributeSet拿到的是属性id,但TypedArray可以获得具体的值
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FollowBehavior);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            if(a.getIndex(i) == R.styleable.FollowBehavior_target){
                targetId = a.getResourceId(attr, -1);
            }
        }
        a.recycle();
    }

    //确定依赖哪个view
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == targetId;
    }

    //child 是指应用behavior的View ，dependency 担任触发behavior的角色，其实就是上面依赖的view，并与child进行互动。
    //确定你是否依赖于这个View。CoordinatorLayout会将自己所有View遍历判断。
    //如果确定依赖。这个方法很重要。当所依赖的View变动时会回调这个方法。
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY()+dependency.getHeight());
        return true;
    }
}
