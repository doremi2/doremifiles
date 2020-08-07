package bean;

public class ReviewBean {
	private String rcode;
	private String rid;
	private String rnickname;
	private String rdate;
	private String rdetail;
	private int rscore;
	private String mlname;
	
	
	public String getMlname() {
		return mlname;
	}
	public void setMlname(String mlname) {
		this.mlname = mlname;
	}
	public String getRnickname() {
		return rnickname;
	}
	public void setRnickname(String rnickname) {
		this.rnickname = rnickname;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getRdetail() {
		return rdetail;
	}
	public void setRdetail(String rdetail) {
		this.rdetail = rdetail;
	}
	public int getRscore() {
		return rscore;
	}
	public void setRscore(int rscore) {
		this.rscore = rscore;
	}
	
}
