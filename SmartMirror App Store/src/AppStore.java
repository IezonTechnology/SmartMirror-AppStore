import java.io.IOException;
import java.util.ArrayList;

public class AppStore {
	
	public ArrayList<App> apps = new ArrayList<App>();

	public AppStore() {
		apps.add(new App("SettingsApp", "Your settings app.", "file:/C://Temp/SettingsApp.jar", 0));
		apps.add(new App("SMalarm", "Your alarm app.", "file:/C://Temp/SMalarm.jar", 0));
		apps.add(new App("Application C", "Application A is a new inovative app.", "http://www.example.com", 0.99));
		apps.add(new App("Application D", "Application A is a new inovative app.", "http://www.example.com", 0.99));
		apps.add(new App("Application E", "Application A is a new inovative app.", "http://www.example.com", 0.99));
		apps.add(new App("Application F", "Application A is a new inovative app.", "http://www.example.com", 0.99));
		apps.add(new App("Application G", "Application A is a new inovative app.", "http://www.example.com", 0.99));
		apps.add(new App("Application H", "Application A is a new inovative app.", "http://www.example.com", 0.99));
		apps.add(new App("Application I", "Application A is a new inovative app.", "http://www.example.com", 0.99));
		apps.add(new App("Application J", "Application A is a new inovative app.", "http://www.example.com", 0.99));
	}

	public static void main(String[] args) {
		AppStore as = new AppStore();
		
		Server.rh = new RequestHandler() {
			@Override
			public void run(String requestString, ClientSocket socket) {
				System.out.println(socket.getIpAddress() + " sent a request: " + requestString);
				if(requestString.equals("get-all-apps")) {
					socket.sendRequest(String.valueOf(as.apps.size()));
					for(App a : as.apps) {
						// System.out.println(a.name + "/" + String.valueOf(a.cost) + "/" + a.description + "/" + a.url);
						socket.sendRequest(a.name + "#" + String.valueOf(a.cost) + "#" + a.description + "#" + a.url);
					}
				}
				return;
			}
		};

		try {
			DebugConsole.setDebugState(true);
			ServerBuilder server = new ServerBuilder(43594);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
