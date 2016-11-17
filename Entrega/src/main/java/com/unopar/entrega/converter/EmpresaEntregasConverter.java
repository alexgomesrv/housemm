package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.EmpresaEntrega;
import com.unopar.entrega.repository.EmpresaEntregas;

@FacesConverter(forClass = EmpresaEntrega.class)
public class EmpresaEntregasConverter implements Converter {
	@Inject
	private EmpresaEntregas empresaEntregas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EmpresaEntrega retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.empresaEntregas.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			EmpresaEntrega empresaEntrega = (EmpresaEntrega) value;
			return empresaEntrega.getIdEmpresaEntrega() == 0 ? null
					: String.valueOf(((EmpresaEntrega) value).getIdEmpresaEntrega());
		}
		return null;
	}
}
