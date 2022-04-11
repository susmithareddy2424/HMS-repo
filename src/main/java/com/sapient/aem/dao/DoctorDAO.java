package com.sapient.aem.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.aem.model.Doctor;

public interface DoctorDAO {
	  public abstract Doctor getDoctorById(Integer doctorId) throws SQLException;
	  public abstract List<Doctor> getAllDoctors() throws SQLException;
      public abstract Integer  addDoctor(Doctor doctor) throws SQLException;
      public abstract String updateDoctor(Doctor doctor) throws SQLException;
      public abstract String deleteDoctor(Integer doctorId) throws SQLException;
      public abstract List<Doctor> searchDoctor(String string) throws SQLException;
      public abstract Integer countOfDoctors() throws SQLException;
      public abstract Integer getDoctorIdByUserName(String userName) throws SQLException;
}
