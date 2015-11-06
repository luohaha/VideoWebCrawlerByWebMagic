package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
