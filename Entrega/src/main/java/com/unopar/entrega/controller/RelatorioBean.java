package com.unopar.entrega.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.unopar.entrega.model.Pedido;
import com.unopar.entrega.repository.Pedidos;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Named
@RequestScoped
public class RelatorioBean {
	private Date dataInicial;
	private Date dataFinal;
	private String status;
	private BigDecimal valorTotal;
	@Inject
	private Pedidos pedidosRepository;
	private List<Pedido> pedidos;

	public void preparar() {
		this.dataInicial = new Date();
		this.dataFinal = new Date();
		this.status = "";
		this.consultar();		
	}
	
	public void consultar() {
		this.pedidos = pedidosRepository.consultar(getStatus(), getDataInicial(), getDataFinal());
		this.valorTotal = BigDecimal.ZERO;
		if	(this.pedidos != null && !this.pedidos.isEmpty()) {
			for (Pedido p : this.pedidos) {
				this.valorTotal = this.valorTotal.add(p.getValorTotalPedido());
			}
		}
	}

	public StreamedContent imprimir() {
		HashMap params = new HashMap();
		params.put("dataInicial", getDataInicial());
		params.put("dataFinal", getDataFinal());
		params.put("status", getStatus());

		ReportFactory rf = new ReportFactory("entregasPeriodo.jasper", params);
		return new DefaultStreamedContent(rf.getReportStream(), "", "teste.pdf");
	}

	public void imprimir3() {
		try {
			HashMap params = new HashMap();
			params.put("dataInicial", getDataInicial());
			params.put("dataFinal", getDataFinal());
			params.put("status", getStatus());
			List l = new ArrayList();
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(l);

			String reportPath = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/WEB-INF/Report/entregasPeriodo.jasper");

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, params,
					beanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.setContentType("application / pdf");
			httpServletResponse.addHeader("Content-disposition",
					"inline; filename=Receipt_" + "asdf" + ".pdf");
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

			servletOutputStream.write(JasperExportManager.exportReportToPdf(jasperPrint));
			servletOutputStream.flush();
			servletOutputStream.close();
			FacesContext.getCurrentInstance().renderResponse();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void imprimir2() {
		HashMap params = new HashMap();
		params.put("dataInicial", getDataInicial());
		params.put("dataFinal", getDataFinal());
		params.put("status", getStatus());

		try {
			String driverName = "com.mysql.jdbc.Driver";

			Class.forName(driverName);

			// Configurando a nossa conexão com um banco de dados//

			String serverName = "localhost"; // caminho do servidor do BD

			String mydatabase = "unopar"; // nome do seu banco de dados

			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

			String username = "comercial"; // nome de um usuário de seu BD

			String password = "pgcadmin"; // sua senha de acesso

			Connection conexao = DriverManager.getConnection(url, username, password);

			FacesContext facesContext = FacesContext.getCurrentInstance();

			facesContext.responseComplete();

			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

			JasperPrint jasperPrint = JasperFillManager
					.fillReport(scontext.getRealPath("/WEB-INF/Report/entregasPeriodo.jasper"), params, conexao);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

			exporter.exportReport();

			byte[] bytes = baos.toByteArray();

			if (bytes != null && bytes.length > 0) {

				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

				response.setContentType("application/pdf");

				response.setHeader("Content-disposition", "inline; filename=\"relatorioPorData.pdf\"");

				response.setContentLength(bytes.length);

				ServletOutputStream outputStream = response.getOutputStream();

				outputStream.write(bytes, 0, bytes.length);

				outputStream.flush();

				outputStream.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
