package in.dd4you.unityads;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private String unityGameID = "3556774";
    private Boolean testMode = true;
    private String banner_id = "baner1";
    private String interstitial_id = "interstitial1";
    private ViewGroup viewGroup;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        viewGroup = view.findViewById(R.id.banner_ads_view);

        // Interstital
        UnityInterstitalAdsListener interstitalAdsListener = new UnityInterstitalAdsListener();
        UnityAds.initialize(getActivity(), unityGameID, interstitalAdsListener, testMode);

        //Banner
        UnityBannerListener unityBannerListener = new UnityBannerListener();
        UnityBanners.setBannerListener(unityBannerListener);



        UnityAds.show(getActivity(), interstitial_id);
        UnityBanners.loadBanner(getActivity(), banner_id);

        return  view;
    }

    private class UnityBannerListener implements IUnityBannerListener {

        @Override
        public void onUnityBannerLoaded(String s, View view) {
            viewGroup.removeView(view);
            viewGroup.addView(view);
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
            UnityBanners.loadBanner(getActivity(), banner_id);
        }
    }
    private class UnityInterstitalAdsListener implements IUnityAdsListener {
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
            UnityAds.show(getActivity(), interstitial_id);
        }
    }
}
