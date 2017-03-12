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
public class DevanagariEditor3
      {
		Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
		double minX = screenSize.getMinX();
		double minY = screenSize.getMinY();
		double width = screenSize.getWidth ();
		double height = screenSize.getHeight ();
		GridPane content;
		//HBox buttonBox;


		public DevanagariEditor3 ()
		      {
                content = new GridPane();
                TextArea ta = new TextArea ();
        				content.setPadding (new Insets (height * 0.01, width * 0.01, height * 0.01, width * 0.01));
        				content.setAlignment (Pos.CENTER);
			  }
      }
