package DAO;

import Util.Exibir;
import Util.Formatar;
import controller.LoginBean;
import entities.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Autor Alexandre Almeida / Winder Rezende
 * @Data 28/11/2018
 */
public class UsuarioDAO {

    public void inserirUsuario(Usuario usuario) {

        String SQL = "INSERT INTO usuario(login, senha, situacao, data_cad, email) VALUES (?, md5(?), ?, '" + new Date() + "', ?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, usuario.getLogin());
            pstm.setString(2, usuario.getSenha());
            pstm.setBoolean(3, Boolean.parseBoolean(usuario.getSituacao()));
            pstm.setString(4, usuario.getEmail());
            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir usuário: " + ex);
        }
    }

    public ArrayList<Usuario> obterUsuarios() {

        ArrayList<Usuario> usuario = new ArrayList<>();

        String SQL = "SELECT id_user, login, senha, situacao, data_cad, email FROM usuario ORDER BY login ASC";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Usuario usr = new Usuario(
                            rs.getInt("id_user"),
                            rs.getString("login"),
                            rs.getString("senha").equals("e8d95a51f3af4a3b134bf6bb680a213a") ? "Padrão" : "Privada",
                            rs.getBoolean("situacao") ? "Ativo" : "Inativo",
                            Formatar.data(rs.getDate("data_cad"), "dd/MM/yyyy"),
                            rs.getString("email")
                    );
                    usuario.add(usr);
                }
                pstm.close();
            }
            System.out.println("Usuários obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter usuários!: \n" + ex);
        }
        return usuario;
    }

    public void alterarUsuario(Usuario usuario) {
        String SQL;
        if (!usuario.getSenha().equals("")) {
            SQL = "UPDATE usuario SET login = ?, senha = md5(?), situacao = ?, email = ? WHERE id_user = (?)";
        } else {
            SQL = "UPDATE usuario SET login = ?, situacao = ?, email = ? WHERE id_user = ?";
        }

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, usuario.getLogin());
            if (!usuario.getSenha().equals("")) {
                pstm.setString(2, usuario.getSenha());
                pstm.setBoolean(3, Boolean.parseBoolean(usuario.getSituacao()));
                pstm.setString(4, usuario.getEmail());
                pstm.setInt(5, usuario.getId_user());
            } else {
                pstm.setBoolean(2, Boolean.parseBoolean(usuario.getSituacao()));
                pstm.setString(3, usuario.getEmail());
                 pstm.setInt(4, usuario.getId_user());
            }
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Usuário!:\n" + ex);
        }
    }

    public void apagarUsuario(int id_user) {

        String SQL = "DELETE FROM usuario WHERE id_user = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, id_user);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Usuário Apagado! ");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Apagar Usuário!:\n" + ex);
        }
    }

    public boolean verificaUsuarioSenha(String usuario, String senha) {

        boolean result = false;
        String SQL = "SELECT login, senha, situacao FROM usuario WHERE UPPER(login) = UPPER(?) AND senha = md5(?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, usuario);
            pstm.setString(2, senha);

            try (ResultSet rs = pstm.executeQuery()) {

                if (rs.next()) {
                    if (!rs.getBoolean("situacao")) {
                        Exibir.Mensagem("Usuário desabilitado contate o administrador do sistema!");
                        return false;
                    }
                    result = true;
                    pstm.close();
                    BD.getConexao().close();
                } else {
                    BD.getConexao().close();
                    result = false;
                    Exibir.Mensagem("Usuário ou senha inválidos!");
                }
                pstm.close();
                rs.close();
            }
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Verificar Usuário e Senha!: \n" + ex);
        }
        return result;
    }

    public boolean verificaUsuarioEmail(String usuario, String email) {

        boolean result = false;
        String SQL = "SELECT login, email, situacao FROM usuario WHERE UPPER(login) = UPPER(?) AND UPPER(email) = UPPER(?)";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            pstm.setString(1, usuario);
            pstm.setString(2, email);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                if (!rs.getBoolean("situacao")) {
                    Exibir.Mensagem("Usuário desabilitado contate o administrador do sistema!");
                    return false;
                }
                result = true;
                pstm.close();
                BD.getConexao().close();
                rs.close();
            } else {
                pstm.close();
                BD.getConexao().close();
                result = false;
                rs.close();
                Exibir.Mensagem("Usuário ou senha inválidos!");
            }
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Verificar Usuário e Senha!: \n" + ex);
        }
        System.out.println(result);
        return result;
    }

    public void obterLogin(LoginBean login) {

        String SQL = "SELECT login, id_user FROM usuario WHERE UPPER(login) = UPPER(?)";

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, login.getUsuario());
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    login.setNomeUsr(rs.getString("login"));
                    LoginBean.id_logado = rs.getInt("id_user");
                }

                pstm.close();
                BD.getConexao().close();
            }
            System.out.println("Login obtido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Obter Login do Banco de Dados!: \n" + ex);
        }
    }

    public void alterarSenha(String login, String novaSenha) {

        String SQL = "UPDATE usuario SET senha = md5(?) WHERE UPPER(login) = UPPER(?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, novaSenha);
            pstm.setString(2, login);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Usuário!:\n" + ex);
        }
    }
}
