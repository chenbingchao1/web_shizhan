package cn.itcast.domain;

public class Category {
	private String cname;
	private String cid;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Category [cname=" + cname + ", cid=" + cid + "]";
	}

	public Category(String cname, String cid) {
		super();
		this.cname = cname;
		this.cid = cid;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}
}
