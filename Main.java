import java.lang.Math;
import java.util.stream.IntStream;

public class Main {
    
    public static void main(String[] args) {    
	// Setup constants for first array
	final int firstArraySize = 18;
	final int firstArrayMaxValue = 19;
	// Setup constants for second array
	final int secondArraySize = 11;
	final float secondArrayMin = -15.0F;
	final float secondArrayMax = 9.0F;
	// Setup constants for third array
	final int firstFunctionExpectedValue = 15;
	final int[] secondFunctionExpectedArray = {
	    2, 7, 8, 10, 11,
	    12, 14, 16, 19
	};
	
	// Arrays initialization
	int[] first = new int[firstArraySize];
	float[] second = new float[secondArraySize];
	double[][] third = new double[firstArraySize][secondArraySize];
	
	// Setup values of first array
	for (int i = 0; i < firstArraySize; ++i) {
	    first[i] = firstArrayMaxValue - i;
	}
	// Setup values of second array
	for (int i = 0; i < secondArraySize; ++i) {
	    second[i] = (float)
		(Math.random() *
		 (secondArrayMax + secondArrayMin) -
		 secondArrayMin);
	    
	}
	// Setup values of third array
	for (int i = 0; i < firstArraySize; ++i) {
	    for (int j = 0; j < secondArraySize; ++j) {
		final int value = first[i];
		final float result = second[j];
		if (value == firstFunctionExpectedValue) {
		    
		    third[i][j] = firstFunction(result);
		    
		} else if (IntStream.of(secondFunctionExpectedArray)
			   .anyMatch(val -> val == value)) {
		    
		    third[i][j] = secondFunction(result);
		    
		} else {
		    
		    third[i][j] = thirdFunction(result);
		    
		}
	    }
	}
	// Print third function to the screen
	// printToScreen(third, firstArraySize, secondArraySize);
	printFullToScreen(third,
			  first, firstArraySize,
			  second, secondArraySize);
    }
    
    static double firstFunction(final double value) {
	return Math.tan(Math.cos(powAnyValue(value,
					     (2.0/3.0) * value)));
    }
    
    static double secondFunction(final double value) {
	double dimension = 0.75 / (0.25 - Math.asin((value - 3.0) / 24.0));
	return powAnyValue((2.0 + powAnyValue(Math.tan(value),
					    Math.cos(value))) / 2.0,
			   dimension);
    }
    
    static double thirdFunction(final double value) {
	return (1.0/3.0) /
	    (powAnyValue((4.0 /
			  Math.tan((powAnyValue(value,
						(value + 1.0) / value)))),
			 3.0) - 4.0);
    }
    
    static double powAnyValue(double value, final double dimension) {
	boolean isNegative = value < 0;
	if (isNegative) {
	    value *= -1.0;
	}
	double result = Math.pow(value, dimension);
	if (isNegative) {
	    result *= -1.0;
	}
	return result;
    }

    private static void
	printToScreen(final double[][] arr,
		      final int columns,
		      final int lines) {
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < lines; ++j) {
                System.out.printf("%13.4e", arr[i][j]);
            }
            System.out.println();
        }
    }
    private static void
	printFullToScreen(final double[][] arr,
			  final int[] first,
			  final int lines,
			  final float[] second,
			  final int columns) {
	System.out.printf("    \u2503");
	for (int i = 0; i < columns; ++i) {
	    System.out.printf("%13.4e", second[i]);
	}
	System.	out.println();
	for (int i = 0; i < columns * 13 + 5; ++i) {
	    if (i == 4)
		System.out.printf("\u254B");
	    else
		System.out.printf("\u2501");
	}
	System.out.println();
        for (int i = 0; i < lines; ++i) {
	    System.out.printf("%3d \u2503", first[i]);
            for (int j = 0; j < columns; ++j) {
                System.out.printf("%13.4e", arr[i][j]);
            }
            System.out.println();
        }
    }
    
}
