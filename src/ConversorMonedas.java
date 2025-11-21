public class ConversorMonedas {

    private final ConsultaApi consultaApi = new ConsultaApi();

    public double convertir(String monedaOrigen, String monedaDestino, double monto) {
        double tasa = consultaApi.obtenerTasa(monedaOrigen, monedaDestino);
        return monto * tasa;
    }
}
