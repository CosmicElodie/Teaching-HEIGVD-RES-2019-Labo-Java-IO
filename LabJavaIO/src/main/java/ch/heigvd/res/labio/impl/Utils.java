package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines)
  {
    String separators[] = {"\r\n", "\r", "\n"}, res[] = {"", ""}, separator = "";
    int idx = separators.length, idxSep = lines.length(), i = 0, check[] = new int[idx];


    //On recherche les 3 séparateurs et leur index.
    while(i < idx)
    {
      check[i] = lines.indexOf(separators[i]);

      if ((idxSep > check[i]) && (check[i] != -1 ))
      {
        separator = separators[i];
        idxSep = check[i];
      }
      ++i;
    }

    int resInt = idxSep + separator.length();


    //Le 1er élément est la ligne + son séparateur
    //Le 2nd élément est le reste du texte.
    if (!separator.isEmpty()) {
      res[0] = lines.substring(0, resInt);
      res[1] = lines.substring(resInt);
    }
    else
    //Si l'argument ne contient pas de séparateur, alors le premier élément est une string vide.
    {
      res[1] = lines;
    }

    return res;
  }
}