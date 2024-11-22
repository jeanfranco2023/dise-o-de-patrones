document.addEventListener('DOMContentLoaded', () => {
    const buscarClienteBtn = document.getElementById('buscarClienteBtn');

    buscarClienteBtn.addEventListener('click', () => {
        const dni = document.getElementById('dni').value.trim();

        if (!dni) {
            alert("Por favor, ingrese un DNI válido.");
            return;
        }

        fetch(`/buscar/${dni}`)
            .then(response => {
                if (!response.ok) throw new Error('Cliente no encontrado');
                return response.json();
            })
            .then(data => {
                document.getElementById("clienteNombre").textContent = `${data.nombreCliente} ${data.apellidoCliente}`;
                document.getElementById("nombreCliente").value = data.nombreCliente; // Asignar el nombre del cliente oculto
            })
            .catch(error => {
                alert(error.message);
                if (confirm("Cliente no encontrado. ¿Desea registrar un nuevo cliente?")) {
                    window.location.href = '/registrarCliente';
                }
            });
    });
});


document.addEventListener('DOMContentLoaded', () => {
    const buscarProductoBtn = document.getElementById('buscarProductoBtn');

    buscarProductoBtn.addEventListener('click', () => {
        const codigoProducto = document.getElementById('codigoProducto').value.trim();

        if (!codigoProducto) {
            alert("Por favor, ingrese un código de producto válido.");
            return;
        }

        fetch(`/producto/buscarPorCodigo/${codigoProducto}`)
            .then(response => {
                if (!response.ok) throw new Error('Producto no encontrado');
                return response.json();
            })
            .then(data => {
                document.getElementById("productoNombre").textContent = data.nombreProducto;
                document.getElementById("descripcion").textContent = data.descripcionProducto;
                document.getElementById("precio").textContent = data.precioProducto;

                const unidad = data.descripcionProducto.toLowerCase() === "herramientas" ? "unidades" : "kg";
                document.getElementById("stock").textContent = `${data.stockProducto} ${unidad}`;
            })
            .catch(error => {
                alert(error.message);
            });
    });
});