package application;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainController {
	
	    @FXML
	    private Label lblCountry;

	    @FXML
	    private Label lblRegion;

	    @FXML
	    private Label lblCity;

	    @FXML
	    private Label lblTimezone;

	    @FXML
	    private Label lblIsp;

	    @FXML
	    private Label lblIp;

	    @FXML
	    private JFXButton btnGetDetail;

	    @FXML
	    private JFXTextField txtIp;

	    @FXML
	    void getData(ActionEvent event)  
	    {
	    	OkHttpClient client = new OkHttpClient();
	    	Request req = new Request.Builder()
    			.url("http://ip-api.com/json/"+txtIp.getText())
    			.get()
    			.build();
    	
		try 
		{
			Response response = client.newCall(req).execute();
			
			String data = response.body().string();
			JSONParser jsonParser=new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(data);
			
			lblCity.setText("City : "+jsonObject.get("city"));
			lblRegion.setText("Region : "+jsonObject.get("regionName"));
			lblCountry.setText("Country : "+jsonObject.get("country")+"("+jsonObject.get("countryCode")+")");
			lblIsp.setText("ISP : "+jsonObject.get("as"));
			lblTimezone.setText("Timezone : "+jsonObject.get("timezone"));
			lblIp.setText("IP : "+txtIp.getText());
		}
		
		catch (IOException e) {e.printStackTrace(); }
		catch (ParseException e) {e.printStackTrace();}
    }

}
