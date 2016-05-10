/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package matise.mapbox;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;


import android.app.Activity;


// This proxy can be created by calling Mapbox.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule=MapboxModule.class)
public class MapViewProxy extends TiViewProxy
{
    // Standard Debugging variables
    private static final String TAG = "MapViewProxy";
    private static final boolean DBG = TiConfig.LOGD;

    private MapView mapView;

    private class MapViewView extends TiUIView
    {


        public MapViewView(TiViewProxy proxy) {
            super(proxy);

            // Get access token from AndroidManifest
            String accessToken = "";

            try {
                ApplicationInfo ai = proxy.getActivity().getPackageManager().getApplicationInfo(proxy.getActivity().getPackageName(), PackageManager.GET_META_DATA);
                Bundle bundle = ai.metaData;
                accessToken = bundle.getString("matise.mapbox.access_token");

                Log.i(TAG, "access token: " + accessToken);
            } catch (NameNotFoundException e) {
                Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
            } catch (NullPointerException e) {
            	Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
            }

            // Get MapView from layout file
            String packageName = proxy.getActivity().getPackageName();
			Resources resources = proxy.getActivity().getResources();
			View mapViewWrapper;
			int resId_mapboxLayout = -1;
			int resId_mapView = -1;

			resId_mapboxLayout = resources.getIdentifier("matise_mapbox", "layout", packageName);
			resId_mapView = resources.getIdentifier("matiseMapView","id", packageName);

			LayoutInflater inflater = LayoutInflater.from(getActivity());
			mapViewWrapper = inflater.inflate(resId_mapboxLayout, null);
			mapView = (MapView) mapViewWrapper.findViewById(resId_mapView);

            setNativeView(mapViewWrapper);

            // setNativeView(view);
            //
            // mapView = new MapView(proxy.getActivity());
            // mapView.setAccessToken(accessToken);
            //
            // //mapView.onCreate(savedInstanceState);
            // mapView.getMapAsync(new OnMapReadyCallback() {
            //     @Override
            //     public void onMapReady(MapboxMap mapboxMap) {
            //
            //         // Customize map with markers, polylines, etc.
            //     }
            // });
            //
            // view.addView(mapView);
        }

        @Override
        public void processProperties(KrollDict d)
        {
            super.processProperties(d);
        }
    }


    // Constructor
    public MapViewProxy()
    {
        super();
    }

    @Override
    public TiUIView createView(Activity activity)
    {
        TiUIView view = new MapViewView(this);
        view.getLayoutParams().autoFillsHeight = true;
        view.getLayoutParams().autoFillsWidth = true;
        return view;
    }

    ////////////////////
    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //
    //     String packageName = proxy.getActivity().getPackageName();
	// 	Resources resources = proxy.getActivity().getResources();
    //
    //
    //
    //     mapView = (MapView) findViewById(R.id.mapView);
    //     mapView.onCreate(savedInstanceState);
    //     mapView.getMapAsync(new OnMapReadyCallback() {
    //         @Override
    //         public void onMapReady(MapboxMap mapboxMap) {
    //
    //             // Customize map with markers, polylines, etc.
    //         }
    //     });
    // }
    //
    // @Override
    // public void onResume() {
    //     super.onResume();
    //     mapView.onResume();
    // }
    //
    // @Override
    // public void onPause() {
    //     super.onPause();
    //     mapView.onPause();
    // }
    //
    // @Override
    // public void onLowMemory() {
    //     super.onLowMemory();
    //     mapView.onLowMemory();
    // }
    //
    // @Override
    // protected void onDestroy() {
    //     super.onDestroy();
    //     mapView.onDestroy();
    // }
    //
    // @Override
    // protected void onSaveInstanceState(Bundle outState) {
    //     super.onSaveInstanceState(outState);
    //     mapView.onSaveInstanceState(outState);
    // }
    ////////////////////

    // Handle creation options
    @Override
    public void handleCreationDict(KrollDict options)
    {
        super.handleCreationDict(options);

        if (options.containsKey("message")) {
            Log.d(TAG, "example created with message: " + options.get("message"));
        }
    }

    // Methods
    @Kroll.method
    public void printMessage(String message)
    {
        Log.d(TAG, "printing message: " + message);
    }


    @Kroll.getProperty @Kroll.method
    public String getMessage()
    {
        return "Hello World from my module";
    }

    @Kroll.setProperty @Kroll.method
    public void setMessage(String message)
    {
        Log.d(TAG, "Tried setting module message to: " + message);
    }
}