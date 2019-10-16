package mdt.db.testinfra.contracts;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import java.util.Objects;

/**
 * This Interface provide default methods to test the implementation of {@code compareTo}.
 */
public interface IComparableTester
{
    /**
     * Ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. This implies that x.compareTo(y) must throw an
     * exception if y.compareTo(x) throws an exception.
     * @param <T>
     *
     * @param x
     *            - the first instance 'x' to test, not null.
     * @param y
     *            - the second instance 'y' to test, not null.
     */
    public default <T extends Comparable<T>> void assertComparisonReversal(T x, T y)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );

        final double res1 = Math.signum( x.compareTo( y ) );
        final double res2 = -Math.signum( y.compareTo( x ) );

        assertThat( "Comparison reversal should apply: sgn(o1.compareTo(o2)) == -sgn(o2.compareTo(o1)). ",
                res1, is( closeTo( res2, 0.01 ) ) );
    }

    /**
     * The comparator should be consistent with equals if and only if x.compareTo(y) == 0 has the same boolean value as
     * x.equals(y) for every x and y of class C
     *
     * @param x
     *            - the first instance 'x' to test consistency with equals, not null.
     * @param y
     *            - the second instance 'y' to test consistency with equals, not null.
     */
    public default <T extends Comparable<T>> void assertConsistencyWithEqual(T x, T y)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );

        final boolean res1 = x.equals( y );
        final boolean expectedRes1 = true;

        assertThat( "x and y should be equal. Before testing comparison. ", res1, is( equalTo( expectedRes1 ) ) );

        final int res2 = x.compareTo( y );
        final int expectedRes2 = 0;

        assertThat( "Since x and y are equals, x.compareTo(y) should return zero!", res2, is( equalTo( expectedRes2 ) ) );
    }

    /**
     * x.compareTo(null) should throw a NullPointerException
     *
     * @param x
     *            - the instance 'x' to test, not null.
     */

    public default <T extends Comparable<T>> void assertNullPointerException(T x)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );

        String errorMessage = "x.compareTo(null) should throw a NullPointerException";

        try
        {
            x.compareTo( null );
            assertThat( errorMessage, false );
        }
        catch( Exception e )
        {
            assertThat( errorMessage, e, instanceOf( NullPointerException.class ) );
        }
    }

    /**
     * Transitivity means x.compareTo(y)>0 && y.compareTo(z)>0 implies that x.compareTo(z) is also >0.
     *
     * @param x
     *            - the first instance 'x' to test it is transitive, not null.
     * @param y
     *            - the second instance 'y' to test it is transitive, not null.
     * @param z
     *            - the third instance 'z' to test it is transitive, not null.
     */
    public default <T extends Comparable<T>> void assertTransitivity(T x, T y, T z)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );
        Objects.requireNonNull( z, "The parameter 'z' must not be null!" );

        final int res1 = x.compareTo( y );
        final int res2 = y.compareTo( z );
        final int res3 = x.compareTo( z );

        assertThat( res1, allOf( equalTo( res2 ), equalTo( res3 ) ) );
    }

    /**
     * Ensure that x.compareTo(y) == 0. This implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
     *
     * @param x
     *            - the first instance 'x' to test consistency, not null.
     * @param y
     *            - the second instance 'y' to test consistency, not null.
     * @param z
     *            - the third instance 'z' to test consistency, not null.
     */
    public default <T extends Comparable<T>> void assertConsistency(T x, T y, T z)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );
        Objects.requireNonNull( z, "The parameter 'z' must not be null!" );

        final boolean res = ( x.compareTo( y ) == 0 )
                && ( Math.signum( x.compareTo( z ) ) == Math.signum( y.compareTo( z ) ) );
        final boolean expected = true;

        assertThat( res, is( equalTo( expected ) ) );
    }
}
