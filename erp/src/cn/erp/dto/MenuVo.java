package cn.erp.dto;

public class MenuVo {
	private Long uuid;

	private String name;

	private String url;

	private Long puuid;
	
	private String fathername;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPuuid() {
		return puuid;
	}

	public void setPuuid(Long puuid) {
		this.puuid = puuid;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	
	
}
