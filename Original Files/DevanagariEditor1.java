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
public class DevanagariEditor1
      {
		Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
		double minX = screenSize.getMinX();
		double minY = screenSize.getMinY();
		double width = screenSize.getWidth ();
		double height = screenSize.getHeight ();
		GridPane page;
		HBox buttonBox;
		Button i1_c, i1_i2;
		Canvas content;
		//Contents c1 = new Contents (); //problem
		public DevanagariEditor1 ()
		      {
				i1_c = new Button ("Contents");
				i1_i2 = new Button (">");
                page = new GridPane();
				page.setPadding (new Insets (height * 0.01, width * 0.01, height * 0.01, width * 0.01));
				page.setAlignment (Pos.CENTER);

				content = new Canvas (width * 0.72, height * 0.72);
				GraphicsContext gc = content.getGraphicsContext2D ();
				Font subtitleFont = Font.font ("Times New Roman", FontPosture.ITALIC, 24);
				gc.setFont (subtitleFont);
				gc.setStroke (Color.RED);
				gc.strokeText ("\u0938\u0902\u0938\u094D\u0915\u0943\u0924\u091C\u094D\u092F\u094B\u0924\u094D\u0938\u094D\u0928\u093E", width * 0.324, height * 0.036);
				gc.setStroke (Color.BLUE);
				gc.strokeText ("Devan\u0101gar\u012B Editors", width * 0.297, height * 0.099);
				Font textFont = Font.font ("Times New Roman", 20);
                gc.setFont (textFont);
				gc.setFill (Color.ORANGE);
				gc.fillText ("Devan\u0101gar\u012B Editors p 1", width * 0.55, height * 0.099);
				gc.setFill (Color.RED);
				Font redTextFont = Font.font ("Times New Roman", FontPosture.ITALIC, 20);
				gc.setFill (Color.BLACK);
				gc.setFont (textFont);
				gc.fillText ("Two editors are provided for the creation of Sanskrit text. The first, named dnB, allows you to enter", width * 0.09, height * 0.243);
				gc.fillText ("text using the qwerty keyboard, or via an existing text file. Here \"text\" implies lines like:", width * 0.09, height * 0.297);
				gc.setFill (Color.RED);
				gc.fillText ("mama naama sudhiira: |", width * 0.09, height * 0.351);
				gc.fillText ("aha* bhavataa saha sa*skr.tabhaas.aayaa* sa*bhaas.an.a* kartum icchaami |", width * 0.09, height * 0.405);
				gc.setFill (Color.BLACK);
				gc.fillText ("When the Convert button is clicked, the following (copy-able) lines are output ", width * 0.09, height * 0.459);
				gc.fillText ("                                                                                                                               en bloc:", width * 0.09, height * 0.459);
                gc.setFill (Color.BLUE);
				gc.fillText ("\u092E\u092E \u0928\u093E\u092E \u0938\u0941\u0927\u0940\u0930\u0903 \u0964", width * 0.09, height * 0.513);
				gc.fillText ("\u0905\u0939\u0902 \u092D\u0935\u0924\u093E \u0938\u0939 \u0938\u0902\u0938\u094D\u0915\u0943\u0924\u092D\u093E\u0937\u093E\u092F\u093E\u0902 \u0938\u0902\u092D\u093E\u0937\u0923\u0902 \u0915\u0930\u094D\u0924\u0941\u092E\u094D \u0907\u091A\u094D\u091B\u093E\u092E\u093F \u0964", width * 0.09, height * 0.567);
				gc.setFill (Color.MAGENTA);
				gc.fillText ("mama n\u0101ma sudh\u012Brah\u0323 |", width * 0.09, height * 0.621);
				gc.fillText ("aham\u0307 bhavat\u0101 saha sam\u0307skr\u0323tabh\u0101s\u0323\u0101y\u0101m\u0307 sam\u0307bh\u0101s\u0323an\u0323am\u0307 kartum icch\u0101mi |", width * 0.09, height * 0.675);
			  }
      }