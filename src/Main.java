import javax.swing.SwingUtilities;
import controlador.ProductoController;
import vista.ProductoView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductoController(new ProductoView(new ProductoController(null))));
    }
}
