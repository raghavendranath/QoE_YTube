

package com.example.ragha.qoe_ytube;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import com.example.ragha.qoe_ytube.com.example.logger.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    Button button;
    String video;
    WebView wView;
    String frameVideo;
    StringGetter stringGetter;
    public static long time;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.playVideo);
        edit = (EditText) findViewById(R.id.VideoID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                video = edit.getText().toString();
                time = System.currentTimeMillis();
                initWebView();
            }
        });
    }


    private void initWebView() {
        wView = (WebView) findViewById(R.id.mView);
        wView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                try {
                    Log.d("MyApplication", cm.message() + " -- From line "
                            + cm.lineNumber() + " of "
                            + cm.sourceId() );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        wView.setWebViewClient(new WebViewClient());
        stringGetter = new StringGetter(this);
        wView.setLayerType(wView.LAYER_TYPE_HARDWARE, null);
        WebSettings webSettings = wView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        webSettings.setJavaScriptEnabled(true);
        /* try {
            final File path = new File(
                    Environment.getExternalStorageDirectory(), "QoE_DailyMotion_Logs");
            if (!path.exists()) {
                path.mkdir();
            }

            Runtime.getRuntime().exec(
                    "logcat -d -f " + path + File.separator
                            + "QoE_DM_logcat_" + System.currentTimeMillis()
                            + ".txt");

        } catch (IOException e) {
            e.printStackTrace();
        }*/

//https://github.com/travist/minplayer/blob/master/doc/minplayer.players.dailymotion.js.html
//http://api.dmcloud.net/static/dmplayer/dmplayer-sdk.html
//http://cgit.drupalcode.org/mediafront/tree/players/osmplayer/player/minplayer/src/minplayer.players.dailymotion.js?id=94dc313ee8a2533f80bf649fbb94fc4c49a595fa
//http://embed.plnkr.co/o2R49j/
        webSettings.setJavaScriptEnabled(true);
        wView.loadUrl("file:///android_asset/ytube.html");
       /* try {
            final File path = new File(
                    Environment.getExternalStorageDirectory(), "QoE_DailyMotion_Logs");
            if (!path.exists()) {
                path.mkdir();
            }
            //"logcat  -d -f " + path + File.separator
            Runtime.getRuntime().exec(
                    "logcat -d -f " + path + File.separator
                            + "QoE_DM_logcat_" + System.currentTimeMillis()
                            + ".txt");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //wView.addJavascriptInterface(new StringGetter(this), "AndroidFunction");

    }

    public class StringGetter {
        Context jContext;

        StringGetter(Context context){
            jContext = context;
        }
        @JavascriptInterface
        public String getString() {
            return video;

        }

        @JavascriptInterface
        public void setString() {
            //count ++;

        }
    }




}

