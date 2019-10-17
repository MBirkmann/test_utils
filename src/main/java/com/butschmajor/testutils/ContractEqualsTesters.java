package com.butschmajor.testutils;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class provide methods to test the {@code equals} contract.
 */
final class ContractEqualsTesters {

	/**
	 * Ensures that 'x', 'y' and 'z' are equal.
	 *
	 * @param x
	 * 		the instance 'x' to test, not null.
	 * @param y
	 * 		the second instance 'y' to test, not null.
	 * @param z
	 * 		the third instance 'z' to test, not null.
	 */
	static <T> void assertParametersEquals(T x, T y, T z) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");
		Objects.requireNonNull(z, "The parameter 'z' must not be null!");

		final String errorMessage1 = "The parameters 'x' and 'y' must be equal!";
		final boolean res1 = x.equals(y);
		final String errorMessage2 = "The parameters 'y' and 'z' must be equal!";
		final boolean res2 = y.equals(z);

		assertThat(res1).overridingErrorMessage(errorMessage1).isTrue();
		assertThat(res2).overridingErrorMessage(errorMessage2).isTrue();
	}

	/**
	 * According to Object.equals for any non-null reference value x, x.equals(x) should return true.
	 *
	 * @param x
	 * 		the instance 'x' to test it is reflexive, not null.
	 */
	static <T> void assertEqualsIsReflexive(T x) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");

		final String errorMessage = "Object 'x' should be reflexibly equal to itself.";
		final boolean res = x.equals(x);

		assertThat(res).overridingErrorMessage(errorMessage).isTrue();
	}

	/**
	 * For any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
	 *
	 * @param x
	 * 		the first instance 'x' to test it is symmetric, not null.
	 * @param y
	 * 		the second instance 'y' to test it is symmetric, not null.
	 */
	static <T> void assertEqualsIsSymmetric(T x, T y) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");

		final String errorMessage = "x and y should be symetrically equal to each other.";
		final boolean res1 = x.equals(y);
		final boolean res2 = y.equals(x);

		assertThat(res1).overridingErrorMessage(errorMessage).isEqualTo(res2);
	}

	/**
	 * For any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z)
	 * should return true.
	 *
	 * @param x
	 * 		the first instance 'x' to test it is transitive, not null.
	 * @param y
	 * 		the second instance 'y' to test it is transitive, not null.
	 * @param z
	 * 		the third instance 'z' to test it is transitive, not null.
	 */
	static <T> void assertEqualsIsTransitive(T x, T y, T z) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");
		Objects.requireNonNull(z, "The parameter 'z' must not be null!");

		final String errorMessage1 = "x should transitively be equal to y.";
		final String errorMessage2 = "y should transitively be equal to z.";
		final String errorMessage3 = "x should transitively be equal to z.";
		final boolean res1 = x.equals(y);
		final boolean res2 = y.equals(z);
		final boolean res3 = x.equals(z);

		assertThat(res1).overridingErrorMessage(errorMessage1).isTrue();
		assertThat(res2).overridingErrorMessage(errorMessage2).isTrue();
		assertThat(res3).overridingErrorMessage(errorMessage3).isTrue();
	}

	/**
	 * For any non-null reference value x, x.equals(null) should return false.
	 *
	 * @param x
	 * 		the instance 'x' to assert, not null.
	 */
	static <T> void assertEqualsNonNullity(T x) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");

		final String errorMessage = "x should not be equals to null!";
		final boolean res = x.equals(null);

		assertThat(res).overridingErrorMessage(errorMessage).isFalse();
	}

	/**
	 * Test that 'x' and 'y' are different.
	 *
	 * @param x
	 * 		the first instance 'x' to assert, not null.
	 * @param y
	 * 		the second instance 'y' to check against 'x', may be null.
	 */
	static <T> void assertEqualsIsDifferent(T x, T y) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");

		final String errorMessage = "x should not be equals to y.";
		final boolean res = x.equals(y);

		assertThat(res).overridingErrorMessage(errorMessage).isFalse();
	}
}
