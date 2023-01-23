window.addEventListener('load', function () {
    
    listarPacientes();
    listarOdontologos();

    const formulario = document.querySelector('#add_new_turno');

    formulario.addEventListener('submit', function (event) {

        const formData = {
            paciente: {
                id: document.querySelector('#pacientes').value
            },
            odontologo: {
                id: document.querySelector('#odontologos').value
            },
            fecha: document.querySelector('#fecha').value,
        };

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Turno agregado </strong></div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {

                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong> Error intente nuevamente</strong> </div>'

                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();
            })
    });

    function listarPacientes() {

        const url = '/pacientes';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {

                for(paciente of data) {
                    var pacientesList = document.getElementById("pacientes");
                    var pacienteOption = '<option value=\"' + paciente.id + '\">' + 
                        paciente.nombre + ' ' + paciente.apellido + 
                        '</option>';
                    pacientesList.innerHTML += pacienteOption;
            };
        })
    }

    function listarOdontologos() {

        const url = '/odontologos';
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                for(odontologo of data){
                    var odontologosList = document.getElementById("odontologos");
                    var odontologoOption = '<option value=\"' + odontologo.id + '\">' + 
                        odontologo.nombre + ' ' + odontologo.apellido + 
                        '</option>';
                    odontologosList.innerHTML += odontologoOption;
            };
        })
    }

    function resetUploadForm(){
        document.querySelector('#pacientes').value = "";
        document.querySelector('#odontologos').value = "";
        document.querySelector('#fecha').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/get_all_turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
    
});