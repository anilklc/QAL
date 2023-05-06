module qal {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;
	opens Models;
	opens application to javafx.graphics, javafx.fxml;
}
