package cs4218.impl.apps;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.lang.String[] strArray6 = new java.lang.String[] { "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        // The following exception was thrown during execution in test generation
        try {
            repApplicationImpl4.run((java.util.List<java.lang.String>) strList7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        // The following exception was thrown during execution in test generation
        try {
            repApplicationImpl4.run((java.util.List<java.lang.String>) strList7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.lang.Class<?> wildcardClass5 = repApplicationImpl4.getClass();
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.util.List<java.lang.String> strList5 = null;
        // The following exception was thrown during execution in test generation
        try {
            repApplicationImpl4.run(strList5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // The following exception was thrown during execution in test generation
        try {
            repApplicationImpl4.run((java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test7() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test7");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // The following exception was thrown during execution in test generation
        try {
            repApplicationImpl4.run((java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test8() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test8");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // The following exception was thrown during execution in test generation
        try {
            repApplicationImpl4.run((java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test9() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test9");
        cs4218.interfaces.parsers.RepArgsParser repArgsParser0 = null;
        java.io.InputStream inputStream1 = null;
        java.io.OutputStream outputStream2 = null;
        cs4218.FileUtil fileUtil3 = null;
        cs4218.impl.apps.RepApplicationImpl repApplicationImpl4 = new cs4218.impl.apps.RepApplicationImpl(repArgsParser0, inputStream1, outputStream2, fileUtil3);
        java.lang.String[] strArray7 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // The following exception was thrown during execution in test generation
        try {
            repApplicationImpl4.run((java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }
}

