package client;
import exceptions.numberInvalid;


public class Client {
	private String cpf_cnpj;
	private String nm_customer;
	private int is_active;
	private double vl_total;

	public Client(String cpf_cnpj, String nm_customer, int is_active, double vl_total) {
		this.cpf_cnpj = cpf_cnpj;
		this.nm_customer = nm_customer;
		this.is_active = is_active;
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

	public void setIs_active(int is_active) {
		try {
			if (is_active == 1 || is_active == 0) {
				this.is_active = is_active;
			} else {
				throw new numberInvalid();
			}
		} catch (numberInvalid e) {
			e.toString("The value for the active client can only be one or zero!");
		}
	}

	public double getVl_total() {
		return vl_total;
	}

	public void setVl_total(double vl_total) {
		this.vl_total = vl_total;
	}

}
