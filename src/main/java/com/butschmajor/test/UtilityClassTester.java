package com.butschmajor.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UtilityClassTester {

    private UtilityClassTester() {
    }

    private static final Logger LOG = LogManager.getLogger( UtilityClassTester.class );

    /**
     * Verifies that a utility class is well defined.
     *
     * @param clazz
     *            - utility class to verify, not null.
     */
    public static void assertThatClassIsWellDefined( final Class<?> clazz ) {
        Objects.requireNonNull( clazz, "The parameter 'clazz' must not be null!" );

        // Verify that the class is final
        final boolean isClassFinal = Modifier.isFinal( clazz.getModifiers() );
        assertThat( isClassFinal )
                .withFailMessage( "The class <%s> is not final", clazz.getName() )
                .isTrue();

        // Verify that the number of constructors is 1
        final int numberOfConstructors = clazz.getDeclaredConstructors().length;
        assertThat( numberOfConstructors )
                .withFailMessage( "The class <%s> has more than one constructor", clazz.getName() )
                .isEqualTo( 1 );

        try{
            // Verify that the constructor is private and not accessible
            final Constructor<?> constructor = clazz.getDeclaredConstructor();
            @SuppressWarnings("deprecation")
            final boolean isConstructorAccessible = constructor.isAccessible();
            final boolean isConstructorPrivate = Modifier.isPrivate( constructor.getModifiers() );
            assertThat( isConstructorAccessible )
                    .withFailMessage( "The constructor of the class <%s> is accessible", clazz.getName() )
                    .isFalse();
            assertThat( isConstructorPrivate )
                    .withFailMessage( "The constructor of the class <%s> is not private", clazz.getName() )
                    .isTrue();

            // Verify that the instantiation of the class e.g. via reflection throw an IllegalAccessError
            constructor.setAccessible( true );
            constructor.newInstance();
            constructor.setAccessible( false );
            assertThat( Boolean.TRUE )
                    .withFailMessage( "The instantiation of the class <%s> do not throw an Exception!", clazz.getName() )
                    .isFalse();

        }
        catch( InvocationTargetException e ){
            Throwable throwable = e.getTargetException();
            assertThat( throwable )
                    .isInstanceOf( IllegalAccessError.class )
                    .withFailMessage( "The instantiation of the class do not throw an IllegalAccessError" );
        }
        catch( Exception e ){
            LOG.error( "An error occured at instantiaton of class: " + clazz.getName(), e );
        }

        // Verify that there exists no non-static method(s)
        for( final Method method : clazz.getMethods() ){
            final boolean isMethodStatic = Modifier.isStatic( method.getModifiers() );
            final boolean isDeclaringClassEqual = method.getDeclaringClass().equals( clazz );
            assertThat( !isMethodStatic && isDeclaringClassEqual )
                    .withFailMessage( "The class <%s> has a non-static method: '%s'", clazz.getName(), method.getName() )
                    .isFalse();
        }
    }
}
