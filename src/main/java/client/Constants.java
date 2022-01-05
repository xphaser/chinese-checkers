package client;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Constants {
    public static final RadialGradient COLOR_WHITE = new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.DARKGREY));
    public static final RadialGradient COLOR_RED = new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.RED));
    public static final RadialGradient COLOR_YELLOW = new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.GOLD));
    public static final RadialGradient COLOR_GREEN = new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.GREEN));
    public static final RadialGradient COLOR_BLUE = new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.BLUE));
    public static final RadialGradient COLOR_BLACK = new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.LIGHTGREY), new Stop(1, Color.BLACK));
}
