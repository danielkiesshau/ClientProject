package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionDB;
import dao.ClientDAO;
import models.Client;

public class Main {

	public static void main(String[] args) {
		ClientDAO dao = new ClientDAO();
		List<Client> clients = new ArrayList<Client>();
		String op = "yes";
		Client client;
		double average;

		try {

			// Loop to input all clients desired to insert into the database
			op = JOptionPane.showInputDialog("Do you wish to add more clients?");
			while (!op.equalsIgnoreCase("no")) {
				client = getInputs();
				clients.add(client);
				op = JOptionPane.showInputDialog("Do you wish to add more clients?");

			}
			
			dao.insertRegisters(clients);
			
			average = dao.calcTotalAverage();
			JOptionPane.showMessageDialog(null, "The average is = " + average);
			  
			System.out.print("\n-----The clients ordered by vl_total-----\n");
			clients = dao.orderClients();
			for (Client client2 : clients) {
				System.out.println("\n\nCustomer ID: " + client2.getId_customer() + "\t");
				System.out.println("\nCPF/CNPJ: " + client2.getCpf_cnpj() + "\t");
				System.out.println("\nName: " + client2.getNm_customer() + "\t");
				System.out.println("\nIsActive: " + client2.getIs_active() + "\t");
				System.out.println("\nVl_Total: " + client2.getVl_total() + "\t");
			 
			}
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private static Client getInputs() {
		String cpf_cnpj;
		String nm_customer;
		String is_active;
		int id_customer;
		double vl_total;

		// Read inputs for the Client Object
		id_customer = Integer.parseInt(JOptionPane.showInputDialog(null,"Type the id of the customer"));
		cpf_cnpj = JOptionPane.showInputDialog("Type the CPF or CNPJ of the client");
		nm_customer = JOptionPane.showInputDialog("Type the name of the client");
		is_active = JOptionPane.showInputDialog("Type if you want to declare the client active(yes) or not(no)");
		vl_total = Double.parseDouble(JOptionPane.showInputDialog("Type the total budget of the client"));
		// Set the data in client object
		Client client = new Client(id_customer, cpf_cnpj, nm_customer, is_active, vl_total);

		return client;
	}
}
