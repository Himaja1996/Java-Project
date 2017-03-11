import javafx.application.*;
import javafx.collections.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.io.*;
import java.util.*;

public class DnB
      {
		private Stage editorStage = new Stage ();
		private SanskritLineConvert slc = new SanskritLineConvert ();
		private String [] fileWordsArray, lineArray;
		private String inputLine = "";
		private FileChooser fileChooser = new FileChooser ();
		private TextArea inputArea, nagariOutputArea, diacriticsOutputArea;
		private MenuBar dnbMenuBar;
		private Scene devanagariScene, diacriticsScene;
		private Button convert;
		private Label input, nagariOut, diacriticsOut, saveInfo;
		private double width, height, minX, minY;
		private Font defaultFont = new Font (16);
		private File savedFile;
		private DevanagariHelp dHelp = new DevanagariHelp ();
		public void dnbEditor ()
	          {
				Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
				width = screenSize.getWidth ();
				height = screenSize.getHeight ();
				minX = screenSize.getMinX ();
				minY = screenSize.getMinY ();
				convert = new Button ("Convert");
		 		convert.setAlignment (Pos.CENTER);
				convert.setCenterShape (true);
				input = new Label ("Steps: (1) Enter text e.g.    aha* gr.ha* gacchaami  ( \u0905\u0939\u0902 \u0917\u0943\u0939\u0902 \u0917\u091A\u094D\u091B\u093E\u092E\u093F );  (2) Click Convert; (3) Click View for diacritics. (4) Click File to open/save a file.");
				input.setStyle ("-fx-font-size: 15px;");
		        nagariOut = new Label ("\u0926\u0947\u0935\u0928\u093E\u0917\u0930\u0940");
		        nagariOut.setStyle ("-fx-font-size: 15px;");
		        diacriticsOut = new Label ("Diacritics");
		        saveInfo = new Label ();
                diacriticsOut.setStyle ("-fx-font-size: 15px;");
		        input.setMinWidth (width);
		        input.setFont (defaultFont);

		        nagariOut.setMinWidth (width);
		        nagariOut.setAlignment (Pos.CENTER);
		        nagariOut.setFont (defaultFont);

		        diacriticsOut.setMinWidth (width);
		        diacriticsOut.setAlignment (Pos.CENTER);
		        diacriticsOut.setFont (defaultFont);

				inputArea = new TextArea ();
				inputArea.setMinWidth (width*0.8);
				nagariOutputArea = new TextArea ();
				nagariOutputArea.setEditable (false);
				diacriticsOutputArea = new TextArea ();
				diacriticsOutputArea.setEditable (false);

				inputArea.setFont (defaultFont);
				nagariOutputArea.setFont (defaultFont);
				diacriticsOutputArea.setFont (defaultFont);

		        fileChooser.getExtensionFilters ().add (new FileChooser.ExtensionFilter ("TXT files (*.txt)", "*.txt"));

		        dnbMenuBar = new MenuBar ();

				//File Menu
				Menu dnbFile = new Menu ("File");

				MenuItem openItem = new MenuItem ("Open");
				openItem.setOnAction (e -> open ());

                MenuItem save = new MenuItem ("Save");
                save.setAccelerator(KeyCombination.keyCombination ("Ctrl+S"));
				save.setOnAction (e -> {save (); saveInfo.setText ("File saved as " + savedFile.getName ());}); //build new method, if no file exists, ask for file, else, save existing file

				MenuItem saveAsItem = new MenuItem ("Save As...");
				saveAsItem.setOnAction (e -> saveAs ());

                MenuItem saveAndQuit = new MenuItem ("Save And Quit");
				saveAndQuit.setOnAction (e -> { save (); editorStage.close ();}); //call save, then call editorStage.close ();

				MenuItem exitItem = new MenuItem ("Quit Without Saving");
				exitItem.setOnAction (e -> editorStage.close ());

				dnbFile.getItems ().addAll (openItem, save, saveAsItem, saveAndQuit, exitItem);

				//Edit Menu
				Menu dnbEdit = new Menu ("Edit");

				MenuItem clearItem = new MenuItem ("Clear");
				clearItem.setAccelerator (KeyCombination.keyCombination ("Ctrl+X"));
				clearItem.setOnAction (e -> {inputArea.clear (); nagariOutputArea.clear (); diacriticsOutputArea.clear ();});

				dnbEdit.getItems ().addAll (clearItem);

				//View Menu
				Menu dnbView = new Menu ("View");

				CheckMenuItem diacriticsItem = new CheckMenuItem ("Diacritics Only");
				diacriticsItem.setOnAction (e -> {
					if (diacriticsItem.isSelected ())
					  {
						VBox diacriticsBox = new VBox ();
						diacriticsBox.setSpacing(5);
						diacriticsBox.getChildren ().addAll (dnbMenuBar, input, inputArea, convert, saveInfo, diacriticsOut, diacriticsOutputArea);
						diacriticsScene = new Scene (diacriticsBox, width, height);
						editorStage.setScene (diacriticsScene);
						diacriticsItem.setText ("Devan\u0101gar\u012B Only");
					  }
					else
					  {

						VBox devanagariBox = new VBox ();
						devanagariBox.setSpacing (5);
						devanagariBox.getChildren ().addAll (dnbMenuBar, input, inputArea, convert, saveInfo, nagariOut, nagariOutputArea);
						devanagariScene = new Scene (devanagariBox, width, height);
						editorStage.setScene (devanagariScene);
						diacriticsItem.setText ("Diacritics Only");
				      }
				});

				dnbView.getItems ().addAll (diacriticsItem);

				Menu dnbHelp = new Menu ("Help");
				MenuItem helpItem = new MenuItem ("Help");
				helpItem.setOnAction (e -> help ());
				dnbHelp.getItems ().addAll (helpItem);

		        dnbMenuBar.getMenus ().addAll (dnbFile, dnbEdit, dnbView, dnbHelp);

				VBox primaryNode = new VBox ();
				primaryNode.setPadding (new Insets (10, 10, 10 , 10));
				primaryNode.setSpacing (5);
				primaryNode.getChildren ().addAll (dnbMenuBar, input, convert, nagariOut, nagariOutputArea);

				VBox devanagariBox = new VBox ();
				devanagariBox.setSpacing (5);
				devanagariBox.getChildren ().addAll (dnbMenuBar, input, inputArea, convert, nagariOut, nagariOutputArea);
				devanagariScene = new Scene (devanagariBox, width, height);

				editorStage.setX (minX);
				editorStage.setY (minY);
				editorStage.setWidth (width);
				editorStage.setHeight (height);
				editorStage.setScene (devanagariScene);
				editorStage.show ();
				editorStage.setResizable (false);
				editorStage.setTitle ("Devan\u0101gar\u012B Editor - Batch Mode");

		        convert.setOnAction (e -> {

				String converted = "";
				String diaConverted = "";
				String fileErrorLine = "";
			    slc.flush();

			    String lines [] = inputArea.getText ().split ("\\n");
			    String line = "";
			    String processedNagariLine = "" , processedNagariText = "", processedDiacriticsLine = "", processedDiacriticsText = "";
			    int n = 0, k = 0;
			    boolean errorPresent = false;

			    for (int i = 0; i < lines.length; i ++)
			       {

			         if (lines [i].length () == 0 || lines [i].charAt (0) == '\0' || lines [i].charAt (0) == '\n')
			           continue;
			          line = clearInitialWhiteSpace(lines [i]);
			         String words [] = line.split ("\\s+");
			         for (int j = 0; j < words.length; j ++)
			            {
			              slc.wordConvert (words [j]);
			              if (slc.foundError == true)
			                {
								 errorPresent = slc.foundError;
							     n = inputArea.getText ().indexOf (words [j]);
							     k = words [j].length ();
							     System.out.println ("j = " + j + "n =" + n + " k = " + k + "    " + slc.errorString);
							     nagariOutputArea.setText (slc.errorString);
							     diacriticsOutputArea.setText (slc.errorString);
							     inputArea.positionCaret (n);
							     inputArea.selectRange (n, n+k);
							     slc.foundError = false;
							     break;
						     }

						    processedNagariLine += slc.nagari + " ";
						    processedDiacriticsLine += slc.diacritics + " ";
					       }
                        processedNagariLine += slc.errorString;
                        processedNagariText += processedNagariLine + '\n';
                        processedNagariLine = "";
                        nagariOutputArea.setText (processedNagariText);
                        processedDiacriticsLine += slc.errorString;
                        processedDiacriticsText += processedDiacriticsLine + '\n';
                        processedDiacriticsLine = "";
                        diacriticsOutputArea.setText (processedDiacriticsText);

                        if (errorPresent == true)
                           break;
				   }
			});
	}
    public String clearInitialWhiteSpace (String string)
          {
			  int i = 0;
			  while (string.charAt (i) == ' ' || string.charAt (i) == '\t')
			       i ++ ;
			  return string.substring (i, string.length ());
		  }
	private void open()
	{
			File selectedFile = null;
			BufferedReader br = null;
				try
				     {
						selectedFile = fileChooser.showOpenDialog(null);
				   		br = new BufferedReader(new InputStreamReader( new FileInputStream(selectedFile), "UTF-8"));
				   		String line="";

				   		String in = "";
				   		if (selectedFile != null){
					   		while ((line = br.readLine()) != null )
					   		     {
						   		   in += (line + "\n");
						   		   inputArea.setText(in);
							     }
				   		}
				   }
				catch(IOException ie) {
						ie.printStackTrace();
				   }
	}

	private void save ()
	       {
		      if (savedFile != null)
				{
					try
					    {
						  OutputStream outputStream       = new FileOutputStream (savedFile);
		                  Writer outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
		                  BufferedWriter bw = new BufferedWriter (outputStreamWriter);

		                  bw.write ("***************");
		                  bw.newLine ();

		                for (String txt : inputArea.getText().split("\\n"))
		                   {
							  bw.write(txt);
							  bw.newLine();
						   }

						bw.write ("***************");
		                bw.newLine ();

						for (String line : nagariOutputArea.getText().split("\\n"))
						   {
							  bw.write (line);
							  bw.newLine ();
						   }

						bw.write ("***************");
		                bw.newLine ();

		                for (String line : diacriticsOutputArea.getText().split("\\n"))
		                   {
							  bw.write (line);
							  bw.newLine ();
						   }

						bw.close ();
					}
					catch (IOException ie)
					     {
						   ie.printStackTrace ();
					     }
		}
		else
		{
			saveAs ();
		}
	}

	private void saveAs()
	{
		savedFile = fileChooser.showSaveDialog(null);

		if (savedFile != null)
		{
			try {
				savedFile.createNewFile();
				OutputStream outputStream       = new FileOutputStream(savedFile);
                Writer       outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bw = new BufferedWriter(outputStreamWriter);

                bw.write("***************");
                bw.newLine();

                for (String txt : inputArea.getText().split("\\n"))
                {
					bw.write(txt);
					bw.newLine();
				}

				bw.write("***************");
                bw.newLine();

				for (String line : nagariOutputArea.getText().split("\\n"))
				{
					bw.write(line);
					bw.newLine();
				}

				bw.write("***************");
                bw.newLine();

                for (String line : diacriticsOutputArea.getText().split("\\n"))
                {
					bw.write(line);
					bw.newLine();
				}

				bw.close();
			}
			catch(IOException ie) {
				ie.printStackTrace();
			}
		}
	}



	private void help()
	{
		Stage help = new Stage();
		Pane helpPane = new Pane();
		helpPane.getChildren().add(dHelp.content);
		Scene helpScene = new Scene(helpPane, width*0.75, height*0.75);
		help.setScene(helpScene);
		help.setResizable(false);
		help.show();
	};

	private String findError(String line)
	{
		String [] wordsArray;
		String wordsConverted = "";
		int n = 0,k = 0;
		wordsArray = line.split ("\\s+");

		for (int i = 0; i < wordsArray.length; i++)
		{
			slc.wordConvert(wordsArray[i]);
			if(slc.foundError == true)
			{
				n = inputArea.getText().indexOf(wordsArray[i]);
				k = wordsArray[i].length();
				inputArea.positionCaret(n);
				inputArea.selectRange(n,n+k);
				wordsConverted = slc.errorString;
				slc.foundError = false;
			}
			else
			{
				wordsConverted += slc.nagari + " ";

			}
		}
		return wordsConverted;


	}

}
