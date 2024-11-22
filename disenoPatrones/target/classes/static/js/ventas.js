document.addEventListener("DOMContentLoaded", () => {
    if (typeof ventasPorCliente === 'undefined' || !ventasPorCliente) {
        console.error("No se encontraron datos para el gráfico");
        return;
    }

    // Extraer etiquetas y valores de los datos
    const labels = Object.keys(ventasPorCliente); // Nombres de clientes
    const values = Object.values(ventasPorCliente); // Montos totales

    // Crear gráfico de torta
    const ctx = document.getElementById('ventasChart').getContext('2d');
    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                label: 'Ventas por Cliente',
                data: values,
                backgroundColor: [
                    '#FF6384',
                    '#36A2EB',
                    '#FFCE56',
                    '#4CAF50',
                    '#FF5733'
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top'
                },
                tooltip: {
                    callbacks: {
                        label: function (context) {
                            return `${context.label}: $${context.raw.toFixed(2)}`;
                        }
                    }
                }
            }
        }
    });
});
