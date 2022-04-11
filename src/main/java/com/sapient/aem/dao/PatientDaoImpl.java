package com.sapient.aem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sapient.aem.model.Patient;

public class PatientDaoImpl implements PatientDAO {

	@Override
	public Patient getPatientById(Integer PatientId) throws SQLException {
		String sql="select * from patient where patient_id= ?";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(QueryMapper.GET_PATIENT_BY_ID);
			preparedStatement.setInt(1, PatientId );
			ResultSet resultSet= preparedStatement.executeQuery();

			if(resultSet.next()) {
				Patient patient= new Patient();
				populatePatient(resultSet,patient);
				return patient;				
			}else {
				return null;
			}

		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}

	}
	private void populatePatient(ResultSet resultSet, Patient patient) throws SQLException{

		patient.setPatientId(resultSet.getInt("patient_id"));
		patient.setPatientName(resultSet.getString("patient_name"));
		patient.setGender(resultSet.getString("gender"));
		patient.setBirthdate(resultSet.getDate("birthdate").toLocalDate());		
		patient.setBloodGroup(resultSet.getString("blood_group"));
		patient.setMobile(resultSet.getLong("mobile"));
		patient.setAddress(resultSet.getString("address"));
		patient.setEmail(resultSet.getString("email"));
		patient.setUserName(resultSet.getString("user_name"));


	}

	@Override
	public List<Patient> getAllPatients() throws SQLException {
		String sql= "select * from patient";
		Connection connection=null;
		try {

			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			Statement statement= 
					connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			ResultSet resultSet= statement.executeQuery(QueryMapper.GET_ALL_PATIENTS);
			List<Patient> patientList=new ArrayList<>();			
			while(resultSet.next()) {
				Patient patient= new Patient();
				populatePatient(resultSet,patient);
				patientList.add(patient);				
			}

			return patientList;
		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
	}

	@Override
	public Integer addPatient(Patient patient) throws SQLException {
		String sql="insert into patient(patient_name,gender,birthdate,blood_group,mobile,address,email,user_name) values(?,?,?,?,?,?,?,?)";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(QueryMapper.ADD_PATIENT);

			preparedStatement.setString(1,patient.getPatientName());
			preparedStatement.setString(2,patient.getGender());
			preparedStatement.setDate(3,java.sql.Date.valueOf(patient.getBirthdate()));
			preparedStatement.setString(4,patient.getBloodGroup());
			preparedStatement.setLong(5,patient.getMobile());
			preparedStatement.setString(6,patient.getAddress());
			preparedStatement.setString(7,patient.getEmail());
			preparedStatement.setString(8,patient.getUserName());

			int n=preparedStatement.executeUpdate();

			if(n>0) {
				return this.getMaxId();
			}else {
				throw new SQLException("Unable to add record");
			}
		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
	}
	private Integer getMaxId()  throws SQLException{
		String sql="select max(patient_id) from patient";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			Statement statement= connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);

			if(resultSet.next()) {
				return resultSet.getInt(1);
			}else {
				throw new SQLException("No records in the table");
			}
		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
	}
	@Override
	public String deletePatient(Integer patientId) throws SQLException {
		String sql="delete from patient where patient_id=?";
		Connection connection=null;
		try{
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(QueryMapper.DELETE_PATIENT);

			preparedStatement.setInt(1, patientId);
			int n= preparedStatement.executeUpdate();
			if(n>0) {
				return "PatientId: " + patientId+ " deleted from database";
			}else {
				return "Unable to delete patientid: "+patientId;
			}

		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
	}
	@Override
	public String updatePatient(Patient patient) throws SQLException {
		String sql="update hms set mobile=?,address=?,email=? where patient_id=?";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(QueryMapper.UPDATE_PATIENT);


			preparedStatement.setLong(1, patient.getMobile());
			preparedStatement.setString(2, patient.getAddress());
			preparedStatement.setString(3, patient.getEmail());
			preparedStatement.setInt(4, patient.getPatientId());


			int n = preparedStatement.executeUpdate();
			if(n>0) {
				return "PatientId: "+ patient.getPatientId()+" updated";
			}else {
				return "Unable to update PatientId: "+ patient.getPatientId();
			}

		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}

	}
	@Override
	public Integer countOfPatients() throws SQLException {
		String sql="select count(patient_id) from doctor";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			Statement statement= connection.createStatement();
			ResultSet resultSet=statement.executeQuery(QueryMapper.COUNT_OF_PATIENTS);

			if(resultSet.next()) {
				return resultSet.getInt(1);
			}else {
				throw new SQLException("No records in the table");
			}
		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
	}
	@Override
	public Integer getPatientIdByUserName(String userName) throws SQLException {
		String sql="select patient_id from patient where user_name=?";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/hmsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.GET_PATIENT_ID_BY_USERNAME);
			preparedStatement.setString(1, userName);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}else {
				throw new SQLException("No records in the table");
			}
		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}



	}

}
