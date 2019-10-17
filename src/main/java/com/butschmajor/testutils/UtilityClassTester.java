package com.butschmajor.testutils;

import org.assertj.core.api.SoftAssertions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public final class UtilityClassTester {

	/**
	 * Suppresses default constructor, ensuring non-instantiability.
	 */
	private UtilityClassTester() {
		throw new UnsupportedOperationException("Utility class");
	}

	/**
	 * <p>Verifies that a utility class is well-defined.</p>
	 * A class is well-defined if the following properties are fulfilled:
	 * <ul>
	 * <li>the class is declared as final</li>
	 * <li>the class has exactly one constructor and the constructor is declared as private</li>
	 * <li>the instantiation for example via reflection throws an UnsupportedOperationException</li>
	 * <li>all methods of the class are declared as static</li>
	 * </ul>
	 *
	 * @param clazz
	 * 		utility class to verify, not null.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	public static void assertThatClassIsWellDefined(final Class<?> clazz)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException {
		Objects.requireNonNull(clazz, "The parameter 'clazz' must not be null!");
		final SoftAssertions softly = new SoftAssertions();

		// Verify that the class is final
		final boolean isClassFinal = Modifier.isFinal(clazz.getModifiers());
		softly.assertThat(isClassFinal) //
				.withFailMessage("The class <%s> is not final", clazz.getName()) //
				.isTrue();

		// Verify that the class has exactly one constructor
		final int numberOfConstructors = clazz.getDeclaredConstructors().length;
		softly.assertThat(numberOfConstructors) //
				.withFailMessage("The class <%s> has more than one constructor", clazz.getName()) //
				.isEqualTo(1);

		try {
			// Verify that the constructor is private and not accessible via reflection
			final Constructor<?> constructor = clazz.getDeclaredConstructor();
			final boolean isConstructorAccessible = constructor.isAccessible();
			final boolean isConstructorPrivate = Modifier.isPrivate(constructor.getModifiers());

			softly.assertThat(isConstructorAccessible) //
					.withFailMessage("The constructor of the class <%s> is accessible", clazz.getName()) //
					.isFalse();
			softly.assertThat(isConstructorPrivate) //
					.withFailMessage("The constructor of the class <%s> is not private", clazz.getName()) //
					.isTrue();

			// Verify that the instantiation of the class e.g. via reflection throw an UnsupportedOperationException
			constructor.setAccessible(true);
			constructor.newInstance();
			constructor.setAccessible(false);
			failBecauseExceptionWasNotThrown(UnsupportedOperationException.class);
		} catch(InvocationTargetException e) {
			final Throwable throwable = e.getTargetException();

			softly.assertThat(throwable) //
					.isInstanceOf(UnsupportedOperationException.class) //
					.withFailMessage("The instantiation of the class <%s> do not throw an UnsupportedOperationException",
							clazz.getName());
		}

		// Verify that there exists no non-static method(s)
		for(final Method method : clazz.getMethods()) {
			final boolean isMethodStatic = Modifier.isStatic(method.getModifiers());
			final boolean isDeclaringClassEqual = method.getDeclaringClass().equals(clazz);
			softly.assertThat(!isMethodStatic && isDeclaringClassEqual) //
					.withFailMessage("The class <%s> has a non-static method: '%s'", clazz.getName(), method.getName()) //
					.isFalse();
		}

		softly.assertAll();
	}
}
