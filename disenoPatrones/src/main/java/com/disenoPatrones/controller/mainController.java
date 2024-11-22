package com.disenoPatrones.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.disenoPatrones.dto.Login;
import com.disenoPatrones.entity.DetalleVenta;
import com.disenoPatrones.entity.Producto;
import com.disenoPatrones.entity.Venta;
import com.disenoPatrones.entity.cliente;
import com.disenoPatrones.entity.usuario;
import com.disenoPatrones.repository.ProductoRepository;
import com.disenoPatrones.repository.VentaRepository;
import com.disenoPatrones.repository.UsuarioRepository;
import com.disenoPatrones.repository.clienteRepository;
import com.disenoPatrones.repository.detalleVentaReporsitory;
import com.disenoPatrones.service.UsuarioService;
import com.disenoPatrones.service.clienteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class mainController {

    @Autowired
    private UsuarioRepository usuarioRepository;

     @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoRepository productoRepository; 

    @Autowired
    private VentaRepository VentaRepository;

    @Autowired
    private clienteRepository clienteRepository;

    @Autowired
    private clienteService clienteService;

    @Autowired
    private detalleVentaReporsitory detalleVentaRepository;

    private usuario usuarioIniciado;



    //Login
@GetMapping("/index")
    public String verVista(Model model) {
        model.addAttribute("login", new Login());
        return "index";
    }

    @PostMapping("/login")
    public String postMethodName(@ModelAttribute("login") Login login) {
        System.out.println(login);

        Optional<usuario> usuarioOptional = usuarioService.findByEmail(login.getEmail());

        if (usuarioOptional.isPresent()) {
            usuario usuario = usuarioOptional.get();
            String password = usuario.getPassword();
            boolean isAdmin = usuario.getIsAdmin();

            if (password.equals(login.getPassword())) {
                usuarioIniciado = usuario;
                if (isAdmin == true) {
                    return "redirect:/principalAdmin";
                } else {
                    return "redirect:/registroVentas";
                }
            }
        }

        return "redirect:/index";
    }




    //Registro de ventas - principal usuario
    @GetMapping("/registroVentas")
    public String mostrarVistaVentas(Model model) {
        List<cliente> clientes = clienteRepository.findAll();
        List<Producto> productos = productoRepository.findAll();

        // Agregar datos al modelo para que estén disponibles en la vista
        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);

        return "principalUsuario";
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<cliente> buscarCliente(@PathVariable String dni) {
        cliente cliente = clienteService.findByDni(dni);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/producto/buscarPorCodigo/{codigoProducto}")
    public ResponseEntity<?> buscarProductoPorCodigo(@PathVariable String codigoProducto) {
    try {
        Producto producto = productoRepository.findByCodigoProducto(codigoProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el código: " + codigoProducto));
        
        // Crear respuesta en formato JSON
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombreProducto", producto.getNombreProducto());
        respuesta.put("descripcionProducto", producto.getDescripcionProducto());
        respuesta.put("precioProducto", producto.getPrecioProducto());
        respuesta.put("stockProducto", producto.getStockProducto());

        return ResponseEntity.ok(respuesta);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    }

    @PostMapping("/registra/venta")
    public String registrarVenta(
            @RequestParam String nombreCliente, 
            @RequestParam String productosJson,
            @RequestParam String fecha) {
    
                System.out.println("Nombre del cliente recibido: " + nombreCliente);
    
        try {
            // Validar datos de entrada
            if (nombreCliente == null || nombreCliente.isEmpty()) {
                throw new RuntimeException("El nombre del cliente no puede estar vacío.");
            }
            if (productosJson == null || productosJson.isEmpty()) {
                throw new RuntimeException("La lista de productos no puede estar vacía.");
            }
            if (fecha == null || fecha.isEmpty()) {
                throw new RuntimeException("La fecha de la venta no puede estar vacía.");
            }
    
            // Recuperar cliente
            cliente cliente = clienteRepository.findByNombreCliente(nombreCliente)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + nombreCliente));
    
            // Recuperar usuario logueado
            if (usuarioIniciado == null) {
                throw new RuntimeException("No hay un usuario logueado actualmente.");
            }
    
            ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> productos = objectMapper.readValue(productosJson, new TypeReference<List<Map<String, Object>>>() {});
                System.out.println("Productos recibidos: " + productos);
    
            // Crear venta
            Venta venta = new Venta();
            venta.setCliente(cliente);
            venta.setUsuario(usuarioIniciado); // Asociar el usuario logueado
            venta.setNombreUsuario(usuarioIniciado.getNombre()+" "+usuarioIniciado.getApellido()); // Guardar el nombre del usuario
            venta.setFecha(Date.valueOf(fecha));
            venta.setMontoVenta(0); // Se calculará más adelante
    
            Venta ventaGuardada = VentaRepository.save(venta);
    
            double totalVenta = 0;
                for (Map<String, Object> productoData : productos) {
                    String nombreProducto = (String) productoData.get("nombre");
                    double precio = ((Number) productoData.get("precio")).doubleValue();
                    int cantidad = (int) productoData.get("cantidad");
                    double total = ((Number) productoData.get("total")).doubleValue();
        
                    Producto producto = productoRepository.findByNombreProducto(nombreProducto)
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + nombreProducto));
        
                    if (producto.getStockProducto() < cantidad) {
                        throw new RuntimeException("Stock insuficiente para el producto: " + nombreProducto);
                    }
        
                    DetalleVenta detalleVenta = new DetalleVenta();
                    detalleVenta.setVenta(ventaGuardada);
                    detalleVenta.setProducto(producto);
                    detalleVenta.setCantidad(cantidad);
                    detalleVenta.setPrecioUnitario(precio);
                    detalleVenta.setTotal(total);
        
                    detalleVentaRepository.save(detalleVenta);
        
                    producto.setStockProducto(producto.getStockProducto() - cantidad);
                    productoRepository.save(producto);
        
                    totalVenta += total;
                }
                ventaGuardada.setMontoVenta(totalVenta);
                VentaRepository.save(ventaGuardada);
    
    
            return "redirect:/registroVentas";
        } catch (Exception e) {
            System.err.println("Error al registrar la venta: " + e.getMessage());
            throw new RuntimeException("Error al registrar la venta: " + e.getMessage());
        }
    }

    //Registro de usuarios - empelados
    @GetMapping("/registro")
    public String mostrarVistaRegistro(Model model) {
        model.addAttribute("usuario", new usuario());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "registro";
    }

    @PostMapping("/usuarios/save")
    public String guardarOActualizarUsuario(@ModelAttribute("usuario") usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch(Exception e) {
            return "redirect:/registro";
        }
        return "redirect:/registro";
    }

    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
        return "redirect:/registro";
    }
    
    @GetMapping("/usuarios/edit/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
        usuario usuario = usuarioRepository.findById(id)
                               .orElseThrow(() -> new IllegalArgumentException("ID de usuario inválido:" + id));
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "registro";
    }

    

    //Principal Administrador
    @GetMapping("/principalAdmin")
    public String principalAdmin(Model model) {
        model.addAttribute("usuario", usuarioIniciado);
        List<Venta> ventas = VentaRepository.findAll();
        Map<String, Double> ventasPorCliente = ventas.stream()
        .collect(Collectors.groupingBy(
            venta -> venta.getCliente().getNombreCliente(),
            Collectors.summingDouble(Venta::getMontoVenta)
        ));

    model.addAttribute("ventasPorCliente", ventasPorCliente);
    return "principalAdmin";
    }


    //Registro de productos
    @GetMapping("/registroProducto")
    public String registrarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productoRepository.findAll());
        return "registroDeProductos";
    }

    @PostMapping("/registrar/producto")
    public String guardarOActualizarProducto(@ModelAttribute("producto") Producto producto) {
        try {
            productoRepository.save(producto);
        } catch(Exception e) {
            return "redirect:/registroProducto";
        }
        return "redirect:/registroProducto";
        }

        @GetMapping("/productos/edit/{id}")
        public String editarProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoRepository.findById(id)
                       .orElseThrow(() -> new IllegalArgumentException("ID de producto inválido:" + id));
        model.addAttribute("producto", producto);
        model.addAttribute("productos", productoRepository.findAll());
        return "registroDeProductos";
        }

        @PostMapping("/productos/updateStock")
        public String actualizarStockProducto(@RequestParam("id") Integer id, @RequestParam("stockIncremento") Integer stockIncremento) {
            Producto productoExistente = productoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID de producto inválido: " + id));
            
            // Incrementar el stock actual con el valor proporcionado
            productoExistente.setStockProducto(productoExistente.getStockProducto() + stockIncremento);
            productoRepository.save(productoExistente);
            
            return "redirect:/registroProducto";
        }



    //Registro de clientes
        @GetMapping("/registrarCliente")
        public String registrarCliente(Model model) {
        model.addAttribute("cliente", new cliente());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "registroCliente";
    }

    @PostMapping("/registrar/cliente")
    public String guardarOActualizarCliente(@ModelAttribute("cliente") cliente cliente) {
        try {
            clienteRepository.save(cliente);
        } catch(Exception e) {
            return "redirect:/registrarCliente";
        }
        return "redirect:/registrarCliente";
    }

    @PostMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/registrarCliente";
    }

    @GetMapping("/clientes/edit/{id}")
    public String editarCliente(@PathVariable Integer id, Model model) {
        cliente cliente = clienteRepository.findById(id)
                               .orElseThrow(() -> new IllegalArgumentException("ID de cliente inválido:" + id));
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteRepository.findAll());
        return "registroCliente";
    }
    
}
