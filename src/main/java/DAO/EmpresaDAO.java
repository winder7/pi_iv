package DAO;

import Util.Exibir;
import Util.Formatar;
import entities.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alexandre Almeida
 */
public class EmpresaDAO {

    public void inserirEmpresa(Empresa empresa) {
        String SQL = "INSERT INTO empresa(cnpj, nome, telefone, email, responsavel, fk_Usuario_id_user, data_cadastro) VALUES (?,?,?,?,?,?, '" + new Date() + "')";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, empresa.getCnpj());
            pstm.setString(2, empresa.getNome());
            pstm.setString(3, empresa.getTelefone());
            pstm.setString(4, empresa.getEmail());
            pstm.setString(5, empresa.getResponsavel());
            pstm.setInt(6, empresa.getFk_Usuario_id_user());
            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            if (ex.toString().contains("ERROR: duplicate key value violates unique constraint")) {
                Exibir.MensagemErro("Erro ao inserir Empresa! Já existe uma empresa cadastrada com esse CNPJ! ");
            } else {
                Exibir.MensagemErro("Erro ao inserir Empresa: " + ex);
            }
        }
    }

    public ArrayList<Empresa> obterEmpresa(int id) {

        ArrayList<Empresa> empresa = new ArrayList<>();

        String SQL = "SELECT * FROM empresa WHERE fk_Usuario_id_user = " + id + " ORDER BY nome";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Empresa emp = new Empresa(
                            rs.getInt("id"),
                            rs.getString("cnpj"),
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("responsavel"),
                            Formatar.data(rs.getDate("data_cadastro"), "dd/MM/yyyy"),
                            rs.getInt("fk_Usuario_id_user")
                    );
                    empresa.add(emp);
                }
                pstm.close();
            }
            System.out.println("Empresas obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.MensagemErro("Erro ao obter Empresa!: \n" + ex);
        }

        return empresa;
    }

     public Empresa visualizarEmpresa(int id) {

        Empresa empresa = null;

        String SQL = "SELECT * FROM empresa WHERE id = " + id;
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    empresa = new Empresa(
                            rs.getInt("id"),
                            rs.getString("cnpj"),
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("responsavel"),
                            Formatar.data(rs.getDate("data_cadastro"), "dd/MM/yyyy"),
                            rs.getInt("fk_Usuario_id_user")
                    );
                }
                pstm.close();
            }
            System.out.println("Visualizar Empresas obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.MensagemErro("Erro ao obter Empresa!: \n" + ex);
        }

        return empresa;
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
