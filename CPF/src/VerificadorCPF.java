import java.util.Scanner;

public class VerificadorCPF {
    private String cpf;

    public void registrar(String cpfEntrada) {
        this.cpf = cpfEntrada.replaceAll("\\D", "");
    }

    public boolean checar() {
        if (cpf.length() != 11)
            return false;

        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        for (int i = 0; i < 10; i++) {
            if (numeros[i] != numeros[i + 1]) {
                break;
            }
            if (i == 9) {
                return false;
            }
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += numeros[i] * (10 - i);
        }
        int resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != numeros[9]) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += numeros[i] * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        return resto == numeros[10];
    }

    public String formatarCPF() {
        return(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        VerificadorCPF verificador = new VerificadorCPF();

        System.out.print("Insira o CPF: ");
        verificador.registrar(input.next());

        System.out.println("O CPF é válido? " + (verificador.checar() ? "Sim" : "Não"));
        System.out.println("CPF formatado: " + verificador.formatarCPF());
    }
}
