package com.chipuha.cachevalleybus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends Activity {
	
	final Context context = this;
	
	private final LatLng LOGANCENTER = new LatLng(41.740887, -111.830906);
	private LatLng CAMERACENTER = new LatLng(41.740887, -111.830906);
	private LatLng LOCATION = new LatLng(41.740887, -111.830906);
	
	private Spinner spinnerR;
	private Spinner spinnerS;
	
	private ImageButton button;
	
	private String routeS;
	private String scheduleS;
	private String time;
	private String busS;
	
	private static int color1;
	private static int color2;
	private static int color3;
	private static int color4;
	private static int color5;
	private static int color6;
	private static int color7;
	private static int color8;
	private static int color9;
	private static int color10;
	private static int color11;
	private static int color12;
	private static int color13;
	private static int color14;
	private static int color14a;
	private static int color15;
	private static int color15a;
	private static int color16;
	private static int color17;
	private static int color18;
	private static int color1B;
	private static int color2B;
	private static int color3B;
	private static int color4B;
	private static int color5B;
	private static int color6B;
	private static int color7B;
	private static int color8B;
	private static int color9B;
	private static int color10B;
	private static int color11B;
	private static int color12B;
	private static int color13B;
	private static int color14B;
	private static int color14aB;
	private static int color15B;
	private static int color15aB;
	private static int color16B;
	private static int color17B;
	private static int color18B;
    
	private static PolylineOptions polyRoute1a;
	private static PolylineOptions polyRoute1b;
	private static PolylineOptions polyRoute1c;
	private static PolylineOptions polyRoute1d;
	private static PolylineOptions polyRoute2a;
	private static PolylineOptions polyRoute2b;
	private static PolylineOptions polyRoute2c;
	private static PolylineOptions polyRoute2d;
	private static PolylineOptions polyRoute3a;
	private static PolylineOptions polyRoute3b;
	private static PolylineOptions polyRoute3c;
	private static PolylineOptions polyRoute3d;
	private static PolylineOptions polyRoute4a;
	private static PolylineOptions polyRoute4b;
	private static PolylineOptions polyRoute4c;
	private static PolylineOptions polyRoute4d;
	private static PolylineOptions polyRoute5a;
	private static PolylineOptions polyRoute5b;
	private static PolylineOptions polyRoute5c;
	private static PolylineOptions polyRoute5d;
	private static PolylineOptions polyRoute6a;
	private static PolylineOptions polyRoute6b;
	private static PolylineOptions polyRoute6c;
	private static PolylineOptions polyRoute6d;
	private static PolylineOptions polyRoute7a;
	private static PolylineOptions polyRoute8a;
	private static PolylineOptions polyRoute8b;
	private static PolylineOptions polyRoute8c;
	private static PolylineOptions polyRoute8d;
	private static PolylineOptions polyRoute9a;
	private static PolylineOptions polyRoute9b;
	private static PolylineOptions polyRoute9c;
	private static PolylineOptions polyRoute9d;
	private static PolylineOptions polyRoute10a;
	private static PolylineOptions polyRoute10b;
	private static PolylineOptions polyRoute10c;
	private static PolylineOptions polyRoute10d;
	private static PolylineOptions polyRoute11;
	private static PolylineOptions polyRoute12;
	private static PolylineOptions polyRoute13;
	private static PolylineOptions polyRoute14;
	private static PolylineOptions polyRoute14a;
	private static PolylineOptions polyRoute15;
	private static PolylineOptions polyRoute15a;
	private static PolylineOptions polyRoute16;
	private static PolylineOptions polyRoute17;
	private static PolylineOptions polyRoute18;
	
	private GoogleMap map;
		
	// Progress Dialog
	private ProgressDialog pDialog;
	
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();
	JSONObject locations = new JSONObject();
	ArrayList<HashMap<String, String>> stoplocations;
	// url to get all locations list
	private static String url_get_stops = "http://www.chipuha.com/CVBus/get_stop_locations.php";
    private static String url_get_shapes = "http://www.chipuha.com/CVBus/CVData/shapes.txt";



	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_TIME = "time";
	private static final String TAG_ROUTE1 = "route1";
	private static final String TAG_LATLNG = "latlng";
	private static final String TAG_STOPTIME = "stoptime";
	private static final String TAG_STOPID = "stopid";
	private static final String TAG_TABLE = "table";
		
	// locations JSONArray
	JSONArray stops = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Spinner options
		//addListenerOnButton();
		//addListenerOnSpinnerItemSelection();
		
		
		String hey = "hey you made it";
		Log.d("onCreate", hey);
		
		//Locate the Banner Ad in activity_main.xml
        AdView adView = (AdView) this.findViewById(R.id.adView);
		
		//Request for ads
		AdRequest adRequest = new AdRequest.Builder()
			//add a test device to show test ads
			//.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
			//.addTestDevice("57CFAE48E0835472108368B813F9BDC0") //random text
				.build();
		
		//Load ads into Banner Ads
		adView.loadAd(adRequest);
		
		//Show map
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOGANCENTER, 15);
		Log.d("cameraupdate", String.valueOf(update));
		map.animateCamera(update);
		Marker TC = map.addMarker(new MarkerOptions().position(LOGANCENTER).title("Transit Center"));
		TC.showInfoWindow();
		
		color1 = Color.argb(255, 54, 255, 185);
		color2 = Color.argb(225, 200, 16, 210);
		color3 = Color.argb(225, 255, 95, 43);
		color4 = Color.argb(225, 254, 65, 243);
		color5 = Color.argb(225, 254, 16, 32);
		color6 = Color.argb(225, 255, 188, 3);
		color7 = Color.argb(225, 162, 52, 249);
		color8 = Color.argb(225, 49, 253, 43);
		color9 = Color.argb(225, 223, 104, 83);
		color10 = Color.argb(225, 148, 68, 0);
		color11= Color.argb(225,  49,  253, 43);
		color12 = Color.argb(225, 0, 130, 240);
		color13 = Color.argb(225, 0, 130, 240);
		color14 = Color.argb(225, 1, 144, 0);
		color14a = Color.argb(225, 1, 144, 0);
		color15 = Color.argb(225, 255, 66, 4);
		color15a = Color.argb(225, 255, 66, 4);
		color16 = Color.argb(225, 3, 5, 233);
		color17 = Color.argb(225, 3, 5, 233);
		color18 = Color.argb(225, 3, 5, 233);
		
		color1B = Color.argb(255, 255 ,54, 124);
		color2B = Color.argb(225, 26, 210, 16);
		color3B = Color.argb(225, 43, 203, 255);
		color4B = Color.argb(225, 65, 254, 76);
		color5B = Color.argb(225, 16, 254, 238);
		color6B = Color.argb(225, 3, 70, 255);
		color7B = Color.argb(225, 139, 249, 52);
		color8B = Color.argb(225, 247, 43, 253);
		color9B = Color.argb(225, 83, 202, 223);
		color10B = Color.argb(225, 0, 80, 148);
		color11B = Color.argb(225,  247, 43, 253); //check colors from here on...
		color12B = Color.argb(225, 0, 130, 240);
		color13B = Color.argb(225, 0, 130, 240);
		color14B = Color.argb(225, 1, 144, 0);
		color14aB = Color.argb(225, 1, 144, 0);
		color15B = Color.argb(225, 255, 66, 4);
		color15aB = Color.argb(225, 255, 66, 4);
		color16B = Color.argb(225, 3, 5, 233);
		color17B = Color.argb(225, 3, 5, 233);
		color18B = Color.argb(225, 3, 5, 233);


		// Instantiates a new Polyline for route 1
		polyRoute1a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740642, -111.816767))
			.add(new LatLng(41.74368252332771, -111.8166299879055))	
			.add(new LatLng(41.74374268326381, -111.816608533351))	
			.add(new LatLng(41.74383367484204, -111.8165661550111))	
			.add(new LatLng(41.74393235969379, -111.8165076262972))	
			.add(new LatLng(41.74403868515723, -111.8164394441142))	
			.add(new LatLng(41.74414842624995, -111.8163589032297))	
			.add(new LatLng(41.74424844553149, -111.8162701504106))	
			.add(new LatLng(41.74433507142278, -111.8161624797493))	
			.add(new LatLng(41.74436247644956, -111.8161167494081))	
			.add(new LatLng(41.74438940951289, -111.8160569455581))
			.add(new LatLng(41.74441734324604, -111.8159917376468))	
			.add(new LatLng(41.74443916711693, -111.815931323808))	
			.add(new LatLng(41.74445419094374, -111.8158660494633))	
			.add(new LatLng(41.74446877517654, -111.8157919695441))	
			.add(new LatLng(41.74447425342482, -111.8157383317778))	
			.add(new LatLng(41.74447704748258, -111.8156966437012))	
			.add(new LatLng(41.7444779718351, -111.8156528104448))	
			.add(new LatLng(41.74447968412864, -111.8155860308656))	
			.add(new LatLng(41.74447878856157, -111.8155074677189))	
			.add(new LatLng(41.7444787315337, -111.815416168252))
			.add(new LatLng(41.744367, -111.81097))
			.color(color1);
					
					
		polyRoute1b = new PolylineOptions()	
			.add(new LatLng(41.744367, -111.81097))
			.add(new LatLng(41.744326, -111.804287))
			.add(new LatLng(41.757138, -111.804272))
			.add(new LatLng(41.757206, -111.80334))
			.add(new LatLng(41.758399, -111.800714))
			.add(new LatLng(41.758349, -111.79794))
			.color(color1);
					
		polyRoute1c = new PolylineOptions()	
			.add(new LatLng(41.758349, -111.79794))
			.add(new LatLng(41.758434, -111.794617))
			.add(new LatLng(41.749444, -111.794505))
			.add(new LatLng(41.749417, -111.796805))
			.add(new LatLng(41.744156, -111.796820))
			.add(new LatLng(41.744367, -111.81097))
			.color(color1);
				
		//route 2 display
		polyRoute2a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740882, -111.829537))
			.add(new LatLng(41.744681, -111.829444))
			.add(new LatLng(41.75748, -111.82900))
			.add(new LatLng(41.757298, -111.822193))
			.color(color2);
				
		polyRoute2b = new PolylineOptions()
			.add(new LatLng(41.75077, -111.81406))
			.add(new LatLng(41.764802, -111.813680))
			.add(new LatLng(41.76494, -111.820889))
			.add(new LatLng(41.763989, -111.820914))
			.add(new LatLng(41.76298396584725, -111.8213335230786))
			.add(new LatLng(41.7629465663789, -111.8213448446315))
			.add(new LatLng(41.76290921082805, -111.8213530234894))
			.add(new LatLng(41.76285304697026, -111.8213793776418))
			.add(new LatLng(41.76281107170397, -111.8213873861117))
			.add(new LatLng(41.76276929780357, -111.8213797326021))
			.add(new LatLng(41.76272283628634, -111.8213782032728))
			.add(new LatLng(41.76268344072226, -111.8213706327299))
			.add(new LatLng(41.76264409803437, -111.8213598845043))
			.add(new LatLng(41.76260718966594, -111.8213430030647))
			.add(new LatLng(41.76256571897667, -111.8213228653759))
			.add(new LatLng(41.76251747577668, -111.8212932692411))
			.add(new LatLng(41.76248769275618, -111.8212705051131))
			.add(new LatLng(41.76244863552413, -111.8212505562127))
			.add(new LatLng(41.76241440182993, -111.8212182122566))
			.add(new LatLng(41.76238255556361, -111.8211828504136))
			.add(new LatLng(41.76235309496737, -111.8211444650974))
			.add(new LatLng(41.76232601357127, -111.8211031113874))
			.add(new LatLng(41.7622988595265, -111.8210680741577))
			.add(new LatLng(41.76226960854551, -111.821020582024))
			.add(new LatLng(41.76224728811639, -111.8209733440991))
			.add(new LatLng(41.76222733649195, -111.8209231062049))
			.add(new LatLng(41.76220740401692, -111.8208729106827))
			.add(new LatLng(41.76219893169704, -111.8208261795475))
			.add(new LatLng(41.76219051379913, -111.820776390396))
			.add(new LatLng(41.76217985503322, -111.8207234858475))
			.add(new LatLng(41.76217394028653, -111.8206614862639))
			.add(new LatLng(41.757298, -111.822193))
			.add(new LatLng(41.762164, -111.818942))
			.add(new LatLng(41.757419, -111.819052))
			.add(new LatLng(41.757298, -111.822193))
			.color(color2);
				
		polyRoute2c = new PolylineOptions()
			.add(new LatLng(41.757298, -111.822193))
			.add(new LatLng(41.757419, -111.819052)) //split
			.add(new LatLng(41.749873, -111.819249))
			.add(new LatLng(41.749964, -111.816987))
			.add(new LatLng(41.750137, -111.815988))
			.add(new LatLng(41.750158, -111.813898))
			.add(new LatLng(41.75077, -111.81406)) //stop
			.color(color2);
				
		polyRoute2d = new PolylineOptions()
			.add(new LatLng(41.757298, -111.822193))
			.add(new LatLng(41.75748, -111.82900))
			.add(new LatLng(41.744681, -111.829444))
			.add(new LatLng(41.744693, -111.831886))
			.add(new LatLng(41.740907, -111.832034))
			.add(new LatLng(41.740836, -111.830909)) //TStation
			.color(color2);

		//route 3 display
		polyRoute3a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740882, -111.829537))
			.add(new LatLng(41.737065, -111.829612))
			.add(new LatLng(41.736946, -111.824505))
			.add(new LatLng(41.735044, -111.82458))
			.add(new LatLng(41.735065, -111.827088))
			.add(new LatLng(41.734383, -111.826858))
			.add(new LatLng(41.733820, -111.826873))
			.add(new LatLng(41.733173, -111.827282))
			.add(new LatLng(41.733249, -111.832274))
			.add(new LatLng(41.731312, -111.832319))
			.add(new LatLng(41.731166, -111.827496))
			.color(color3);
					
		polyRoute3b = new PolylineOptions()
			.add(new LatLng(41.731166, -111.827496))
			.add(new LatLng(41.731227, -111.822186))
			.add(new LatLng(41.73504, -111.822065))
			.add(new LatLng(41.735971, -111.820812))
			.add(new LatLng(41.736656, -111.819273))
			.add(new LatLng(41.737297, -111.815881))
			.add(new LatLng(41.738062, -111.812977))
			.add(new LatLng(41.736203, -111.812877))
			.color(color3);
				
		polyRoute3c = new PolylineOptions()
			.add(new LatLng(41.736203, -111.812877))
			.add(new LatLng(41.734803, -111.812699))
			.add(new LatLng(41.734143, -111.811882))
			.add(new LatLng(41.732940, -111.812))
			.add(new LatLng(41.732442, -111.812601))
			.add(new LatLng(41.732243, -111.813721))
			.add(new LatLng(41.731797, -111.814522))
			.add(new LatLng(41.731072, -111.815103)) //split
			.add(new LatLng(41.731035, -111.814289)) 
			.add(new LatLng(41.729916, -111.811342)) 
			.add(new LatLng(41.729787, -111.808478)) 
			.add(new LatLng(41.730155, -111.807825)) 
			.add(new LatLng(41.730613, -111.806783)) //split
			.add(new LatLng(41.728834, -111.805144)) 
			.add(new LatLng(41.728545, -111.803358)) 
			.add(new LatLng(41.728571, -111.799563)) 
			.add(new LatLng(41.731994, -111.799590))
			.add(new LatLng(41.732, -111.80033))
			.color(color3);
				
		polyRoute3d = new PolylineOptions()
			.add(new LatLng(41.732, -111.80033))
			.add(new LatLng(41.731908, -111.805121))
			.add(new LatLng(41.731764, -111.806072))
			.add(new LatLng(41.730907, -111.806239))
			.add(new LatLng(41.730613, -111.806783))
			.add(new LatLng(41.730155, -111.807825)) 
			.add(new LatLng(41.729787, -111.808478))
			.add(new LatLng(41.729916, -111.811342))
			.add(new LatLng(41.731035, -111.814289))
			.add(new LatLng(41.731072, -111.815103))
			.add(new LatLng(41.731, -111.832319312)) //center and 1st
			.add(new LatLng(41.740914, -111.832031))
			.add(new LatLng(41.740836, -111.830909))
			.color(color3);
					
		//route 4 display
		polyRoute4a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740687, -111.819285))
			.add(new LatLng(41.749862, -111.819003))
			.add(new LatLng(41.749923, -111.817100))
			.add(new LatLng(41.750120, -111.815999))
			.add(new LatLng(41.750143, -111.813896))
			.add(new LatLng(41.744529, -111.814113))
			.add(new LatLng(41.744186, -111.800296))
			.add(new LatLng(41.743739, -111.800288))
			.add(new LatLng(41.743661, -111.801374))
			.add(new LatLng(41.743524, -111.802208))
			.add(new LatLng(41.743315, -111.802906))
			.add(new LatLng(41.742995, -111.803630))
			.add(new LatLng(41.74266, -111.804229))
			.add(new LatLng(41.744326, -111.804287))
			.color(color4);
					
		//route 5 display
		polyRoute5a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740960, -111.834671)) //main
			.add(new LatLng(41.757541, -111.834331)) //1400
			.add(new LatLng(41.757461, -111.829047))
			.add(new LatLng(41.772266, -111.82842))
			.add(new LatLng(41.772152, -111.823305))
			.add(new LatLng(41.779920, -111.822792))
			.add(new LatLng(41.779988, -111.828354))
			.add(new LatLng(41.777724, -111.828371))
			.add(new LatLng(41.777713, -111.833947))
			.add(new LatLng(41.757541, -111.834331))
			.color(color5);
				
		//route 6 display
		polyRoute6a = new PolylineOptions()
			.add(new LatLng(41.739058, -111.834733))
			.add(new LatLng(41.740960, -111.834671)) //main
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740882, -111.829512))
			.add(new LatLng(41.738976, -111.829565))
			.add(new LatLng(41.739058, -111.834733))
			.add(new LatLng(41.739136, -111.839893))
			.add(new LatLng(41.735304, -111.84002))
			.add(new LatLng(41.735380, -111.845134))
			.add(new LatLng(41.726084, -111.845508))
			.add(new LatLng(41.726048, -111.842909))
			.add(new LatLng(41.724098, -111.842983))
			.add(new LatLng(41.724268, -111.852465))
			.add(new LatLng(41.723604, -111.853116))
			.add(new LatLng(41.723445, -111.853155))
			.add(new LatLng(41.720588, -111.853184))
			.add(new LatLng(41.720722, -111.859854))
			.add(new LatLng(41.723615, -111.859865))
			.add(new LatLng(41.723445, -111.853155))
			.color(color6);
				
		//route 7 display
		polyRoute7a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740908, -111.832027))
			.add(new LatLng(41.735169, -111.832243))
			.add(new LatLng(41.735248, -111.837482))
			.add(new LatLng(41.725973, -111.837734))
			.add(new LatLng(41.725900, -111.835131))
			.add(new LatLng(41.718043, -111.835449))
			.add(new LatLng(41.716600, -111.835753))
			.add(new LatLng(41.715318, -111.836446))
			.add(new LatLng(41.715465, -111.837094))
			.add(new LatLng(41.715384, -111.838193))
			.add(new LatLng(41.716034, -111.839334))
			.add(new LatLng(41.716069, -111.840844))
			.add(new LatLng(41.715565, -111.842525))
			.add(new LatLng(41.720525, -111.842445))
			.add(new LatLng(41.720508, -111.839900))
			.add(new LatLng(41.720775, -111.837906))
			.add(new LatLng(41.725973, -111.837734))
			.color(color7);
				
		//route 8 display
		polyRoute8a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740960, -111.834671)) //main
			.add(new LatLng(41.739048, -111.834766))
			.add(new LatLng(41.739273, -111.849547))
			.add(new LatLng(41.746185, -111.849201))
			.add(new LatLng(41.746860, -111.849420))
			.add(new LatLng(41.755070, -111.849099))
			.add(new LatLng(41.755062, -111.849603))
			.add(new LatLng(41.755267, -111.849805))
			.add(new LatLng(41.756077, -111.849775))
			.add(new LatLng(41.756357, -111.849386))
			.add(new LatLng(41.756378, -111.849055))
			.add(new LatLng(41.757815, -111.849010))
			.add(new LatLng(41.757945, -111.858782))
			.add(new LatLng(41.742136, -111.859203))
			.add(new LatLng(41.739925, -111.859478))
			.add(new LatLng(41.728078, -111.859855))
			.add(new LatLng(41.727963, -111.849892))
			.add(new LatLng(41.735484, -111.849659))
			.add(new LatLng(41.735383, -111.845158))
			.add(new LatLng(41.739229, -111.845015))
			.color(color8);
					
		//route 9 display
		polyRoute9a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.741011, -111.839874))
			.add(new LatLng(41.744416, -111.839750))
			.add(new LatLng(41.744963, -111.839570))
			.add(new LatLng(41.765053, -111.839083))
			.add(new LatLng(41.765268, -111.848790))
			.add(new LatLng(41.757819, -111.849008))
			.add(new LatLng(41.757694, -111.839215))
			.color(color9);
					
		//route 10 display
		polyRoute10a = new PolylineOptions()
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740882, -111.829537))
			.add(new LatLng(41.737065, -111.829612))
			.add(new LatLng(41.736946, -111.824505))
			.add(new LatLng(41.735044, -111.82458))
			.add(new LatLng(41.735065, -111.827088))
			.add(new LatLng(41.734383, -111.826858))
			.add(new LatLng(41.733820, -111.826873))
			.add(new LatLng(41.733173, -111.827282))
			.add(new LatLng(41.733249, -111.832274))
			.add(new LatLng(41.731312, -111.832319))
			.add(new LatLng(41.731227, -111.822186))
			.add(new LatLng(41.73504, -111.822065))
			.add(new LatLng(41.735971, -111.820812))
			.add(new LatLng(41.736656, -111.819273))
			.add(new LatLng(41.737297, -111.815881))
			.add(new LatLng(41.738062, -111.812977))
			.add(new LatLng(41.734803, -111.812699))
			.add(new LatLng(41.734143, -111.811882))
			.add(new LatLng(41.732940, -111.812))
			.add(new LatLng(41.732442, -111.812601))
			.add(new LatLng(41.732243, -111.813721))
			.add(new LatLng(41.731797, -111.814522))
			.add(new LatLng(41.731072, -111.815103))
			.add(new LatLng(41.731262, -111.827301))
			.add(new LatLng(41.731312, -111.832319))
			.add(new LatLng(41.725884, -111.832530))
			.add(new LatLng(41.725828, -111.827479))
			.add(new LatLng(41.725750, -111.825912))
			.add(new LatLng(41.724098, -111.825465))
			.add(new LatLng(41.723626, -111.825596))
			.add(new LatLng(41.723576, -111.822341))
			.add(new LatLng(41.721367, -111.822387))
			.add(new LatLng(41.721410, -111.825787))
			.add(new LatLng(41.723626, -111.825596))
			.add(new LatLng(41.724098, -111.825465))
			.add(new LatLng(41.725750, -111.825912))
			.add(new LatLng(41.725828, -111.827479))
			.add(new LatLng(41.731262, -111.827301))
			.add(new LatLng(41.731312, -111.832319))
			.add(new LatLng(41.733249, -111.832274))
			.add(new LatLng(41.740907, -111.832034))
			.add(new LatLng(41.740836, -111.830909))
			.color(color10);
					
		//route 10 display
		polyRoute11 = new PolylineOptions()
			.add(new LatLng(41.739058, -111.834733))
			.add(new LatLng(41.740960, -111.834671)) //main
			.add(new LatLng(41.740836, -111.830909))
			.add(new LatLng(41.740882, -111.829512))
			.add(new LatLng(41.738976, -111.829565))
			.add(new LatLng(41.739058, -111.834733))
			.add(new LatLng(41.717746, -111.835372))
			.add(new LatLng(41.715864, -111.836002))
			.add(new LatLng(41.714375, -111.837057))
			.add(new LatLng(41.713321, -111.838117))
			.add(new LatLng(41.712338, -111.839433))
			.add(new LatLng(41.711816, -111.838922))
			.add(new LatLng(41.710870, -111.838854))
			.add(new LatLng(41.710529, -111.838585))
			.add(new LatLng(41.710208, -111.839082))
			.add(new LatLng(41.709424, -111.839700))
			.add(new LatLng(41.707985, -111.841586))
			.add(new LatLng(41.707446, -111.841174))
			.add(new LatLng(41.702849, -111.841219))
			.add(new LatLng(41.702850, -111.842284))
			.add(new LatLng(41.702611, -111.845053))
			.add(new LatLng(41.703179, -111.848460))
			.add(new LatLng(41.703178, -111.851124))
			.add(new LatLng(41.704161, -111.852771))
			.add(new LatLng(41.705232, -111.853041))
			.add(new LatLng(41.705053, -111.853926))
			.add(new LatLng(41.704988, -111.856052))
			.add(new LatLng(41.704645, -111.856850))
			.add(new LatLng(41.703946, -111.857817))
			.add(new LatLng(41.703718, -111.858293))
			.add(new LatLng(41.703527, -111.858241))
			.add(new LatLng(41.701002, -111.854891))
			.add(new LatLng(41.697112, -111.860240))
			.add(new LatLng(41.700097, -111.860338))
			.add(new LatLng(41.700104, -111.864761))
			.add(new LatLng(41.700069, -111.866670))
			.add(new LatLng(41.703385, -111.866682))
			.add(new LatLng(41.703394, -111.865924))
			.add(new LatLng(41.701254, -111.865878))
			.add(new LatLng(41.701052, -111.865565))
			.add(new LatLng(41.701064, -111.864783))			
			.add(new LatLng(41.700104, -111.864761))
			.add(new LatLng(41.700097, -111.860338))
			.add(new LatLng(41.697112, -111.860240))
			.add(new LatLng(41.694377, -111.863845))
			.add(new LatLng(41.691152, -111.867379))
			.add(new LatLng(41.690566, -111.866218))
			.add(new LatLng(41.690189, -111.866033))
			.add(new LatLng(41.689858, -111.866089))
			.add(new LatLng(41.689537, -111.866310))
			.add(new LatLng(41.689376, -111.865523))
			.add(new LatLng(41.689440, -111.864614))
			.add(new LatLng(41.690245, -111.862797))
			.add(new LatLng(41.690276, -111.861951))
			.add(new LatLng(41.690138, -111.861474))
			.add(new LatLng(41.688641, -111.859267))
			.add(new LatLng(41.688494, -111.858688))
			.add(new LatLng(41.688509, -111.857234)) //here
			.add(new LatLng(41.680590, -111.857165))
			.add(new LatLng(41.679434, -111.857840))
			.add(new LatLng(41.678523, -111.857999))
			.add(new LatLng(41.677215, -111.857983))
			.add(new LatLng(41.676325, -111.857647))
			.add(new LatLng(41.675008, -111.857638))
			.add(new LatLng(41.674847, -111.844923))
			.add(new LatLng(41.675758, -111.844961))
			.add(new LatLng(41.677218, -111.845395))
			.add(new LatLng(41.678450, -111.847195))
			.add(new LatLng(41.681117, -111.847235))
			.add(new LatLng(41.681484, -111.847428))
			.add(new LatLng(41.685502, -111.847379))
			.add(new LatLng(41.685548, -111.852379))
			.add(new LatLng(41.688146, -111.852408))
			.add(new LatLng(41.688223, -111.853194))
			.add(new LatLng(41.688782, -111.854629))
			.add(new LatLng(41.688799, -111.855938))
			.add(new LatLng(41.688508, -111.856853))
			.add(new LatLng(41.688509, -111.857234))
			.color(color11);
							
		// Map the lines
		Polyline polyline1a = map.addPolyline(polyRoute1a);
		Polyline polyline1b = map.addPolyline(polyRoute1b);
		Polyline polyline1c = map.addPolyline(polyRoute1c);
		Polyline polyline1d = map.addPolyline(polyRoute1d);
		Polyline polyline2a = map.addPolyline(polyRoute2a);
		Polyline polyline2b = map.addPolyline(polyRoute2b);
		Polyline polyline2c = map.addPolyline(polyRoute2c);
		Polyline polyline2d = map.addPolyline(polyRoute2d);
		Polyline polyline3a = map.addPolyline(polyRoute3a);
		Polyline polyline3b = map.addPolyline(polyRoute3b);
		Polyline polyline3c = map.addPolyline(polyRoute3c);
		Polyline polyline3d = map.addPolyline(polyRoute3d);
		//Polyline polyline4a = map.addPolyline(polyRoute4a);
		//Polyline polyline4b = map.addPolyline(polyRoute4b);
		//Polyline polyline4c = map.addPolyline(polyRoute4c);
		//Polyline polyline4d = map.addPolyline(polyRoute4d);
		//Polyline polyline5a = map.addPolyline(polyRoute5a);
		//Polyline polyline5b = map.addPolyline(polyRoute5b);
		//Polyline polyline5c = map.addPolyline(polyRoute5c);
		//Polyline polyline5d = map.addPolyline(polyRoute5d);
		//Polyline polyline6a = map.addPolyline(polyRoute6a);
		//Polyline polyline6b = map.addPolyline(polyRoute6b);
		//Polyline polyline6c = map.addPolyline(polyRoute6c);
		//Polyline polyline6d = map.addPolyline(polyRoute6d);
		//Polyline polyline7a = map.addPolyline(polyRoute7a);
		//Polyline polyline2 = map.addPolyline(polyRoute
	
		Polyline polyline4 = map.addPolyline(polyRoute4a);
		Polyline polyline5 = map.addPolyline(polyRoute5a);
		Polyline polyline6 = map.addPolyline(polyRoute6a);
		Polyline polyline7 = map.addPolyline(polyRoute7a);
		Polyline polyline8 = map.addPolyline(polyRoute8a);
		Polyline polyline9 = map.addPolyline(polyRoute9a);
		Polyline polyline10 = map.addPolyline(polyRoute10a);
		Polyline polyline11 = map.addPolyline(polyRoute11);
		
		spinnerR = (Spinner) findViewById(R.id.spinner1);
		spinnerS = (Spinner) findViewById(R.id.spinner2);
		//spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		button = (ImageButton) findViewById(R.id.button1);	
		
		button.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				runrun();
			}
		});
	}
	/*
	//Run ever minute
	private void runEveryMinute() {
		final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleWithFixedDelay(new Runnable() {
			@Override
				public void run() { 
				//fetching file
				runrun();
				new LoadAllStops().execute();
			}
		}, 0, 60, TimeUnit.SECONDS);
		
	}
	*/

	protected void runrun() {
		
		scheduleS = String.valueOf(spinnerS.getSelectedItem());
		routeS = String.valueOf(spinnerR.getSelectedItem());

		Log.d("routeS", routeS);
		Log.d("scheduleS", scheduleS);
		
		
		SimpleDateFormat hms = new SimpleDateFormat("HH:mm");
		time = hms.format(new Date());
		time = time + ":00";
		
		
		//time = "7:31:00";
		Log.d("Time Now Now", time);
			
				
		if (routeS.equals("Route 1 Extended")) {
								
			map.clear();			
			
			// set camera center location
			CAMERACENTER = new LatLng(41.750128, -111.813925);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
						
			//draw the route
			Polyline polyline1a = map.addPolyline(polyRoute1a);
			Polyline polyline1b = map.addPolyline(polyRoute1b);
			Polyline polyline1c = map.addPolyline(polyRoute1c);
			Polyline polyline1d = map.addPolyline(polyRoute1d);
			
			// set busS
			busS = "Bus 1 is heading this way";
			
			// Loading locations in Background Thread
			new LoadAllStops().execute();
				
		} else if (routeS.equals("Route 1")) {
								
			map.clear();			
			
			// set camera center location
			CAMERACENTER = new LatLng(41.750128, -111.813925);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
						
			//draw the route
			Polyline polyline1a = map.addPolyline(polyRoute1a);
			Polyline polyline1b = map.addPolyline(polyRoute1b);
			Polyline polyline1c = map.addPolyline(polyRoute1c);
			Polyline polyline1d = map.addPolyline(polyRoute1d);

			// set busS
			busS = "Bus 1 is heading this way";
			
			// Loading locations in Background Thread
			new LoadAllStops().execute();
			
			
		} else if (routeS.equals("Route 2")) {
											
			map.clear();			
						
			// set camera center location
			CAMERACENTER = new LatLng(41.752833, -111.822898);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1a = map.addPolyline(polyRoute2a);
			Polyline polyline1b = map.addPolyline(polyRoute2b);
			Polyline polyline1c = map.addPolyline(polyRoute2c);
			Polyline polyline1d = map.addPolyline(polyRoute2d);
			
			// set busS
			busS = "Bus 2 is heading this way";
			
			// Loading locations in Background Thread
			new LoadAllStops().execute();
		
		} else if (routeS.equals("Route 3")) {
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.734708, -111.815940);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1a = map.addPolyline(polyRoute3a);
			Polyline polyline1b = map.addPolyline(polyRoute3b);
			Polyline polyline1c = map.addPolyline(polyRoute3c);
			Polyline polyline1d = map.addPolyline(polyRoute3d);
			
			// Loading locations in Background Thread
			new LoadAllStops().execute();
		
		} else if (routeS.equals("Route 4")) {
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.745311, -111.815925);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute4a);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();
		
		} else if (routeS.equals("Route 5")) {
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.760235, -111.828785);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute5a);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();			
		
		} else if (routeS.equals("Route 6")) {
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.730773, -111.844720);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute6a);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();	
		
		} else if (routeS.equals("Route 7")) {
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.728161, -111.836451);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute7a);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();	
		
		} else if (routeS.equals("Route 8")) {
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.742948, -111.845057);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute8a);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();	
		
		} else if (routeS.equals("Route 9")) {
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.752881, -111.839671);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute9a);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();	
		
		} else if (routeS.equals("Route 10")) {	
										
			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.731140, -111.822164);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 13);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute10a);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();	
		
		} else if (routeS.equals("Route 11")) {

			map.clear();			
					
			// set camera center location
			CAMERACENTER = new LatLng(41.707904, -111.848420);
			Log.d("CAMERACENTER", String.valueOf(CAMERACENTER));	
			CameraUpdate updater = CameraUpdateFactory.newLatLngZoom(CAMERACENTER, 12);
			Log.d("Camera updater", String.valueOf(updater));
			map.moveCamera(updater);			
									
			//draw the route
			Polyline polyline1 = map.addPolyline(polyRoute11);
					
			// Loading locations in Background Thread
			new LoadAllStops().execute();	

		} else {
			Log.d("Else routeS", routeS);
		}
		
		
		
		
	}
	
	//public void addListenerOnButton() {
		//spinner = (Spinner) findViewById(R.id.spinner1);
		//spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		//button = (Button) findViewById(R.id.button1);	
	//}
	 

	/**
	 *  Background Async Task to Load all location by making HTTP Request
     * */
	class LoadAllStops extends AsyncTask<String, String, String> {
		
		/**
        * Before starting background thread Show Progress Dialog
 		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading route. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
        * getting All locations from url
        * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("time", time));
			params.add(new BasicNameValuePair("table", scheduleS));
			
			String madeIt = "Hey you made it";
			
			//Get info
			JSONObject json;
			Log.d("doInBackground: ", madeIt);
			json = jParser.makeHttpRequest(url_get_stops, "GET", params);

			
			// Check your log cat for JSON response
			Log.d("All Stops: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);
				Log.d("success", String.valueOf(success));
				
				if (success == 1) {
					// locations found
					// Getting Array of locations
					
					stops = json.getJSONArray(TAG_TIME);					
					Log.d("All Locations: ", stops.toString());					
					
					locations = stops.getJSONObject(0);
					Log.d("locations: ", locations.toString());
					
				} else {
					// no locations found
					Toast.makeText(getApplicationContext(), "Sorry, the bus must not be out", Toast.LENGTH_LONG).show();					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		�* After completing background task Dismiss the progress dialog
		�* **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all locations
            pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					
					int success = 1;
					
					try{
						
						if (routeS.equals("Route 1 Extended")) {
							String Location1 = locations.getString("route1");
							if (!Location1.equals("")) {
								Log.d("Location1 : ", Location1);
								//Log.d("String 2: ", Location2[1]);
								//double lati = Double.parseDouble(Location2[0]);
								//double lngi = Double.parseDouble(Location2[1]);
								//LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 1")) {
							String Location1 = locations.getString("route2");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 2")) {
							String Location1 = locations.getString("route3");
							if (!Location1.equals("")) {	
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 3")) {
							String Location1 = locations.getString("route4");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 10")) {
							String Location1 = locations.getString("route5");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 4")) {
							String Location1 = locations.getString("route6");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 5")) {
							String Location1 = locations.getString("route7");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 6")) {
							String Location1 = locations.getString("route8");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 7")) {
							String Location1 = locations.getString("route9");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 8")) {
							String Location1 = locations.getString("route10");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 9")) {
							String Location1 = locations.getString("route11");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("Route 11")) {
							String Location1 = locations.getString("route12");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						} else if (routeS.equals("CVN (Cache Valley North")) {
							String Location1 = locations.getString("route14");
							if (!Location1.equals("")) {
								String[] Location2 = Location1.split(",", 2);
								Location2[1] = Location2[1].trim();
								Log.d("String 1: ", Location2[0]);
								Log.d("String 2: ", Location2[1]);
								double lati = Double.parseDouble(Location2[0]);
								double lngi = Double.parseDouble(Location2[1]);
								LOCATION = new LatLng(lati, lngi);
							} else {
								success = 2;
							}
						}else {
							Toast.makeText(getApplicationContext(), "Please select a route.", Toast.LENGTH_LONG);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					
					/**
					 * Plotting the data
					 **/
					Log.d("Mapping LOCATION: ", LOCATION.toString());
	
					//Map it! But only if there was a location change
					if (success == 2) {
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
						
						alertDialogBuilder.setTitle("Sorry...");
						
						alertDialogBuilder
							.setMessage("this bus isn't running right now.")
							.setCancelable(false)
							.setPositiveButton("OK", null);
						
						AlertDialog alertDialog = alertDialogBuilder.create();
						
						alertDialog.show();
						
					} else {
						map.addMarker(new MarkerOptions()
							.position(LOCATION)
							.title(routeS));
					}
				}
			});	
			

		}

	}
	
}
