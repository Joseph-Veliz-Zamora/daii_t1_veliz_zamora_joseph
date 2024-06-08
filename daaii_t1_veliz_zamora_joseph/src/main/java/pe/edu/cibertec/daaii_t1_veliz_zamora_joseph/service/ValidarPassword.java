package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.service;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidarPassword {
    public boolean validarPassword(String password) {
        boolean tieneMinimoCaracteres = password.length() >= 8;
        boolean tieneLetraMayuscula = !password.equals(password.toLowerCase());
        boolean tieneLetraMinuscula = !password.equals(password.toUpperCase());
        boolean tieneNumero = password.matches(".*\\d.*");
        boolean tieneCaracterEspecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

        // Verifica los requsitos
        return tieneMinimoCaracteres &&
                tieneLetraMayuscula &&
                tieneLetraMinuscula &&
                tieneNumero &&
                tieneCaracterEspecial;
    }
}