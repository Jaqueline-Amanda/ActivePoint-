package br.com.curso.model;
 
import javax.servlet.http.HttpSession;

 
public class Usuario {
   
    private int idUsuario;
    private String nome;
    
    private String login;
    private String senha;
    private String tipo;
    private int id;
   
    public Usuario() {
        this.idUsuario = 0;
        this.id = 0;
        this.tipo = "";
    }
 
    public Usuario(int idUsuario, String nome, String login, String senha, String tipo, int id) {
        this.idUsuario = idUsuario;
        this.nome = nome;
     
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.id = id;
    }

 
 
    public int getIdUsuario() {
        return idUsuario;
    }
 
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
   
 
    public String getLogin() {
        return login;
    }
 
    public void setLogin(String login) {
        this.login = login;
    }
 
    public String getSenha() {
        return senha;
    }
 
    public void setSenha(String senha) {
        this.senha = senha;
    }
 
    public String getTipo() {
        return tipo;
    }
 
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    public static boolean verificaUsuario(String recurso, HttpSession sessao){
        boolean status=false;
       
        try{
            if ( recurso.equalsIgnoreCase("/index.jsp") ||
                     recurso.equalsIgnoreCase("/home.jsp") ||
                     recurso.equalsIgnoreCase("/login.jsp") ||
                     recurso.equalsIgnoreCase("/header.jsp") ||
                     recurso.equalsIgnoreCase("/menu.jsp") ||
                     recurso.equalsIgnoreCase("/footer.jsp") ||
                     recurso.equalsIgnoreCase("/UsuarioBuscarPorLogin") ||
                     recurso.equalsIgnoreCase("/cadastros/cadastrarAluno.jsp") ||
                     recurso.equalsIgnoreCase("/AlunoNovoCadastro") ||
                     recurso.equalsIgnoreCase("/ProfessorNovoCadastro") ||
                     recurso.equalsIgnoreCase("/ProfessorCadastrar") ||
                     recurso.equalsIgnoreCase("/ProfessorDAO") ||
                     recurso.equalsIgnoreCase("/Professor") ||
                     recurso.equalsIgnoreCase("/cadastros/cadastrarProfessor.jsp") ||
                     recurso.equalsIgnoreCase("/css/estilo-tela-login.css") ||
                     recurso.equalsIgnoreCase("/AlunoDAO") ||
                     recurso.equalsIgnoreCase("/AlunoNovo") ||
                     recurso.equalsIgnoreCase("/PessoaDAO") ||
                     recurso.equalsIgnoreCase("/Aluno") ||
                     recurso.equalsIgnoreCase("/Usuario") ||
                     recurso.equalsIgnoreCase("/AlunoCadastrar") ||
                     recurso.startsWith("/css/estilo-tela-login.css") ||
                     recurso.startsWith("/scss/") ||
                    recurso.startsWith("/vendor/") ||
                    recurso.startsWith("/img/") ||
                    recurso.startsWith("/css/") ||
                    recurso.startsWith("/js/") ||
                    recurso.equalsIgnoreCase("/UsuarioLogar")) {
                status = true;
            }
           
            if(sessao.getAttribute("idusuario") != null && sessao != null && sessao.getAttributeNames().hasMoreElements()){
                
                int idUsuario = Integer.parseInt(sessao.getAttribute("idusuario").toString());
                String tipoUsuario = sessao.getAttribute("tipousuario").toString();

                if (tipoUsuario.equalsIgnoreCase("administrador")){
                    status=true;
                } else {
                    if (tipoUsuario.equalsIgnoreCase("professor")) {
                        if (tipoUsuario.equalsIgnoreCase("/SemestreCadastrar") ||
                            recurso.equalsIgnoreCase("/SemestreCarregar") ||
                            recurso.equalsIgnoreCase("/SemestreAlterar") ||
                            recurso.equalsIgnoreCase("/SemestreListar") ||
                            recurso.equalsIgnoreCase("/SemestreNovo") ||
                            recurso.equalsIgnoreCase("/DisciplinaCadastrar") ||
                            recurso.equalsIgnoreCase("/DisciplinaCarregar") ||
                            recurso.equalsIgnoreCase("/DisciplinaAlterar") ||
                            recurso.equalsIgnoreCase("/DisciplinaListar") ||
                            recurso.equalsIgnoreCase("/DisciplinaNovo") ||
                            recurso.equalsIgnoreCase("/AtividadeCadastrar") ||
                            recurso.equalsIgnoreCase("/AtividadeCarregar") ||
                            recurso.equalsIgnoreCase("/AtividadeAlterar") ||
                            recurso.equalsIgnoreCase("/AtividadeListar") ||
                            recurso.equalsIgnoreCase("/AtividadeEntregar") ||
                            recurso.equalsIgnoreCase("/AtividadeRealizadaListar") ||
                            recurso.equalsIgnoreCase("/AtividadeNovo") ||
                            recurso.equalsIgnoreCase("/UsuarioDeslogar") ||
    
                            recurso.equalsIgnoreCase("/home.jsp")){
                            status=true;
                        }
                    }
                    else if (tipoUsuario.equalsIgnoreCase("aluno")){
                        if (tipoUsuario.equalsIgnoreCase("/SemestreListar") ||
                            recurso.equalsIgnoreCase("/SemestreListar") ||
                            recurso.equalsIgnoreCase("/DisciplinaListar") ||
                            recurso.equalsIgnoreCase("/AtividadeListar") ||
                            recurso.equalsIgnoreCase("/AtividadeEntregar") ||
                            recurso.equalsIgnoreCase("/AtividadeRealizadaListar") ||
                            recurso.equalsIgnoreCase("/cadastros/cadastrarAluno.jsp") ||
                            recurso.equalsIgnoreCase("/UsuarioDeslogar") ||
                            recurso.equalsIgnoreCase("/AlunoCadastrar") ||
    
                            recurso.equalsIgnoreCase("/home.jsp")){
                            status=true;
                        }
                    }
                }
            }
        } catch (Exception ex){
             System.out.println("Erro: " + ex.getMessage());
             ex.printStackTrace();
        }        
        return status;
    }
}