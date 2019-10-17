package com.butschmajor.testutils;

import java.util.Objects;

/**
 *
 */
public final class ContractTesters {

	/**
	 * Ensures that the methods {@code equals}, {@code hashCode} and {@code compareTo} comply with the contract.
	 *
	 * @param x
	 * 		the instance 'x' to test, not null.
	 * @param y
	 * 		the second instance 'y' to test, not null.
	 * @param z
	 * 		the third instance 'z' to test, not null.
	 */
	public static <T extends Comparable<T>> void assertContractEqualsHashCodeCompareTo(T x, T y, T z) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");
		Objects.requireNonNull(z, "The parameter 'z' must not be null!");

		assertContractEqualsAndHashCode(x, y, z);
		assertContractCompareTo(x, y, z);
	}

	/**
	 * Ensures that the methods {@code equals} and {@code hashCode} comply with the contract.
	 *
	 * @param x
	 * 		the instance 'x' to test, not null.
	 * @param y
	 * 		the second instance 'y' to test, not null.
	 * @param z
	 * 		the third instance 'z' to test, not null.
	 */
	public static <T> void assertContractEqualsAndHashCode(T x, T y, T z) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");
		Objects.requireNonNull(z, "The parameter 'z' must not be null!");

		ContractEqualsTesters.assertParametersEquals(x, y, z);
		ContractEqualsTesters.assertEqualsIsReflexive(x);
		ContractEqualsTesters.assertEqualsIsSymmetric(x, y);
		ContractEqualsTesters.assertEqualsIsTransitive(x, y, z);
		ContractEqualsTesters.assertEqualsNonNullity(x);

		ContractHashCodeTesters.assertHashCodeEquality(x, y);
		ContractHashCodeTesters.assertHashCodeConsistency(x);
	}

	/**
	 * Ensures that the method {@code compareTo} comply with the contract.
	 *
	 * @param x
	 * 		the instance 'x' to test, not null.
	 * @param y
	 * 		the second instance 'y' to test, not null.
	 * @param z
	 * 		the third instance 'z' to test, not null.
	 */
	public static <T extends Comparable<T>> void assertContractCompareTo(T x, T y, T z) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");
		Objects.requireNonNull(y, "The parameter 'y' must not be null!");
		Objects.requireNonNull(z, "The parameter 'z' must not be null!");

		ContractCompareToTesters.assertComparisonReversal(x, y);
		ContractCompareToTesters.assertConsistency(x, x, y);
		ContractCompareToTesters.assertConsistencyWithEqual(x, y);
		ContractCompareToTesters.assertNullPointerException(x);
		ContractCompareToTesters.assertTransitivity(x, y, z);
	}

	/**
	 * Test that 'x' and 'y' are different.
	 *
	 * @param x
	 * 		the first instance 'x' to assert, not null.
	 * @param y
	 * 		the second instance 'y' to check against 'x', may be null.
	 */
	public static <T> void assertEqualsIsDifferent(T x, T y) {
		Objects.requireNonNull(x, "The parameter 'x' must not be null!");

		ContractEqualsTesters.assertEqualsIsDifferent(x, y);
	}
}
