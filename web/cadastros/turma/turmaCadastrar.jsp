<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<!--Page Heading-->
<div class="row">
    <div class="col-12">
        <div class="page-title-box">
            <h4 class="page-title float-left">Turmas</h4>

            <ol class="breadcrumb float-right">
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="#">Cadastros</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/TurmaListar">Atividade</a></li>
                <li class="breadcrumb-item active">Turmas</li>
            </ol>

            <div class="clearfix"></div>
        </div>
    </div>
</div>

<!-- DataTables Example -->
<div class="card shadow col-md-12">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Turmas</h6>
    </div>

    <div class="card-body">
        
           
            <div class="form-row">
                <input type="hidden" class="form-control" name="idturma" id="idturma" required value="${turma.idTurma}" style="text-transform: uppercase" />
             
                <div class="form-group col-md-8">
                    <label for="numturma" class="col-form-label">Turma</label>
                    <input type="text" class="form-control descricao" id="numturma" name="numturma" required value="${turma.numTurma}" style="text-transform: uppercase">
                </div>
            </div>
            <div class="form-row form-group">

                <div class="form-group col-md-4">
                    <div class="col-form-label">Disciplina</div>
                    <select name="iddisciplina" id="iddisciplina" class="form-control">
                        <option value="">Selecione</option>
                        <c:forEach var="disciplina" items="${disciplinas}">
                            <option value="${disciplina.idDisciplina}"
                                    ${turma.disciplina.idDisciplina == disciplina.idDisciplina ? "selected" : ""}>
                                ${disciplina.nomeDisciplina}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group col-md-4">
                    <div class="col-form-label">Alunos</div>
                    <div>
                      <c:forEach var="aluno" items="${alunos}">
                        <div class="form-check">
                          <input class="form-check-input" type="checkbox" value="${aluno.idAluno}" id="idaluno${aluno.idAluno}" name="idaluno">
                          <label class="form-check-label" for="idaluno${aluno.idAluno}">
                            ${aluno.nome}
                          </label>
                        </div>
                      </c:forEach>
                    </div>
                </div>
            </div>
             
        <tr><td>
                <input type="hidden" name="situacao" id="situacao" value="${turma.situacao}" readonly="readonly"/>
        </td></tr>
        
            <div class="form-group">
                <button type="button" class="btn btn-success" onclick="validarCampos()">Cadastrar</button>
                <button type="reset" class="btn btn-danger">Limpar</button>
                <button class="btn btn-warning">
                    <a href="${pageContext.request.contextPath}/TurmaListar" class="btn-warning">Voltar</a>
                </button>
            </div>
     
    </div>
</div>

<script>
    $(document).ready(function() {
        console.log("entrei na ready do documento");
    });

    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("numturma").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a turma!',
                showConfirmButton: true,
                timer: 1000
            });
        } else if (document.getElementById("iddisciplina").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a disciplina!',
                showConfirmButton: true,
                timer: 1000
            });
            $("#iddisciplina").focus();
        } else {
            gravarDados();
        }
    }


    function gravarDados() {
        console.log("Gravando dados...");
        var selectedAlunoIDs = [];
        //$('input[name="idaluno"]:checked').each(function() {
          //selectedAlunoIDs.push($(this).val());
        //});
        // Lógica para adicionar IDs dos alunos selecionados ao array
       
        var checkboxes = document.querySelectorAll('input[type=checkbox][name=idaluno]');
        checkboxes.forEach(function(checkbox) {
          if (checkbox.checked) {
                selectedAlunoIDs.push(parseInt(checkbox.value)); // Adiciona o ID convertido para inteiro
            }
        });
        console.log(selectedAlunoIDs);
        $.ajax({
            type: 'post',
            url: 'TurmaCadastrar',
           data: {
                                idturma: $('#idturma').val(),
                                numturma: $('#numturma').val(),
                                situacao: $('#situacao').val(),
                                iddisciplina: $('#iddisciplina').val(),
                                idaluno: selectedAlunoIDs.join(',')
                                //idaluno: selectedAlunoIDs
                                //idaluno: JSON.stringify({ idaluno: selectedAlunoIDs })
                                //idaluno: selectedAlunoIDs // Array of selected aluno IDs
                        },
            success: function(data) {
                console.log("resposta servlet->");
                console.log(data);
                if (data == 1) {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Sucesso',
                        text: 'Turma gravada com sucesso!',
                        showConfirmButton: true,
                        timer: 1000
                    });
                } else {
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: 'Erro',
                        text: 'Não foi possível gravar a turma!',
                        showConfirmButton: true,
                        timer: 1000
                    });
                }
                setTimeout(() => {
                    window.location.href = "${pageContext.request.contextPath}/TurmaListar"
                }, 2000);
            },
            error: function(data) {
                setTimeout(() => {
                    window.location.href = "${pageContext.request.contextPath}/TurmaListar"
                }, 2000);
            }
        });
    }
</script>
<%@include file="/footer.jsp" %>