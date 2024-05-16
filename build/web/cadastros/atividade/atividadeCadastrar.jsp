<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<!--Page Heading-->
<div class="row">
    <div class="col-12">
        <div class="page-title-box">
            <h4 class="page-title float-left">Atividades</h4>

            <ol class="breadcrumb float-right">
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="#">Cadastros</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/AtividadeListar">Atividade</a></li>
                <li class="breadcrumb-item active">Atividades</li>
            </ol>

            <div class="clearfix"></div>
        </div>
    </div>
</div>

<!-- DataTables Example -->
<div class="card shadow col-md-12">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Atividades</h6>
    </div>

    <div class="card-body">
        
           <div class="form-group">
            <center>
                <embed src="${atividade.documento}" type="application/pdf" width="200" height="200">
                <div id="preview-container">
                    <span id="file-preview"></span>
                </div>
                <br><br>
                <input type="file" id="gallery-photo-add" class="inputfile" onchange="uploadFile();" />
                <button onclick="previewFile();" class="btn btn-primary">Preview</button>
                <label for="gallery-photo-add" class="btn btn-success">
                    <i class="fas fa-file-upload"></i>
                    Selecionar documento
                </label>
            </center>
        </div>      
            <div class="form-row">
                <input type="hidden" class="form-control" name="idatividade" id="idatividade" required value="${atividade.idAtividade}" style="text-transform: uppercase" />
             
                <div class="form-group col-md-8">
                    <label for="descricao" class="col-form-label">Descrição</label>
                    <input type="text" class="form-control descricao" id="descricao" name="descricao" required value="${atividade.descricao}" style="text-transform: uppercase">
                </div>
            </div>
            <div class="form-row form-group">
                <div class="form-group col-md-4">
                    <label for="dataprazo" class="col-form-label">Prazo</label>
                    <input type="date" class="form-control descricao" id="dataprazo" name="dataprazo" required value="${atividade.dataPrazo}" style="text-transform: uppercase">
                </div>

                <div class="form-group col-md-4">
                    <label for="pontuacaomax" class="col-form-label">Pontuação Máxima</label>
                    <input type="text" class="form-control descricao" id="pontuacaomax" name="pontuacaomax" required value="${atividade.pontuacaomax}" style="text-transform: uppercase">
                </div>
                
                   <div class="form-group col-md-4">
                    <label for="pontuacaofinal" class="col-form-label">Pontuação Final</label>
                    <input type="text" class="form-control descricao" id="pontuacaofinal" name="pontuacaofinal" required value="${atividade.pontuacaofinal}" style="text-transform: uppercase">
                </div>


                <div class="form-group col-md-4">
                    <div class="col-form-label">Disciplina</div>
                    <select name="iddisciplina" id="iddisciplina" class="form-control">
                        <option value="">Selecione</option>
                        <c:forEach var="disciplina" items="${disciplinas}">
                            <option value="${disciplina.idDisciplina}"
                                    ${atividade.disciplina.idDisciplina == disciplina.idDisciplina ? "selected" : ""}>
                                ${disciplina.nomeDisciplina}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <div class="col-form-label">Turma</div>
                    <select name="idturma" id="idturma" class="form-control">
                        <option value="">Selecione</option>
                        <c:forEach var="turma" items="${turmas}">
                            <option value="${turma.idTurma}"
                                    ${atividade.turma.idTurma == turma.idTurma ? "selected" : ""}>
                                ${turma.numTurma}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
             
        <tr><td>
                <input type="hidden" name="situacao" id="situacao" value="${atividade.situacao}" readonly="readonly"/>
        </td></tr>
          <tr><td>
                <input type="hidden" name="status" id="status" value="${atividade.status}" readonly="readonly"/>
        </td></tr>
            <div class="form-group">
                <button type="button" class="btn btn-success" onclick="validarCampos()">Cadastrar</button>
                <button type="reset" class="btn btn-danger">Limpar</button>
                <button class="btn btn-warning">
                    <a href="${pageContext.request.contextPath}/AtividadeListar" class="btn-warning">Voltar</a>
                </button>
            </div>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        console.log("entrei na ready do documento");
    });

    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("descricao").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a descrição da atividade!',
                showConfirmButton: true,
                timer: 1000
            });
            $("#descricao").focus();
        } else if (document.getElementById("dataprazo").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o prazo da atividade!',
                showConfirmButton: true,
                timer: 1000
            });
            $("#dataprazo").focus();
        } else if (document.getElementById("iddisciplina").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a disciplina!',
                showConfirmButton: true,
                timer: 1000
            });
            $("#iddisciplina").focus();
        } else if (document.getElementById("pontuacaomax").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a pontuação máxima!',
                showConfirmButton: true,
                timer: 1000
            });
            $("#pontuacaomax").focus();
        } else if (document.getElementById("pontuacaofinal").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a pontuação final!',
                showConfirmButton: true,
                timer: 1000
            });
            $("#pontuacaofinal").focus();
        }
        else if (document.getElementById("idturma").value === '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a turma!',
                showConfirmButton: true,
                timer: 1000
            });
            $("#idturma").focus();
        }
        else {
            gravarDados();
        }
    }

    function gravarDados() {
        console.log("Gravando dados...");
        var filePreview = document.getElementById("file-preview").src;
       
        $.ajax({
            type: 'post',
            url: 'AtividadeCadastrar',
           data: {
                                idatividade: $('#idatividade').val(),
                                descricao: $('#descricao').val(),
                                situacao: $('#situacao').val(),
                                status: $('#status').val(),
                                dataatividade: $('#dataatividade').val(),
                                dataprazo: $('#dataprazo').val(),
                                iddisciplina: $('#iddisciplina').val(),
                                idturma: $('#idturma').val(),
                                pontuacaomax: $('#pontuacaomax').val(),
                                pontuacaofinal: $('#pontuacaofinal').val(),
                                documento: filePreview
                        },
            success: function(data) {
                console.log("resposta servlet->");
                console.log(data);
                if (data == 1) {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Sucesso',
                        text: 'Atividade gravada com sucesso!',
                        showConfirmButton: true,
                        timer: 1000
                    });
                } else {
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: 'Erro',
                        text: 'Não foi possível gravar a atividade!',
                        showConfirmButton: true,
                        timer: 1000
                    });
                }
                setTimeout(() => {
                    window.location.href = "${pageContext.request.contextPath}/AtividadeListar"
                }, 2000);
            },
            error: function(data) {
                setTimeout(() => {
                    window.location.href = "${pageContext.request.contextPath}/AtividadeListar"
                }, 2000);
            }
        });
    }

    function limparCampos() {
        $('#formAtividade')[0].reset();
    }

      function uploadFile() {
        var filePreview = document.getElementById('file-preview');
        filePreview.src = "";
        var file = document.querySelector("input[type='file']").files[0];
        if (file) {
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = function() {
                filePreview.src = reader.result;
            };
        }
    }
    function previewFile() {
        var fileInput = document.getElementById('gallery-photo-add');
        var file = fileInput.files[0];

        if (file) {
            var fileType = getFileType(file.name);
            if (fileType === 'image') {
                var img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.onload = function() {
                    URL.revokeObjectURL(this.src);
                };
                var newWindow = window.open('', '_blank');
                newWindow.document.write('<html><head><title>Preview</title></head><body style="text-align: center;">' + img.outerHTML + '</body></html>');
            } else if (fileType === 'text') {
                var reader = new FileReader();
                reader.onload = function(event) {
                    var newWindow = window.open('', '_blank');
                    newWindow.document.write('<html><head><title>Preview</title></head><body style="white-space: pre-wrap;">' + event.target.result + '</body></html>');
                };
                reader.readAsText(file);
            }else if (fileType === 'pdf') {
                var newWindow = window.open('', '_blank');
                newWindow.document.write('<html><head><title>Preview</title></head><body><embed src="' + URL.createObjectURL(file) + '" width="100%" height="100%" type="application/pdf"></body></html>');
            } 
            else {
                alert('Este tipo de arquivo não pode ser pré-visualizado.');
            }
        }
    }

   function getFileType(fileName) {
        var extension = fileName.split('.').pop().toLowerCase();
        if (extension === 'jpg' || extension === 'jpeg' || extension === 'png' || extension === 'gif' || extension === 'bmp') {
            return 'image';
        } else if (extension === 'txt') {
            return 'text';
        }else if (extension === 'pdf') {
            return 'pdf';
        }
        else if (extension === 'docx') {
            return 'docx';
        } else {
            return 'unknown';
        }
    }
</script>
<%@include file="/footer.jsp" %>