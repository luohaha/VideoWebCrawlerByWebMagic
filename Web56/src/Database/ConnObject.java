package Database;

public class ConnObject {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/samp_db";
    private String username = "root";
    private String password = "";
    public void setUrl(String urls) {
    	this.url = urls;
    }
    public void setUsername(String name) {
    	this.username = name;
    }
    public void setPassword(String ps) {
    	this.password = ps;
    }
}
