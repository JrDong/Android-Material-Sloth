#Material Design控件介绍（一）———Toolbar,DrawerLayout,NavigationView
###项目地址:https://github.com/JrDong/Android-Sloth
##概述
###介绍的控件有：
###Toolbar,TabLayout,DrawerLayout,NavigationView
###CoordinatorLayout,AppBarLayout,CollapsingToolbarLayout
###NestedScrollView,CardView,FloatingActionButton,Snackbar等
###还有自定义CoordinatorLayout.Behavior。

###本次先介绍Toolbar，DrawerLayout和NavigationView
##Toolbar的用法
###之前大家都接触过ActionBar，继ActionBar之后。Google又推出了一个更加灵活的控件——Toolbar.为什么说Toolbar更加灵活呢，点开Toolbar的源码，我们发现，Toolbar继承自ViewGroup。也就是说，我们可以以Toolbar为父控件，随意的摆放我们需要的控件，并自定义控件的位置。但也可以不这样麻烦，Toolbar也提供了一系列的方法，供我们使用。注意，使用Toolbar需要在AppCompatActivity中，并使用NoActionBar的主题。  
###1.首先我们来看下布局文件
	<android.support.v7.widget.Toolbar
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main_toolbar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:minHeight="?attr/actionBarSize"
    android:fitsSystemWindows="true"
    android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
    app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
    android:background="?attr/colorPrimary">
####Theme表示Toolbar的主题样式，popupTheme表示弹出框的样式。需要改变颜色的话，需要自定义theme，这里先不做介绍。
####fitsSystemWindows这个属性，是我们使用透明状态栏时用的。当我们使用透明状态栏时。我们应用的标题栏会顶上去，占据原来系统状态栏的位置。这个属性就是用来让view可以根据系统窗口(如status bar)来调整自己的布局，如果值为true,就会调整view的paingding属性来给system windows留出空间….需要在标题栏的父控件中使用。 
###2.布局了解(图片来源于网络)
![Alt text](file:///C:\Users\aidonglei\Desktop\Material-Design\1416285884351.png)
###3.代码实现
####添加以下代码就可以使用Toolbar
	setSupportActionBar(mainToolbar);
#####setNavigationIcon：比如返回按钮等
#####setLogo:设置应用图标
#####setTitle:主标题
#####setSubTitle:副标题
#####setOnMenuItemClickListener:菜单按钮的点击事件。
###4.菜单栏用法
####在res/menu/menu_main.xml中定义
	<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      tools:context=".MainActivity">
    <item android:id="@+id/action_settings"
          android:title="@string/action_settings"
          android:orderInCategory="100"
          app:showAsAction="never"/>
    <item android:id="@+id/action_about"
        android:title="@string/action_about"
        android:orderInCategory="100"
        app:showAsAction="never"/>
    <item android:id="@+id/action_search"
        android:title="@string/action_search"
        android:orderInCategory="100"
        android:icon="@mipmap/icon_search"
        app:showAsAction="ifRoom"
        />
	</menu>
####这里需要介绍showAsAction中常用的属性：never表示始终在菜单栏中；ifRoom表示如果标题栏有空闲则在标题栏上显示，没有空闲就在菜单栏中显示；always表示无论有没有空余控件，始终显示在标题栏。不过不推荐使用always，推荐使用ifRoom。
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
####设置点击事件  

	mainToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()){
                    case R.id.action_search:
                        msg = "search";
                        break;
                    case R.id.action_about:
                        msg = "about";
                        break;
                    case R.id.action_settings:
                        msg = "setting";
                        break;
                }
                SlothUtil.showToast(msg);
                return false;
            }
        });

####至此Toolbar介绍完毕，有需要再继续做补充
##DrawerLayout，NavigationView的用法
###DrawerLayout是Google官方的侧滑菜单栏，要使用需要引入v4包，看源码我们发现DrawerLayout其实使用了ViewDragHelper这个类来进行手势处理（有时间会讲解这个类，自定义侧滑菜单）。之前我们使用DrawerLayout，菜单栏喜欢用ListView实现。但有了NavigationView之后，就方便许多。省去了写Adapter的时间。下面就两个结合起来讲解。
###首先我们来看下布局文件：
	<android.support.v4.widget.DrawerLayout
        android:id="@+id/drawerLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">


        <FrameLayout
            android:id="@+id/fl_root"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            />


        <android.support.design.widget.NavigationView
            android:id="@+id/navigation_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="left"
            app:headerLayout="@layout/navigationview_header"
            app:menu="@menu/menu_drawer" />

    </android.support.v4.widget.DrawerLayout>
###在布局的最外层是DrawerLayout，内容使用FrameLayout，Drawer使用NavigationView
###细心的同学可能会发现NavigationView多了两个属性。 app:headerLayout="@layout/navigationview_header"，app:menu="@menu/menu_drawer" 。这两个属性分别代表，菜单栏的头布局和菜单布局。我们来看下这两个属性中的内容：
1.headerLayout

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:layout_width="match_parent"
    	android:layout_height="192dp"
    	android:background="@mipmap/banner2"
    	android:fitsSystemWindows="true"
    	android:orientation="vertical"
        android:padding="16dp"
    	android:theme="@style/ThemeOverlay.AppCompat.Dark">

    <TextView
        android:id="@+id/id_link"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="16dp"
        android:text="http://ibat.xyz" />

    <TextView
        android:id="@+id/id_username"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_above="@id/id_link"
        android:text="DongJr" />

	</RelativeLayout>
2.menu

	<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <group android:checkableBehavior="single">
        <item
            android:id="@+id/nav_home"
            android:icon="@mipmap/ic_dashboard"
            android:title="Home"/>
        <item
            android:id="@+id/nav_messages"
            android:icon="@mipmap/ic_event"
            android:title="Messages"/>
        <item
            android:id="@+id/nav_friends"
            android:icon="@mipmap/ic_headset"
            android:title="Friends"/>
        <item
            android:id="@+id/nav_discussion"
            android:icon="@mipmap/ic_forum"
            android:title="Discussion"/>
    </group>

    <item android:title="Sub items">
        <menu>
            <item
                android:id="@+id/sub_item1"
                android:icon="@mipmap/ic_dashboard"
                android:title="Sub item 1"/>
            <item
                android:id="@+id/sub_item2"
                android:icon="@mipmap/ic_forum"
                android:title="Sub item 2"/>
        </menu>
    </item>
	</menu>
###写好布局后，我们就可以看到效果了。使用起来是不是很方便呢，当然，你也可以调整布局，达到自己想要的效果。
###下面我们来看下实现：
###主要是设置菜单栏监听。

	```  
	mNavigationView.setNavigationItemSelectedListener(this);  

	@Override
	    public boolean onNavigationItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.nav_home:
	                SlothUtil.showToast("HOME");
	                mDrawerLayout.closeDrawer(Gravity.LEFT);
	                startActivity(new Intent(this,HomeActivity.class));
	                break;
	            case R.id.nav_messages:
	                SlothUtil.showToast("MESSAGE");
	                mDrawerLayout.closeDrawer(Gravity.LEFT);
	                startActivity(new Intent(this,MessageActivity.class));
	                break;
	            case R.id.nav_friends:
	                SlothUtil.showToast("FRIENDS");
	                mDrawerLayout.closeDrawer(Gravity.LEFT);
	                startActivity(new Intent(this,FriendActivity.class));
	                break;
	            case R.id.nav_discussion:
	                SlothUtil.showToast("DISCUSSION");
	                mDrawerLayout.closeDrawer(Gravity.LEFT);
	                startActivity(new Intent(this,DiscussionActivity.class));
	                break;
	            case R.id.sub_item1:
	                SlothUtil.showToast("SUB_ITEM1");
	                mDrawerLayout.closeDrawer(Gravity.LEFT);
	                break;
	            case R.id.sub_item2:
	                SlothUtil.showToast("SUB_ITEM2");
	                mDrawerLayout.closeDrawer(Gravity.LEFT);
	                break;
	        }
	        return false;
	    }
 	```
###下面来讲解设置Toolbar和DrawerLayout的监听。就是我们常见的三个横杠变箭头，然后菜单栏滑出的效果。
这里需要用到ActionBarDrawerToggle这个类。  

	 private void initDrawLayout() {
        //DrawerLayout和Toolbar关联
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mainToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);
    }


		/**
	     * Activity彻底运行起来之后的回调
	     * Called when activity start-up is complete
	     * (after onStart() and onRestoreInstanceState(Bundle) have been called).
	     */
	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        //箭头旋转动画的关键
	        mDrawerToggle.syncState();
	    }
###DrawerLayout，NavigationView的用法讲解结束，有兴趣的同学可以查看源代码——Meterial Design的控件Demo。
###https://github.com/JrDong/Android-Sloth
		 
