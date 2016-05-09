#Material Design控件介绍（一）
###项目地址:https://github.com/JrDong/Android-Sloth,欢迎Fork，点赞
##概述
###介绍的控件有：
###Toolbar,TabLayout,DrawerLayout,NavigationView
###CoordinatorLayout,AppBarLayout,CollapsingToolbarLayout
###NestedScrollView,CardView,FloatingActionButton,Snackbar等
###还有自定义CoordinatorLayout.Behavior。
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


