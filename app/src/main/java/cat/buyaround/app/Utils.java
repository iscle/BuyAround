package cat.buyaround.app;

import java.text.DecimalFormat;

public class Utils {
    public static String floatToString(float value) {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getNumberInstance();
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        return decimalFormat.format(value);
    }
}
