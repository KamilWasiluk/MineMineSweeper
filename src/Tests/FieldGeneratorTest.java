package Tests;

import Fields.*;
import GUI.Window;
public class FieldGeneratorTest {

    public static void main(String[] args) {
        Window window = new Window();
        new FieldGenerator(window).getAdjacents();
    }
}
