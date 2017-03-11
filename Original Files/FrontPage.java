import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
public class FrontPage
      {
		Rectangle2D screenSize = Screen.getPrimary ().getVisualBounds ();
		double minX = screenSize.getMinX ();
		double minY = screenSize.getMinY ();
		double width = screenSize.getWidth ();
		double height = screenSize.getHeight ();
		Canvas content;
		public FrontPage ()
		      {

				content = new Canvas (width * 0.72, height * 0.72);
				GraphicsContext gc = content.getGraphicsContext2D ();
				Font titleFont = Font.font ("Times New Roman", FontPosture.ITALIC, 72);
				Font subtitleFont = Font.font ("Times New Roman", FontPosture.ITALIC, 24);
				gc.setFont (titleFont);
				gc.setStroke (Color.RED);
				gc.strokeText ("\u0938\u0930\u0932\u0938\u0902\u0938\u094D\u0915\u0943\u0924\u0932\u0947\u0916\u0915\u0903", width * 0.162, height * 0.135);

				gc.setStroke (Color.BLUE);
				gc.strokeText ("SaralaSam\u0307skr\u0323taLekhakah\u0323", width * 0.09, height * 0.288);

				gc.setFont (subtitleFont);
				gc.strokeText ("A Simple Editor", width * 0.306, height * 0.396);
				gc.strokeText ("for", width * 0.351, height * 0.441);
				gc.strokeText ("The Creation of Sam\u0307skr\u0323ta Text", width * 0.252, height * 0.486);

				gc.setFill (Color.ORANGE);
				gc.setFont (titleFont);
				gc.fillText ("Sudhir Kaicker", width * 0.180, height * 0.612);
			  }
      }