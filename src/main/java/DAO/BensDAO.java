package DAO;

import Util.Exibir;
import Util.Formatar;
import controller.LoginBean;
import entities.Bens;
import entities.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class BensDAO {

    public void inserirBem(Bens bens) {
        String SQL = "INSERT INTO bens(nome, data_compra, vida_util, valor_residual, tempo_uso, situacao, categoria, turno_trabalhado, fk_Empresa_id, custo_bem) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, bens.getNome());
            pstm.setDate(2, java.sql.Date.valueOf(bens.getData_compra()));
            pstm.setInt(3, bens.getVida_util());
            pstm.setDouble(4, bens.getValor_residual());
            pstm.setInt(5, bens.getTempo_uso());
            pstm.setString(6, bens.getSituacao());
            pstm.setString(7, bens.getCategoria());
            pstm.setInt(8, bens.getTurno_trabalhado());
            pstm.setInt(9, bens.getFk_Empresa_id());
            pstm.setDouble(10, bens.getCusto_bem());
            
            pstm.execute();
            System.out.println(pstm);
            BD.getConexao().close();
            pstm.close();
            System.out.println("Bem inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.MensagemErro("Erro ao inserir Patrimônio: " + ex);
        }
    }

    public ArrayList<Bens> obterBens() {

        ArrayList<Bens> bens = new ArrayList<>();

        String SQL = "SELECT b.id, b.nome, b.data_compra, b.vida_util, \n"
                + "b.valor_residual, b.tempo_uso, b.situacao, b.categoria,\n"
                + "b.turno_trabalhado, b.data_baixa, b.fk_Empresa_id, b.custo_bem FROM bens b\n"
                + "INNER JOIN empresa e ON (b.fk_Empresa_id = e.id)\n"
                + "INNER JOIN usuario u ON (u.id_user = e.fk_Usuario_id_user)\n"
                + "WHERE e.id = " + LoginBean.id_logado + " ORDER BY b.id";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Bens b = new Bens(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            Formatar.data(rs.getDate("data_compra"), "dd/MM/yyyy"),
                            Formatar.data(rs.getDate("data_baixa"), "dd/MM/yyyy"),
                            rs.getInt("vida_util"),
                            rs.getDouble("valor_residual"),
                            rs.getInt("tempo_uso"),
                            rs.getString("situacao"),
                            rs.getString("categoria"),
                            rs.getInt("turno_trabalhado"),
                            rs.getInt("fk_Empresa_id"),
                            rs.getDouble("custo_bem")
                    );
                    bens.add(b);
                }
                pstm.close();
            }
            System.out.println("Bens obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.MensagemErro("Erro ao obter bens!: \n" + ex);
        }

        return bens;
    }

    public void editarEmpresa(Empresa empresa) {
        String SQL = "UPDATE empresa SET cnpj = ?, nome = ?, telefone = ?, email = ?, responsavel = ? WHERE id = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, empresa.getCnpj());
            pstm.setString(2, empresa.getNome());
            pstm.setString(3, empresa.getTelefone());
            pstm.setString(4, empresa.getEmail());
            pstm.setString(5, empresa.getResponsavel());
            pstm.setInt(6, empresa.getId());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.MensagemErro("Erro ao Alterar empresa!:\n" + ex);
        }
    }

    public void removerEmpresa(Empresa empresa) {
        String SQL = "DELETE FROM empresa WHERE id = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, empresa.getId());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.MensagemErro("\nErro ao remover empresa: " + ex);
        }
    }
}
