package DAO;

import Util.Exibir;
import entities.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alexandre Almeida
 */
public class EmpresaDAO {

    public void inserirEmpresa(Empresa empresa) {
        String SQL = "INSERT INTO empresa(cnpj, nome, telefone, email, responsavel, data_cadastro, fk_Usuario_id_user) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, empresa.getCnpj());
            pstm.setString(2, empresa.getNome());
            pstm.setString(3, empresa.getTelefone());
            pstm.setString(4, empresa.getEmail());
            pstm.setString(5, empresa.getResponsavel());
            pstm.setString(6, empresa.getData_cadastro());
            pstm.setInt(7, empresa.getFk_Usuario_id_user());

            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Empresa: " + ex);
        }
    }

    public ArrayList<Empresa> obterEmpresa() {

        ArrayList<Empresa> empresa = new ArrayList<>();

        String SQL = "SELECT * FROM empresa ORDER BY nome";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Empresa disc = new Empresa(
                            rs.getInt("id"),
                            rs.getString("cnpj"),
                            rs.getString("nome"),
                            rs.getString("telefone"), 
                            rs.getString("email"), 
                            rs.getString("responsavel"), 
                            rs.getString("data_cadastro"), 
                            rs.getInt("fk_Usuario_id_user")
                    );
                    empresa.add(disc);
                }
                pstm.close();
            }
            System.out.println("Empresas obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Empresa!: \n" + ex);
        }

        return empresa;
    }
    
    public void editarEmpresa(Empresa empresa) {
        String SQL = "UPDATE disciplina SET cnpj = ?, nome = ?, telefone = ?, email = ?, responsavel = ?, data_cadastro = ? WHERE id = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, empresa.getCnpj());
            pstm.setString(2, empresa.getNome());
            pstm.setString(3, empresa.getTelefone());
            pstm.setString(4, empresa.getEmail());
            pstm.setString(5, empresa.getResponsavel());
            pstm.setString(6, empresa.getData_cadastro());
            pstm.setInt(7, empresa.getId());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar empresa!:\n" + ex);
        }
    }

    public void removerEmpresa(Empresa empresa) {
        String SQL = "DELETE FROM empresa WHERE codigo = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, empresa.getId());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("\nErro ao remover empresa: " + ex);
        }
    }
}
