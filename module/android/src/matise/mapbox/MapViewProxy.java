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
import org.appcelerator.titanium.TiApplication;
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

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;

// fragment test
import org.appcelerator.titanium.view.TiUIFragment;
import android.support.v4.app.Fragment;

@Kroll.proxy(creatableInModule=MapboxModule.class)
public class MapViewProxy extends TiViewProxy
{
    // Standard Debugging variables
    private static final String TAG = "MapViewProxy";
    private static final boolean DBG = TiConfig.LOGD;

    private MapView mapView;
    private MapboxMap mapboxMap;

    // Variables that are needed during setup
    private String styleUrl = Style.SATELLITE;
    private double lat = 0;
    private double lng = 0;
    private double zoom = 10;

    private class MapViewFragment extends TiUIFragment
    {
        public MapViewFragment(final TiViewProxy proxy, Activity activity) {
            super(proxy, activity);
        }

        @Override
    	protected Fragment createFragment() {
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

            // Set options
            MapboxMapOptions options = new MapboxMapOptions();
            options.accessToken(accessToken);
            options.styleUrl(styleUrl);

            if(lat != 0) {
                options.camera(new CameraPosition.Builder()
                    .target(new LatLng(lat, lng))
                    .zoom(zoom)
                    .build());
            }

            // Create MapFragment
            SupportMapFragment map = SupportMapFragment.newInstance(options);

    		if (map instanceof SupportMapFragment) {
                map.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(MapboxMap _mapboxMap) {
                        mapboxMap = _mapboxMap;

                        // Customize map with markers, polylines, etc.

                    }
                });
            }

            return map;
    	}

        @Override
        public void processProperties(KrollDict options)
        {
            super.processProperties(options);

            Log.e(TAG, "***** processing!");

            if (options.containsKey("styleUrl")) {
                //styleUrl = options.get("styleUrl");
                // Log.e(TAG, "***** styleUrl!");
            }
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
        MapViewFragment view = new MapViewFragment(this, activity);
        view.getLayoutParams().autoFillsHeight = true;
        view.getLayoutParams().autoFillsWidth = true;
        return view;
    }

    // Handle creation options
    @Override
    public void handleCreationDict(KrollDict options)
    {
        Log.e(TAG, "***** handleCreationDict");

        super.handleCreationDict(options);

        if (options.containsKey("styleUrl")) {
            styleUrl = TiConvert.toString(options.get("styleUrl"));
        }

        if (options.containsKey("lat") && options.containsKey("lng")) {
            lat = TiConvert.toDouble(options.get("lat"));
            lng = TiConvert.toDouble(options.get("lng"));
        }

        if (options.containsKey("zoom")) {
            zoom = TiConvert.toDouble(options.get("zoom"));
        }
    }

    // Methods

}
