package models;

import exceptions.ResponseInvalid;

public class Client implements Comparable {
	private int id_customer = 0;
	private String cpf_cnpj;
	private String nm_customer;
	private int is_active;
	private double vl_total = 0;

	public Client() {
		super();
	}

	public Client(int id_customer, String cpf_cnpj, String nm_customer, int is_active, double vl_total) {
		this.id_customer = id_customer;
		this.cpf_cnpj = cpf_cnpj;
		this.nm_customer = nm_customer;
		this.is_active = is_active;
		this.vl_total = vl_total;
	}

	public Client(int id_customer, String cpf_cnpj, String nm_customer, String is_active, double vl_total) {
		this.id_customer = id_customer;
		this.cpf_cnpj = cpf_cnpj;
		this.nm_customer = nm_customer;
		this.setIs_active(is_active);
		this.vl_total = vl_total;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getNm_customer() {
		return nm_customer;
	}

	public void setNm_customer(String nm_customer) {
		this.nm_customer = nm_customer;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		try {
			if (is_active.equalsIgnoreCase("yes")) {

				this.is_active = 1;

			} else if (is_active.equalsIgnoreCase("no")) {

				this.is_active = 0;

			} else {

				throw new ResponseInvalid();
			}
		} catch (ResponseInvalid e) {
			e.toString("The value informed is invalid, please type 'yes' or 'no'");
		}
	}

	public double getVl_total() {
		return vl_total;
	}

	public void setVl_total(double vl_total) {
		this.vl_total = vl_total;
	}

	public int getId_customer() {
		return id_customer;
	}

	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
	}

	// Method implemented by Comparable interface
	public int compareTo(Object oClient) {
		// Basically, everytime we return one, it'll sort base on the return 1 , -1 and
		// 0
		if (this.vl_total < ((Client) oClient).getVl_total()) {
			// When 1 is returned, the object this.Client should go after the oClient
			return 1;
		}
		if (this.getVl_total() > ((Client) oClient).getVl_total()) {
			return -1;
		}
		// If they'r equal, we return 0
		return 0;
	}

}
