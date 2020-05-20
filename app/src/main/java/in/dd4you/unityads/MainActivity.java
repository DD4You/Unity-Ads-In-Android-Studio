package in.dd4you.unityads;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class MainActivity extends AppCompatActivity {

    private String unityGameID = "3556774";
    private Boolean testMode = true;
    private String banner_id = "baner1";
    private String interstitial_id = "interstitial1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Interstital
        UnityInterstitalAdsListener interstitalAdsListener = new UnityInterstitalAdsListener();
        UnityAds.initialize(this, unityGameID, interstitalAdsListener, testMode);

        //Banner
        UnityBannerListener unityBannerListener = new UnityBannerListener();
        UnityBanners.setBannerListener(unityBannerListener);


        UnityAds.show(MainActivity.this, interstitial_id);
        UnityBanners.loadBanner(MainActivity.this, banner_id);
    }

    private class UnityBannerListener implements IUnityBannerListener{

        @Override
        public void onUnityBannerLoaded(String s, View view) {
            ((ViewGroup) findViewById(R.id.banner_ads_view)).removeView(view);
            ((ViewGroup) findViewById(R.id.banner_ads_view)).addView(view);
         //   Log.d("TEST_MAINACTIVITY","Banner loaded "+s);
        }

        @Override
        public void onUnityBannerUnloaded(String s) {
           // Log.d("TEST_MAINACTIVITY","Banner Unloaded "+s);
        }

        @Override
        public void onUnityBannerShow(String s) {
           // Log.d("TEST_MAINACTIVITY","Banner SHow "+s);
        }

        @Override
        public void onUnityBannerClick(String s) {
          //  Log.d("TEST_MAINACTIVITY","Banner Click "+s);
        }

        @Override
        public void onUnityBannerHide(String s) {
           // Log.d("TEST_MAINACTIVITY","Banner Hide "+s);
        }

        @Override
        public void onUnityBannerError(String s) {
           // Log.d("TEST_MAINACTIVITY","Banner Error "+s);
            UnityBanners.loadBanner(MainActivity.this, banner_id);
        }
    }
    private class UnityInterstitalAdsListener implements IUnityAdsListener{
        @Override
        public void onUnityAdsReady(String s) {
            Log.d("TEST_MAINACTIVITY","Inertstitial Ready "+s);
        }
        @Override
        public void onUnityAdsStart(String s) {
            Log.d("TEST_MAINACTIVITY","Inertstitial Start "+s);
        }
        @Override
        public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
            Log.d("TEST_MAINACTIVITY","Inertstitial Finish "+s);
        }
        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
            Log.d("TEST_MAINACTIVITY","Inertstitial Error "+s);
            UnityAds.show(MainActivity.this, interstitial_id);
        }
    }
}
