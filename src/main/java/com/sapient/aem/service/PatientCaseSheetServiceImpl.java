package com.sapient.aem.service;

import java.sql.SQLException;
import java.util.List;

import com.sapient.aem.dao.PatientCaseSheetDAO;
import com.sapient.aem.dao.PatientCaseSheetDaoImpl;
import com.sapient.aem.exception.PatientCaseSheetException;
import com.sapient.aem.model.PatientCaseSheet;

public class PatientCaseSheetServiceImpl implements PatientCaseSheetService {
private PatientCaseSheetDAO patientCaseSheetDAO = new PatientCaseSheetDaoImpl();
	
	@Override
	public PatientCaseSheet getPatientCaseSheetById(Integer patientCaseSheetId) throws PatientCaseSheetException {
		try {
			PatientCaseSheet patientCaseSheet= patientCaseSheetDAO.getPatientCaseSheetById(patientCaseSheetId);
			
			return patientCaseSheet; 
		}catch(SQLException e) {
			
			throw new PatientCaseSheetException(e.getMessage(),e);
		}
	}

	@Override
	public List<PatientCaseSheet> getPatientCasesheet() throws PatientCaseSheetException {
		try {
			List<PatientCaseSheet> patientCaseSheetList= patientCaseSheetDAO.getPatientCasesheet();
			return patientCaseSheetList; 
		}catch(SQLException e) {
			throw new PatientCaseSheetException(e.getMessage(),e);
		}
	}

	@Override
	public Integer addPatientCaseSheet(PatientCaseSheet patientCaseSheet) throws PatientCaseSheetException {
		try {
			Integer result= patientCaseSheetDAO.addPatientCaseSheet(patientCaseSheet);
			
			return result; 
		}catch(SQLException e) {
			
			throw new PatientCaseSheetException(e.getMessage(),e);
		}
	}
	@Override
	public String updatePatientCaseSheet(PatientCaseSheet patientCaseSheet) throws PatientCaseSheetException {
		try {
			String result= patientCaseSheetDAO.updatePatientCaseSheet(patientCaseSheet);
			return result; 
		}catch(SQLException e) {
			
			throw new PatientCaseSheetException(e.getMessage(),e);
		}
	}
	@Override
	public String deletePatientCaseSheet(Integer patientCaseSheetId) throws PatientCaseSheetException {
		try {
			String result= patientCaseSheetDAO.deletePatientCaseSheet(patientCaseSheetId);			
			return result; 
		}catch(SQLException e) {
			
			throw new PatientCaseSheetException(e.getMessage(),e);
		}
	}

	@Override
	public List<PatientCaseSheet> getPatientCaseSheetByDoctorId(Integer doctorId) throws PatientCaseSheetException {
		try {
			List<PatientCaseSheet> patientCaseSheet= patientCaseSheetDAO.getPatientCaseSheetByDoctorId(doctorId);
			
			return patientCaseSheet; 
		}catch(SQLException e) {
			
			throw new PatientCaseSheetException(e.getMessage(),e);
		}
	}

	@Override
	public List<PatientCaseSheet> getPatientCaseSheetByPatientCaseSheetId(Integer patientCaseSheetId) throws PatientCaseSheetException {
		try {
			List<PatientCaseSheet> patientCaseSheet= patientCaseSheetDAO.getPatientCaseSheetByDoctorId(patientCaseSheetId);
			
			return patientCaseSheet; 
		}catch(SQLException e) {
			
			throw new PatientCaseSheetException(e.getMessage(),e);
		}
	}
	
	
}
