package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.IObservacionService;
import com.bolsadeideas.springboot.app.models.service.IRolService;
import com.bolsadeideas.springboot.app.models.service.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class UsuariosController {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IRolService iRolService;

    @Autowired
    private ITareaService tareaService;


    @RequestMapping(value = "/administrador/usuarios")
    public String ver_usuarios(Model model){
        model.addAttribute("lista_users", usuarioDao.findAll());
        System.out.println(usuarioDao.findAll());
        return "administrador/usuarios";
    }

    @RequestMapping(value = "/administrador/editar_usuario/{id}")
    public String editar_usuario(@PathVariable(value = "id") Long id, RedirectAttributes flash, Model model){

        Usuario usuario = null;
        usuario = usuarioDao.getOne(id);


        if(usuario.getUsername().equals(null)){

            flash.addFlashAttribute("error", "El usuario no existe");
            return "redirect:/administrador/usuarios";

        }else{

            if (id > 0){

                model.addAttribute("usuario", usuario);
                return "administrador/editar_usuario";

            }else{

                flash.addFlashAttribute("error", "El usuario no existe");
                return "redirect:/administrador/usuarios";
            }
        }

    }

    @RequestMapping(value = "/administrador/guardar_usuario")
    public String guardarUser(@Valid Usuario usuario, BindingResult result, RedirectAttributes flash, SessionStatus status,
                              @RequestParam(name = "password") String newPasword,
                              @RequestParam(name = "roles", required = false) String roles,
                              @RequestParam(name = "idUsuario") String idUsuario){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(newPasword));

        //usuarioDao.save(usuario);
        usuarioDao.UpdatePassword(encoder.encode(newPasword),usuario.getIdUsuario());
        status.setComplete();
        flash.addFlashAttribute("success", "Usuario editado con exito");
        return "redirect:/administrador/usuarios";

    }


    @RequestMapping(value = "/administrador/form_user")
    public String crearNuevoUsuario(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "administrador/form_user";
    }

    @PostMapping(value = "/administrador/form_guardar_user")
    public String NuevoUser(@Valid Usuario usuario, BindingResult result, RedirectAttributes flash, SessionStatus status,
                              @RequestParam(name = "password") String newPasword){



        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(newPasword));

        usuarioDao.save(usuario);
        Long iduser = usuario.getIdUsuario();
        usuarioDao.InsertRol(iduser);
        status.setComplete();
        flash.addFlashAttribute("success", "¡Usuario " + usuario.getUsername() + " generado con exito!");
        return "redirect:/administrador/usuarios";

    }


  @RequestMapping(value = "/administrador/users/editar/{id}")
    public String eliminar_usuario(@PathVariable(value = "id") Long id, RedirectAttributes flash){

       Usuario usuario = null;
       usuario = usuarioDao.getOne(id);

       if(usuario == null){
           flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
           return "redirect:/administrador/usuarios";
       }

       boolean habilitado;

     if(usuario.getHabilitado() == true){
         habilitado = false;
         flash.addFlashAttribute("warning", "Usuario dado de baja");
     }else{
         habilitado = true;
         flash.addFlashAttribute("success", "Usuario habilitado");
     }


      usuarioDao.HabilitarDeshabilitarUser(habilitado,id);
      return "redirect:/administrador/usuarios";

  }



       /*

                para comparar las contraseñas
                BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
                enconder.matches(password de html, usuario.getPassword());

                */


    @RequestMapping(value = "/administrador/users/editar/password")
    public String updatePassword(@Valid Usuario usuario, BindingResult result, RedirectAttributes flash, SessionStatus status, @AuthenticationPrincipal User user,
                                  @RequestParam(name = "contrasena_actual") String contrasena_actual,
                                  @RequestParam(name = "nueva_contrasena") String nueva_contrasena){

        /* Verificar que la contraseña actual este ok */

        Usuario usuario_logueado = usuarioDao.findByUsername(user.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        /* Comparar contraseña actual con la que esta almacenada en la base de datos */

        boolean contrasenasSonIguales = encoder.matches(contrasena_actual, usuario_logueado.getPassword());

        System.out.println("el valor del bool es " + contrasenasSonIguales);

       if(!contrasenasSonIguales){
           flash.addFlashAttribute("error", "La contraseña actual con la ingresada no coincide");
           return "redirect:/administrador/admin";
       }


        /* update password */

        usuario.setPassword(encoder.encode(nueva_contrasena));

        usuarioDao.UpdatePassword(encoder.encode(nueva_contrasena),usuario_logueado.getIdUsuario());
        status.setComplete();
        flash.addFlashAttribute("success", "Contraseña editada con exito.");
        return "redirect:/administrador/admin";

    }


}
