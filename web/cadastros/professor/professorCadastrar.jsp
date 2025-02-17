<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<!--Page Heading-->
<div class="row">
    <div class="col-12">
        <div class="page-title-box">
            <h4 class="page-title float-left">Professores</h4>

            <ol class="breadcrumb float-right">
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="#">Cadastros</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/ProfessorListar">Professor</a></li>
                <li class="breadcrumb-item active">Professores</li>
            </ol>

            <div class="clearfix"></div>
        </div>
    </div>
</div>

                <!-- DataTables Example -->
<div class="card shadow col-md-12">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Professores</h6>
    </div>

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
                        <label for="formacaoprofessor" class="col-form-label">Forma��o</label>
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
        <div class="form-group col-md-1">
            <label class="col-form-label">Permite Login</label>
            <select class="form-control descricao" name="permitelogin" id="permitelogin">
            <option value="S" ${professor.permiteLogin == 'S' ? "selected" : ""}>Sim</option>       
            <option value="N" ${professor.permiteLogin == 'N' ? "selected" : ""}>N�o</option>
            </select>    
        </div>     
                <tr><td>
                        <input type="hidden" name="situacao" id="situacao" value="${professor.situacao}" readonly="readonly"/>
                </td></tr>
               <div class="form-group">
            <button  class="btn btn-success" type="submit"  id="submit" onclick="validarCampos()">Cadastrar</button>
                    <button type="reset" class="btn btn-danger navbar-btn" id="reset" onclick="limparCampos()"><i class="fa fa-floppy-o" aria-hidden="true"></i> Limpar</button>
            <button class="btn btn-warning">
                <a href="${pageContext.request.contextPath}/ProfessorListar" class="btn-warning">Voltar</a>
            </button>
        </div>
       
    </div>
</div>
                
         <script>
   $(document).ready(function () {
	console.log("entrei na ready do documento");
     });
    function validarCampos() {
        console.log("entrei na valida��o de campos");
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
                                title: 'Verifique a forma��o!',
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
                                                        text: 'N�o foi poss�vel gravar o professor!',
                                                        showConfirmButton: false,
                                                        timer: 1000
                                                });
                                        }
                                        setTimeout(() => {window.location.href = "${pageContext.request.contextPath}/ProfessorListar"}, 2000);
                                },
                        error:
                                function (data) {
                                        setTimeout(() => {window.location.href = "${pageContext.request.contextPath}/ProfessorListar"}, 2000);
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

/*function carregarPessoa(v) {
    var idM = v;
    var tipoPessoa = 'professor';
    console.log("Usuario = " + idM);
    $(document).ready(function () {
        $.getJSON('PessoaBuscarCpf', {cpfcnpjpessoa: idM, tipopessoa: tipoPessoa}, function (respostaProf) {
            console.log(respostaProf);
            var id = respostaProf.idProfessor;
            if (respostaProf != null)
            {
                $('#idprofessor').val(respostaProf.idProfessor);
                $('#permitelogin').val(respostaProf.permiteLogin);
                $('#situacao').val(respostaProf.situacao);
                $('#idpessoa').val(respostaProf.idPessoa);
               
                $('#nome').val(respostaProf.nome);
                $('#login').val(respostaProf.login);
                $('#senha').val(respostaProf.senha);
                $('#emailprofessor').val(respostaProf.emailProfessor);
                $('#formacaoprofessor').val(respostaProf.formacaoProfessor);

            } else {
                //se n?o encontrou administrador busca por pessoa somente
                tipoPessoa = 'pessoa';
                $.getJSON('PessoaBuscarCpf', {cpfcnpjpessoa: idM, tipopessoa: tipoPessoa}, function (respostaPessoa) {
                    console.log(respostaPessoa);
                    var id = respostaPessoa.idPessoa;
                    if (id != "0")
                    {
                        $('#idpessoa').val(respostaPessoa.idPessoa);
                        $('#cpf').val(respostaPessoa.cpf);
                        $('#nome').val(respostaPessoa.nome);
                        $('#senha').val(respostaPessoa.senha);
                        $('#login').val(respostaPessoa.login);
                    }
                });
            }
        });
    });
}*/
</script>       
<%@include file="/footer.jsp" %>

