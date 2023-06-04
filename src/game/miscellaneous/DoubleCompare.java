// 206573289 Omri Levi


package game.miscellaneous;


/**
 * A utility class that contains static methods for performing various operations on double values.
 */
public class DoubleCompare {
    /**
     * The threshold value used to determine equality between two double values.
     */
    public static final double EPSILON = 0.00001;

    /**
     * Checks if two double values are equal up to a certain threshold value.
     *
     * @param num1 the first double value
     * @param num2 the second double value
     * @return true if the two values are equal, false otherwise
     */
    public static boolean equals(double num1, double num2) {
        return difference(num1, num2) == 0;
    }

    /**
     * Calculates the absolute difference between two double values.
     *
     * @param num1 the first double value
     * @param num2 the second double value
     * @return the absolute difference between the two values, or 0 if the difference is below the threshold
     */
    public static double difference(double num1, double num2) {
        return Math.abs(num1 - num2) < EPSILON ? 0 : Math.abs(num1 - num2);
    }

    /**
     * Subtracts one double value from another and returns the result.
     *
     * @param num1 the first double value
     * @param num2 the second double value
     * @return the result of subtracting num2 from num1, or 0 if the difference is below the threshold
     */
    public static double subtract(double num1, double num2) {
        double num = num1 - num2;
        return -EPSILON < num && num < EPSILON ? 0 : num;
    }

    /**
     * Finds if a double is between two other doubles.
     *
     * @param start the starting double value
     * @param middle the middle double value
     * @param end the ending double value
     * @return true if the middle double value is between the starting and ending double values, false otherwise.
     */
    public static boolean between(double start, double middle, double end) {
        if (start > end) {
            return DoubleCompare.subtract(start, middle) >= 0 && DoubleCompare.subtract(middle, end) >= 0;
        } else {
            return DoubleCompare.subtract(end, middle) >= 0 && DoubleCompare.subtract(middle, start) >= 0;
        }
    }
}
