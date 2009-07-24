package sak.distancemeter;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity implements LocationListener {
	
//	private final static String API_KEY =
//        "0AhsSZ3Ip3T_fXKIwb9h7lrMPvSwGoz620RXQew";

	private MapView         mapView;
    private MapController   mapCtrl;
    private LocationManager lm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        setContentView(R.layout.main);
        
        mapView = (MapView) findViewById(R.id.mapview);
//        setContentView(mapView);
        mapCtrl = mapView.getController();
        mapCtrl.setZoom(16);        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		GeoPoint pos = new GeoPoint(
	            (int)(location.getLatitude()*1E6),
	            (int)(location.getLongitude()*1E6));
	    mapCtrl.setCenter(pos);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}