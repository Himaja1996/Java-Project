import javafx.collections.*;
import java.util.*;
public class SanskritLineConvert
      {
//============= Declarations =================================================
        Set<Character> allowedChars,
                       notAllowedInitChars,
                       consonants,
                       postConsonants,
                       vowels,
                       postVowels,
                       rashaabhyaamChars;
        String nagari, diacritics,
               nagariLine, diacriticsLine, errorString,
               singleWord;
        boolean foundError = false;
        char [] arrayProcessed;

//============= Main Program =================================================
	    public void wordConvert (String input)
	    {
        singleWord = input;
        setSets ();        //sets the collections to that provided
        nagari = "";
        diacritics = "";

        //if nothing written and pressed return
        if (input.length () == 0)
  			{
          foundError = true;
  				errorString = "Illegal: Empty input string.";
  				return;
  			}

        // if Illegal Characters entered
				if (notAllowedInitChars.contains (input.charAt (0)))
				{
          foundError = true;
				  errorString = "Illegal init char: " + input.charAt (0);
				  nagari = diacritics = errorString;
				  return;
				}

				for (int i = 0; i < input.length (); i ++)
				{
          if (!(allowedChars.contains (input.charAt (i))))
				  {
            foundError = true;
				    errorString = "Illegal char: " + input.charAt (i);
				    nagari = diacritics = errorString;
				    return;
		      }
				}

//============= Switch Cases = 0 ============================================
				for (int i = 1; i < input.length (); i ++)
				{
          /*if (input.charAt (i) == '\'')
				      if (input.charAt (i - 1) != 's')*/
              switch(input.substring(i-1,i))
				      {

                case "s\'":
                  foundError = true;
  						   	errorString = "Illegal char: " + input.charAt (i);
  						   	nagari = diacritics = errorString;
  						   	//return;
                  break;
					   //}
				/*}
				for (int i = 1; i < input.length (); i ++)
				{*/

          /*if (input.charAt (i) == '<' || input.charAt (i) == '>')
				      if (input.charAt (i - 1) != 'n')*/
                case "n<":
                case "n>":
				      //{
                  foundError = true;
  						   	errorString = "Illegal char: " + input.charAt (i);
  						   	nagari = diacritics = errorString;
  						   	//return;
                  break;

				/*for (int i = 1; i < input.length (); i ++)
				{
            if (input.charAt (i) == '.')
				      if (!(input.charAt (i - 1) == 'r' || input.charAt (i - 1) == 's' || input.charAt (i - 1) == 'n' || input.charAt (i - 1) == 't' || input.charAt (i - 1) == 'd'))
				      {*/
              case "r.":
              case "s.":
              case "n.":
              case "t.":
              case "d.":
                foundError = true;
						   	errorString = "Illegal char: " + input.charAt (i);
						   	nagari = diacritics = errorString;
						   	break;
						//  }
				//}

              default:
                return;

            }
       }

//===========================================================================
         String inputA = input;
				 char [] arrayInput = inputA.toCharArray ();

//============= Switch Cases = 1 ============================================
				 for (int i = 0; i <= inputA.length () - 4; i ++)
				  		switch(inputA.substring (i, i + 4))
				  		{
                
                case "r.r.":
				  			arrayInput [i] = 'L';
				  			arrayInput [i + 1] = '!';
				  			arrayInput [i + 2] = '!';
				  			arrayInput [i + 3] = '!';
				  			i += 3;
				  			if (i >= inputA.length () - 1)
				  			  break;
                default:
                return;

					    }

//============= Switch Cases = 2 ============================================
				  inputA = new String (arrayInput);
				  for (int i = 0; i <= inputA.length () - 3; i ++)
					   if (inputA.substring (i, i + 3).equals ("t.h"))
					     {
  							 arrayInput [i] = 'q';
  							 arrayInput [i + 1] = '!';
  							 arrayInput [i + 2] = '!';
  							 i += 2;
  							 if (i >= inputA.length () - 1)
  							   break;
					     }
					   else
					   if (inputA.substring (i, i + 3).equals ("d.h"))
					    {
  						 	arrayInput [i] = 'w';
  						 	arrayInput [i + 1] = '!';
  						 	arrayInput [i + 2] = '!';
  						 	i += 2;
  						 	if (i >= inputA.length () - 1)
  						 	  break;
					    }
				  inputA = new String (arrayInput);
				  for (int i = 0; i <= inputA.length () - 2; i ++)
					   if (inputA.substring (i, i + 2).equals ("aa"))
					     {
  						    arrayInput [i] = 'A';
  						    arrayInput [i + 1] = '!';
  						    if (i >= inputA.length () - 1)
  							   break;
					     }
					   else
					   if (inputA.substring (i, i + 2).equals ("ii"))
					     {
						    arrayInput [i] = 'I';
						    arrayInput [i + 1] = '!';
						    if (i >= inputA.length () - 1)
							  break;
					     }
					   else
					   if (inputA.substring (i, i + 2).equals ("uu"))
					     {
					  	    arrayInput [i] = 'U';
						    arrayInput [i + 1] = '!';
						    if (i >= inputA.length () - 1)
							  break;
					     }
					   else
					   if (inputA.substring (i, i + 2).equals ("r."))
					     {
						    arrayInput [i] = 'R';
						    arrayInput [i + 1] = '!';
						    if (i >= inputA.length () - 1)
							   break;
					   }
					  else
					  if (inputA.substring (i, i + 2).equals ("ai"))
					    {
						  arrayInput [i] = 'E';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					    }
					  else
					  if (inputA.substring (i, i + 2).equals ("au"))
					    {
						  arrayInput [i] = 'O';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					    }
					  else
					  if (inputA.substring (i, i + 2).equals ("kh"))
					    {
						  arrayInput [i] = 'Z';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					    }
					  else
					  if (inputA.substring (i, i + 2).equals ("gh"))
					    {
						  arrayInput [i] = 'Y';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					    }
					  else
					  if (inputA.substring (i, i + 2).equals ("ch"))
					    {
						  arrayInput [i] = 'X';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("jh"))
					   {
						  arrayInput [i] = 'W';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("t."))
					   {
						  arrayInput [i] = 'T';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("d."))
					   {
						  arrayInput [i] = 'D';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("n."))
					   {
						  arrayInput [i] = 'N';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							 break;
					   }
                     else
					 if (inputA.substring (i, i + 2).equals ("th"))
					   {
						  arrayInput [i] = 'V';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("dh"))
					   {
						  arrayInput [i] = 'Q';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("ph"))
					   {
						  arrayInput [i] = 'P';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
						    break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("bh"))
					   {
						 arrayInput [i] = 'B';
						 arrayInput [i + 1] = '!';
						 if (i >= inputA.length () - 1)
						   break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("s'"))
					   {
						  arrayInput [i] = 'z';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("s."))
					   {
						  arrayInput [i] = 'S';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("n<"))
					   {
						  arrayInput [i] = 'G';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }
					 else
					 if (inputA.substring (i, i + 2).equals ("n>"))
					   {
						  arrayInput [i] = 'J';
						  arrayInput [i + 1] = '!';
						  if (i >= inputA.length () - 1)
							break;
					   }

		  int exclams = 0;
		  for (int i = 0; i < arrayInput.length; i ++)
			 if (arrayInput [i] == '!')
			   exclams ++;
		  arrayProcessed = new char [arrayInput.length - exclams];
		  int jA = 0;
		  for (int i = 0; i < arrayInput.length; i ++)
		     if (arrayInput [i] != '!')
			   arrayProcessed [jA ++] = arrayInput [i];

				 for (int i = 0; i < arrayProcessed.length; i ++)
				    {
				       if (consonants.contains (arrayProcessed [i]))
				         if ( i < (arrayProcessed.length - 1))
				           if (postVowels.contains (arrayProcessed [i + 1]))
				           {
						   	   foundError = true;
						   	   errorString = "Illegal postConsonant char: " + arrayProcessed [i + 1];
						   	   nagari = diacritics = errorString;
						   	   return;
						   	}
				    }
				 for (int i = 0; i < arrayProcessed.length; i ++)
				    {
				       if (vowels.contains (arrayProcessed [i]))
				         if (i < (arrayProcessed.length - 1))
				           if (postConsonants.contains (arrayProcessed [i + 1]))
				           {
						   	   foundError = true;
						   	   errorString = "Illegal postVowel char: " + arrayProcessed [i + 1];
						   	   nagari = diacritics = errorString;
						   	   return;
						   	}
				    }

				 for (int i = 1; i < arrayProcessed.length; i ++)
				    {
				       if (postVowels.contains (arrayProcessed [i]))
				         if (!(vowels.contains (arrayProcessed [i - 1]) || (arrayProcessed [i - 1] == '*' && arrayProcessed [i] == '~')))
				           {
						   	   foundError = true;
						   	   errorString = "Illegal postVowel char: " + arrayProcessed [i];
						   	   nagari = diacritics = errorString;
						   	   return;
						   	}
				    }
          for (int i = 0; i < arrayProcessed.length - 1; i ++)
             if (vowels.contains (arrayProcessed [i]) && vowels.contains (arrayProcessed [i + 1]))
			   {
				   foundError = true;
				   errorString = "Illegal: Two consecutive vowels" ;
				   nagari = diacritics = errorString;
				   return;
				}
int i = 0;
if (vowels.contains (arrayProcessed [i]))
  {
	  if (arrayProcessed [i] == 'a')
		{
			nagari += '\u0905';
			diacritics += 'a';
		}
	  else
	  if (arrayProcessed [i] == 'A')
		{
			nagari += '\u0906';
			diacritics += '\u0101';
		}
	  else
	  if (arrayProcessed [i] == 'i')
		{
			nagari += '\u0907';
			diacritics += 'i';
		}
	  else

	  if (arrayProcessed [i] == 'I')
		{
			nagari += '\u0908';
			diacritics += '\u012B';
		}
	  else
	  if (arrayProcessed [i] == 'u')
		{
			nagari += '\u0909';
			diacritics += 'u';
		}
	  else
	  if (arrayProcessed [i] == 'U')
		{
			nagari += '\u090A';
			diacritics += '\u016B';
		}
	  else
	  if (arrayProcessed [i] == 'R')
		{
			nagari += '\u090B';
			diacritics += "r\u0323";
		}
	  else
	  if (arrayProcessed [i] == 'L')
		{
			nagari += '\u0960';
			diacritics += '\u1E5D';
		}
	  else
	  if (arrayProcessed [i] == 'e')
		{
			nagari += '\u090F';
			diacritics += 'e';
		}
	  else
	  if (arrayProcessed [i] == 'E')
		{
			nagari += '\u0910';
			diacritics += "ai";
		}
	  else
	  if (arrayProcessed [i] == 'o')
		{
			nagari += '\u0913';
			diacritics += 'o';
		}
	  else
	  if (arrayProcessed [i] == 'O')
		{
			nagari += '\u0914';
			diacritics += "au";
		}
	  i = 1;
    }
for (; i < arrayProcessed.length; i ++)
   if (arrayProcessed [i] == '*')
       {
   		nagari += '\u0902';
   		diacritics += "m\u0307";
   	   }
   else
   if (arrayProcessed [i] == ':')
       {
   		nagari += '\u0903';
   		diacritics += "h\u0323";
   	   }
   else
   if (arrayProcessed [i] == '~')
       {
   		nagari += '\u093D';
   		diacritics += '\'';
   	   }
   	else
    if (arrayProcessed [i] == '(')
       {
   		nagari += '\u0901';
   		diacritics += "m\u0323";
   	   }
     else
     if (arrayProcessed [i] == 'a')
       {
   		nagari += "";
   		diacritics += 'a';
   	   }
     else
     if (arrayProcessed [i] == 'A')
       {
   		nagari += '\u093E';
   		diacritics += '\u0101';
   	   }
     else
     if (arrayProcessed [i] == 'i')
       {
   		nagari += '\u093F';
   		diacritics += 'i';
   	   }
     else
     if (arrayProcessed [i] == 'I')
       {
   		nagari += '\u0940';
   		diacritics += '\u012B';
   	   }
     else
     if (arrayProcessed [i] == 'u')
       {
   		nagari += '\u0941';
   		diacritics += 'u';
   	}
     else
     if (arrayProcessed [i] == 'U')
       {
   		nagari += '\u0942';
   		diacritics += '\u016B';
   	   }
     else
     if (arrayProcessed [i] == 'R')
       {
   		nagari += '\u0943';
   		diacritics += "r\u0323";
       }
     else
     if (arrayProcessed [i] == 'L')
       {
   		nagari += '\u0944';
   		diacritics += '\u1E5D';
   	   }
     else
     if (arrayProcessed [i] == 'e')
       {
   		nagari += '\u0947';
   		diacritics += 'e';
   	   }
     else
     if (arrayProcessed [i] == 'E')
       {
   		nagari += '\u0948';
   		diacritics += "ai";
   	   }
     else
     if (arrayProcessed [i] == 'o')
       {
   		nagari += '\u094B';
   		diacritics += 'o';
   	   }
     else
     if (arrayProcessed [i] == 'O')
       {
   		nagari += '\u094C';
   		diacritics += "au";
       }
     else
     if (arrayProcessed [i] == 'k')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0915' + "" + '\u094D');
		      diacritics += 'k';
		    }
          else
		    {
			  nagari += ('\u0915');
		      diacritics += 'k';
		    }
	   }
     else
     if (arrayProcessed [i] == 'Z')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0916' + "" + '\u094D');
		      diacritics += "kh";
		    }
          else
		    {
			  nagari += ('\u0916');
		      diacritics += "kh";
		    }
	   }
     else
     if (arrayProcessed [i] == 'g')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0917' + "" + '\u094D');
		      diacritics += 'g';
		    }
          else
		    {
			  nagari += ('\u0917');
		      diacritics += 'g';
		    }
	   }
     else
     if (arrayProcessed [i] == 'Y')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0918' + "" + '\u094D');
		      diacritics += "gh";
		    }
          else
		    {
			  nagari += ('\u0918');
		      diacritics += "gh";
		    }
	   }
     else
     if (arrayProcessed [i] == 'G')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0919' + "" + '\u094D');
		      diacritics += "\u1E45";
		    }
          else
		    {
			  nagari += ('\u0919');
		      diacritics += "\u1E45";
		    }
	   }
     else
     if (arrayProcessed [i] == 'c')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u091A' + "" + '\u094D');
		      diacritics += 'c';
		    }
          else
		    {
			  nagari += ('\u091A');
		      diacritics += 'c';
		    }
	   }
     else
     if (arrayProcessed [i] == 'X')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u091B' + "" + '\u094D');
		      diacritics += "ch";
		    }
          else
		    {
			  nagari += ('\u091B');
		      diacritics += "ch";
		    }
	   }
     else
     if (arrayProcessed [i] == 'j')
       {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u091C' + "" + '\u094D');
		      diacritics += 'j';
		    }
          else
		    {
			  nagari += ('\u091C');
		      diacritics += 'j';
		    }
	   }
    else
    if (arrayProcessed [i] == 'W')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u091D' + "" + '\u094D');
		      diacritics += "jh";
		    }
          else
		    {
			  nagari += ('\u091D');
		      diacritics += "jh";
		    }
	  }
    else
    if (arrayProcessed [i] == 'J')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u091E' + "" + '\u094D');
		      diacritics += '\u00F1';
		    }
          else
		    {
			  nagari += ('\u091E');
		      diacritics += '\u00F1';
		    }
	  }
    else
    if (arrayProcessed [i] == 'T')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u091F' + "" + '\u094D');
		      diacritics += "t\u0323";
		    }
          else
		    {
			  nagari += ('\u091F');
		      diacritics += "t\u0323";
		    }
	  }
    else
    if (arrayProcessed [i] == 'q')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0920' + "" + '\u094D');
		      diacritics += "t\u0323h";
		    }
          else
		    {
			  nagari += ('\u0920');
		      diacritics += "t\u0323h";
		    }
	  }
	else
    if (arrayProcessed [i] == 'D')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0921' + "" + '\u094D');
		      diacritics += "d\u0323";
		    }
          else
		    {
			  nagari += ('\u0921');
		      diacritics += "d\u0323";
		    }
	  }
    else
    if (arrayProcessed [i] == 'w')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0922' + "" + '\u094D');
		      diacritics += "d\u0323h";
		    }
          else
		    {
			  nagari += ('\u0922');
		      diacritics += "d\u0323h";
		    }
	  }
    else
    if (arrayProcessed [i] == 'N')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0923' + "" + '\u094D');
		      diacritics += "n\u0323";
		    }
          else
		    {
			  nagari += ('\u0923');
		      diacritics += "n\u0323";
		    }
	  }
    else
    if (arrayProcessed [i] == 't')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0924' + "" + '\u094D');
		      diacritics += 't';
		    }
          else
		    {
			  nagari += ('\u0924');
		      diacritics += 't';
		    }
	  }
    else
    if (arrayProcessed [i] == 'V')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0925' + "" + '\u094D');
		      diacritics += "th";
		    }
          else
		    {
			  nagari += ('\u0925');
		      diacritics += "th";
		    }
	  }
    else
    if (arrayProcessed [i] == 'd')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0926' + "" + '\u094D');
		      diacritics += 'd';
		    }
          else
		    {
			  nagari += ('\u0926');
		      diacritics += 'd';
		    }
	  }
    else
    if (arrayProcessed [i] == 'Q')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0927' + "" + '\u094D');
		      diacritics += "dh";
		    }
          else
		    {
			  nagari += ('\u0927');
		      diacritics += "dh";
		    }
	  }
    else
    if (arrayProcessed [i] == 'n')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0928' + "" + '\u094D');
		      diacritics += 'n';
		    }
          else
		    {
			  nagari += ('\u0928');
		      diacritics += 'n';
		    }
	  }
    else
    if (arrayProcessed [i] == 'p')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u092A' + "" + '\u094D');
		      diacritics += 'p';
		    }
          else
		    {
			  nagari += ('\u092A');
		      diacritics += 'p';
		    }
	  }
    else
    if (arrayProcessed [i] == 'P')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u092B' + "" + '\u094D');
		      diacritics += "ph";
		    }
          else
		    {
			  nagari += ('\u092B');
		      diacritics += "ph";
		    }
	  }
    else
    if (arrayProcessed [i] == 'b')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u092C' + "" + '\u094D');
		      diacritics += 'b';
		    }
          else
		    {
			  nagari += ('\u092C');
		      diacritics += 'b';
		    }
	  }
    else
    if (arrayProcessed [i] == 'B')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u092D' + "" + '\u094D');
		      diacritics += "bh";
		    }
          else
		    {
			  nagari += ('\u092D');
		      diacritics += "bh";
		    }
	  }
    else                                        //:~(
    if (arrayProcessed [i] == 'm')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u092E' + "" + '\u094D');
		      diacritics += 'm';
		    }
          else
		    {
			  nagari += ('\u092E');
		      diacritics += 'm';
		    }
	  }
    else
    if (arrayProcessed [i] == 'y')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u092F' + "" + '\u094D');
		      diacritics += 'y';
		    }
          else
		    {
			  nagari += ('\u092F');
		      diacritics += 'y';
		    }
	  }
    else
    if (arrayProcessed [i] == 'r')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0930' + "" + '\u094D');
		      diacritics += 'r';
		    }
          else
		    {
			  nagari += ('\u0930');
		      diacritics += 'r';
		    }
	  }
    else
    if (arrayProcessed [i] == 'l')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0932' + "" + '\u094D');
		      diacritics += 'l';
		    }
          else
		    {
			  nagari += ('\u0932');
		      diacritics += 'l';
		    }
	  }
    else
    if (arrayProcessed [i] == 'v')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0935' + "" + '\u094D');
		      diacritics += 'v';
		    }
          else
		    {
			  nagari += ('\u0935');
		      diacritics += 'v';
		    }
	  }
    else
    if (arrayProcessed [i] == 'z')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0936' + "" + '\u094D');
		      diacritics += '\u015B';
		    }
          else
		    {
			  nagari += ('\u0936');
		      diacritics += '\u015B';
		    }
	  }
    else
    if (arrayProcessed [i] == 'S')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0937' + "" + '\u094D');
		      diacritics += "s\u0323";
		    }
          else
		    {
			  nagari += ('\u0937');
		      diacritics += "s\u0323";
		    }
	  }
    else
    if (arrayProcessed [i] == 's')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0938' + "" + '\u094D');
		      diacritics += 's';
		    }
          else
		    {
			  nagari += ('\u0938');
		      diacritics += 's';
		    }
	  }
    else
    if (arrayProcessed [i] == 'h')
      {
		  if (i == (arrayProcessed.length - 1) || consonants.contains (arrayProcessed [i + 1]))
		    {
			  nagari += ('\u0939' + "" + '\u094D');
		      diacritics += 'h';
		    }
          else
		    {
			  nagari += ('\u0939');
		      diacritics += 'h';
		    }
	  }
	else
	if (arrayProcessed [i] == '|')
	  {
		 nagari += ('\u0964');
		 diacritics += '.';
	  }
	else
	if (arrayProcessed [i] == '#')
	  {
		 nagari += ('\u0965');
		 diacritics += '.';
	  }
	else
	if (arrayProcessed [i] == '0')
	  {
		nagari += ('\u0966');
		diacritics += '0';
	  }
	else
	if (arrayProcessed [i] == '1')
	  {
		 nagari += ('\u0967');
		 diacritics += '1';
	  }
	else
	if (arrayProcessed [i] == '2')
	  {
		nagari += ('\u0968');
		diacritics += '2';
	  }
	else
	if (arrayProcessed [i] == '3')
	  {
		nagari += ('\u0969');
		diacritics += '3';
	  }
	else
	if (arrayProcessed [i] == '4')
	  {
		nagari += ('\u096A');
		diacritics += '4';
	  }
	else
	if (arrayProcessed [i] == '5')
	  {
		nagari += ('\u096B');
		diacritics += '5';
	  }
	else
	if (arrayProcessed [i] == '6')
	  {
		 nagari += ('\u096C');
		 diacritics += '6';
	  }
	else
	if (arrayProcessed [i] == '7')
	  {
		 nagari += ('\u096D');
		 diacritics += '7';
	  }
	else
	if (arrayProcessed [i] == '8')
	  {
		 nagari += ('\u096E');
		 diacritics += '8';
	  }
	else
	if (arrayProcessed [i] == '9')
	  {
		 nagari += ('\u096F');
		 diacritics += '9';
	  }
	else
	if (arrayProcessed [i] == '@')
	  {
		 nagari += ('\u0970');
		 diacritics += '.';
	  }
	else
	if (arrayProcessed [i] == '\"')
	  {
		nagari += ('\"');
		diacritics += '\"';
	  }
	else
	if (arrayProcessed [i] == '{')
	  {
		 nagari += ('{');
		 diacritics += '{';
	  }
	else
	if (arrayProcessed [i] == '}')
	  {
		 nagari += ('}');
		 diacritics += '}';
	  }
	else
	if (arrayProcessed [i] == '&')
	  {
		 nagari += ('\u0950');
		 diacritics += "om\u0323";
	  }
	else
	if (arrayProcessed [i] == '^')
	  {
		 nagari += ('^');
		 diacritics += '^';
	  }
	else
	if (arrayProcessed [i] == '-')
	  {
		 nagari += ('-');
		 diacritics += '-';
	  }
	else
	if (arrayProcessed [i] == '_')
	  {
		 nagari += ('_');
		 diacritics += '_';
	  }
	else
	if (arrayProcessed [i] == '[')
	  {
		 nagari += ('[');
		 diacritics += '[';
	  }
	else
	if (arrayProcessed [i] == ']')
	  {
		 nagari += (']');
		 diacritics += ']';
	  }
	else
	if (arrayProcessed [i] == '?')
	  {
		 nagari += ('?');
		 diacritics += '?';
	  }
	else
	if (arrayProcessed [i] == ' ')
	  {
		 nagari += (' ');
		 diacritics += ' ';
	  }
	else
	if (arrayProcessed [i] == ',')
	  {
		nagari += (',');
		diacritics += ',';
	  }
	  	 }
public void setSets ()
          {
				 allowedChars = new HashSet<Character> ();
				 notAllowedInitChars = new HashSet<Character> ();
				 vowels = new HashSet<Character> ();
				 consonants = new HashSet<Character> ();
				 postVowels = new HashSet<Character> ();
				 postConsonants = new HashSet<Character> ();
				 rashaabhyaamChars = new HashSet<Character> ();
				 	    allowedChars.add ('a');
				 	    allowedChars.add ('b');
				 	    allowedChars.add ('c');
				 	    allowedChars.add ('d');
				 	    allowedChars.add ('e');
				 	    allowedChars.add ('g');
				 	    allowedChars.add ('h');
				 	    allowedChars.add ('i');
				 	    allowedChars.add ('j');
				 	    allowedChars.add ('k');
				 	    allowedChars.add ('l');
				 	    allowedChars.add ('m');
				 	    allowedChars.add ('n');
				 	    allowedChars.add ('o');
				 	    allowedChars.add ('p');
				 	    allowedChars.add ('r');
				 	    allowedChars.add ('s');
				 	    allowedChars.add ('t');
				 	    allowedChars.add ('u');
				 	    allowedChars.add ('v');
				 	    allowedChars.add ('y');
				 	    allowedChars.add ('\'');
				 	    allowedChars.add ('.');
				 	    allowedChars.add (',');
				 	    allowedChars.add (':');
				 	    allowedChars.add ('~');
				 	    allowedChars.add ('*');
				 	    allowedChars.add ('(');
				 	    allowedChars.add ('<');
				 	    allowedChars.add ('>');
				 	    allowedChars.add ('0');
				 	    allowedChars.add ('1');
				 	    allowedChars.add ('2');
				 	    allowedChars.add ('3');
				 	    allowedChars.add ('4');
				 	    allowedChars.add ('5');
				 	    allowedChars.add ('6');
				 	    allowedChars.add ('7');
				 	    allowedChars.add ('8');
				 	    allowedChars.add ('9');
				 	    allowedChars.add ('|');
				 	    allowedChars.add ('#');
				 	    allowedChars.add ('\"');
				 	    allowedChars.add ('{');
				 	    allowedChars.add ('}');
				 	    allowedChars.add ('[');
				 	    allowedChars.add (']');
				 	    allowedChars.add ('&');
				 	    allowedChars.add ('@');
				 	    allowedChars.add ('\n');
				 	    allowedChars.add ('$');
				 	    allowedChars.add ('^');
				 	    allowedChars.add ('-');
				 	    allowedChars.add ('_');
				 	    allowedChars.add ('?');
				 	    allowedChars.add (' ');

                        notAllowedInitChars.add ('.');
				 	    notAllowedInitChars.add (':');
				 	    notAllowedInitChars.add ('~');
				 	    notAllowedInitChars.add ('*');
				 	    notAllowedInitChars.add ('(');
				 	    notAllowedInitChars.add ('<');
				 	    notAllowedInitChars.add ('>');

				 	    vowels.add ('a');
				 	    vowels.add ('A');
				 	    vowels.add ('i');
				 	    vowels.add ('I');
				 	    vowels.add ('u');
				 	    vowels.add ('U');
				 	    vowels.add ('R');
				 	    vowels.add ('L');
				 	    vowels.add ('e');
				 	    vowels.add ('E');
				 	    vowels.add ('o');
				 	    vowels.add ('O');

				 	    postVowels.add (':');
				 	    postVowels.add ('~');
				 	    postVowels.add ('*');
				 	    postVowels.add ('(');

				 	    consonants.add ('k'); //k
				 	    consonants.add ('Z'); //kh
				 	    consonants.add ('g'); //g
				 	    consonants.add ('Y'); //gh
				 	    consonants.add ('G'); //n<

				 	    consonants.add ('c'); //c
				 	    consonants.add ('X'); //ch
				 	    consonants.add ('j'); //j
				 	    consonants.add ('W'); //jh
				 	    consonants.add ('J'); //n>

				 	    consonants.add ('T'); //t.
				 	    consonants.add ('q'); //t.h
				 	    consonants.add ('D'); //d.
				 	    consonants.add ('w'); //d.h
				 	    consonants.add ('N'); //n.

				 	    consonants.add ('t'); //t
				 	    consonants.add ('V'); //th
				 	    consonants.add ('d'); //d
				 	    consonants.add ('Q'); //dh
				 	    consonants.add ('n'); //n

				 	    consonants.add ('p'); //p
				 	    consonants.add ('P'); //ph
				 	    consonants.add ('b'); //b
				 	    consonants.add ('B'); //bh
				 	    consonants.add ('m'); //m

				 	    consonants.add ('y'); //y
				 	    consonants.add ('r'); //r
				 	    consonants.add ('l'); //l
				 	    consonants.add ('v'); //v

				 	    consonants.add ('z'); //
				 	    consonants.add ('S');
				 	    consonants.add ('s');
				 	    consonants.add ('h');

				 	    postConsonants.add ('\'');
				 	    postConsonants.add ('.');
				 	    postConsonants.add ('>');
				 	    postConsonants.add ('<');

				 rashaabhyaamChars.add ('a');
				 rashaabhyaamChars.add ('A');
				 rashaabhyaamChars.add ('i');
				 rashaabhyaamChars.add ('I');
				 rashaabhyaamChars.add ('u');
				 rashaabhyaamChars.add ('U');
				 rashaabhyaamChars.add ('R');
				 rashaabhyaamChars.add ('L');
				 rashaabhyaamChars.add ('e');
				 rashaabhyaamChars.add ('E');
				 rashaabhyaamChars.add ('o');
				 rashaabhyaamChars.add ('O');
				 rashaabhyaamChars.add ('h');
				 rashaabhyaamChars.add ('y');
				 rashaabhyaamChars.add ('v');
				 rashaabhyaamChars.add ('r');
				 rashaabhyaamChars.add ('k');
				 rashaabhyaamChars.add ('Z');
				 rashaabhyaamChars.add ('g');
				 rashaabhyaamChars.add ('Y');
				 rashaabhyaamChars.add ('G');
				 rashaabhyaamChars.add ('p');
				 rashaabhyaamChars.add ('P');
				 rashaabhyaamChars.add ('b');
				 rashaabhyaamChars.add ('B');
				 rashaabhyaamChars.add ('m');
				 rashaabhyaamChars.add ('*');
				 rashaabhyaamChars.add ('~');
		  }
    public void lineConvert (String input)
          {
	         String [] wordsArray;
	         wordsArray = input.split ("\\s+");
	         for (int i = 0; i < wordsArray.length; i++)
	            {
		            wordConvert (wordsArray[i]);
		            if (foundError == false)
		              {
				        nagariLine += nagari + " ";
				        diacriticsLine += diacritics + " ";
				        errorString = "";
					  }
		            else
		             {
				        nagariLine = diacriticsLine = errorString;
		             }
				 }
	       }
     public void flush()
           {
	         nagariLine = "";
	         diacriticsLine = "";
	         errorString = "";
            }
      }
