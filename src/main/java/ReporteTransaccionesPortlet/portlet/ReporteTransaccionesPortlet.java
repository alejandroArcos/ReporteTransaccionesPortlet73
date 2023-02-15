package ReporteTransaccionesPortlet.portlet;

import ReporteTransaccionesPortlet.constants.ReporteTransaccionesPortletKeys;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import com.tokio.cotizador.CotizadorService;
import com.tokio.cotizador.Bean.PagosCatalogoResponse;
import com.tokio.cotizador.constants.CotizadorServiceKey;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author alejandroarcos
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ReporteTransaccionesPortlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ReporteTransaccionesPortletKeys.ReporteTransacciones,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.private-request-attributes=false" 
	},
	service = Portlet.class
)
public class ReporteTransaccionesPortlet extends MVCPortlet {
	/******************************/
	DateFormat dateFormat = null;
	Calendar cal=null;
	Date today = null;
	Date nextYear = null;
	/** Declaración el ArrayList **/
	/******************************/
	
	@Reference
	CotizadorService _CotizadorService; 
	
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
				throws PortletException, IOException{
		
		String p_usuario = null;
		String p_pantalla = ReporteTransaccionesPortletKeys.PANTALLA;
		
		try{
			
			User user = (User) renderRequest.getAttribute(WebKeys.USER);
			p_usuario = user.getScreenName();
			
			PagosCatalogoResponse listaMoneda = fGetCatalogo(CotizadorServiceKey.LISTA_MONEDA, 1, p_usuario, p_pantalla, p_pantalla);
			PagosCatalogoResponse listaEstatus = fGetCatalogo(CotizadorServiceKey.CAT_EST_PAG, 1, p_usuario, p_pantalla, p_pantalla);
			PagosCatalogoResponse listaTipPago = fGetCatalogo(CotizadorServiceKey.CAT_TIP_MOV, 1, p_usuario, p_pantalla, p_pantalla);
			
			System.out.println("listaEstatus: ");
			System.out.println(listaEstatus);
			
			renderRequest.setAttribute("listaMoneda", listaMoneda.getCatalogo());
			renderRequest.setAttribute("listaEstatus", listaEstatus.getCatalogo());
			renderRequest.setAttribute("listaTipPago", listaTipPago.getCatalogo());
			
			super.render(renderRequest, renderResponse);
		}
		catch(Exception e){
			SessionErrors.add(renderRequest, "errorLogueo" );
			SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		}
	}
	
	
	
	
	
	private PagosCatalogoResponse fGetCatalogo(String codigo, int activo, String usuario, String pantalla, String modulo) {
		try {
			return _CotizadorService.wsPagosCatalogo(codigo, activo, usuario, pantalla, modulo);
			/*return null;*/
		} catch (Exception e) {
			return null;
		}
	}
	
	

}