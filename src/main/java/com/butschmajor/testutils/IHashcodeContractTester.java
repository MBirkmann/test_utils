package mdt.db.testinfra.contracts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Objects;

/**
 * This Interface provide default methods to test the implementation of {@code hashCode}.
 */
public interface IHashcodeContractTester
{
    /**
     * Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method
     * must consistently return the same integer.
     *
     * @param x
     *            - the instance 'x' to test consistency, not null.
     */
    public default <T> void assertHashCodeConsistency(T x)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );

        final String errorMessage = "Object hashcode consistency with itself failed! Weird. ";
        final int res1 = x.hashCode();
        final int res2 = x.hashCode();

        assertThat( errorMessage, res1, is( equalTo( res2 ) ) );
    }

    /**
     * If 'x' is equals to 'y', then {@code x.hashCode} should be equals to {@code y.hashCode}
     *
     * @param x
     *            - the first instance 'x' to test hash code equality, not null.
     * @param y
     *            - the second instance 'y' to test hash code equality, not null.
     */
    public default <T> void assertHashCodeEquality(T x, T y)
    {
        Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
        Objects.requireNonNull( y, "The parameter 'y' must not be null!" );

        final boolean res1 = x.equals( y );
        final boolean expectedRes1 = true;

        assertThat( "x and y should be equal. Before testing hash code equality. ", res1, is( equalTo( expectedRes1 ) ) );

        final String errorMessage = "Since x and y are equals, x.hashCode() and y.hashCode() should be the same!";
        final int res2 = x.hashCode();
        final int res3 = y.hashCode();

        assertThat( errorMessage, res2, is( equalTo( res3 ) ) );

    }
}
