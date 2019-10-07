$(document).ready(function() {
    consumirMarcaApi();
});

var arrayMarca = [];

function consumirMarcaApi() {
    var select = document.getElementById("marca");
    $.getJSON("https://parallelum.com.br/fipe/api/v1/carros/marcas\n" +
        "\n").done(function(data) {

        $.each(data, function(i, v) {
            var option = document.createElement("option");
            option.textContent = v.nome;
            option.value = v.codigo;
            select.appendChild(option);
        })
    });
}

function consumirModeloApi(codigo) {
    var selectModelo = document.getElementById("modelo");
    $.getJSON("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + parseInt(codigo) + "/modelos")
        .done(function(data) {

            for (let i = 0; i < data.modelos.length; i++) {
                var option = document.createElement("option");
                option.textContent = data.modelos[i].nome;
                option.value = data.modelos[i].codigo;
                selectModelo.appendChild(option);
                const objeto = {
                    codigoMarca: codigo,
                    codigoModelo: data.modelos[i].codigo,
                };
                arrayMarca.push(objeto);
            }
        });
}

function getCodigoMarca(codigoModelo) {
    for (let i = 0; i < arrayMarca.length; i++) {
        if (codigoModelo == arrayMarca[i].codigoModelo) {
            return arrayMarca[i].codigoMarca;
        }
    }
    return 0;
}

function consumirAnoApi(codigoMarca, codigoModelo) {
    var selectAno = document.getElementById("anoModelo");
    $.getJSON("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + parseInt(codigoMarca) +
            "/modelos/" + parseInt(codigoModelo) + "/anos")
        .done(function(data) {
            for (let i = 0; i < data.length; i++) {
                var option = document.createElement("option");
                option.textContent = data[i].nome;
                option.value = data[i].codigo;
                selectAno.appendChild(option);
            }

        });
}

function retornaVeiculoApi(codigoMarca, codigoModelo, ano) {
    $.getJSON("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + parseInt(codigoMarca) + "/modelos/" + parseInt(codigoModelo) + "/anos/" + ano)
        .done(function(data) {
            console.log(data);
            veiculo(data);
        });
}

function veiculo(objeto) {
    let modelo = document.getElementById("valor-modelo"); // acessando o id do input no formulario
    let marca = document.getElementById("valor-marca"); // acessando o id do input no formulario 
    let valor = document.getElementById("valor-Valor");
    let ano = document.getElementById("valor-ano");
    let combustivel = document.getElementById("valor-combustivel");
    
    modelo.value = objeto.Modelo; // atribuindo no input nome o valor nome do veiculo
    marca.value = objeto.Marca; // atribuindo no input marca o valor marca do veiculo 
    valor.value = objeto.Valor;
    ano.value = objeto.AnoModelo;
    combustivel.value = objeto.Combustivel;
    
}



$("#marca").click(function() {
    consumirModeloApi($(this).val());
});

$("#modelo").click(function() {
    consumirAnoApi(getCodigoMarca($(this).val()), $(this).val());
});

$("#salva").click(function(event) {
    event.preventDefault();



    retornaVeiculoApi($("#marca").val(), $("#modelo").val(), $("#anoModelo").val())

});