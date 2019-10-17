package com.butschmajor.testutils;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class provide methods to test the contract of {@code hashCode}.
 */
final class ContractHashCodeTesters {

	/**
	 * Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method
	 * must consistently return the same integer.
	 *
	 * @param x
	 *            the instance 'x' to test consistency, not null.
	 */
	static <T> void assertHashCodeConsistency(T x)
	{
		Objects.requireNonNull( x, "The parameter 'x' must not be null!" );

		final String errorMessage = "Object hashcode consistency with itself failed! Weird. ";
		final int res1 = x.hashCode();
		final int res2 = x.hashCode();

		assertThat(res1).overridingErrorMessage(errorMessage).isEqualTo(res2);
	}

	/**
	 * If 'x' is equals to 'y', then {@code x.hashCode} should be equals to {@code y.hashCode}
	 *
	 * @param x
	 *            the first instance 'x' to test hash code equality, not null.
	 * @param y
	 *            the second instance 'y' to test hash code equality, not null.
	 */
	static <T> void assertHashCodeEquality(T x, T y)
	{
		Objects.requireNonNull( x, "The parameter 'x' must not be null!" );
		Objects.requireNonNull( y, "The parameter 'y' must not be null!" );

		final String errorMessage1 = "x and y should be equal. Before testing hash code equality. ";
		final boolean res1 = x.equals( y );
		final boolean expectedRes1 = true;

		assertThat(res1).overridingErrorMessage(errorMessage1).isEqualTo(expectedRes1);

		final String errorMessage2 = "Since x and y are equals, x.hashCode() and y.hashCode() should be the same!";
		final int res2 = x.hashCode();
		final int res3 = y.hashCode();

		assertThat(res2).overridingErrorMessage(errorMessage2).isEqualTo(res3);
	}
}

