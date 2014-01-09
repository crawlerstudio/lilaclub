package com.makerx.lilac.entity;

public class InitInfo {

	private String nfcid;
	private String codeid;
	private String sn;

	public String getNfcid() {
		return nfcid;
	}

	public void setNfcid(String nfcid) {
		this.nfcid = nfcid;
	}

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Override
	public String toString() {
		return "nfcid: " + nfcid + "\n" + "codeid " + codeid + "\n" + "sn "
				+ sn;
	}

}
