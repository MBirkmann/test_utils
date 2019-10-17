package com.butschmajor.testutils;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.within;

/**
 * This class provide methods to test the contract of {@code compareTo}.
 */
final class ContractCompareToTesters {

	/**
	 * Ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. This implies that x.compareTo(y) must throw an
	 * exception if y.compareTo(x) throws an exception.
	 *
	 * @param <T>
	 * @param x
	 * 		the first instance 'x' to test, not null.
	 * @param y
	 * 		the second instance 'y' to test, not null.
	 */
	static <T extends Comparable<T>> void assertComparisonReversal(T x, T y) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");

		final String errorMessage = "Comparison reversal should apply: sgn(o1.compareTo(o2)) == -sgn(o2.compareTo(o1)).";
		final double res1 = Math.signum(x.compareTo(y));
		final double res2 = -Math.signum(y.compareTo(x));

		assertThat(res1).overridingErrorMessage(errorMessage).isCloseTo(res2, within(0.01));
	}

	/**
	 * The comparator should be consistent with equals if and only if x.compareTo(y) == 0 has the same boolean value as
	 * x.equals(y) for every x and y of class C
	 *
	 * @param x
	 * 		the first instance 'x' to test consistency with equals, not null.
	 * @param y
	 * 		the second instance 'y' to test consistency with equals, not null.
	 */
	static <T extends Comparable<T>> void assertConsistencyWithEqual(T x, T y) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");

		final String errorMessage1 = "x and y should be equal. Before testing comparison. ";
		final boolean res1 = x.equals(y);

		assertThat(res1).overridingErrorMessage(errorMessage1).isTrue();

		final String errorMessage2 = "Since x and y are equals, x.compareTo(y) should return zero!";
		final int res2 = x.compareTo(y);
		final int expectedRes2 = 0;

		assertThat(res2).overridingErrorMessage(errorMessage2).isEqualTo(expectedRes2);
	}

	/**
	 * x.compareTo(null) should throw a NullPointerException
	 *
	 * @param x
	 * 		the instance 'x' to test, not null.
	 */
	static <T extends Comparable<T>> void assertNullPointerException(T x) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");

		final String errorMessage = "x.compareTo(null) should throw a NullPointerException";

		final Throwable thrown = catchThrowable(() -> {
			x.compareTo(null);
		});

		assertThat(thrown).overridingErrorMessage(errorMessage) //
				.isInstanceOf(NullPointerException.class);
	}

	/**
	 * Transitivity means x.compareTo(y)>0 && y.compareTo(z)>0 implies that x.compareTo(z) is also >0.
	 *
	 * @param x
	 * 		the first instance 'x' to test it is transitive, not null.
	 * @param y
	 * 		the second instance 'y' to test it is transitive, not null.
	 * @param z
	 * 		the third instance 'z' to test it is transitive, not null.
	 */
	static <T extends Comparable<T>> void assertTransitivity(T x, T y, T z) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");
		Objects.requireNonNull(z, "The parameter 'z' must not be null!");

		final int res1 = x.compareTo(y);
		final int res2 = y.compareTo(z);
		final int res3 = x.compareTo(z);

		assertThat(res1).isEqualTo(res2).isEqualTo(res3);
	}

	/**
	 * Ensure that x.compareTo(y) == 0. This implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
	 *
	 * @param x
	 * 		the first instance 'x' to test consistency, not null.
	 * @param y
	 * 		the second instance 'y' to test consistency, not null.
	 * @param z
	 * 		the third instance 'z' to test consistency, not null.
	 */
	static <T extends Comparable<T>> void assertConsistency(T x, T y, T z) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");
		Objects.requireNonNull(z, "The parameter 'z' must not be null!");

		final boolean res = (x.compareTo(y) == 0) && (Math.signum(x.compareTo(z)) == Math.signum(y.compareTo(z)));

		assertThat(res).isTrue();
	}
}


