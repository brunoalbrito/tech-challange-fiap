package br.com.fiap.techchallenge.utils;

public class CpfValidator {
    public static boolean isValid(String cpf) {

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * (10 - i);
        }
        int remainder = sum % 11;
        int firstVerificationDigit = (remainder < 2) ? 0 : 11 - remainder;

        if (Character.getNumericValue(cpf.charAt(9)) != firstVerificationDigit) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * (11 - i);
        }
        remainder = sum % 11;
        int secondVerificationDigit = (remainder < 2) ? 0 : 11 - remainder;

        return Character.getNumericValue(cpf.charAt(10)) == secondVerificationDigit;
    }
}