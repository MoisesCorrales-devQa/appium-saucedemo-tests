package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.CartPage;

import static stepdefinitions.Hooks.driver;
import static stepdefinitions.Hooks.context;

public class CartSteps {

    private final CartPage cartPage = new CartPage(driver);
    private String productNameAdded;

    @Given("que el usuario está en la pantalla del catálogo")
    public void el_usuario_esta_en_catalogo() {
        Assert.assertTrue(cartPage.estaEnPantallaCatalogo());
    }

    @When("el usuario añade el primer producto al carrito")
    public void el_usuario_anade_producto() {
        productNameAdded = cartPage.addProductToCartAndGo(0);
        context.setContext("addedProduct", productNameAdded);

        double cartPrice = cartPage.obtenerPrecioTotalEnCarrito();
        context.setContext("cartPrice", cartPrice);
    }

    @Then("el producto debe estar visible en el carrito")
    public void el_producto_debe_estarse_en_el_carrito() {
        Assert.assertTrue(cartPage.productoEstaEnCarrito(productNameAdded));
    }

    @Given("que el usuario añadió un producto al carrito")
    public void el_usuario_añade_un_producto_para_eliminar() {
        productNameAdded = cartPage.addProductToCartAndGo(0);
    }

    @When("el usuario elimina el producto del carrito")
    public void el_usuario_elimina_producto() {
        cartPage.eliminarProductoDelCarrito();
    }

    @Then("el carrito debe estar vacío")
    public void el_carrito_debe_estar_vacio() {
        Assert.assertTrue(cartPage.carritoVacio());
    }

    @When("el usuario añade múltiples productos al carrito")
    public void el_usuario_anade_multiples_productos() {
        cartPage.addMultiplesProductos(3); // O el número que desees
    }

    @Then("la suma total debe coincidir con el total mostrado en el carrito")
    public void el_total_debe_coincidir() {
        double totalCalculado = cartPage.calcularTotalProductos();
        double totalMostrado = cartPage.obtenerTotalMostradoEnCarrito();
        Assert.assertEquals(totalCalculado, totalMostrado, 0.01);
    }
}