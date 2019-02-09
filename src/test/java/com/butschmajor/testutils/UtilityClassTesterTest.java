package com.butschmajor.testutils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

import com.butschmajor.testutils.UtilityClassTester;

public class UtilityClassTesterTest {

    @Test
    public void testAssertThatClassIsNotFinal() {
        assertThatExceptionOfType( AssertionError.class )
                .isThrownBy( () -> {
                    UtilityClassTester.assertThatClassIsWellDefined( UtilNotFinal.class );
                } )
                .withMessageContaining( "The class <com.butschmajor.testutils.UtilityClassTesterTest$UtilNotFinal> is not final" );
    }
    
    @Test
    public void testAssertThatClassConstructorPreventReflectionInstantiation() {
        assertThatExceptionOfType( AssertionError.class )
        .isThrownBy( () -> {
            UtilityClassTester.assertThatClassIsWellDefined( UtilConstructorNotPreventReflection.class );
        } )
        .withMessageContaining( "IllegalAccessError should have been thrown" );
    }
    
    @Test
    public void testAssertThatClassWithPublicConstructor() {
        assertThatExceptionOfType( AssertionError.class )
                .isThrownBy( () -> {
                    UtilityClassTester.assertThatClassIsWellDefined( UtilWithPublicConstructor.class );
                } )
                .withMessageContaining(
                        "The constructor of the class <com.butschmajor.testutils.UtilityClassTesterTest$UtilWithPublicConstructor> is accessible" );
    }  
    
    @Test
    public void testAssertThatClassHasMoreThanOneConstructor() {
        assertThatExceptionOfType( AssertionError.class )
                .isThrownBy( () -> {
                    UtilityClassTester.assertThatClassIsWellDefined( UtilMoreThanOneConstructor.class );
                } )
                .withMessageContaining(
                        "The class <com.butschmajor.testutils.UtilityClassTesterTest$UtilMoreThanOneConstructor> has more than one constructor" );
    }    
    
    @Test
    public void testAssertThatClassHasNonStaticMethod() {        
        assertThatExceptionOfType( AssertionError.class )
        .isThrownBy( () -> {
            UtilityClassTester.assertThatClassIsWellDefined( UtilHasNonStaticMethod.class );;
        } )
        .withMessageContaining(
                "The class <com.butschmajor.testutils.UtilityClassTesterTest$UtilHasNonStaticMethod> has a non-static method: 'multiplyNumber'" );
    }

    @Test
    public void testAssertThatClassIsWellDefined() throws InstantiationException, IllegalAccessException, NoSuchMethodException {
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
    
    public static final class UtilWithPublicConstructor {
        public UtilWithPublicConstructor() {
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
