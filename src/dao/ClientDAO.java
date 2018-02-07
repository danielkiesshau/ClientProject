package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionDB;
import models.Client;

public class ClientDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs;

	public void insertRegisters(List<Client> clients) throws SQLException {
		String sql = "INSERT INTO tb_customer_account VALUES(?,?,?,?,?)";
		try {
			connection = ConnectionDB.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);

			for (Client client : clients) {
				preparedStatement.setInt(1, client.getId_customer());
				preparedStatement.setString(2, client.getCpf_cnpj());
				preparedStatement.setString(3, client.getNm_customer());
				preparedStatement.setInt(4, client.getIs_active());
				preparedStatement.setDouble(5, client.getVl_total());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
			System.out.println("The total average is = " + calcTotalAverage());
			System.out.println("\nRegisters added in tb_customer_account\n");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.rollback();
			throw new SQLException();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}

		}

	}

	public List selectRegisters() throws SQLException {
		String sql = "SELECT * FROM tb_customer_account WHERE vl_total> 560 AND id_customer BETWEEN 1500 AND 2700";
		String cpf_cnpj, nm_customer;
		int is_active, id_customer;
		double vl_total;
		List<Client> clients = new ArrayList<Client>();

		try {
			connection = ConnectionDB.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				// Get the result of the query SELECT
				id_customer = rs.getInt(1);
				cpf_cnpj = rs.getString(2);
				nm_customer = rs.getString(3);
				is_active = rs.getInt(4);
				vl_total = rs.getDouble(5);

				// Set the data in client object
				Client client = new Client(id_customer, cpf_cnpj, nm_customer, is_active, vl_total);
				clients.add(client);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}

		return clients;

	}

	public double calcTotalAverage() {
		double average = 0;
		double sum = 0;
		List<Client> clients = new ArrayList<Client>();

		try {
			// Get the registers in the database
			clients = selectRegisters();

			// Sums all the vl_total values of the clients registered
			for (Client client : clients) {
				sum += client.getVl_total();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return the final average
		return sum / clients.size();
	}

	public List orderClients() throws SQLException {
		List<Client> clients = new ArrayList<Client>();
		List<Client> orgClients = new ArrayList<Client>();
		double vl_totals[] = new double[clients.size()];
		double highest = 0;
		try {
			// Get the registers in the database
			clients = selectRegisters();
			// Decreasing order being applied
			Collections.sort(clients);
			
		} catch (

		SQLException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				connection.close();
			}
		}
		
		return clients;
	}

}
