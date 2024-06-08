package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomPassword {

    public String generar(int longitud){
        String cadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < longitud; i++){
            int index = random.nextInt(cadena.length());
            password.append(cadena.charAt(index));
        }
        return password.toString();
    }
}