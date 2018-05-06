package gameData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import game_engine.Engine;

/*
 * Authors: Harry
 * This class is the link that connects Vooga to Firebase
 */

public class Firebase {
	private String SERVICE_KEY = "service_key.json";
	private String FIREBASE_URL = "https://call-us-salad.firebaseio.com/";
	private DatabaseReference ref;
	private boolean instance = false;
	private ManipData md;
	
	public Firebase() {
		//need some string to detail which one you're trying to connect to
		md = new ManipData();
		fbConnection();
	}
	
	private void fbConnection() {
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(SERVICE_KEY);
		} catch (FileNotFoundException e1) {
			System.out.println("Error getting Service Key");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			    .setDatabaseUrl(FIREBASE_URL)
			    .build();
			FirebaseApp.initializeApp(options);		
		} catch (IOException e) {
			System.out.println("Error trying to reach firebase");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ref = FirebaseDatabase.getInstance().getReference();
	}
	
	public void initListeners() {
		ref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
				if(instance) {
					try{
						FirebaseObject obj = snapshot.getValue(FirebaseObject.class);
						md.saveData(obj.getEngine(), obj.getGameName(), obj.getSaveFileName(), obj.getMetaMap(), obj.getConfigMap());
						System.out.println(previousChildName+" / "+snapshot.getValue());
					}catch(Exception e) {
						System.out.println("Not a firebase object");
					}
				}
				instance = true;
				//Engine add = snapshot.getValue(Engine.class);
				//call manipsave
				//on instantiation, this will get called on all the values
			}

			@Override
			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
//				System.out.println("changed");
//				System.out.println("cend");
				try{
					FirebaseObject obj = snapshot.getValue(FirebaseObject.class);
					md.saveData(obj.getEngine(), obj.getGameName(), obj.getSaveFileName(), obj.getMetaMap(), obj.getConfigMap());
					System.out.println(previousChildName+" / "+snapshot.getValue());
				}catch(Exception e) {
					System.out.println("Not a firebase object");
				}
			}

			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				System.out.println("Impossible as no delete feature given");
			}

			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				System.out.println("Impossible as no move feature given");
			}

			@Override
			public void onCancelled(DatabaseError error) {
				System.out.println("Impossible as no cancel feature given");
			}
			
		});
	}
	
	public void updateFirebase(Engine engine, String gameFolderName, String saveFileName, Map<String, String> metaMap, Map<String, String> ConfigMap) {
		md.saveData(engine, gameFolderName, saveFileName, metaMap, ConfigMap);
		FirebaseObject obj = new FirebaseObject(engine, gameFolderName, saveFileName, metaMap, ConfigMap);
		ref.child(saveFileName).setValueAsync(obj);
	}

}
