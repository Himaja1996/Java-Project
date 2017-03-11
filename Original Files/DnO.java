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

public class DnO
{
	private Stage editorStage = new Stage();
	private SanskritLineConvert slc = new SanskritLineConvert();
	private String [] fileWordsArray, lineArray;
	private String inputLine = "";
	private FileChooser fileChooser = new FileChooser ();
	private TextArea inputArea, nagariOutputArea, diacriticsOutputArea;
	private MenuBar dnoMenuBar;
	private Scene devanagariScene, diacriticsScene, devAndDiaScene;
	Button convert;
	Label input, nagariOut, diacriticsOut;
	double width, height, minX, minY;
	private Font defaultFont = new Font (16);
	File savedFile;
	DevanagariHelp dHelp = new DevanagariHelp ();
	public  void dnoEditor()
	{
		Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
		width = screenSize.getWidth ();
		height = screenSize.getHeight ();
		minX = screenSize.getMinX();
		minY = screenSize.getMinY();

		input = new Label("Enter text below, eg    aha* gr.ha* gacchaami  ( \u0905\u0939\u0902 \u0917\u0943\u0939\u0902 \u0917\u091A\u094D\u091B\u093E\u092E\u093F )... Toggle View button for diacritics ...");
		nagariOut = new Label("\u0926\u0947\u0935\u0928\u093E\u0917\u0930\u0940");
		diacriticsOut = new Label("Diacritics");


		input.setMinWidth(width);
		input.setFont(defaultFont);

		nagariOut.setMinWidth(width);
		nagariOut.setAlignment(Pos.CENTER);
		nagariOut.setFont(defaultFont);

		diacriticsOut.setMinWidth(width);
		diacriticsOut.setAlignment(Pos.CENTER);
		diacriticsOut.setFont(defaultFont);

		inputArea = new TextArea();
		inputArea.setMinWidth(width*0.8);
		inputArea.setPromptText("Toggle View button for diacritics");
		nagariOutputArea = new TextArea();
		nagariOutputArea.setEditable(false);
		nagariOutputArea.setPromptText("Toggle View button for diacritics");
		diacriticsOutputArea = new TextArea();
		diacriticsOutputArea.setEditable(false);

		inputArea.setFont(defaultFont);
		nagariOutputArea.setFont(defaultFont);
		diacriticsOutputArea.setFont(defaultFont);

		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));

		dnoMenuBar = new MenuBar();

				//File Menu
				Menu dnoFile = new Menu ("File");

				MenuItem openItem = new MenuItem("Open");
				openItem.setOnAction(e -> open());

                MenuItem save = new MenuItem("Save");
                save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
				save.setOnAction(e -> save()); //build new method, if no file exists, ask for file, else, save existing file

				MenuItem saveAsItem = new MenuItem("Save As...");
				saveAsItem.setOnAction(e -> saveAs());

                MenuItem saveAndQuit = new MenuItem("Save And Quit");
				saveAndQuit.setOnAction(e -> { save(); editorStage.close();}); //call save, then call editorStage.close ();

				MenuItem exitItem = new MenuItem("Quit Without Saving");
				exitItem.setOnAction(e -> editorStage.close());

				dnoFile.getItems().addAll(openItem, save, saveAsItem, saveAndQuit, exitItem);

				//Edit Menu
				Menu dnoEdit = new Menu ("Edit");

				MenuItem clearItem = new MenuItem ("Clear");
				clearItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
				clearItem.setOnAction(e -> {inputLine = ""; inputArea.clear (); nagariOutputArea.clear (); diacriticsOutputArea.clear ();});

				dnoEdit.getItems ().addAll (clearItem);

				//View Menu
				Menu dnoView = new Menu ("View");

				CheckMenuItem diacriticsItem = new CheckMenuItem ("Diacritics Only");
				diacriticsItem.setOnAction(e -> {
					if (diacriticsItem.isSelected ())
					  {
						VBox diacriticsBox = new VBox ();
						diacriticsBox.setSpacing(5);
						diacriticsBox.getChildren ().addAll (dnoMenuBar, input, inputArea, diacriticsOut, diacriticsOutputArea);
						diacriticsScene = new Scene (diacriticsBox, width, height);
						editorStage.setScene (diacriticsScene);
						diacriticsItem.setText("Devan\u0101gar\u012B Only");
					  }
					else
					  {

						VBox devanagariBox = new VBox ();
						devanagariBox.setSpacing (5);
						devanagariBox.getChildren ().addAll (dnoMenuBar, input, inputArea, nagariOut, nagariOutputArea);
						devanagariScene = new Scene (devanagariBox, width, height);
						editorStage.setScene (devanagariScene);
						diacriticsItem.setText("Diacritics Only");
				      }
				});

				dnoView.getItems().addAll(diacriticsItem);

				Menu dnoHelp = new Menu ("Help");
				MenuItem helpItem = new MenuItem("Help");
				helpItem.setOnAction(e -> help());
				dnoHelp.getItems().addAll(helpItem);

		dnoMenuBar.getMenus ().addAll (dnoFile, dnoEdit, dnoView, dnoHelp);

		VBox primaryNode = new VBox();
		primaryNode.setPadding(new Insets(10, 10, 10 , 10));
		primaryNode.setSpacing(5);
		primaryNode.getChildren ().addAll (dnoMenuBar, input, nagariOut, nagariOutputArea);

		VBox devanagariBox = new VBox ();
		devanagariBox.setSpacing (5);
		devanagariBox.getChildren ().addAll( dnoMenuBar, input, inputArea, nagariOut, nagariOutputArea);
		devanagariScene = new Scene (devanagariBox, width, height);

		editorStage.setX (minX);
	    editorStage.setY (minY);
	    editorStage.setWidth (width);
		editorStage.setHeight (height);
		editorStage.setScene (devanagariScene);
		editorStage.show ();
		editorStage.setResizable (false);
		editorStage.setTitle ("Devan\u0101gar\u012B Editor - Online Mode");


		//take a look at this and help me understand what I am doing wrong
		inputArea.setOnKeyPressed(e ->
				{
					if (e.getCode () == KeyCode.ENTER)
					  {
						  if (inputLine != null)
						  	{
						  	  slc.wordConvert(inputLine);
						  	  nagariOutputArea.setText(slc.nagari + "\n");
						  	  diacriticsOutputArea.setText(slc.diacritics + "\n");
						  	  slc.flush();
					       }
					  }
				    if (e.getCode() == KeyCode.BACK_SPACE && inputArea.getText() != null)
				      {
						  nagariOutputArea.deletePreviousChar ();

						int y = inputArea.getCaretPosition();
						System.out.println (y);
						if (inputLine == null || y == 1)
						  {
							  //area A
							  System.out.println ("in area A");
							  inputArea.clear ();
						      nagariOutputArea.clear ();
						  }
					    if (inputLine != null && inputArea.getCaretPosition() == (inputLine.length()))
					    {

                            //area B

                            System.out.println ("in area B");
							inputLine = inputLine.substring(0, inputLine.length() - 1);

						}
					    else if(inputLine != null && inputArea.getCaretPosition() != (inputLine.length()))
					        {
								int x = inputArea.getCaretPosition();
								System.out.println ("x = " + x);
						        inputLine = inputLine.substring(0, x - 1) + inputLine.substring(x, inputLine.length());
						    }


						    System.out.println(inputLine);
						 if (inputLine != null)
						   {
					       slc.wordConvert(inputLine);
						   nagariOutputArea.setText(slc.nagari);
						   diacriticsOutputArea.setText(slc.diacritics);
					       slc.flush();
					       }
				    }
				    else if(e.isShiftDown() )
				    {
					    if(e.getCode() == KeyCode.DIGIT9)
					    inputLine = inputLine + "(" ;
					    if(e.getCode() == KeyCode.BACK_SLASH)
					    inputLine = inputLine + "|" ;
					    if(e.getCode() == KeyCode.DIGIT8)
					    inputLine = inputLine + "*" ;
					    if(e.getCode() == KeyCode.DIGIT7)
					    inputLine = inputLine + "&" ;
					    if(e.getCode() == KeyCode.DIGIT5)
					    inputLine = inputLine + "%" ;
					    if(e.getCode() == KeyCode.DIGIT3)
					    inputLine = inputLine + "#" ;
					    if(e.getCode() == KeyCode.DIGIT2)
					    inputLine = inputLine + "@" ;
					    if(e.getCode() == KeyCode.QUOTE)
					    inputLine = inputLine + "\"" ;
					    if(e.getCode() == KeyCode.COMMA)
					    inputLine = inputLine + "<" ;
					    if(e.getCode() == KeyCode.PERIOD)
					    inputLine = inputLine + ">" ;
					    if(e.getCode() == KeyCode.BACK_QUOTE)
					    inputLine = inputLine + "~" ;

					    slc.wordConvert(inputLine);
						nagariOutputArea.setText(slc.nagari);
						diacriticsOutputArea.setText(slc.diacritics);
					    slc.flush();

				    }

		//carriage return line feed

					else
					{
					    inputLine = inputLine + e.getText();
					    slc.wordConvert(inputLine);
						nagariOutputArea.setText(slc.nagari);
						diacriticsOutputArea.setText(slc.diacritics);
					    slc.flush();
					}
		System.out.println(inputLine);
			});
   }
	private void open()
	{
			File selectedFile = null;
			BufferedReader br = null;
				try{
						selectedFile = fileChooser.showOpenDialog(null);
				   		br = new BufferedReader(new FileReader(selectedFile));
				   		String line;
				   		String in = "";
				   		if (selectedFile != null){
					   		while((line = br.readLine()) != null )
					   		{
						   		in = in + line + "\n";
							}
						inputArea.setText(in + line);
				   		}
				   }
				catch(IOException ie) {
						ie.printStackTrace();
				   }
	}

	private void save()
	{
		if (savedFile != null)
				{
					try {

						OutputStream outputStream       = new FileOutputStream(savedFile);
		                Writer       outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
		                BufferedWriter bw = new BufferedWriter(outputStreamWriter);

		                bw.write("******** INPUT *******");
		                bw.newLine();

		                for (String txt : inputArea.getText().split("\\n"))
		                {
							bw.write(txt);
							bw.newLine();
						}

						bw.write("******** DEVANAGARI OUTPUT *******");
		                bw.newLine();

						for (String line : nagariOutputArea.getText().split("\\n"))
						{
							bw.write(line);
							bw.newLine();
						}

						bw.write("******** DIACRITICS OUTPUT *******");
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
		else
		{
			saveAs();
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

                bw.write("******** INPUT *******");
                bw.newLine();

                for (String txt : inputArea.getText().split("\\n"))
                {
					bw.write(txt);
					bw.newLine();
				}

				bw.write("******** DEVANAGARI OUTPUT *******");
                bw.newLine();

				for (String line : nagariOutputArea.getText().split("\\n"))
				{
					bw.write(line);
					bw.newLine();
				}

				bw.write("******** DIACRITICS OUTPUT *******");
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

}
