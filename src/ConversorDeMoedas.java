import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConversorDeMoedas {
    private static final String API_KEY = "8c80885a57d32d8970804a51";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        Map<String, Double> currencies = new HashMap<>();

        try {
            String response = getApiResponse();
            JsonObject responseObject = JsonParser.parseString(response).getAsJsonObject();

            if (responseObject.get("result").getAsString().equals("success")) {
                JsonObject ratesObject = responseObject.getAsJsonObject("conversion_rates");

                for (String currency : ratesObject.keySet()) {
                    currencies.put(currency, ratesObject.get(currency).getAsDouble());
                }
                currencies.put("USD", 1.0);

            } else {
                System.out.println("Erro ao obter taxas de câmbio: " + responseObject.get("error").getAsString());
                continuar = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            continuar = false;
        }

        if (continuar) {
            System.out.println("**************************************************************************");
            System.out.println("Sejam bem vindos ao Conversor de Moedas Yas =)\n");

            while (continuar) {
                System.out.println("1) Dólar (USD) -> Real Brasileiro (BRL)");
                System.out.println("2) Dólar (USD) -> Peso Argentino (ARS)");
                System.out.println("3) Dólar (USD) -> Boliviano (BOB)");
                System.out.println("4) Dólar (USD) -> Peso Chileno (CLP)");
                System.out.println("5) Dólar (USD) -> Peso Colombiano (COP)");
                System.out.println("6) Real Brasileiro (BRL) -> Dólar (USD)");
                System.out.println("7) Real Brasileiro (BRL) -> Peso Argentino (ARS)");
                System.out.println("8) Real Brasileiro (BRL) -> Boliviano (BOB)");
                System.out.println("9) Real Brasileiro (BRL) -> Peso Chileno (CLP)");
                System.out.println("10) Real Brasileiro (BRL) -> Peso Colombiano (COP)");
                System.out.println("11) Sair\n");
                System.out.println("Escolha uma opção válida:");

                int opcao = scanner.nextInt();
                double valor;
                double resultado;

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o valor em dólar (USD) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor * currencies.get("BRL");
                        System.out.println("O valor " + valor + " USD corresponde a " + resultado + " BRL");
                        break;
                    case 2:
                        System.out.println("Digite o valor em dólar (USD) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor * currencies.get("ARS");
                        System.out.println("O valor " + valor + " USD corresponde a " + resultado + " ARS");
                        break;
                    case 3:
                        System.out.println("Digite o valor em dólar (USD) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor * currencies.get("BOB");
                        System.out.println("O valor " + valor + " USD corresponde a " + resultado + " BOB");
                        break;
                    case 4:
                        System.out.println("Digite o valor em dólar (USD) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor * currencies.get("CLP");
                        System.out.println("O valor " + valor + " USD corresponde a " + resultado + " CLP");
                        break;
                    case 5:
                        System.out.println("Digite o valor em dólar (USD) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor * currencies.get("COP");
                        System.out.println("O valor " + valor + " USD corresponde a " + resultado + " COP");
                        break;
                    case 6:
                        System.out.println("Digite o valor em real brasileiro (BRL) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor / currencies.get("BRL");
                        System.out.println("O valor " + valor + " BRL corresponde a " + resultado + " USD");
                        break;
                    case 7:
                        System.out.println("Digite o valor em real brasileiro (BRL) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor / currencies.get("ARS");
                        System.out.println("O valor " + valor + " BRL corresponde a " + resultado + " ARS");
                        break;
                    case 8:
                        System.out.println("Digite o valor em real brasileiro (BRL) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor / currencies.get("BOB");
                        System.out.println("O valor " + valor + " BRL corresponde a " + resultado + " BOB");
                        break;
                    case 9:
                        System.out.println("Digite o valor em real brasileiro (BRL) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor / currencies.get("CLP");
                        System.out.println("O valor " + valor + " BRL corresponde a " + resultado + " CLP");
                        break;
                    case 10:
                        System.out.println("Digite o valor em real brasileiro (BRL) que deseja converter:");
                        valor = scanner.nextDouble();
                        resultado = valor / currencies.get("COP");
                        System.out.println("O valor " + valor + " BRL corresponde a " + resultado + " COP");
                        break;
                    case 11:
                        continuar = false;
                        System.out.println("Obrigado por usar o Conversor de Moedas Yas. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            }
        }
        scanner.close();
    }

    private static String getApiResponse() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
}