import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class linuxwccmd{
   private static String COMMAND,FILE_PATH;
   public static void main(String args[]) throws IOException {
      String a="";
      Scanner sc = new Scanner(System.in);
      do{
      System.out.println("Enter the Command");
      System.out.print("$ ");
      COMMAND=sc.nextLine();
      String[] str=COMMAND.split(" ");
      if(str.length==2)
      {
      FILE_PATH=str[1];
      a=str[0];
      }
      else if(str.length==1)
      {
      a=str[0];
      }
      else if(str.length==3)
      {
      //COMMAND=sc.nextLine();
      //String[] str=COMMAND.split(" ");
      FILE_PATH=str[2];
      a=str[1];
      }
      if(a.compareTo("wc")==0)
      {
      FileUtil fileUtil = new FileUtil(FILE_PATH);
      System.out.print(" " + fileUtil.getLineCount());
      System.out.print(" " + fileUtil.getWordCount(FILE_PATH));
      System.out.print(" " + fileUtil.getCharCount(FILE_PATH));
      System.out.println(" " + FILE_PATH);
      }
      else if(a.compareTo("-m")==0) 
      { 
       FileUtil fileUtil = new FileUtil(FILE_PATH);
       System.out.print(" " + fileUtil.getCharCount(FILE_PATH));
       System.out.println(" " + FILE_PATH);
      } 
      else if(a.compareTo("-l")==0)
      {
       FileUtil fileUtil = new FileUtil(FILE_PATH);
       System.out.print(" " + fileUtil.getLineCount());
       System.out.println(" " + FILE_PATH);
      }
      else if(a.compareTo("-w")==0)
      {
       FileUtil fileUtil = new FileUtil(FILE_PATH);
       System.out.print(" " + fileUtil.getWordCount(FILE_PATH));
       System.out.println(" " + FILE_PATH);
      }
      else if(a.compareTo("-c")==0)
      {
       FileUtil fileUtil = new FileUtil(FILE_PATH);
       System.out.print(" " + fileUtil.getFileSize(FILE_PATH));
       System.out.println(" " + FILE_PATH);
      }
      else if(a.compareTo("-L")==0)
      {
       FileUtil fileUtil = new FileUtil(FILE_PATH);
       System.out.print(" " + fileUtil.getLongestLine(FILE_PATH));
       System.out.println(" " + FILE_PATH);
      }
      else if(a.compareTo("--help")==0)
      {
       System.out.println("Enter '-w' to get Word the Count");
       System.out.println("Enter '-m' to get Character Count");
       System.out.println("Enter '-l' to get Line Count");
       System.out.println("Enter '-L' to get the Length of the Longest Line");
       System.out.println("Enter '-c' to get the Filesize");
       break;
      }
      else
      {
       System.out.println("Enter The Command Properly");
      }
      }while((a.compareTo("wc")!=0) || (a.compareTo("-m")!=0) || (a.compareTo("-l")!=0) || (a.compareTo("-c")!=0) || (a.compareTo("--help")!=0));
   }
}

class FileUtil {
   private static final int MIN_VALUE = 0;
   static BufferedReader reader = null;    
   public FileUtil(String filePath) throws FileNotFoundException {
      File file = new File(filePath);
      FileInputStream fileStream = new FileInputStream(file);
      InputStreamReader input = new InputStreamReader(fileStream);
      reader = new BufferedReader(input);
   }
   public static int getLineCount() throws IOException {
      int lineCount = 0;
      String data=reader.readLine();        
      while(data!= null) {
         lineCount++;
         data=reader.readLine();
      }            
      return lineCount;
    }
    public static int getWordCount(String filePath) throws IOException {
      int wordCount = 0;
      File file = new File(filePath);
      FileInputStream fileStream = new FileInputStream(file);
      InputStreamReader input = new InputStreamReader(fileStream);
      reader = new BufferedReader(input);
      String data=reader.readLine();
      while(data != null){
         String[] words = data.split(" ");
         wordCount = wordCount + words.length;
         data=reader.readLine();
      }
      return wordCount;
    }
    public static int getCharCount(String filePath) throws IOException {
      int charCount = 0;
      File file = new File(filePath);
      FileInputStream fileStream = new FileInputStream(file);
      InputStreamReader input = new InputStreamReader(fileStream);
      reader = new BufferedReader(input);
      String data=reader.readLine();      
      while(data!= null) {
      String words[] = data.split(" ");
      for(String word : words)
      {
      charCount=charCount+word.length();
      }
      data=reader.readLine();
      }            
      return charCount;
   }
   public static int getFileSize(String filePath) throws IOException {
      Path path = Paths.get(filePath);
      long ByteCount = Files.size(path);
      return (int) ByteCount;
      }
    public static int getLongestLine(String filePath) throws IOException {
	      int length=0,max=MIN_VALUE;
	      File file = new File(filePath);
	      FileInputStream fileStream = new FileInputStream(file);
	      InputStreamReader input = new InputStreamReader(fileStream);
	      reader = new BufferedReader(input);
	      String data=reader.readLine();
	      while(data!=null) {
	    	  if(data.length()>max)
	    	  max=data.length();
	    	  data=reader.readLine();
	      }
	      length=max;
		return length; 
   }
}