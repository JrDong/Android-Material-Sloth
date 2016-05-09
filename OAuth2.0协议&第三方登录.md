#OAuth2.0协议&第三方登录（微信，QQ，微博）
##OAuth 2.0协议
###1.应用场景 
###OAuth 2.0是一个关于授权的开放标准。
###假如某个应用需要得到你的授权，如果不使用OAuth2.0协议，会要求输入账号密码。这样就带来了一些问题：
1.第三方应用会保存用户的账户和密码，这样很不安全。  
2.用户没法限制第三方应用的授权范围和有效期。  
3.用户只有修改密码才会取消授权，但这样的话会使其他的第三方授权也失效。  
4.如果第三方应用被破解，那么就会导致用户密码泄露，和数据泄露。  
###使用OAuth 2.0就不会出现上述问题了。
###2.OAuth思路
OAuth在"客户端"与"服务提供商"之间，设置了一个授权层（authorization layer）。  
"客户端"不能直接登录"服务提供商"，只能登录授权层，以此将用户与客户端区分开来。"客户端"登录授权层所用的令牌（token），与用户的密码不同。用户可以在登录的时候，指定授权层令牌的权限范围和有效期。（一般是开发时指定scope，规定权限范围。）  
"客户端"登录授权层以后，"服务提供商"根据令牌的权限范围和有效期，向"客户端"开放用户储存的资料。这个令牌授权“客户端”在特定时间内（assert_token具有有效期）访问特定资源。这样就恰如其分的控制了第三方应用所获取的权限。
###3.运行流程  

1.打开客户端后，客户端要求用户授权  
2.用户同意授权  
3.客户端使用授权，向认证服务器（第三方认证服务器）申请令牌  
4.认证服务器进行认证后，同意发放令牌  
5.用户使用令牌，向资源服务器申请资源(第三方资源服务器，可以是一个服务器，也可以是两个)  
6.资源服务器确认令牌无误，同意向客户端开放资源 


![Alt text](file:///C:/Users/aidonglei/Desktop/OAuth授权流程.jpg)
##微信登录
###0.个人感觉微信登录最大的难点在回调上，如果接收不到回调，请确认1，2两点。并仔细检查应用的签名和包名，和在微信开放平台申请时候的完全一致。
###1.在AndroidManifest.xml中配置，其中Theme.NoDisplay代表透明主题。
	
	<activity
            android:name="com.xywy.xytrain.wxapi.WXEntryActivity"
            android:exported="true"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:launchMode="singleTop"
            android:theme="@android:style/Theme.NoDisplay"
            android:screenOrientation="portrait"/>
###2.在主项目跟目录下（包名下的目录）,新建wxapi文件夹，并创建WXEntryActivity，继承activity。必须在跟目录下，不然无法回调。
###3.微信提供了两个回调类onReq和onResp，客户端将消息发送的微信会回调onReq，从微信接受到消息会回调onResp。在onResp回调方法里，可以得到ErrCode，code等参数，通过ErrCode可以判断用户行为，通过Code可以换取access_token，这个code只有在ErrCode为0时才有效。
##QQ登录
###1.QQ登录相比较微信登录要简单点，初始化QQ,和IUiListener回调
	 if (mTencent == null) {
            mTencent = Tencent.createInstance(MineConstants.QQAPP_ID, getContext());
        }
###2.在进行登录的activity中重写OnActivityResult方法
	  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //一定要在与fragment绑定的activity中重写此方法，不然无法回调
        if (requestCode == Constants.REQUEST_API ||
                requestCode == Constants.REQUEST_LOGIN) {
            Tencent.handleResultData(data, ThirdLoginFragment.mTencentListener);
        }
    }
###3.IUiListener中有三个回调方法，onComplete(Object response)授权成功调用,onError(UiError e)授权失败调用,onCancel()授权取消调用.onComplete中的response是一个JsonObject，里面含有OpenId， AccessToken等参数，通过参数就可以取得用户信息。 
###4.注意一点，QQ文档中介绍，只需要在AndroidManifest.xml中配置
	<activity
            android:name="com.tencent.tauth.AuthActivity"
            android:launchMode="singleTask"
            android:noHistory="true">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data android:scheme="tencent+"your appid"" />
            </intent-filter>
        </activity>
###但如果使用最新的sdk还需要配置以下内容，这点在文档中没有介绍，走了一点弯路。
	 <activity
            android:name="com.tencent.connect.common.AssistActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
##微博登录
###1.微博登录文档写的比较详细，大家看文档就可以了。https://github.com/sinaweibosdk/weibo_android_sdk
###2.注意一点，如果把第三方登录放入子项目中，微博的jni文件需要放在main目录下，不然会找不到jni文件。
