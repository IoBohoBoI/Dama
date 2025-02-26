module dama.game.dama {
    requires javafx.controls;
    requires javafx.fxml;


    opens dama.game to javafx.fxml;
    exports dama.game;
}