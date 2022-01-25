import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InterfazController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textID;

    @FXML
    private TextField textMunicipio;

    @FXML
    private TextField textTipoCuerpo;

    @FXML
    private TextField textTipoAgua;

    @FXML
    private TextField textIrca;

    @FXML
    private Button btnIngresar;

    @FXML
    private Label labelEstado;

    @FXML
    private Label estadoRegistro;

    @FXML
    private TextArea areaObtenerDatos;

    @FXML
    private Button btnObtenerDatos;

    @FXML
    private TextArea areaProcesarDatos;

    @FXML
    private Button btnProcesarDatos;

    @FXML
    private TextField textIdBuscar;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField muestraNombre;

    @FXML
    private TextField muestraMunicipio;

    @FXML
    private TextField muestraCuerpoAgua;

    @FXML
    private TextField muestraId;

    @FXML
    private TextField muestraTipoAgua;

    @FXML
    private TextField muestraIrca;

    @FXML
    private TextField muestraPoblacional;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TextField textDensidadPoblacional;

    @FXML
    private Button btnAfeccion;

    protected static ArrayList<ObjetoGeografico> cuerpos = new ArrayList<>();

    public boolean verificarCadena(String cadena) {
        boolean aceptada = false;
        Pattern p = Pattern.compile("[a-zA-Z]*");
        Matcher m = p.matcher(cadena);
        if (m.matches()) {
            aceptada = true;
        }
        return aceptada;
    }

    public String mensajeError(TextField componente) {
        return componente.getId().substring(4, componente.getId().length()).replace("tra", "") + "\n";

    }

    public void borrarContenidoTextFlieds(int op) {

        switch (op) {
            case 1:
                this.textID.setText("");
                this.textTipoAgua.setText("");
                this.textIrca.setText("");
                this.textMunicipio.setText("");
                this.textNombre.setText("");
                this.textTipoCuerpo.setText("");
                this.textDensidadPoblacional.setText("");
                break;

            case 2:
                this.muestraCuerpoAgua.setText("");
                this.muestraId.setText("");
                this.muestraIrca.setText("");
                this.muestraMunicipio.setText("");
                this.muestraNombre.setText("");
                this.muestraTipoAgua.setText("");
                this.textIdBuscar.setText("");
                this.muestraPoblacional.setText("");
                textIdBuscar.setText("");
                break;
        }

    }

    public void ventanaEmergente(String ecabezado, String contenido, String urlImagen) {
        Alert a = new Alert(AlertType.INFORMATION);
        a.setGraphic(new ImageView(urlImagen));
        a.setTitle("Error !");
        a.setHeaderText(ecabezado);
        a.setContentText(contenido);
        a.showAndWait();
    }

    public Object[] buscarID(int id) {
        boolean encontrado = false;
        int pos = -1;
        for (int i = 0; i < cuerpos.size(); i++) {
            if (cuerpos.get(i) instanceof CuerpoDeAgua) {
                CuerpoDeAgua elCuerpo = (CuerpoDeAgua) cuerpos.get(i);
                if (elCuerpo.getId() == id) {
                    encontrado = true;
                    pos = i;
                    break;
                }
            }
        }
        Object[] v = { encontrado, pos };
        return v;
    }

    public void bloquearBotones(boolean estado) {
        btnEditar.setDisable(estado);
        btnEliminar.setDisable(estado);
    }

    public void bloquearTextFieldBusqueda(boolean estado) {
        muestraNombre.setEditable(estado);
        muestraMunicipio.setEditable(estado);
        muestraCuerpoAgua.setEditable(estado);
        muestraTipoAgua.setEditable(estado);
        muestraIrca.setEditable(estado);
        muestraId.setEditable(false);
        muestraPoblacional.setEditable(estado);
    }

    public void editarValoresArraylist(String nombre, int Id, String muni, double irca, String tipoDeCuerpo,
            String tipoDeAgua, int densidadPoblacional) {

        try {
            int idBuscar = Integer.parseInt(textIdBuscar.getText());
            CuerpoDeAgua cuerpo = (CuerpoDeAgua) cuerpos.get((int) buscarID(idBuscar)[1]);
            DensidadPoblacional dp = (DensidadPoblacional) cuerpos.get((int) buscarID(idBuscar)[1] + 1);
            // Modifica
            cuerpo.setNombre(nombre);
            cuerpo.setId(Id);
            cuerpo.setMunicipio(muni);
            cuerpo.setIrca(irca);
            cuerpo.setTipoDeCuerpo(tipoDeCuerpo);
            cuerpo.setTipoDeAgua(tipoDeAgua);
            dp.setNumeroDeHabitantes(densidadPoblacional);

        } catch (Exception e) {
            System.out.println("debe ingresar un numero");
        }

    }

    public boolean validarID(int id) {
        boolean repetido = false;
        for (ObjetoGeografico elemento : cuerpos) {
            if (elemento instanceof CuerpoDeAgua) {
                CuerpoDeAgua elCuerpo = (CuerpoDeAgua) elemento;
                if (elCuerpo.getId() == id) {
                    repetido = true;
                    break;
                }
            }

        }
        return repetido;
    }

    @FXML
    void eventoAfeccion(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Afeccion.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle(" - Numero de Habitantes y Afeccion - ");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @FXML
    void eventoBuscar(ActionEvent event) {
        if (!this.textIdBuscar.getText().isEmpty()) {
            try {
                int id = Integer.parseInt(this.textIdBuscar.getText());
                if (id >= 0) {
                    Object[] v = buscarID(id);
                    if (Boolean.parseBoolean(v[0].toString())) {
                        int pos = Integer.parseInt(v[1].toString());
                        CuerpoDeAgua elCuerpo = (CuerpoDeAgua) cuerpos.get(pos);
                        DensidadPoblacional dp = (DensidadPoblacional) cuerpos.get(pos + 1);
                        muestraCuerpoAgua.setText(elCuerpo.getTipoDeCuerpo());
                        muestraIrca.setText(elCuerpo.getIrca() + "");
                        muestraId.setText(elCuerpo.getId() + "");
                        muestraMunicipio.setText(elCuerpo.getMunicipio());
                        muestraNombre.setText(elCuerpo.getNombre());
                        muestraTipoAgua.setText(elCuerpo.getTipoDeAgua());
                        muestraPoblacional.setText(dp.getNumeroDeHabitantes() + "");
                        bloquearTextFieldBusqueda(true);
                        bloquearBotones(false);

                    } else {
                        ventanaEmergente("Error !", "el ID no se encuentra registrado", "imagenes//busqueda.png");
                    }
                } else {
                    ventanaEmergente("Numero negativo !", "debe ingresar un ID positivo", "imagenes//numeros.png");
                }

            } catch (Exception e) {
                ventanaEmergente("Error !", "debe ingresar un numero", "imagenes//numeros.png");
            }
        }
    }

    @FXML
    void eventoEditar(ActionEvent event) {
        int densPoblacional = 0;
        int ID = 0;
        double irca = 0;
        String estado = "";
        String error = "";
        // valida nombre
        String nombre = this.muestraNombre.getText();
        if (nombre.equals("") || !verificarCadena(nombre)) {
            error += mensajeError(this.muestraNombre);

        } else {
            estado += "Ok";
        }
        // valida municipio
        String municipio = this.muestraMunicipio.getText();
        if (municipio.equals("") || !verificarCadena(municipio)) {
            error += mensajeError(this.muestraMunicipio);
        } else {
            estado += "Ok";
        }
        // valida cuerpo de agua
        String tipoCuerpoAgua = this.muestraCuerpoAgua.getText();
        if (tipoCuerpoAgua.equals("") || !verificarCadena(tipoCuerpoAgua)) {
            error += mensajeError(this.muestraCuerpoAgua);
        } else {
            estado += "Ok";
        }
        // valida tipo de agua
        String tipoAgua = this.muestraTipoAgua.getText();
        if (tipoAgua.equals("") || !verificarCadena(tipoAgua)) {
            error += mensajeError(this.muestraTipoAgua);
        } else {
            estado += "Ok";
        }

        try {
            // valida el ID
            ID = Integer.parseInt(this.muestraId.getText());
            if (ID >= 0) {
                estado += "Ok";
            } else if (ID < 0) {
                error += "No se Permite un ID con valor Negativo\n";
            }
        } catch (NumberFormatException e) {
            error += mensajeError(this.muestraId);

        }

        try {
            // valida el irca
            irca = Float.parseFloat(this.muestraIrca.getText());
            if (irca >= 0) {
                estado += "Ok";
            } else if (irca < 0) {
                error += "No se Permite un irca con valor Negativo\n";
            }

        } catch (NumberFormatException e) {
            error += mensajeError(this.muestraIrca);
        }

        try {
            // valida el densidad poblacional
            densPoblacional = Integer.parseInt((this.muestraPoblacional.getText()));
            if (densPoblacional >= 0) {
                estado += "Ok";
            } else if (densPoblacional < 0) {
                error += "No se Permite una densidad poblacional con valor Negativo\n";
            }

        } catch (NumberFormatException e) {
            error += mensajeError(this.muestraPoblacional);
        }

        if (estado.equals("OkOkOkOkOkOkOk")) {
            editarValoresArraylist(nombre, ID, municipio, irca, tipoCuerpoAgua, tipoAgua, densPoblacional);
            borrarContenidoTextFlieds(2);
            ventanaEmergente("Editado correctamente", "", "imagenes//comprobado.png");
            bloquearTextFieldBusqueda(false);
            bloquearBotones(true);
            textIdBuscar.requestFocus();
        } else {
            ventanaEmergente("Error en los siquientes campos", error, "imagenes//error.png");
        }

    }

    @FXML
    void eventoEliminar(ActionEvent event) {

        if (!this.textIdBuscar.getText().isEmpty()) {
            try {
                int id = Integer.parseInt(textIdBuscar.getText());
                if (id >= 0) {
                    Object tempo[] = buscarID(id);
                    if ((boolean) tempo[0]) {
                        cuerpos.remove((int) tempo[1]);
                        cuerpos.remove((int) tempo[1]);
                        borrarContenidoTextFlieds(2);
                        ventanaEmergente("Eliminado correctamente", "", "imagenes//comprobado.png");
                        bloquearTextFieldBusqueda(false);
                        bloquearBotones(true);
                        textIdBuscar.requestFocus();
                    }
                } else {
                    ventanaEmergente("Numero negativo !", "debe ingresar un ID positivo", "imagenes//numeros.png");
                    borrarContenidoTextFlieds(2);
                    ventanaEmergente("Eliminado correctamente", "", "imagenes//comprobado.png");
                }

            } catch (Exception e) {
                ventanaEmergente("Error !", "debe ingresar un numero", "imagenes//numeros.png");
            }
        }

    }

    @FXML
    void eventoIngresar(ActionEvent event) {
        int densPoblacional = 0;
        int ID = -1;
        double irca = 0;
        String estado = "";
        String error = "";
        // valida nombre
        String nombre = this.textNombre.getText();
        if (nombre.equals("") || !verificarCadena(nombre)) {
            error += mensajeError(textNombre);

        } else {
            estado += "Ok";
        }
        // valida municipio
        String municipio = this.textMunicipio.getText();
        if (municipio.equals("") || !verificarCadena(municipio)) {
            error += mensajeError(textMunicipio);
        } else {
            estado += "Ok";
        }
        // valida cuerpo de agua
        String tipoCuerpoAgua = this.textTipoCuerpo.getText();
        if (tipoCuerpoAgua.equals("") || !verificarCadena(tipoCuerpoAgua)) {
            error += mensajeError(textTipoCuerpo);
        } else {
            estado += "Ok";
        }
        // valida tipo de agua
        String tipoAgua = this.textTipoAgua.getText();
        if (tipoAgua.equals("") || !verificarCadena(tipoAgua)) {
            error += mensajeError(textTipoAgua);
        } else {
            estado += "Ok";
        }

        try {
            // valida el irca
            irca = Float.parseFloat(this.textIrca.getText());
            if (irca >= 0) {
                estado += "Ok";
            } else if (irca < 0) {
                error += "No se Permite un irca con valor Negativo\n";
            }

        } catch (NumberFormatException e) {
            error += mensajeError(textIrca);
        }

        try {
            // valida el ID
            ID = Integer.parseInt(this.textID.getText());
            if (ID >= 0 && !validarID(ID)) {
                estado += "Ok";
            } else if (ID < 0) {
                error += "No se Permite un ID con valor Negativo\n";
            } else {
                error += "El ID ya se encuentra registrado\n";
            }

        } catch (NumberFormatException e) {
            error += mensajeError(textID);
        }

        try {
            // valida el densidad poblacional
            densPoblacional = Integer.parseInt((this.textDensidadPoblacional.getText()));
            if (densPoblacional >= 0) {
                estado += "Ok";
            } else if (densPoblacional < 0) {
                error += "No se Permite una densidad poblacional con valor Negativo\n";
            }

        } catch (NumberFormatException e) {
            error += mensajeError(this.textDensidadPoblacional);
        }

        if (estado.equals("OkOkOkOkOkOkOk")) {
            cuerpos.add(new CuerpoDeAgua(nombre, ID, municipio, irca, tipoAgua, tipoCuerpoAgua));
            cuerpos.add(new DensidadPoblacional(densPoblacional));
            borrarContenidoTextFlieds(1);
        } else {
            ventanaEmergente("Error en los siquientes campos", error, "imagenes//error.png");
        }

    }

    @FXML
    void eventoObtenerDatos(ActionEvent event) {
        String salida = "";
        for (int i = 0; i < cuerpos.size() - 1; i += 2) {
            salida += cuerpos.get(i).toString() + cuerpos.get(i + 1).toString() + "\n";
        }
        areaObtenerDatos.setText(salida);
        btnProcesarDatos.setDisable(false);
    }

    @FXML
    void eventoProcesarDatos(ActionEvent event) {
        String salida = "";
        float cont = 0;
        double maximo = 0;
        ArrayList<String> cuerpoNivelAlto = new ArrayList<>();
        for (int i = 0; i < cuerpos.size(); i++) {
            if (cuerpos.get(i) instanceof CuerpoDeAgua) {
                CuerpoDeAgua elCuerpo = (CuerpoDeAgua) cuerpos.get(i);
                salida += salida_1(elCuerpo);
                cont = salida_2(elCuerpo, cont);
                cuerpoNivelAlto = riesgoAlto(elCuerpo, cuerpoNivelAlto);
                maximo = maximoNivel(elCuerpo, maximo);
                // los metodos se encuentra debajo
            }
        }
        salida += new DecimalFormat("0.00").format(cont) + "\n";
        if (cuerpoNivelAlto.size() == 0) {
            salida += "NA\n";
        } else {
            salida += cuerpoNivelAlto.toString().replace("[", "").replace("]", "").replace(",", "") + "\n";
        }
        salida += CuerpoDeAgua.nivel(maximo) + "\n" + "\n";
        areaProcesarDatos.setText(salida);
    }

    public String salida_1(CuerpoDeAgua cuerpo) {
        return cuerpo.getNombre() + " " + cuerpo.getMunicipio() + "\n";
    }

    public float salida_2(CuerpoDeAgua cuerpo, float contador) {
        double irca = cuerpo.getIrca();
        if (irca > 35 && irca <= 100) {
            contador++;
        }
        return contador;
    }

    public ArrayList<String> riesgoAlto(CuerpoDeAgua cuerpo, ArrayList<String> cAltos) {

        if (CuerpoDeAgua.nivel(cuerpo.getIrca()).equals("ALTO")) {
            cAltos.add(cuerpo.getMunicipio());
        }
        return cAltos;
    }

    public double maximoNivel(CuerpoDeAgua cuerpo, double max) {
        if (cuerpo.getIrca() > max) {
            max = cuerpo.getIrca();
        }

        return max;
    }

    @FXML
    void initialize() {

        bloquearTextFieldBusqueda(false);
        bloquearBotones(true);
        btnProcesarDatos.setDisable(true);

        // agregando estilo a los demas botones
        btnEditar.setStyle(btnIngresar.getStyle());
        btnObtenerDatos.setStyle(btnIngresar.getStyle());
        btnProcesarDatos.setStyle(btnIngresar.getStyle());
        btnBuscar.setStyle(btnIngresar.getStyle());
        btnEliminar.setStyle(btnIngresar.getStyle());
        btnAfeccion.setStyle(btnIngresar.getStyle());

        textNombre.requestFocus();

        // cargue para pruebas
        cuerpos.add(new CuerpoDeAgua("Mallorquin", 0, "Barranquilla", 30, "Dulce", "Cienaga"));
        cuerpos.add(new DensidadPoblacional(30000));
        cuerpos.add(new CuerpoDeAgua("Magdalena", 1, "Barranquilla", 40, "Dulce", "Rio"));
        cuerpos.add(new DensidadPoblacional(9700));
        cuerpos.add(new CuerpoDeAgua("Magdalena", 2, "Cartagena", 50, "Dulce", "Rio"));
        cuerpos.add(new DensidadPoblacional(8000));
        cuerpos.add(new CuerpoDeAgua("Magdalena", 3, "Cordoba", 90, "Dulce", "Rio"));
        cuerpos.add(new DensidadPoblacional(8500));

    }
}