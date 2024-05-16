<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>


    <div class="card-body">
        
                <div class="form-row form-group">
                    <input type="hidden" class="form-control" name="idprofessor" id="idprofessor" required value="${professor.idProfessor}" style="text-transform: uppercase"/>
                    <input type="hidden" class="form-control" name="idpessoa" id="idpessoa" required value="${professor.idPessoa}" style="text-transform: uppercase"/>

                    <div class="form-group col-md-8">
                         <label for="nome" class="col-form-label">Nome professor</label>
                        <input type="text" class="form-control descricao" id="nome" name="nome" required value="${professor.nome}" style="text-transform: uppercase">
                    </div>
                    
                    <div class="form-group col-md-8">
                         <label for="rm" class="col-form-label">RM</label>
                        <input type="text" class="form-control descricao" id="rm" name="rm" required value="${professor.rm}" style="text-transform: uppercase">
                    </div>
                    
                    <div class="form-group col-md-8">
                        <label for="emailprofessor" class="col-form-label">E-mail</label>
                        <input type="text" class="form-control descricao" id="emailprofessor" name="emailprofessor" required value="${professor.emailProfessor}" style="text-transform: uppercase">
                    </div>
                    
                     <div class="form-group col-md-8">
                        <label for="login" class="col-form-label">Login</label>
                        <input type="text" class="form-control descricao" id="login" name="login" required value="${professor.login}" style="text-transform: uppercase">
                    </div>
                    
                     <div class="form-group col-md-8">
                        <label for="senha" class="col-form-label">Senha</label>
                        <input type="password" class="form-control descricao" id="senha" name="senha" required value="${professor.senha}" style="text-transform: uppercase">
                    </div>
                    
                     <div class="form-group col-md-8">
                        <label for="formacaoprofessor" class="col-form-label">Formação</label>
                        <input type="text" class="form-control descricao" id="formacaoprofessor" name="formacaoprofessor" required value="${professor.formacaoProfessor}" style="text-transform: uppercase">
                    </div>
                   
                <div class="form-group col-md-8">
               
                <div class="col-form-label">Disciplina</div>
                <select name="iddisciplina" id="iddisciplina" class="form-control">
                    <option value="">Selecione</option>
                    <c:forEach var="disciplina" items="${disciplinas}">
                        <option value="${disciplina.idDisciplina}"
                                ${professor.disciplina.idDisciplina == disciplina.idDisciplina ? "selected" : ""}>
                            ${disciplina.nomeDisciplina}
                        </option>
                    </c:forEach>
                </select>
            </div>
             
        </div>
          
                <tr><td>
                        <input type="hidden" name="situacao" id="situacao" value="${professor.situacao}" readonly="readonly"/>
                </td></tr>
               <div class="form-group">
            <button  class="btn btn-success" type="submit"  id="submit" onclick="validarCampos()">Cadastrar</button>
                    <button type="reset" class="btn btn-danger navbar-btn" id="reset" onclick="limparCampos()"><i class="fa fa-floppy-o" aria-hidden="true"></i> Limpar</button>
        </div>
       
    </div>
</div>
                
         <script>
   $(document).ready(function () {
	console.log("entrei na ready do documento");
     });
    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nome").value === '') {
                        Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Verifique o nome do professor!',
                                showConfirmButton: false,
                                timer: 1000
                        });
                        $("#nome").focus();
        }else if (document.getElementById("rm").value === '') {
                        Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Verifique o RM!',
                                showConfirmButton: false,
                                timer: 1000
                        });
                        $("#rm").focus();
        } else if (document.getElementById("emailprofessor").value === '') {
                        Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Verifique o e-mail!',
                                showConfirmButton: false,
                                timer: 1000
                        });
                        $("#emailprofessor").focus();
        } else if (document.getElementById("login").value === '') {
                        Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Verifique o login!',
                                showConfirmButton: false,
                                timer: 1000
                        });
                        $("#login").focus();
        }else if (document.getElementById("senha").value === '') {
                        Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Verifique a senha!',
                                showConfirmButton: false,
                                timer: 1000
                        });
                        $("#senha").focus();
        }else if (document.getElementById("formacaoprofessor").value === '') {
                        Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Verifique a formação!',
                                showConfirmButton: false,
                                timer: 1000
                        });
                        $("#formacaoprofessor").focus();
        } else if (document.getElementById("iddisciplina").value === '') {
                        Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Verifique a disciplina!',
                                showConfirmButton: true,
                                timer: 1000
                        });
                        $("#iddisciplina").focus();
        } 
        
        else {
                gravarDados();
        }
    }
     

        function gravarDados() {
        console.log("Gravando dados...");
                $.ajax({
                        type: 'post',
                        url: 'ProfessorCadastrar',
                        data: {
                                idpessoa: $('#idpessoa').val(),
                                idprofessor: $('#idprofessor').val(),
                                rm: $('#rm').val(),
                                formacaoprofessor: $('#formacaoprofessor').val(),
                                emailprofessor: $('#emailprofessor').val(),
                                situacao: $('#situacao').val(),
                                permitelogin: $('#permitelogin').val(),
                                nome: $('#nome').val(),
                                iddisciplina: $('#iddisciplina').val(),
                                login: $('#login').val(),
                                senha: $('#senha').val()

                        },
                        success:
                                function(data) {
                                        console.log("resposta servlet->");
                                        console.log(data);
                                        if (data == 1) {
                                                Swal.fire({
                                                        position: 'center',
                                                        icon: 'success',
                                                        title: 'Sucesso',
                                                        text: 'Professor gravado com sucesso!',
                                                        showConfirmButton: false,
                                                        timer: 1000
                                                });
                                        } else {
                                                Swal.fire({
                                                        position: 'center',
                                                        icon: 'error',
                                                        title: 'Erro',
                                                        text: 'Não foi possível gravar o professor!',
                                                        showConfirmButton: false,
                                                        timer: 1000
                                                });
                                        }
                                        setTimeout(() => {window.location.href = "${pageContext.request.contextPath}/login.jsp"}, 2000);
                                },
                        error:
                                function (data) {
                                        setTimeout(() => {window.location.href = "${pageContext.request.contextPath}/login.jsp"}, 2000);
                                }
        });
    }
    function limparCampos() {
    $('#idprofessor').val("");
    $('#permitelogin').val("");
    $('#rm').val("");
    $('#situacao').val("");
    $('#idpessoa').val("");
    $('#emailprofessor').val("");
    $('#nome').val("");
    $('#login').val("");
    $('#senha').val("");
    $('#formacaoprofessor').val("");
    $('#iddisciplina').val("");
}

</script>       


