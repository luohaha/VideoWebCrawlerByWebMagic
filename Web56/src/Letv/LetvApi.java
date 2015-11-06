package Letv;
/*
 * 存储乐视的api url
 * */
public class LetvApi {
	private String api = 
			"http://api.my.letv.com/vcm/api/list?jsonp=jQuery&cid=2&type=video";
	private static String regex = "http://api.my.letv.com/vcm/api/list";
	private int rows;
	private int page;
	private int xid;
	public LetvApi() {
		this.rows = 20;
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
		return this.api+"&rows="+String.valueOf(this.rows)+"&page="
				+String.valueOf(this.page)+"&xid="+String.valueOf(this.xid);
	}
}
