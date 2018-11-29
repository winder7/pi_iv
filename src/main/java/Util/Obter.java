package Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Autor Winder Rezende
 * @Data 29/11/2018
 */
public class Obter {

    public static int Periodo(String data_Compra, String data_baixa) {
        int periodo = 0;
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data_Compra);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(data_baixa);
            
            //define datas
            Calendar dataInicio = Calendar.getInstance();
            dataInicio.setTime(date1);
            Calendar dataFinal = Calendar.getInstance();
            dataFinal.setTime(date2);

            //Regras para contagem de prazos
            int Adquirido = dataInicio.get(Calendar.DATE) <= 15 ? 0 : 1;
            int Vendido = dataFinal.get(Calendar.DATE) <= 15 ? 0 : 1;
            
            //calcula diferença
            periodo = (dataFinal.get(Calendar.YEAR) * 12 + dataFinal.get(Calendar.MONTH) + Vendido) - (dataInicio.get(Calendar.YEAR) * 12 + dataInicio.get(Calendar.MONTH) + Adquirido);
        } catch (Exception ex) {
            System.out.println("ERRO ao calcular período: " + ex);
        }
        return periodo;
    }

    public static double Taxa(double vidaUtil, int tempoUso, int turnosTrab) {

        double taxa = 0.0;
        try {
            tempoUso = tempoUso / 12;
            vidaUtil = (vidaUtil - tempoUso) < (vidaUtil / 2) ? (vidaUtil / 2) : vidaUtil - tempoUso;
            double fatoTurno = (turnosTrab == 2 ? 1.5 : turnosTrab == 3 ? 2.0 : 0);
            if (turnosTrab > 1) {
                taxa = ((100 / vidaUtil) * fatoTurno) / 100;
            } else {
                taxa = (100 / vidaUtil) / 100;
            }
        } catch (Exception ex) {
            System.out.println("ERRO ao calcular taxa: " + ex);
        }

        return taxa;
    }
    
    public static double GanhoPerda(double custoBaixa, double valorContabil) {
        return  custoBaixa - valorContabil;
    }

    public static double ValorContabil(double custoBem, double depreciacao) {
        return custoBem - depreciacao;
    }

    public static double Depreciacao(double custoBem, double valorResidual, double taxa, int periodo) {
        return ((custoBem - valorResidual) * taxa * periodo) / 12;
    }
}
