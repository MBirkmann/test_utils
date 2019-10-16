package mdt.db.testinfra.contracts;

import java.util.Objects;

public interface IContractTester extends IEqualsContractTester, IHashcodeContractTester, IComparableTester
{
    /**
     * Ensures that the methods {@code equals}, {@code hashCode} and {@code compareTo} comply with the contract.
     *
     * @param x
     *            - the instance 'x' to test, not null.
     * @param y
     *            - the second instance 'y' to test, not null.
     * @param z
     *            - the third instance 'z' to test, not null.
     */
    public default <T extends Comparable<T>> void assertContractEqualsHashCodeCompareTo(T x, T y, T z)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );
        Objects.requireNonNull( z, "The parameter 'z' must not be null!" );

        assertContractEqualsAndHashCode( x, y, z );
        assertContractCompareTo( x, y, z );
    }

    /**
     * Ensures that the methods {@code equals} and {@code hashCode} comply with the contract.
     *
     * @param x
     *            - the instance 'x' to test, not null.
     * @param y
     *            - the second instance 'y' to test, not null.
     * @param z
     *            - the third instance 'z' to test, not null.
     */
    public default <T> void assertContractEqualsAndHashCode(T x, T y, T z)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );
        Objects.requireNonNull( z, "The parameter 'z' must not be null!" );

        assertParametersEquals( x, y, z );
        assertEqualsIsReflexive( x );
        assertEqualsIsSymmetric( x, y );
        assertEqualsIsTransitive( x, y, z );
        assertEqualsNonNullity( x );

        assertHashCodeEquality( x, y );
        assertHashCodeConsistency( x );
    }

    /**
     * Ensures that the method {@code compareTo} comply with the contract.
     *
     * @param x
     *            - the instance 'x' to test, not null.
     * @param y
     *            - the second instance 'y' to test, not null.
     * @param z
     *            - the third instance 'z' to test, not null.
     */
    public default <T extends Comparable<T>> void assertContractCompareTo(T x, T y, T z)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );
        Objects.requireNonNull( z, "The parameter 'z' must not be null!" );

        assertComparisonReversal( x, y );
        assertConsistency( x, x, y );
        assertConsistencyWithEqual( x, y );
        assertNullPointerException( x );
        assertTransitivity( x, y, z );
    }
}
