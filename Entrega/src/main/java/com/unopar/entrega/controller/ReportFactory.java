package com.unopar.entrega.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportFactory {

	private String reportName;
	private Map<?, ?> params;

	public ReportFactory(String ReportName, Map<?, ?> params) {
		this.reportName = ReportName;
		this.params = params;
	}

	public ReportFactory(String ReportName) {
		this.reportName = ReportName;
	}

	public InputStream getReportStream() {

		InputStream input = null;

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			facesContext.responseComplete();

			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			System.out.println("/WEB-INF/Report/" + reportName);
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(getClass().getClassLoader().getResourceAsStream("/WEB-INF/Report/" + reportName));
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			JasperPrint print = JasperFillManager.fillReport(jasperReport, (Map<String, Object>) params);
			JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.exportReport();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (JRException ex) {
			Logger.getLogger(ReportFactory.class.getName()).log(Level.SEVERE, null, ex);
		}

		return input;

	}

}