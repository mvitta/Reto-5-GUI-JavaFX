import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class AfeccionController extends InterfazController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea area;

    @FXML
    private TableView<Datos> tabla;

    @FXML
    private TableColumn<Datos, String> colID;

    @FXML
    private TableColumn<Datos, String> colNombre;

    @FXML
    private TableColumn<Datos, String> colMunicipio;

    @FXML
    private TableColumn<Datos, String> colTipoDeAgua;

    @FXML
    private TableColumn<Datos, String> colTipoDeCuerpo;

    @FXML
    private TableColumn<Datos, String> colIrca;

    @FXML
    private TableColumn<Datos, String> colHabitantes;

    @FXML
    private TableColumn<Datos, String> colAfeccion;

    @FXML
    void initialize() {

        ObservableList<Datos> datos = FXCollections.observableArrayList();
        colID.setCellValueFactory(new PropertyValueFactory<Datos, String>("Id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Datos, String>("nombre"));
        colMunicipio.setCellValueFactory(new PropertyValueFactory<Datos, String>("municipio"));
        colIrca.setCellValueFactory(new PropertyValueFactory<Datos, String>("irca"));
        colTipoDeAgua.setCellValueFactory(new PropertyValueFactory<Datos, String>("tipoDeAgua"));
        colTipoDeCuerpo.setCellValueFactory(new PropertyValueFactory<Datos, String>("tipoDeCuerpo"));
        colHabitantes.setCellValueFactory(new PropertyValueFactory<Datos, String>("numeroDeHabitantes"));
        colAfeccion.setCellValueFactory(new PropertyValueFactory<Datos, String>("afeccion"));
        for (int i = 0; i < cuerpos.size() - 1; i += 2) {
            CuerpoDeAgua c = (CuerpoDeAgua) cuerpos.get(i);
            DensidadPoblacional d = (DensidadPoblacional) cuerpos.get(i + 1);
            String nombre = c.getNombre();
            String municipio = c.getMunicipio();
            int Id = c.getId();
            double irca = c.getIrca();
            String tipoDeAgua = c.getTipoDeAgua();
            String tipoDeCuerpo = c.getTipoDeCuerpo();
            int numeroDeHabitantes = d.getNumeroDeHabitantes();
            int afec = d.afeccion();
            datos.add(new Datos(nombre, Id, municipio, irca, tipoDeAgua, tipoDeCuerpo, numeroDeHabitantes, afec));
        }
        tabla.setItems(datos);
    }

}
