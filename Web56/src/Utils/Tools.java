package Utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class Tools {
	/*
	 * 清空文件的内容
	 * */
	 public static void clearInfoForFile(String fileName) {
	        File file =new File(fileName);
	        try {
	            if(!file.exists()) {
	                file.createNewFile();
	            }
	            FileWriter fileWriter =new FileWriter(file);
	            fileWriter.write("");
	            fileWriter.flush();
	            fileWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 /*
	  * 获取文件的长度
	  * */
	 public static int getFileLineSize(String filename) {
		 File file = new File(filename);
		 if (!file.exists())
			 return 0;
		 long fileLength = file.length();
		 LineNumberReader lnr = null;
		 int lines = 0;
		 try{
			 lnr = new LineNumberReader(new FileReader(file));
			 if (lnr != null) {
				 
				 lnr.skip(fileLength);
				 lines = lnr.getLineNumber();
				 lnr.close();
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return lines;
	 }
}
