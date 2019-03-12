package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter
{
  private int testCharAndCounter[] = {1, 0};
  private char pChar;

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out)
  {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException
  {
    int index = off, indexLimit = off + len;
    while(index < indexLimit)
    {
      write(str.charAt(index));
      index++;
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    int index = off, indexLimit = off + len;
    while(index < indexLimit)
    {
      write(cbuf[index]);
      ++index;
    }
   }

  @Override
  public void write(int c) throws IOException {
    char character = (char)c;
    String addT = "";

    if(c != '\n' && pChar == '\r')
    {
      testCharAndCounter[1]++;
      out.write(testCharAndCounter[1] + "\t");
    }

    if(testCharAndCounter[0] == 0)
    {
      out.write(c);
    }
    else
    {
      testCharAndCounter[0] = 0;
      testCharAndCounter[1]++;
      addT = testCharAndCounter[1] + "\t";
      out.write(addT + character);
    }

    if(c == '\n')
    {
      testCharAndCounter[1]++;
      addT = testCharAndCounter[1] + "\t";
      out.write(addT);
    }

    pChar = character;

  }

}
