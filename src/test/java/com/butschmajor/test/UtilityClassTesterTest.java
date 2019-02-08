package com.butschmajor.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class UtilityClassTesterTest {

    @Test
    public void testAssertThatClassIsNotFinal() {
        assertThatExceptionOfType( AssertionError.class )
                .isThrownBy( () -> {
                    UtilityClassTester.assertThatClassIsWellDefined( UtilNotFinal.class );
                } )
                .withMessageContaining( "The class <com.butschmajor.test.UtilityClassTesterTest$UtilNotFinal> is not final" );
    }
    
    @Test
    public void testAssertThatClassConstructorPreventReflectionInstantiation() {
        assertThatExceptionOfType( AssertionError.class )
        .isThrownBy( () -> {
            UtilityClassTester.assertThatClassIsWellDefined( UtilConstructorNotPreventReflection.class );
        } )
        .withMessageContaining(
                "The instantiation of the class <com.butschmajor.test.UtilityClassTesterTest$UtilConstructorNotPreventReflection> do not throw an Exception!" );
    }
    
    @Test
    public void testAssertThatClassHasMoreThanOneConstructor() {
        assertThatExceptionOfType( AssertionError.class )
                .isThrownBy( () -> {
                    UtilityClassTester.assertThatClassIsWellDefined( UtilMoreThanOneConstructor.class );
                } )
                .withMessageContaining(
                        "The class <com.butschmajor.test.UtilityClassTesterTest$UtilMoreThanOneConstructor> has more than one constructor" );
    }    
    
    @Test
    public void testAssertThatClassHasNonStaticMethod() {        
        assertThatExceptionOfType( AssertionError.class )
        .isThrownBy( () -> {
            UtilityClassTester.assertThatClassIsWellDefined( UtilHasNonStaticMethod.class );;
        } )
        .withMessageContaining(
                "The class <com.butschmajor.test.UtilityClassTesterTest$UtilHasNonStaticMethod> has a non-static method: 'multiplyNumber'" );
    }

    @Test
    public void testAssertThatClassIsWellDefined() {
        UtilityClassTester.assertThatClassIsWellDefined( UtilIsWellDefined.class );
        assertThat( Boolean.TRUE )
                .withFailMessage( "The class <%s> should be well defined", UtilIsWellDefined.class )
                .isTrue();
    }
    
    public static class UtilNotFinal {
        public static int addNumber( int a, int b ) {
            return a + b;
        }
    }
    
    public static final class UtilMoreThanOneConstructor {
        public UtilMoreThanOneConstructor() {
        }

        public UtilMoreThanOneConstructor(int a, int b) {
        }

        public static int addNumber( int a, int b ) {
            return a + b;
        }
    }    
    
    public static final class UtilHasNonStaticMethod {
        private UtilHasNonStaticMethod() {
            throw new IllegalAccessError();
        }

        public static int addNumber( int a, int b ) {
            return a + b;
        }
        
        public int multiplyNumber(int a, int b) {
            return a * b;
        }
    }
    
    public static final class UtilConstructorNotPreventReflection {
        private UtilConstructorNotPreventReflection() {}

        public static int addNumber( int a, int b ) {
            return a + b;
     
        }
    }
    public static final class UtilIsWellDefined {
        private UtilIsWellDefined() {
            throw new IllegalAccessError();
        }

        public static int addNumber( int a, int b ) {
            return a + b;
        }
    }

}
