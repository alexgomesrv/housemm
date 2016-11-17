package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Usuario;
import com.unopar.entrega.repository.Usuarios;

@FacesConverter(forClass = Usuario.class)
public class UsuariosConverter implements Converter {
	@Inject
	private Usuarios usuarios;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.usuarios.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getIdUsuario() == 0 ? null : String.valueOf(((Usuario) value).getIdUsuario());
		}
		return null;
	}
}
