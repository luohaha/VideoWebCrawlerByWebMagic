package Utils;
import java.util.HashMap;
import java.util.Map;

public class GlobalVar {
	public static Map<String, Double> count = new HashMap<String, Double>();
	public static int total = 0;
	public static int bad = 0;
	/*
	 * 标记urls.txt是否已经读过
	 * */
	public static boolean isFileReaded = false;
	/*
	 * 标记urlPlay.txt
	 * */
	public static boolean isFilePlayUrlsReaded = false;
	/*
	 * 标记commentUrl.txt
	 * */
	public static boolean isFileCommentUrlReaded = false;
	/*
	 * 爱妻一的电影名称和评论url对应 
	 * */
	public static HashMap<String, String>map = new HashMap<String, String>();
	
	/*
	 * letv 的抓取剩余电影个数
	 * */
	public static int TotalMovie = 0;
	public static int currentCatchMoive = 0;
	/*
	 * letv的标记
	 * */
	public static boolean LETV_MARK = false;
}
