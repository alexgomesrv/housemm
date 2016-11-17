package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Empresa;
import com.unopar.entrega.repository.Empresas;

@FacesConverter(forClass = Empresa.class)
public class EmpresasConverter implements Converter {
	@Inject
	private Empresas empresas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Empresa retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.empresas.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Empresa empresa = (Empresa) value;
			return empresa.getIdEmpresa() == 0 ? null : String.valueOf(((Empresa) value).getIdEmpresa());
		}
		return null;
	}
}
