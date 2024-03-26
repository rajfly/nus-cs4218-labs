package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test001");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.Class<?> wildcardClass1 = grepArgsParser0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test002");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser.FLAG_IS_FIRST_NUM;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'n' + "'", char0 == 'n');
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test003");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser.STRING_DASH;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "-" + "'", str0, "-");
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test004");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray3 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList4 = new java.util.ArrayList<int[]>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList4, intArray3);
        java.lang.String[] strArray11 = new java.lang.String[] { "hi!", "", "-", "hi!", "-" };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList4, strArray11);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray11);
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test005");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        java.lang.Class<?> wildcardClass1 = uniqApplication0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test006");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser.FLAG_IS_REV_ORDER;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'r' + "'", char0 == 'r');
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test007");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser.FLAG_BYTE_COUNT;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'c' + "'", char0 == 'c');
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test008");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.IS_DIRECTORY;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Is a directory" + "'", str0, "Is a directory");
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test009");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.INVALID_PATTERN;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Invalid pattern syntax" + "'", str0, "Invalid pattern syntax");
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test010");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
// flaky:         cdApplication0.changeToDirectory("Is a directory");
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test011");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        java.lang.String[] strArray1 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = echoApplication0.constructResult(strArray1);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test012");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
        // The following exception was thrown during execution in test generation
        try {
            exitApplication0.terminateExecution();
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ExitException; message: exit: 0");
        } catch (sg.edu.nus.comp.cs4218.exception.ExitException e) {
            // Expected exception.
        }
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test013");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser.FLAG_IS_PREFIXED;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'n' + "'", char0 == 'n');
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test014");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser.FLAG_LINE_COUNT;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'l' + "'", char0 == 'l');
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test015");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, "-", "Is a directory");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/-: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test016");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test017");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] { "-" };
        grepArgsParser2.parse(strArray4);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = catApplication0.catFiles((java.lang.Boolean) false, strArray4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test018");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.Class<?> wildcardClass2 = mvArgsParser0.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test019");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.NULL_POINTER;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Null Pointer Exception" + "'", str0, "Null Pointer Exception");
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test020");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[] { "-" };
        grepArgsParser3.parse(strArray5);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "Is a directory", strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: mv: -: Unable to move file");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test021");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test022");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.EMPTY_PATTERN;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Pattern should not be empty." + "'", str0, "Pattern should not be empty.");
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test023");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser.FLAG_PARENT;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'p' + "'", char0 == 'p');
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test024");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication3 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[] {};
        sortArgsParser4.parse(strArray5);
        java.lang.String str7 = echoApplication3.constructResult(strArray5);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) false, strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\n" + "'", str7, "\n");
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test025");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser.FLAG_CASE_IGNORE;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'f' + "'", char0 == 'f');
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test026");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] {};
        sortArgsParser3.parse(strArray4);
        java.lang.String str6 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray4);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) true, "\n", "Null Pointer Exception");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: ?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test027");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] {};
        sortArgsParser3.parse(strArray4);
        catArgsParser2.parse(strArray4);
        sortArgsParser1.parse(strArray4);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray4);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test028");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication4 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray12 = new java.lang.String[] { "hi!", "Invalid pattern syntax", "-" };
        java.lang.String str13 = grepApplication4.grepFromFiles("-", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray12);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray12);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray12);
// flaky:         org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!: No such file or directory\nInvalid pattern syntax: Is a directory\n-: Is a directory\n" + "'", str13, "hi!: No such file or directory\nInvalid pattern syntax: Is a directory\n-: Is a directory\n");
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test029");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Invalid pattern syntax", "Pattern should not be empty.", "-", "Invalid pattern syntax" };
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MkdirException; message: mkdir: Invalid pattern syntax: File or directory already exists");
        } catch (sg.edu.nus.comp.cs4218.exception.MkdirException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test030");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test031");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[] {};
        sortArgsParser4.parse(strArray5);
        java.lang.String str7 = mvApplication1.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray5);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test032");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList1 = catArgsParser0.getFileList();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[] { "-" };
        grepArgsParser3.parse(strArray5);
        catArgsParser0.parse(strArray5);
        java.util.List<java.lang.String> strList8 = catArgsParser0.getFileList();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strList8);
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test033");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, "hi!", ": Is a directory\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/hi!: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test034");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication4 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        int[][] intArray9 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList10 = new java.util.ArrayList<int[]>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList10, intArray9);
        java.lang.String str12 = cutApplication4.cutByByte((java.util.List<java.lang.String>) strList7, (java.util.List<int[]>) intArrayList10);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication13 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray15 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList16 = new java.util.ArrayList<java.lang.String>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList16, strArray15);
        int[][] intArray18 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList19 = new java.util.ArrayList<int[]>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList19, intArray18);
        java.lang.String str21 = cutApplication13.cutByByte((java.util.List<java.lang.String>) strList16, (java.util.List<int[]>) intArrayList19);
        java.lang.String str22 = cutApplication3.cutByByte((java.util.List<java.lang.String>) strList7, (java.util.List<int[]>) intArrayList19);
        java.lang.String[] strArray26 = new java.lang.String[] { "\n", "Pattern should not be empty.", "Is a directory" };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str27 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList19, strArray26);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertNotNull(intArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertNotNull(strArray15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNotNull(intArray18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
        org.junit.Assert.assertNotNull(strArray26);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test035");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray6 = new java.lang.String[] {};
        sortArgsParser5.parse(strArray6);
        catArgsParser4.parse(strArray6);
        sortArgsParser3.parse(strArray6);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test036");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray6 = new java.lang.String[] { "-" };
        grepArgsParser4.parse(strArray6);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = wcApplication0.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test037");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        int int1 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isAllDuplicates();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test038");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        int int2 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test039");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, ": Is a directory\n", "Pattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: : Is a directory?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test040");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        int int2 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test041");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        java.io.InputStream inputStream2 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[] {};
        sortArgsParser4.parse(strArray5);
        catArgsParser3.parse(strArray5);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = teeApplication0.teeFromStdin((java.lang.Boolean) false, inputStream2, strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: Could not read from input stream");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test042");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Pattern should not be empty.", "-\nInvalid pattern syntax\nPattern should not be empty." };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test043");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean4 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test044");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray3 = new java.lang.String[] {};
        sortArgsParser2.parse(strArray3);
        java.lang.String str5 = echoApplication1.constructResult(strArray3);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\n" + "'", str5, "\n");
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test045");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication3 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "" };
        java.lang.String str10 = grepApplication3.grepFromFiles("Is a directory", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray9);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) true, strArray9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + ": Is a directory\n" + "'", str10, ": Is a directory\n");
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test046");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.String str5 = uniqArgsParser0.getInputFile();
        java.lang.String str6 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNull(str6);
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test047");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        boolean boolean2 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList3 = catArgsParser0.getFileList();
        java.util.List<java.lang.String> strList4 = catArgsParser0.getFileList();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test048");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray2 = pasteArgsParser1.getFiles();
        java.lang.String[] strArray3 = pasteArgsParser1.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test049");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, "hi!", "-\nInvalid pattern syntax\nPattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/hi!: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test050");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String str5 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray4);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray9 = pasteArgsParser8.getFiles();
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "hi!", strArray9);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray14 = pasteArgsParser13.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "\n", strArray14);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: ?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertNotNull(strArray14);
    }
}
