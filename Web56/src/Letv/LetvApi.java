package Letv;
/*
 * 存储乐视的api url
 * */
public class LetvApi {
	private static final String API = 
			"http://api.my.letv.com/vcm/api/list?jsonp=jQuery&cid=2&type=video";
	private static final String ROWS = "&rows=";
	private static final String PAGE = "&page=";
	private static final String XID = "&xid=";
	private static String regex = "http://api.my.letv.com/vcm/api/list";
	public int rows;
	public int page;
	public int xid;
	public LetvApi() {
		this.rows = 0;
		this.page = 1;
	}
	public static LetvApi create() {
		return new LetvApi();
	}
	public LetvApi setRows(String row) {
		this.rows = Integer.valueOf(row);
		return this;
	}
	public LetvApi setRows(int row) {
		this.rows = row;
		return this;
	}
	public LetvApi setPage(String page) {
		this.page = Integer.valueOf(page);
		return this;
	}
	public LetvApi setPage(int page) {
		this.page = page;
		return this;
	}
	public LetvApi setXid(String xid) {
		this.xid = Integer.valueOf(xid);
		return this;
	}
	public LetvApi setXid(int xid) {
		this.xid = xid;
		return this;
	}
	public static String returnApiRegex() {
		return regex;
	}
	public String toString() {
		return this.API+this.ROWS+String.valueOf(this.rows)+this.PAGE
				+String.valueOf(this.page)+this.XID+String.valueOf(this.xid);
	}
	
	/*
	 * 将url转化为api
	 * */
	public static LetvApi getLetvApi(String url) {
		String rows = url.split(ROWS)[1].split("&")[0];
		String page = url.split(PAGE)[1].split("&")[0];
		String xid = url.split(XID)[1].split("&")[0];
		return LetvApi.create().setRows(rows).setPage(page).setXid(xid);
	}
	
	/*
	 * test unit
	 * */
	public static void main(String args[]) {
		LetvApi pai = LetvApi.getLetvApi("http://api.my.letv.com/vcm/api/list?jsonp=jQuery&cid=2&type=video&rows=0&page=1&xid=23910410");
		System.out.println(pai.xid);
	}
}
