package com.uca.capas.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;

@Controller
public class MainController {
	
	private static Integer Codigo_valido = null;
	@Autowired
	private EstudianteDAO estudianteDAO;
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		//Iniciamos la lista de estudiantes
		List<Estudiante> estudiantes = null;
		try {
			// Nos vamos al metodo para buscar
			estudiantes = estudianteDAO.findAll();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		// añadimos los estudiantes al html
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
	// 	Inicializar los estudiantes
	@RequestMapping("/inicio")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();
		
		Estudiante estudiante = new Estudiante();
		
		mav.addObject("estudiante", estudiante);
		mav.setViewName("index");
		
		return mav;
	}
	// Obtenemos el estudiante del html
	@RequestMapping("/formEstudiante")
	public ModelAndView formProducto(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(!result.hasErrors()) {
			try {
				estudianteDAO.insert(estudiante);
			}catch (Exception e) {
				e.printStackTrace();
			}
			estudiante = new Estudiante();
			mav.addObject("estudiante", estudiante);
			
		}
		
		mav.setViewName("index");
		
		return mav;
		
	}
	@RequestMapping(value="/deleteEstudiante", method=RequestMethod.POST)
	public ModelAndView eliminarEstudiante(@RequestParam(value="codigo") int codigo) {
		if (String.valueOf(codigo)=="") {
			codigo=0;
		}
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			// prodando que el estudiante este en la lista
			estudiantes=estudianteDAO.findAll();
			int Codigo_Valido;
			int i=0;
			while(i<estudiantes.size()) {

				if (codigo == estudiantes.get(i).getCodigo() ) 
					{
						Codigo_valido = estudiantes.get(i).getCodigo();
						break;
					}
					else {
						Codigo_valido=0;
					};
				
				i++;
			}
			
//			estudiantes.forEach((i)->{
//				
//				if (codigo == i.getCodigo() ) 
//				{
//					Codigo_valido = i.getCodigo();
//					boolean primeravez=false;
//				}
//				else {
//					Codigo_valido=0;
//				};
//				System.out.println(i.getCodigo());
// 				System.out.println(codigo);
//			});
			estudianteDAO.delete(Codigo_valido);
			estudiantes = estudianteDAO.findAll();

		}catch (Exception e) {
			e.printStackTrace();
		
		}
		mav.addObject("estudiantes", estudiantes);


		mav.setViewName("listado");

		return mav;

	}

}