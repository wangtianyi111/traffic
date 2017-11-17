package cqupt.wty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public final class FileUtil
{
  public static String[] read(String filePath, Integer spec)
  {
    File file = new File(filePath);

    if ((!isFileExists(file)) || (!file.canRead()))
    {
      System.out.println("file [" + filePath + "] is not exist or cannot read!!!");
      return null;
    }

    List lines = new LinkedList();
    BufferedReader br = null;
    FileReader fb = null;
    try
    {
      fb = new FileReader(file);
      br = new BufferedReader(fb);

      String str = null;
      int index = 0;
      do
      {
        lines.add(str);

        if ((spec != null) && (index++ >= spec.intValue())) 
        	break;  
        } while ((str = br.readLine()) != null);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      closeQuietly(br);
      closeQuietly(fb);
    }

    return (String[])lines.toArray(new String[lines.size()]);
  }

  public static int write(String filePath, String[] contents, boolean append)
  {
    File file = new File(filePath);
    if (contents == null)
    {
      System.out.println("file [" + filePath + "] invalid!!!");
      return 0;
    }

    if ((isFileExists(file)) && (!file.canRead()))
    {
      return 0;
    }

    FileWriter fw = null;
    BufferedWriter bw = null;
    try
    {
      if (!isFileExists(file))
      {
        file.createNewFile();
      }

      fw = new FileWriter(file, append);
      bw = new BufferedWriter(fw);
      for (String content : contents)
      {
        if (content != null)
        {
          bw.write(content);
          bw.newLine();
        }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
    finally
    {
      closeQuietly(bw);
      closeQuietly(fw);
    }

    return 1;
  }

  private static void closeQuietly(Closeable closeable)
  {
    try
    {
      if (closeable != null)
      {
        closeable.close();
      }
    }
    catch (IOException localIOException)
    {
    }
  }

  private static boolean isFileExists(File file)
  {
    if ((file.exists()) && (file.isFile()))
    {
      return true;
    }

    return false;
  }
}