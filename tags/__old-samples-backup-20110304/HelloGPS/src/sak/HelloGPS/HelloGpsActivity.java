package sak.HelloGPS;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ZoomControls;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class HelloGpsActivity extends MapActivity implements LocationListener {
	
	MapController m_controller;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LocationManager l = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        l.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        
        MapView m = (MapView)findViewById(R.id.mapview);
        m_controller = m.getController();
        m_controller.setZoom(15);
        m_controller.setCenter(new GeoPoint(35455281,139629711));

        ZoomControls zc = (ZoomControls) m.getZoomControls();
        zc.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        zc.setGravity(Gravity.BOTTOM + Gravity.CENTER_HORIZONTAL);
        m.addView(zc);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

//	@Override
	public void onLocationChanged(Location location) {
		GeoPoint gp = 
			new GeoPoint((int)(location.getLatitude()*1E6),
				         (int)(location.getLongitude()*1E6));
//		m_controller.animateTo(gp);	
		m_controller.setCenter(gp);
		Log.d("debug", "(" + location.getLatitude() + "," + location.getLongitude() + ")");
	}

//	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}