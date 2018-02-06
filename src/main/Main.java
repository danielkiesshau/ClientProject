package main;

import java.sql.Connection;



import connection.ConnectionDB;

public class Main {

	public static void main(String[] args) {
		Connection c = ConnectionDB.getConnection();
		
	}

}
