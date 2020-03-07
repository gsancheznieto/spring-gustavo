package com.spring.legacy;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.spring.bean.Empresa;
import com.spring.bean.Persona;
import com.spring.bean.PersonaTarea;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements ServletContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private ServletContext sc;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.sc=servletContext;
		
	}
	
	@RequestMapping(value="/ronnie",method=RequestMethod.GET)
	@ResponseBody
	public String alguienSeLaCome(Model model)
	{
		XmlWebApplicationContext ac=new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		Persona p=ac.getBean(Persona.class);
		return p.getNombre()+" se la come";
		
	}
	
	@RequestMapping(value="/hector",method=RequestMethod.GET)
	@ResponseBody
	public String alguienSeLaCome2(Model model)
	{
		XmlWebApplicationContext ac=new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		//Persona p=(Persona) ac.getBean("hector");
		Persona p=ac.getBean("hector",Persona.class);
		return p.getNombre()+" se la come";
		
	}
	
	@RequestMapping(value="/empresa",method=RequestMethod.GET)
	@ResponseBody
	public String empresa(Model model)
	{
		XmlWebApplicationContext ac=new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		Empresa e=ac.getBean("empresa",Empresa.class);
		List<Persona> listper=e.getEmpleados();
		String listaMostrar="";
		for(Persona p:listper)
		{
			listaMostrar+="nombre: "+p.getNombre()+" edad: "+p.getEdad()+" ";
		}
		return "El nombre de la empresa es : "+e.getNombre()+" y su ceo es "+e.getCeo().getNombre()+" y su edad es "+e.getCeo().getEdad()+" y l lista de empleados es "+listaMostrar;
		
	}
	
	@RequestMapping(value="/tareaBeans",method=RequestMethod.GET)
	@ResponseBody
	public String tareaBeans(Model model)
	{
		XmlWebApplicationContext ac=new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		PersonaTarea e=ac.getBean("emmanuelg",PersonaTarea.class);
		
		Collections.sort(e.getSubordinados(), new Comparator<PersonaTarea>(){
			@Override
			public int compare(PersonaTarea o1, PersonaTarea o2) {
				return String.valueOf(o2.getEdad()).compareTo(String.valueOf(o1.getEdad()));
			}
			
		});
		
		String response="";
		response+="<html>";
		response+="<body>";
		response+="<div class=\"gradient-real\" style=\"background-image: linear-gradient(rgb(240, 47, 23) 0%, rgb(240, 47, 23) 4%, rgb(247, 130, 52) 23%, rgb(245, 206, 12) 42%, rgb(60, 240, 24) 64%, rgb(93, 113, 240) 84%, rgb(182, 39, 230) 100%); width: 100%; height: 100%;\">";
		response+="Nombre: "+e.getNombre()+"<br>";
		response+="Sueldo Diario: "+e.getSueldoDiario()+"<br>";
		response+="Edad: "+e.getEdad()+"<br>";
		response+="Sexo: "+e.getSexo()+"<br>";
		response+="<br>";
		response+="Subordinados"+"<br>";
		response+="<br>";
		for(PersonaTarea pt:e.getSubordinados())
		{
			response+=pt.getNombre()+"<br>";
			response+=pt.getEdad()+"<br>";
			response+=pt.getSexo()+"<br>";
			response+=pt.getSueldoDiario()+"<br>";
			response+="<br>";
		}
		response+="</div>";
		response+="</body>";
		response+="</html>";
		//return "Nombre: "+e.getNombre()+" Sueldo Diario: "+e.getSueldoDiario()+" Edad: "+e.getEdad()+" Sexo: "+e.getSexo()+" Subordinados: ";
		return response;
	}
	
}
