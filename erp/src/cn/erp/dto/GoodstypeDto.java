package cn.erp.dto;

public class GoodstypeDto {

	private Long uuid;//商品种类id
	private String name;//商品种类name
	private String gysname;//供应商name
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGysname() {
		return gysname;
	}
	public void setGysname(String gysname) {
		this.gysname = gysname;
	}
	@Override
	public String toString() {
		return "GoodstypeDto [uuid=" + uuid + ", name=" + name + ", gysname="
				+ gysname + "]";
	}
	
	
	
}
