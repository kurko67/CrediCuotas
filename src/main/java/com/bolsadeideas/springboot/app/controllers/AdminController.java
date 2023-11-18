package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Observacion;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("observacion")
public class AdminController {


    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IObservacionService observacionService;

    @Autowired
    private IUsuarioDao usuarioDao;



    @RequestMapping("/administrador")
    public String index_admin(Model model, @AuthenticationPrincipal User user, RedirectAttributes flash) {

        model.addAttribute("user", user.getUsername());
        return "administrador/admin";

    }

    @RequestMapping("/administrador/estadisticas")
    public String estadisticas(Model model, @AuthenticationPrincipal User user) {

        /* Vendedores con mas creditos cerrados */

        List<Object[]> ventasPorMes = clienteService.obtenerVentasPorMes();
        List<String> vendedores = new ArrayList<>();
        List<BigInteger> cantidades = new ArrayList<>();

        for (Object[] cliente : ventasPorMes) {
            vendedores.add((String) cliente[0]);
            cantidades.add((BigInteger) cliente[1]);
        }

        model.addAttribute("vendedores", vendedores);
        model.addAttribute("cantidades", cantidades);

        /* fin Vendedores con mas creditos cerrados */


        /* Financiadores con mas creditos cerrados */

        List<Object[]> consulta = clienteService.obtenerFinanciadorLiquidacion();
        List<String> financiadores = new ArrayList<>();
        List<BigInteger> cantidades_financiadores = new ArrayList<>();

        for (Object[] cliente : consulta) {
            financiadores.add((String) cliente[0]);
            cantidades_financiadores.add((BigInteger) cliente[1]);
        }

        model.addAttribute("financiadores", financiadores);
        model.addAttribute("cantidades_financiadores", cantidades_financiadores);

        /* fin Financiadores con mas creditos cerrados */

        /* Top Provincias */

        List<Object[]> qr3 = clienteService.obtenerTopProvincias();
        List<String> provincias = new ArrayList<>();
        List<BigInteger> cantidades_provincias = new ArrayList<>();

        for (Object[] cliente : qr3) {
            provincias.add((String) cliente[0]);
            cantidades_provincias.add((BigInteger) cliente[1]);
        }

        model.addAttribute("provincias", provincias);
        model.addAttribute("cantidades_provincias", cantidades_provincias);

        /* fin top Provincias */

        /* Top SIT LABORAL */

        List<Object[]> qr4 = clienteService.obtenerTopSituacionLaboral();
        List<String> situacion_laboral = new ArrayList<>();
        List<BigInteger> cantidades_situacion_laboral = new ArrayList<>();

        for (Object[] cliente : qr4) {
            situacion_laboral.add((String) cliente[0]);
            cantidades_situacion_laboral.add((BigInteger) cliente[1]);
        }

        model.addAttribute("situacion_laboral", situacion_laboral);
        model.addAttribute("cantidades_situacion_laboral", cantidades_situacion_laboral);

        /* fin top SIT LABORAL  */

        /* Top SIT Dependencia */

        List<Object[]> qr5 = clienteService.obtenerTopDependencia();
        List<String> dependencia = new ArrayList<>();
        List<BigInteger> cantidades_dependencia = new ArrayList<>();

        for (Object[] cliente : qr5) {
            dependencia.add((String) cliente[0]);
            cantidades_dependencia.add((BigInteger) cliente[1]);
        }

        model.addAttribute("dependencia", dependencia);
        model.addAttribute("cantidades_dependencia", cantidades_dependencia);

        /* fin top Dependencia  */

        /* obtenerTopCanal */

        List<Object[]> qr6 = clienteService.obtenerTopCanal();
        List<String> canal = new ArrayList<>();
        List<BigInteger> cantidades_canal = new ArrayList<>();

        for (Object[] cliente : qr6) {
            canal.add((String) cliente[0]);
            cantidades_canal.add((BigInteger) cliente[1]);
        }

        model.addAttribute("canal", canal);
        model.addAttribute("cantidades_canal", cantidades_canal);

        /* fin  obtenerTopCanal  */

        LocalDate fechaActual = LocalDate.now();
        LocalDate primerDiaDelMes = fechaActual.withDayOfMonth(1); // Obtiene el primer día del mes actual

        model.addAttribute("user", user.getUsername());
        model.addAttribute("cantidad_clientes", clienteService.countClientes(primerDiaDelMes));
        model.addAttribute("cantidad_aprobado", clienteService.SumAprobado(primerDiaDelMes));
        model.addAttribute("cantidad_enProceso", clienteService.SolicitudesEnProceso(primerDiaDelMes));
        model.addAttribute("cantidad_Rechazadas", clienteService.SolicitudesRechazadas(primerDiaDelMes));
        model.addAttribute("sum_Liquidadas", clienteService.SolicitudesLiquidadas(primerDiaDelMes));
        model.addAttribute("cantidad_Liquidadas", clienteService.CantidadLiquidadas(primerDiaDelMes));


        return "administrador/estadisticas";
    }



    @RequestMapping("/administrador/tareas")
    public String tareas(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user.getUsername());
        return "administrador/tareas";
    }

    @GetMapping("administrador/observacion/{idcliente}")
    public String crear_observacion(@PathVariable(value = "idcliente") Long idcliente,Model model, RedirectAttributes flash){

        Cliente cliente = clienteService.findOne(idcliente);

        if(cliente == null){
            flash.addFlashAttribute("error", "El cliente ingresado no existe en la base de datos");
            return "redirect:/administrador/mis_solicitudes";
        }

        Observacion observacion = new Observacion();
        observacion.setCliente(cliente);

        model.addAttribute("observacion", observacion);

        return "administrador/observacion";

    }

    @PostMapping("administrador/observacion")
    public String guardarObservacion(@Valid Observacion observacion, BindingResult result,
                                     RedirectAttributes flash, SessionStatus status){

        if(result.hasErrors()){
            return "/administrador/observacion";
        }

        observacionService.save(observacion);
        status.setComplete();
        flash.addFlashAttribute("success", "Observacion creada con exito");
        return "redirect:/administrador/ver_mas/" + observacion.getCliente().getIdCliente();

    }

    @PostMapping("administrador/nuevo_usuario")
    public String NuevoUsuario(@Valid Usuario usuario, BindingResult result,
                               RedirectAttributes flash)
    {

        if(result.hasErrors()){
            return "redirect:/administrador/nuevo_usuario";
        }

        return "usuarios";
    }





}
