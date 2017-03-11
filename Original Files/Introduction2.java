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
public class Introduction2
      {
		Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
		double minX = screenSize.getMinX();
		double minY = screenSize.getMinY();
		double width = screenSize.getWidth ();
		double height = screenSize.getHeight ();
		Canvas content;
		public Introduction2 ()
		      {
				content = new Canvas (width * 0.72, height * 0.72);
				GraphicsContext gc = content.getGraphicsContext2D ();
				Font subtitleFont = Font.font ("Times New Roman", FontPosture.ITALIC, 24);
				gc.setFont (subtitleFont);
				gc.setStroke (Color.RED);
				gc.strokeText ("\u0938\u0902\u0938\u094D\u0915\u0943\u0924\u091C\u094D\u092F\u094B\u0924\u094D\u0938\u094D\u0928\u093E", width * 0.324, height * 0.036);
				gc.setStroke (Color.BLUE);
				gc.strokeText ("Introduction", width * 0.324, height * 0.099);
				Font textFont = Font.font ("Times New Roman", 20);
                gc.setFont (textFont);
				gc.setFill (Color.ORANGE);
				gc.fillText ("Introduction p 2", width * 0.6, height * 0.099);
				gc.setFill (Color.RED);
				Font redTextFont = Font.font ("Times New Roman", FontPosture.ITALIC, 20);
				gc.setFill (Color.BLACK);
				gc.setFont (textFont);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.243);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.297);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.351);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.405);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.459);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.513);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.567);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.621);
				gc.fillText ("Text will be provided later", width * 0.09, height * 0.675);
		   }
      }