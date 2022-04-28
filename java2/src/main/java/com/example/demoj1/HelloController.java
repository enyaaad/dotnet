package com.example.demoj1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.Arrays;

public class HelloController {
    public class TblContRow {
        private int cID;
        private String cInfo;
        private Object cPreview;

        public TblContRow(int id, String info, Object preview) {
            this.cID = id;
            this.cInfo = info;
            this.cPreview = preview;
        }
        public int getID(){ return this.cID; }
        public String getInfo(){ return this.cInfo; }
        public Object getPreview(){ return this.cPreview; }
    }

    File file;

    @FXML
    private Label LabelText;

    @FXML
    private TableView<TblContRow> MainTable;

    @FXML
    private TextField FileAdress;

    @FXML
    protected void onSelectFileBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        if(file != null){
            File existDirectory = file.getParentFile();
            fileChooser.setInitialDirectory(existDirectory);
        }
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*"),
                new FileChooser.ExtensionFilter("BMP files","*.bmp")
        );
        file = fileChooser.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        if (file != null) FileAdress.setText(file.getAbsolutePath());
    }

    @FXML
    protected void onLoadButtonClick() {
        if (FileAdress.getText().isEmpty()) {
            LabelText.setText("Path is empty!");
            return;
        }
        LabelText.setText("Loading file!");
        ObservableList<TblContRow> data = MainTable.getItems();
        File file = new File(FileAdress.getText());
        try {
            Image image = new Image(file.toURI().toString());
            ImageView iv = new ImageView(image);
            if (image.isError()) {
                InputStream is = new FileInputStream(file);
                data.add(new TblContRow(data.size() + 1, "Size: " + file.length() + " bytes", new TextArea(new String( is.readAllBytes(), StandardCharsets.UTF_8))));
                is.close();
            } else {
                iv.fitWidthProperty().bind(MainTable.getColumns().get(2).widthProperty().subtract(6));
                iv.setPreserveRatio(true);
                data.add(new TblContRow(data.size() + 1, "Size: " + file.length() + " bytes \r\nWidth: " + image.getWidth() + "\r\nHeight: " + image.getHeight(), iv));
            }
            MainTable.setItems(data);
            LabelText.setText("Loading complete!");
        } catch (Exception e){
                LabelText.setText(e.getMessage());
        }
    }

    @FXML
    protected void onClearButtonClick() {
        LabelText.setText("Now table empty!");
        MainTable.getItems().clear();
    }


}
