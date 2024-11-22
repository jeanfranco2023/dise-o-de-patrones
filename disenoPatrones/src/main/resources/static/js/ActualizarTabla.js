document.addEventListener("DOMContentLoaded", () => {
    const agregarBtn = document.getElementById("agregarBtn");
    const tablaDetalles = document.getElementById("tablaDetalles");
    const productosJson = document.getElementById("productosJson");

    agregarBtn.addEventListener("click", () => {
        const productoNombre = document.getElementById("productoNombre").textContent.trim();
        const precio = parseFloat(document.getElementById("precio").textContent.trim()) || 0;
        const cantidad = parseInt(document.getElementById("cantidad").value.trim()) || 0;
        const total = precio * cantidad;

        if (!productoNombre || cantidad <= 0 || precio <= 0) {
            alert("Por favor, complete los datos correctamente antes de agregar.");
            return;
        }

        const nuevaFila = document.createElement("tr");
        nuevaFila.innerHTML = `
            <td>${productoNombre}</td>
            <td>${precio.toFixed(2)}</td>
            <td>${cantidad}</td>
            <td>${total.toFixed(2)}</td>
        `;
        tablaDetalles.appendChild(nuevaFila);

        const productos = Array.from(tablaDetalles.querySelectorAll('tr')).map(row => {
            const cells = row.querySelectorAll('td');
            return {
                nombre: cells[0].textContent,
                precio: parseFloat(cells[1].textContent),
                cantidad: parseInt(cells[2].textContent),
                total: parseFloat(cells[3].textContent)
            };
        });

        productosJson.value = JSON.stringify(productos);
        document.getElementById("cantidad").value = "";
    });
});