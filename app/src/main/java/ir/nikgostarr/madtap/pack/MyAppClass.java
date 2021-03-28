package ir.nikgostarr.madtap.pack;

import android.app.Application;

import com.smartlook.sdk.smartlook.Smartlook;


public class MyAppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Smartlook.setupAndStartRecording("8fdd3d9a6270ed4a0ab36bde331a745fd91d9ca9");
    }
}
