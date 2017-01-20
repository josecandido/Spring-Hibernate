package com.jose.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jose.dao.PedidosDAO;
import com.jose.dao.ProductosDAO;
import com.jose.modelo.Producto;
import com.jose.modelo.Pedido;

@Controller
public class HomeController {

	@Autowired
	private ProductosDAO productosDao;
	
	@Autowired
	private PedidosDAO pedidosDao;
	
	@RequestMapping("/")
	public ModelAndView handleRequest() throws Exception {
		String mensaje="Solo del 25 al 27 de Noviembre";
		ModelAndView model = new ModelAndView("bienvenida");
		model.addObject("mensaje", mensaje);
		return model;
	}
	
	@RequestMapping("/inicio")
	public ModelAndView inicio() throws Exception {
		String mensaje="Solo del 25 al 27 de Noviembre";
		ModelAndView model = new ModelAndView("bienvenida");
		model.addObject("mensaje", mensaje);
		return model;
	}

	@RequestMapping("/listado")
	public ModelAndView verListado() throws Exception {
		List<Producto> listProductos = productosDao.list();
		ModelAndView model = new ModelAndView("ProductosList");
		model.addObject("productosList", listProductos); //nombre items foreach en AsignaturasList.jsp
		return model;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newProducto() {
		ModelAndView model = new ModelAndView("ProductosForm");
		model.addObject("producto", new Producto());
		return model;		
	}
	
	@RequestMapping(value = "/compra", method = RequestMethod.GET)
	public ModelAndView newCompra() {
		ModelAndView model = new ModelAndView("PedidosForm");
		model.addObject("pedido", new Pedido());
		return model;		
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView savePedido(@ModelAttribute Pedido pedido) {
		pedidosDao.saveOrUpdate(pedido);
		return new ModelAndView("redirect:/confirmacion");			
	}
	
	@RequestMapping("/confirmacion")
	public ModelAndView confirmar() throws Exception {
		ModelAndView model = new ModelAndView("confirmacion");
		return model;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editProducto(HttpServletRequest request) {
		int productoId = Integer.parseInt(request.getParameter("id"));
		Producto producto= productosDao.get(productoId);
		ModelAndView model = new ModelAndView("ProductosForm");
		model.addObject("producto", producto);
		return model;		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteProducto(HttpServletRequest request) {
		int productoId = Integer.parseInt(request.getParameter("id"));
		productosDao.delete(productoId);
		return new ModelAndView("redirect:/listado");		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveProducto(@ModelAttribute Producto producto) {
		productosDao.saveOrUpdate(producto);
		return new ModelAndView("redirect:/listado");
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
       }
       return "bienvenida";
    }
	
	 @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	    public String accessDeniedPage(ModelMap model) {
	        model.addAttribute("user", getPrincipal());
	        return "accessDenied";
	    }
	 
	 private String getPrincipal(){
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	        } else {
	            userName = principal.toString();
	        }
	        return userName;
	    }
	
}
