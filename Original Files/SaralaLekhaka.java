import javafx.application.*;
import javafx.collections.*;
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
import java.util.*;
public class SaralaLekhaka extends Application
      {
    	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
		double minX = screenSize.getMinX ();
		double minY = screenSize.getMinY ();
		double width = screenSize.getWidth ();
		double height = screenSize.getHeight ();
	    GridPane page;
		FlowPane fBox1, fBox2;

		FrontPage f;


		Introduction1 i1;
		Introduction2 i2;
		Introduction3 i3;

        DevanagariEditor1 dn1;
        DevanagariEditor2 dn2;
        DevanagariEditor3 dn3;
        DevanagariEditor4 dn4;
        DnO dno; //new
        DnB dnb; //new

        Button
        save, //new
        frontPage_introduction1, introduction1_frontPage,
        introduction1_2, introduction2_1, introduction2_3, introduction3_2, introduction2_frontPage, introduction3_frontPage,
        frontPage_devanagariEditors1, devanagariEditors1_frontPage,
        devanagariEditors1_2, devanagariEditors2_1, devanagariEditors2_3, devanagariEditors3_2, devanagariEditors3_4,
        devanagariEditors4_3, devanagariEditors2_4, devanagariEditors2_frontPage, devanagariEditors3_frontPage, devanagariEditors4_frontPage;

        @Override
        public void start(Stage sanskritStage)
              {
                i1 = new Introduction1 ();
                i2 = new Introduction2 ();
                i3 = new Introduction3 ();
                frontPage_introduction1 = new Button ("Introduction");
                introduction1_frontPage = new Button ("<");
                introduction1_2 = new Button (">");
				introduction2_1 = new Button ("<");
				introduction2_3 = new Button (">");
				introduction3_2 = new Button ("<");
				introduction2_frontPage = new Button ("<<");
				introduction3_frontPage = new Button ("<<");

				frontPage_introduction1.setOnAction (e -> {page.getChildren ().remove (f.content); page.getChildren ().add (i1.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (introduction1_frontPage, introduction1_2);});
				introduction1_frontPage.setOnAction (e -> {page.getChildren ().remove (i1.content); addFrontPage ();});
                introduction1_2.setOnAction (e -> {page.getChildren ().remove (i1.content); page.getChildren ().add (i2.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (introduction2_frontPage, introduction2_1, introduction2_3);});
                introduction2_1.setOnAction (e -> {page.getChildren ().remove (i2.content); page.getChildren ().add (i1.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (introduction1_frontPage, introduction1_2);});
                introduction2_frontPage.setOnAction (e -> {page.getChildren ().remove (i2.content); addFrontPage ();});
                introduction2_3.setOnAction (e -> {page.getChildren ().remove (i2.content); page.getChildren ().add (i3.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (introduction3_frontPage, introduction3_2);});
				introduction3_2.setOnAction (e -> {page.getChildren ().remove (i3.content); page.getChildren ().add (i2.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (introduction2_frontPage, introduction2_1, introduction2_3);});
				introduction3_frontPage.setOnAction (e -> {page.getChildren ().remove (i3.content); addFrontPage ();});

                dn1 = new DevanagariEditor1 ();
                dn2 = new DevanagariEditor2 ();
                dn3 = new DevanagariEditor3 ();
                dn4 = new DevanagariEditor4 ();
                dno = new DnO (); //new
                dnb = new DnB (); //new

                frontPage_devanagariEditors1 = new Button ("Devan\u0101gar\u012B Editors");
                devanagariEditors1_2 = new Button (">");
                devanagariEditors2_1 = new Button ("<");
                devanagariEditors2_frontPage = new Button ("<<");
                devanagariEditors2_3 = new Button ("dnB");
                devanagariEditors2_4 = new Button ("dnO");
                devanagariEditors1_frontPage = new Button ("<");
                devanagariEditors3_frontPage = new Button ("<<");
                devanagariEditors3_2 = new Button ("<");
                devanagariEditors3_4 = new Button (">");
                frontPage_devanagariEditors1.setOnAction (e -> {page.getChildren ().remove (f.content); page.getChildren ().add (dn1.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (devanagariEditors1_frontPage, devanagariEditors1_2);});
				devanagariEditors1_frontPage.setOnAction (e -> {page.getChildren ().remove (dn1.content); addFrontPage ();});
                devanagariEditors1_2.setOnAction (e -> {page.getChildren ().remove (dn1.content); page.getChildren ().add (dn2.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (devanagariEditors2_frontPage, devanagariEditors2_1, devanagariEditors2_3, devanagariEditors2_4);});
                devanagariEditors2_3.setOnAction (e -> {page.getChildren ().remove (dn2.content); page.getChildren ().add (dn3.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (devanagariEditors3_frontPage, devanagariEditors3_2, devanagariEditors3_4);});
                devanagariEditors2_1.setOnAction (e -> {page.getChildren ().remove (dn2.content); page.getChildren ().add (dn1.content); fBox1.getChildren ().clear(); fBox2.getChildren ().clear(); fBox1.getChildren ().addAll (devanagariEditors1_frontPage, devanagariEditors1_2);});
                devanagariEditors2_frontPage.setOnAction (e -> {page.getChildren ().remove (dn2.content); addFrontPage ();});
                devanagariEditors2_3.setOnAction (e -> dnb.dnbEditor ());
                devanagariEditors2_4.setOnAction (e -> dno.dnoEditor ());


		        sanskritStage.setTitle ("\u0938\u0902\u0938\u094D\u0915\u0943\u0924\u091C\u094D\u092F\u094B\u0924\u094D\u0938\u094D\u0928\u093E - Sam\u0307skr\u0323taJyotsn\u0101");
				sanskritStage.setX (minX);
				sanskritStage.setY (minY);
				sanskritStage.setWidth (width);
				sanskritStage.setHeight (height);



                fBox1 = new FlowPane (width * 0.02, height * 0.02);
				fBox1.setPadding (new Insets (height * 0.004, width * 0.004, height * 0.04, width * 0.004));
				fBox1.setAlignment (Pos.CENTER);
				fBox1.getChildren ().addAll (frontPage_introduction1);

				fBox2 = new FlowPane (width * 0.02, height * 0.02);
				fBox2.setPadding (new Insets (height * 0.004, width * 0.004, height * 0.04, width * 0.004));
				fBox2.setAlignment (Pos.CENTER);
                fBox2.getChildren ().addAll (frontPage_devanagariEditors1);
				page = new GridPane ();
				page.setPadding (new Insets (height * 0.01, width * 0.01, height * 0.01, width * 0.01));
				page.setAlignment (Pos.CENTER);

                f = new FrontPage ();

				page.add (f.content, 0, 0);
				page.add (fBox1, 0, 2);
				page.add (fBox2, 0, 3);

				Scene scene = new Scene(page, width * 0.75, height * 0.75);
				sanskritStage.setScene(scene);

				sanskritStage.show();

              }
        public void addFrontPage ()
              {
				  page.getChildren ().add (f.content);
				  fBox1.getChildren ().clear();
				  fBox2.getChildren ().clear ();
				  fBox1.getChildren ().addAll (frontPage_introduction1);
				  fBox2.getChildren ().addAll (frontPage_devanagariEditors1);
			  }

}
