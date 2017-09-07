package com.mobcast.urlscheme;

/**
 * Created by kataoka on 2017/09/07.
 */

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerNativeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SchemeIntentActivity extends Activity{
    private static Activity mThisActivity;

    private static Handler mMoveActivityHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mThisActivity != null) {
                // 起動アプリのメインアクティビティクラスを指定    
                Intent i = new Intent(mThisActivity.getApplication(), UnityPlayerNativeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                mThisActivity.startActivity(i);
                mThisActivity.finish();
                mThisActivity = null;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Unityの取得元に応じて変更.
        UnityPlayer.UnitySendMessage("送信先クラス", "送信先メソッド", getIntent().getData().toString());
        mThisActivity = this;
        mMoveActivityHandler.sendEmptyMessageDelayed(0, 10);
    }
}