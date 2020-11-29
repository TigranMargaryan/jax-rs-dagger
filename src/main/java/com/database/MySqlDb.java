package com.database;

import com.Resource.CountryResource;
import com.data.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySqlDb {

    private String userName = "root";
    private String password = "root";

    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String dbAddress = "jdbc:mysql://localhost:3306/";
    private String userPass = "?user=root&password=root";
    private String dbName = "Countries";


    private Statement statement;
    private Connection con;

    public MySqlDb(){
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            createDatabase();
            createTableCub1();
        }
    }

    private void createDatabase() {
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + userPass);
            Statement s = con.createStatement();
            int myResult = s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private List createCountryList()
    {
        List<Country> listOfCountries = new ArrayList();

        Country indiaCountry=new Country(1, "India");
        Country chinaCountry=new Country(4, "China");
        Country nepalCountry=new Country(3, "Nepal");
        Country bhutanCountry=new Country(2, "Bhutan");

        listOfCountries.add(indiaCountry);
        listOfCountries.add(chinaCountry);
        listOfCountries.add(nepalCountry);
        listOfCountries.add(bhutanCountry);
        return listOfCountries;
    }

    private void createTableCub1() {

        List<Country> countries = new ArrayList<>();

        String myTableName = "CREATE TABLE countries ("
                + "id INT(5) NOT NULL,"
                + "country_name VARCHAR(50),"
                + "PRIMARY KEY (`id`))";
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
            statement = con.createStatement();
            statement.executeUpdate(myTableName);

            countries = createCountryList();

            con.setAutoCommit(false);
            PreparedStatement prepStmt = con.prepareStatement(
                    "insert into countries(id,country_name) values (?,?)");
            Iterator<Country> it = countries.iterator();
            while (it.hasNext()) {
                Country p = it.next();
                prepStmt.setInt(1, p.getId());
                prepStmt.setString(2, p.getCountryName());
                prepStmt.addBatch();
            }
            int [] numUpdates=prepStmt.executeBatch();
            for (int i=0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
            con.commit();
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
        catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
    }

    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();

        try {
            ResultSet rs = null;
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
            statement = con.createStatement();

            con.setAutoCommit(false);

            rs = statement.executeQuery("SELECT * FROM Countries.countries;");

            while (rs.next()) {

                Country count = new Country(rs.getInt("id"), rs.getString("country_name"));
                countries.add(count);
            }
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public void addData(CountryResource country){

        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
            statement = con.createStatement();

            con.setAutoCommit(false);

            PreparedStatement prepStmt = con.prepareStatement(
                    "insert into countries(id,country_name) values (?,?)");

            prepStmt.setInt(1, country.getId());
            prepStmt.setString(2, country.getCountryName());

            prepStmt.executeUpdate();

            con.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(CountryResource country, int id){

        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
            statement = con.createStatement();

            con.setAutoCommit(false);

            String sqlUpdate = "UPDATE countries "
                    + "SET country_name = ? "
                    + "WHERE id = " + id;

            PreparedStatement prepStmt = con.prepareStatement(sqlUpdate);

            prepStmt.setString(1, country.getCountryName());

            prepStmt.executeUpdate();

            con.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(int id){

        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
            statement = con.createStatement();

            String sql = "DELETE FROM Countries.countries " +
                    "WHERE id = " + id;

            statement.executeUpdate(sql);

            con.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

      public Country getData(int ids){
          Country country = new Country(0, null);
        try
        {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(dbAddress + dbName, userName, password);

            String query = "SELECT * FROM Countries.countries WHERE id='" + Integer.parseInt(String.valueOf(ids)) + "'";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String city = rs.getString("country_name");

                country.setId(id);
                country.setCountryName(city);
            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        if (country.getCountryName() == null) {
            return null;
        }
        return country;
    }
}
