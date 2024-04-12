document.addEventListener("DOMContentLoaded", function () {
    var ctx = document.getElementById("graficoVentas").getContext("2d");
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: vendedores,
            datasets: [{
                label: 'Liquidacion por usuario',
                backgroundColor     : 'rgba(60,141,188,0.9)',
                borderColor         : 'rgba(60,141,188,0.8)',
                pointRadius          : false,
                pointColor          : '#3b8bba',
                pointStrokeColor    : 'rgba(60,141,188,1)',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(60,141,188,1)',
                data: cantidades
            }]
        },
        options: {
            // Configura opciones adicionales según tus necesidades
        }
    });

    // grafico 2

    var ctx = document.getElementById("financiadorLiquidacion").getContext("2d");
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: financiadores,
                datasets: [{
                    label: 'Liquidacion por financiador',
                    backgroundColor     : ['#f56954', '#00a65a'],
                    borderColor         : 'rgba(60,141,188,0.8)',
                    pointRadius          : false,
                    pointColor          : '#3b8bba',
                    pointStrokeColor    : 'rgba(60,141,188,1)',
                    pointHighlightFill  : '#fff',
                    pointHighlightStroke: 'rgba(60,141,188,1)',
                    data: cantidades_financiadores
                }]
            },
            options: {
                // Configura opciones adicionales según tus necesidades
            }
        });

     // fin grafico 2

         // grafico 3

         var ctx = document.getElementById("topProvincias").getContext("2d");
             var myChart = new Chart(ctx, {
                 type: 'bar',
                 data: {
                     labels: provincias,
                     datasets: [{
                         label: 'Mayor consulta por Provincias',
                         backgroundColor     :  ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
                         borderColor         : 'rgba(60,141,188,0.8)',
                         pointRadius          : false,
                         pointColor          : '#3b8bba',
                         pointStrokeColor    : 'rgba(60,141,188,1)',
                         pointHighlightFill  : '#fff',
                         pointHighlightStroke: 'rgba(60,141,188,1)',
                         data: cantidades_provincias
                     }]
                 },
                 options: {
                     // Configura opciones adicionales según tus necesidades
                 }
             });

          // fin grafico 3

                // grafico 4

                   var ctx = document.getElementById("topSituacion_laboral").getContext("2d");
                       var myChart = new Chart(ctx, {
                           type: 'bar',
                           data: {
                               labels: situacion_laboral,
                               datasets: [{
                                   label: 'Mayor consulta por Situacion Laboral',
                                   backgroundColor     :  ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
                                   borderColor         : 'rgba(60,141,188,0.8)',
                                   pointRadius          : false,
                                   pointColor          : '#3b8bba',
                                   pointStrokeColor    : 'rgba(60,141,188,1)',
                                   pointHighlightFill  : '#fff',
                                   pointHighlightStroke: 'rgba(60,141,188,1)',
                                   data: cantidades_situacion_laboral
                               }]
                           },
                           options: {
                               // Configura opciones adicionales según tus necesidades
                           }
                       });

                    // fin grafico 4

                         // grafico 5

                                       var ctx = document.getElementById("topDependencia").getContext("2d");
                                           var myChart = new Chart(ctx, {
                                               type: 'bar',
                                               data: {
                                                   labels: dependencia,
                                                   datasets: [{
                                                       label: 'Mayor consulta por Dependencia',
                                                       backgroundColor     :  ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
                                                       borderColor         : 'rgba(60,141,188,0.8)',
                                                       pointRadius          : false,
                                                       pointColor          : '#3b8bba',
                                                       pointStrokeColor    : 'rgba(60,141,188,1)',
                                                       pointHighlightFill  : '#fff',
                                                       pointHighlightStroke: 'rgba(60,141,188,1)',
                                                       data: cantidades_dependencia
                                                   }]
                                               },
                                               options: {
                                                   // Configura opciones adicionales según tus necesidades
                                               }
                                           });

                                        // fin grafico 5

       // grafico 6

                                       var ctx = document.getElementById("topCanal").getContext("2d");
                                           var myChart = new Chart(ctx, {
                                               type: 'bar',
                                               data: {
                                                   labels: canal,
                                                   datasets: [{
                                                       label: 'Mayor consulta por Canal',
                                                       backgroundColor     :  ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
                                                       borderColor         : 'rgba(60,141,188,0.8)',
                                                       pointRadius          : false,
                                                       pointColor          : '#3b8bba',
                                                       pointStrokeColor    : 'rgba(60,141,188,1)',
                                                       pointHighlightFill  : '#fff',
                                                       pointHighlightStroke: 'rgba(60,141,188,1)',
                                                       data: cantidades_canal
                                                   }]
                                               },
                                               options: {
                                                   // Configura opciones adicionales según tus necesidades
                                               }
                                           });

                                        // fin grafico 6


});
