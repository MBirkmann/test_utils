package mdt.db.testinfra.contracts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Objects;

/**
 * This Interface provide default methods to test the implementation of {@code equals}.
 */
public interface IEqualsContractTester
{
    /**
     * Ensures that 'x', 'y' and 'z' are equal.
     *
     * @param x
     *            - the instance 'x' to test, not null.
     * @param y
     *            - the second instance 'y' to test, not null.
     * @param z
     *            - the third instance 'z' to test, not null.
     */
    public default <T> void assertParametersEquals(T x, T y, T z)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );
        Objects.requireNonNull( z, "The parameter 'z' must not be null!" );

        final boolean res1 = x.equals( y );
        final boolean res2 = y.equals( z );

        final boolean expected = true;

        assertThat( "The parameters 'x' and 'y' must be equal!", res1, is( equalTo( expected ) ) );
        assertThat( "The parameters 'y' and 'z' must be equal!", res2, is( equalTo( expected ) ) );
    }

    /**
     * According to Object.equals for any non-null reference value x, x.equals(x) should return true.
     *
     * @param x
     *            - the instance 'x' to test it is reflexive, not null.
     */
    public default <T> void assertEqualsIsReflexive(T x)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );

        final String errorMessage = "Object 'x' should be reflexibly equal to itself.";
        final boolean res = x.equals( x );
        final boolean expected = true;

        assertThat( errorMessage, res, is( equalTo( expected ) ) );

    }

    /**
     * For any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
     *
     * @param x
     *            - the first instance 'x' to test it is symmetric, not null.
     * @param y
     *            - the second instance 'y' to test it is symmetric, not null.
     */
    public default <T> void assertEqualsIsSymmetric(T x, T y)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );

        final String errorMessage = "x and y should be symetrically equal to each other.";
        final boolean res1 = x.equals( y );
        final boolean res2 = y.equals( x );

        assertThat( errorMessage, res1, is( equalTo( res2 ) ) );
    }

    /**
     * For any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z)
     * should return true.
     *
     * @param x
     *            - the first instance 'x' to test it is transitive, not null.
     * @param y
     *            - the second instance 'y' to test it is transitive, not null.
     * @param z
     *            - the third instance 'z' to test it is transitive, not null.
     */
    public default <T> void assertEqualsIsTransitive(T x, T y, T z)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );
        Objects.requireNonNull( z, "The parameter 'z' must not be null!" );

        final String errorMessage1 = "x should transitively be equal to y.";
        final String errorMessage2 = "y should transitively be equal to z.";
        final String errorMessage3 = "x should transitively be equal to z.";
        final boolean res1 = x.equals( y );
        final boolean res2 = y.equals( z );
        final boolean res3 = x.equals( z );
        final boolean expected = true;

        assertThat( errorMessage1, res1, is( equalTo( expected ) ) );
        assertThat( errorMessage2, res2, is( equalTo( expected ) ) );
        assertThat( errorMessage3, res3, is( equalTo( expected ) ) );
    }

    /**
     * For any non-null reference value x, x.equals(null) should return false.
     *
     * @param x
     *            - the instance 'x' to assert, not null.
     */
    public default <T> void assertEqualsNonNullity(T x)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );

        final String errorMessage = "x should not be equals to null!";
        final boolean res = x.equals( null );
        final boolean expected = false;

        assertThat( errorMessage, res, is( equalTo( expected ) ) );
    }

    /**
     * Test that 'x' and 'y' are different.
     *
     * @param x
     *            - the first instance 'x' to assert, not null.
     * @param y
     *            - the second instance 'y' to check against 'x', may be null.
     */
    public default <T> void assertEqualsIsDifferent(T x, T y)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );

        final String errorMessage = "x should not be equals to y.";
        final boolean res = x.equals( y );
        final boolean expected = false;

        assertThat( errorMessage, res, is( equalTo( expected ) ) );
    }
}
