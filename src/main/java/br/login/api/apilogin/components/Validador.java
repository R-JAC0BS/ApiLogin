package br.login.api.apilogin.components;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
    // Expressão regular para validar e-mails
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean validaEmail(String email) {
        if (email == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean validaCPF(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        //Verifica se todos os dígitos são iguais (ex. "111.111.111-11")
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
       }

        try {
            int[] cpfArray = new int[11];
            for (int i = 0; i < 11; i++) {
                cpfArray[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            // Calcula o primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += cpfArray[i] * (10 - i);
            }
            int firstVerifier = 11 - (sum % 11);
            firstVerifier = (firstVerifier >= 10) ? 0 : firstVerifier;

            // Calcula o segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += cpfArray[i] * (11 - i);
            }
            int secondVerifier = 11 - (sum % 11);
            secondVerifier = (secondVerifier >= 10) ? 0 : secondVerifier;

            // Verifica se os dígitos verificadores são iguais aos calculados
            return (cpfArray[9] == firstVerifier && cpfArray[10] == secondVerifier);
        } catch (InputMismatchException e) {
            return false;
        }
    }

}