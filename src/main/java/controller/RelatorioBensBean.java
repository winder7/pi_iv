package controller;

import DAO.BensDAO;
import DAO.EmpresaDAO;
import Util.Relatorio;
import entities.Bens;
import entities.BensRel;
import entities.Empresa;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Alexandre Almeida
 * @Data 07/11/2018
 */
@ManagedBean
@ViewScoped
public class RelatorioBensBean {

    private int fk_Empresa_id;

    private ArrayList<Bens> bens;
    private ArrayList<Empresa> empresa;
    private Map<Integer, String> ItensBoxEmpresa;

    public RelatorioBensBean() {
        this.bens = new ArrayList<>();
        this.empresa = new ArrayList<>();
        setBoxEmpresa();
    }

    public void obter() {
        bens.clear();
        BensDAO bensDAO = new BensDAO();
        bens = bensDAO.calcularBensRel(fk_Empresa_id);
    }

    public String cancelar() {
        this.fk_Empresa_id = 0;
        return "cadastrarBens";
    }

    private void setBoxEmpresa() {
        ItensBoxEmpresa = new LinkedHashMap<>();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.obterEmpresa(LoginBean.id_logado);

        for (Empresa empresas : empresa) {
            ItensBoxEmpresa.put(0, "Selecione uma empresa");
            ItensBoxEmpresa.put(empresas.getId(), empresas.getNome());
        }
    }

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }

    public String formatarCor(double num) {
        String cor = "";

        if (num <= 0) {
            cor = "color:red";
        } else {
            cor = "color:blue";
        }
        return cor;
    }

    public void gerarRelatorio() {
        ArrayList<BensRel> bensRel = new ArrayList<>();

        for (Bens ben : bens) {

            BensRel br = new BensRel(ben.getId(),
                    ben.getNome(),
                    ben.getData_compra(),
                    ben.getData_baixa(),
                    ben.getVida_util(),
                    formatarNumero(ben.getValor_residual()),
                    ben.getTempo_uso(),
                    ben.getSituacao(),
                    ben.getCategoria(),
                    ben.getTurno_trabalhado(),
                    ben.getFk_Empresa_id(),
                    formatarNumero(ben.getCusto_bem()),
                    formatarNumero(ben.getCusto_venda()),
                    formatarNumero(ben.getDepreciacao()) + " (" + String.format("%,.1f",ben.getPercentDepr()) + "%)",
                    formatarNumero(ben.getValorContabil()),
                    formatarNumero(ben.getGanhoPerda()),
                    ben.getNome_empresa(),
                    ben.getResponsavel(),
                    ben.getTelefone(),
                    ben.getEmail()
            );
            bensRel.add(br);
        }

        Relatorio gerar = new Relatorio();
        gerar.getRelatorio(bensRel);
    }

    //gets setters
    public int getFk_Empresa_id() {
        return fk_Empresa_id;
    }

    public void setFk_Empresa_id(int fk_Empresa_id) {
        this.fk_Empresa_id = fk_Empresa_id;
    }

    public ArrayList<Bens> getBens() {
        return bens;
    }

    public void setBens(ArrayList<Bens> bens) {
        this.bens = bens;
    }

    public ArrayList<Empresa> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ArrayList<Empresa> empresa) {
        this.empresa = empresa;
    }

    public Map<Integer, String> getItensBoxEmpresa() {
        return ItensBoxEmpresa;
    }

    public void setItensBoxEmpresa(Map<Integer, String> ItensBoxEmpresa) {
        this.ItensBoxEmpresa = ItensBoxEmpresa;
    }
}
