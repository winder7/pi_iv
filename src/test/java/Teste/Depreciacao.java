package Teste;

import Util.Formatar;
import Util.Obter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Autor Winder Rezende
 * @Data 29/11/2018
 */
public class Depreciacao {

    public static void main(String[] args) throws ParseException {

        String data_Compra = Formatar.Data("15/11/2013", "dd/MM/yyyy", "yyyy-MM-dd");
        String data_baixa = Formatar.Data("20/11/2015", "dd/MM/yyyy", "yyyy-MM-dd");
        double vida_util = 10;
        int tempo_uso = 72;
        int turno_trabalhado = 2;
        double custo_bem = 8000;
        double valor_residual = 800;
        double custo_baixa = 2000;

        int periodo = Obter.Periodo(data_Compra, data_baixa);
        double taxa = Obter.Taxa(vida_util, tempo_uso, turno_trabalhado);
        double depreciacao = Obter.Depreciacao(custo_bem, valor_residual, taxa, periodo);
        double valorContabil = Obter.ValorContabil(custo_bem, depreciacao);
        double ganhoPerda = Obter.GanhoPerda(custo_baixa, valorContabil);

        System.out.println("Período: " + periodo);
        System.out.println("Taxa:" + taxa);
        System.out.println("Depreciacao: " + depreciacao);
        System.out.println("Valor Contábil: " + valorContabil);
        System.out.println("Ganho ou Perda: " + ganhoPerda);
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
            System.out.println(ex);
        }

        return taxa;
    }

    public static int PeriodoMeses(String data_Compra, String data_baixa) {
        int periodo = 0;
        try {

            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data_Compra);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(data_baixa);

            //define datas
            Calendar dataIni = Calendar.getInstance();
            dataIni.setTime(date1);
            Calendar dataFinal = Calendar.getInstance();
            dataFinal.setTime(date2);
//            System.out.println(Formatar.data(dataIni.getTime(), "dd/MM/yyyy"));
//            System.out.println(Formatar.data(dataFinal.getTime(), "dd/MM/yyyy"));

            int Adquirido = dataIni.get(Calendar.DATE) <= 15 ? 0 : 1;

            int Vendido = dataFinal.get(Calendar.DATE) <= 15 ? 0 : 1;

//            System.out.print("Dia: " + dataIni.get(Calendar.DATE));
//            System.out.print("  Mês: " + (dataIni.get(Calendar.MONTH) + Adquirido));
//            System.out.println("  Ano: " + dataIni.get(Calendar.YEAR));
//            
//            System.out.print("Dia: " + dataFinal.get(Calendar.DATE));
//            System.out.print("  Mês: " + dataFinal.get(Calendar.MONTH));
//            System.out.println("  Ano: " + dataFinal.get(Calendar.YEAR));
//            
//            System.out.println(dataFinal.get(Calendar.YEAR) * 12 + dataFinal.get(Calendar.MONTH));
//            System.out.println(dataIni.get(Calendar.YEAR) * 12 + dataIni.get(Calendar.MONTH));
            //calcula diferença
            periodo = (dataFinal.get(Calendar.YEAR) * 12 + dataFinal.get(Calendar.MONTH) + Vendido) - (dataIni.get(Calendar.YEAR) * 12 + dataIni.get(Calendar.MONTH) + Adquirido);
        } catch (Exception ex) {
            System.out.println("ERRO ao calcular período: " + ex);
        }
        return periodo;
    }
}
