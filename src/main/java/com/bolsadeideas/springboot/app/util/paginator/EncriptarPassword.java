package com.bolsadeideas.springboot.app.util.paginator;
import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {



    public static void main(String[] args) {

        var password = "123";
        System.out.println("Pass sin encrip: " + password);
        System.out.println("Pass  encrip: " + encriptarPassword(password));
    }

    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }




}
