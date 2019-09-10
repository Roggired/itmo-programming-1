import java.lang.Math;
import java.util.stream.IntStream;

public class Main {
    
    public static void main(String[] args) {
	
	// Setup constants for first array
	final int firstArraySize = 10;
	// Setup constants for second array
	final int secondArraySize = 10;
	final double secondArrayMin = -5.0;
	final double secondArrayMax = 9.0;
	// Setup constants for third array
	final int firstFunctionExpectedValue = 11;
	final int[] secondFunctionExpectedArray = {
	    7, 15, 17, 21, 23
	};
	
	// Arrays initialization
	long[] first = new long[firstArraySize];
	double[] second = new double[secondArraySize];
	double[][] third = new double[firstArraySize][secondArraySize];
	
	// Setup values of first array
	for (int i = 0; i < firstArraySize; ++i) {
	    first[i] = setupFirstArray(i);
	}
	// Setup values of second array
	for (int i = 0; i < secondArraySize; ++i) {
	    second[i] = (double)
		(Math.random() *
		 (secondArrayMax - secondArrayMin) +
		 secondArrayMin);
	    
	}
	// Setup values of third array
	for (int i = 0; i < firstArraySize; ++i) {
	    for (int j = 0; j < secondArraySize; ++j) {
		final long value = first[i];
		final double result = second[j];
		if (value == firstFunctionExpectedValue) {
		    
		    third[i][j] = executeFirstFunction(result);
		    
		} else if (IntStream.of(secondFunctionExpectedArray)
			   .anyMatch(val -> val == value)) {
		    
		    third[i][j] = executeSecondFunction(result);
		    
		} else {
		    
		    third[i][j] = executeThirdFunction(result);
		    
		}
	    }
	}
	// Print third function to the screen
	// printTwoDimensionalArrayToScreen(third, firstArraySize, secondArraySize);
        printTwoDimensionalArrayToScreenWithTable(third,
			  first, firstArraySize,
			  second, secondArraySize);
    }

    static long setupFirstArray
	(final int value) {
	
	return (value << 1) + 5; 
    }
    
    static double executeFirstFunction
	(final double value) {

	return Math.atan
	    (Math.cos
	     (Math.log
	      (powAnyValue
	       (Math.sin(value), 2.0D))));
    }
    
    static double executeSecondFunction
	(final double value) {

	return Math.tan
	    (Math.cos
	     (Math.asin
	      ((value + 2.0D) / 14.0D)));
    }
    
    static double executeThirdFunction
	(final double value) {
	
	return Math.asin
	    (Math.sin
	     (Math.asin
	      (Math.sin
	       (Math.log
		(powAnyValue
		 (2.0D / Math.abs(value), value))))));
    }
    
    static double powAnyValue
	(double value,
	 final double dimension) {
	
	boolean isNegative = value < 0 && Math.pow(value, dimension) == Double.NaN;
	if (isNegative) {
	    value *= -1.0;
	}
	double result = Math.pow(value, dimension);
	if (isNegative) {
	    result *= -1.0;
	}
	return result;
    }

    private static void printTwoDimensionalArrayToScreen
	(final double[][] arr,
	 final int columns,
	 final int lines) {
	
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < lines; ++j) {
                System.out.printf("%13.4e", arr[i][j]);
            }
            System.out.println();
        }
    }
    private static void printTwoDimensionalArrayToScreenWithTable
	(final double[][] arr,
	 final long[] first,
	 final int lines,
	 final double[] second,
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
