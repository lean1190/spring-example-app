package ar.edu.unlp.info.lifia.grupo4.controllers.validators;

import ar.edu.unlp.info.lifia.grupo1.dto.ProjectDTO;
import ar.edu.unlp.info.lifia.grupo1.dto.SectionDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class ProjectValidator implements Validator  {
       
    @Override
    public boolean supports(Class<?> clazz) {
        return ProjectDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object projectDTO, Errors errors) {              
        for (SectionDTO sectionDTO : ((ProjectDTO)projectDTO).getSections()) {                
          if ( "Dinero requerido".equals(sectionDTO.getName()) ){
              try{
                  Double amount = new Double(sectionDTO.getContent());                  
              }
              catch(NumberFormatException e){
                  errors.rejectValue("sections", "errorMessage", null, "El atributo: 'Dinero requerido' debe ser un numero");
              }
          }
          
          if ( "Nombre".equals(sectionDTO.getName()) ){
            if ( "".equals(sectionDTO.getContent()) ){
                errors.rejectValue("sections", "errorMessage", null, "El atributo: '" + sectionDTO.getName() + "' no puede estar vacio");
                }
          }

          if ( "Objetivo".equals(sectionDTO.getName()) ){
            if ( "".equals(sectionDTO.getContent()) ){
                errors.rejectValue("sections", "errorMessage", null, "El atributo: '" + sectionDTO.getName() + "' no puede estar vacio");
                }
          }
          
          if ( "Descripcion".equals(sectionDTO.getName()) ){
            if ( "".equals(sectionDTO.getContent()) ){
                errors.rejectValue("sections", "errorMessage", null, "El atributo: '" + sectionDTO.getName() + "' no puede estar vacio");
                }
          }                   
          
        }       
    }

    
}
