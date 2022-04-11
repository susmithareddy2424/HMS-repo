package com.sapient.aem.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapient.aem.model.Doctor;
import com.sapient.aem.model.Patient;
import com.sapient.aem.service.DoctorService;
import com.sapient.aem.service.DoctorServiceImpl;
import com.sapient.aem.service.PatientService;
import com.sapient.aem.service.PatientServiceImpl;

@WebServlet("/PatientDetaislServlet")
public class PatientDetaislServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static PatientService patientService = new PatientServiceImpl();
    private static DoctorService doctorService = new DoctorServiceImpl();	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			List<Patient> l = patientService.getAllPatients();
			request.setAttribute("patient", l);
			request.getRequestDispatcher("WEB-INF/views/patient-details.jsp").include(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
