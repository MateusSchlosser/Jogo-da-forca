import java.util.*;

public class JogoDaForca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String[] palavrasSecretas = {
            "programacao", "internet", "tecnologia", "algoritmo", "computador", "software",
            "hardware", "javascript", "java", "python", "ciencia", "dados", "inteligencia",
            "rede", "criptografia"
        };
        final String tema = "Tecnologia";

        Random random = new Random();
        final String palavraSecreta = palavrasSecretas[random.nextInt(palavrasSecretas.length)];

        int tentativasRestantes = 6;
        Set<Character> letrasUsadas = new HashSet<>();
        Set<Character> letrasAcertadas = new HashSet<>();

        for (char c : palavraSecreta.toCharArray()) {
            if (Character.isLetter(c)) {
                letrasAcertadas.add(c);
            }
        }

        System.out.println(">>> BEM-VINDO AO JOGO DA FORCA! <<<");
        System.out.println("Tema: " + tema);
        System.out.println("A palavra tem " + palavraSecreta.length() + " letras.");
        System.out.println();

        while (tentativasRestantes > 0) {
            System.out.println("Palavra: " + getPalavraOculta(palavraSecreta, letrasUsadas));
            System.out.println("Tentativas restantes: " + tentativasRestantes);
            System.out.println("Letras usadas: " + letrasUsadas);
            System.out.print("Digite uma letra: ");

            char tentativa = scanner.next().toLowerCase().charAt(0);

            if (letrasUsadas.contains(tentativa)) {
                System.out.println("Você já usou essa letra. Tente outra.");
                continue;
            }

            letrasUsadas.add(tentativa);

            if (palavraSecreta.contains(String.valueOf(tentativa))) {
                System.out.println("Você acertou uma letra!");
            } else {
                tentativasRestantes--;
                System.out.println("Letra errada.");
                mostrarForca(6 - tentativasRestantes);
            }
            
            if (todasLetrasDescobertas(palavraSecreta, letrasUsadas)) {
                System.out.println("\nParabéns! Você descobriu a palavra: " + palavraSecreta);
                break;
            }

            System.out.println();
        }

        if (tentativasRestantes == 0) {
            System.out.println("\nVocê perdeu! A palavra era: " + palavraSecreta);
        }

        System.out.println("* Fim do Jogo *");
        scanner.close();
    }

    private static String getPalavraOculta(String palavraSecreta, Set<Character> letrasUsadas) {
        StringBuilder resultado = new StringBuilder();
        for (char c : palavraSecreta.toCharArray()) {
            if (letrasUsadas.contains(c)) {
                resultado.append(c).append(" ");
            } else {
                resultado.append("_ ");
            }
        }
        return resultado.toString().trim();
    }

    private static boolean todasLetrasDescobertas(String palavraSecreta, Set<Character> letrasUsadas) {
        for (char c : palavraSecreta.toCharArray()) {
            if (Character.isLetter(c) && !letrasUsadas.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private static void mostrarForca(int erros) {
        switch (erros) {
            case 1:
                System.out.println("   O");
                break;
            case 2:
                System.out.println("   O");
                System.out.println("   |");
                break;
            case 3:
                System.out.println("   O");
                System.out.println("  /|");
                break;
            case 4:
                System.out.println("   O");
                System.out.println("  /|\\");
                break;
            case 5:
                System.out.println("   O");
                System.out.println("  /|\\");
                System.out.println("  /");
                break;
            case 6:
                System.out.println("   O");
                System.out.println("  /|\\");
                System.out.println("  / \\");
                System.out.println("Você foi enforcado!");
                break;
            default:
                System.out.println("Nenhum erro ainda.");
        }
    }
}
