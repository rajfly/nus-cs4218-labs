package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest7 {

    public static boolean debug = false;

    @Test
    public void test351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test351");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean5 = wcArgsParser0.filesContainDash();
        java.util.List<java.lang.String> strList6 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean7 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean8 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(strList6);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + true + "'", boolean7, true);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + true + "'", boolean8, true);
    }

    @Test
    public void test352() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test352");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = wcApplication0.countFromStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
    }

    @Test
    public void test353() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test353");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[]{"-"};
        grepArgsParser0.parse(strArray2);
        java.lang.String str4 = grepArgsParser0.getPattern();
        java.lang.String[] strArray5 = grepArgsParser0.getFileNames();
        java.lang.String str6 = grepArgsParser0.getPattern();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(strArray5);
        org.junit.Assert.assertNull(str6);
    }

    @Test
    public void test354() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test354");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        int int6 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean7 = uniqArgsParser0.isAllDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
    }

    @Test
    public void test355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test355");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        int int4 = uniqArgsParser0.getFileCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test356");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.String str2 = mvArgsParser0.getDestination();
        java.lang.String str3 = mvArgsParser0.getDestination();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test357");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean2 = mvArgsParser1.isNoOverwrite();
        java.util.List<java.lang.String> strList3 = mvArgsParser1.getSourceFiles();
        java.util.List<java.lang.String> strList4 = mvArgsParser1.getSourceFiles();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication5 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray7 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        int[][] intArray10 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList11 = new java.util.ArrayList<int[]>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList11, intArray10);
        java.lang.String str13 = cutApplication5.cutByByte((java.util.List<java.lang.String>) strList8, (java.util.List<int[]>) intArrayList11);
        java.lang.String str14 = cutApplication0.cutByByte(strList4, (java.util.List<int[]>) intArrayList11);
        int[][] intArray17 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList18 = new java.util.ArrayList<int[]>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList18, intArray17);
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser20 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList21 = catArgsParser20.getFileList();
        java.util.List<java.lang.String> strList22 = catArgsParser20.getFileList();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser23 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray25 = new java.lang.String[]{"-"};
        grepArgsParser23.parse(strArray25);
        catArgsParser20.parse(strArray25);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str28 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList18, strArray25);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(intArray10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertNotNull(intArray17);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(strList21);
        org.junit.Assert.assertNotNull(strList22);
        org.junit.Assert.assertNotNull(strArray25);
    }

    @Test
    public void test358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test358");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.String[] strArray4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
    }

    @Test
    public void test359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test359");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication1 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray6 = pasteArgsParser5.getFiles();
        java.lang.String str7 = wcApplication1.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray6);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test360");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication3 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[]{};
        sortArgsParser4.parse(strArray5);
        java.lang.String str7 = echoApplication3.constructResult(strArray5);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) true, strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\n" + "'", str7, "\n");
    }

    @Test
    public void test361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test361");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.lang.Boolean boolean1 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean2 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean3 = mkdirArgsParser0.isParent();
        java.util.List<java.lang.String> strList4 = mkdirArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test362() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test362");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.util.List<java.lang.String> strList2 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean3 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean4 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean5 = lsArgsParser0.isRecursive();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test363() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test363");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray6 = new java.lang.String[]{""};
        java.lang.String str7 = grepApplication0.grepFromFiles("Is a directory", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray6);
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray14 = new java.lang.String[]{};
        sortArgsParser13.parse(strArray14);
        catArgsParser12.parse(strArray14);
        java.lang.String str17 = grepApplication0.grepFromFiles("Invalid pattern syntax\nPattern should not be empty.", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray14);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser22 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        boolean boolean23 = pasteArgsParser22.isSerial();
        boolean boolean24 = pasteArgsParser22.isSerial();
        java.lang.String[] strArray25 = pasteArgsParser22.getFiles();
        java.lang.String str26 = grepApplication0.grepFromFiles("- -> Invalid pattern syntax/-", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray25);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + ": Is a directory\n" + "'", str7, ": Is a directory\n");
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\n" + "'", str17, "\n");
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "" + "'", str26, "");
    }

    @Test
    public void test364() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test364");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray1 = new java.lang.String[]{};
        sortArgsParser0.parse(strArray1);
        boolean boolean3 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean4 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test365() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test365");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isSortByExt();
        java.util.List<java.lang.String> strList2 = lsArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList3 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean4 = lsArgsParser0.isRecursive();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test366() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test366");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        boolean boolean2 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList3 = catArgsParser0.getFileList();
        boolean boolean4 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList5 = catArgsParser0.getFileList();
        boolean boolean6 = catArgsParser0.isLineNumber();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test367() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test367");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray3 = pasteArgsParser2.getFiles();
        java.lang.String str4 = pasteApplication0.mergeFile((java.lang.Boolean) false, strArray3);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication6 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray8 = new java.lang.String[]{};
        java.lang.String str9 = pasteApplication6.mergeFile((java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray12 = new java.lang.String[]{};
        sortArgsParser11.parse(strArray12);
        java.lang.String str14 = pasteApplication6.mergeFile((java.lang.Boolean) false, strArray12);
        java.lang.String str15 = pasteApplication0.mergeFile((java.lang.Boolean) true, strArray12);
        java.io.InputStream inputStream17 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str18 = pasteApplication0.mergeStdin((java.lang.Boolean) true, inputStream17);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test368() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test368");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray6 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList7 = new java.util.ArrayList<int[]>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList7, intArray6);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray10 = pasteArgsParser9.getFiles();
        java.lang.String[] strArray11 = pasteArgsParser9.getFiles();
        java.lang.String str12 = cutApplication3.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList7, strArray11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication16 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser20 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray21 = pasteArgsParser20.getFiles();
        java.lang.String str22 = wcApplication16.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray21);
        java.lang.String str23 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.lang.String str24 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList7, strArray21);
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser25 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.util.List<java.lang.String> strList26 = lsArgsParser25.getDirectories();
        int[][] intArray27 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList28 = new java.util.ArrayList<int[]>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList28, intArray27);
        java.lang.String str30 = cutApplication0.cutByByte(strList26, (java.util.List<int[]>) intArrayList28);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
// flaky:         org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "" + "'", str24, "");
        org.junit.Assert.assertNotNull(strList26);
        org.junit.Assert.assertNotNull(intArray27);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "" + "'", str30, "");
    }

    @Test
    public void test369() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test369");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        boolean boolean3 = catArgsParser0.isLineNumber();
        java.util.List<java.lang.String> strList4 = catArgsParser0.getFileList();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test370() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test370");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList1 = catArgsParser0.getFileList();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        boolean boolean3 = catArgsParser0.isLineNumber();
        boolean boolean4 = catArgsParser0.isLineNumber();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test371() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test371");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray1 = pasteArgsParser0.getFiles();
        boolean boolean2 = pasteArgsParser0.isSerial();
        boolean boolean3 = pasteArgsParser0.isSerial();
        boolean boolean4 = pasteArgsParser0.isSerial();
        java.lang.String[] strArray5 = pasteArgsParser0.getFiles();
        boolean boolean6 = pasteArgsParser0.isSerial();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test372() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test372");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String[] strArray5 = pasteArgsParser3.getFiles();
        java.lang.String str6 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray5);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication9 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication12 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray14 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList15 = new java.util.ArrayList<java.lang.String>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList15, strArray14);
        int[][] intArray17 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList18 = new java.util.ArrayList<int[]>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList18, intArray17);
        java.lang.String str20 = cutApplication12.cutByByte((java.util.List<java.lang.String>) strList15, (java.util.List<int[]>) intArrayList18);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication21 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser24 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray25 = new java.lang.String[]{};
        sortArgsParser24.parse(strArray25);
        java.lang.String str27 = mvApplication21.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray25);
        java.lang.String str28 = cutApplication9.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList18, strArray25);
        java.lang.String str29 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray25);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication32 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication34 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser38 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray39 = pasteArgsParser38.getFiles();
        java.lang.String str40 = sortApplication34.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray39);
        java.lang.String str41 = catApplication32.catFiles((java.lang.Boolean) false, strArray39);
        java.lang.String str42 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray39);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertNotNull(strArray5);
// flaky:         org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(intArray17);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
// flaky:         org.junit.Assert.assertEquals("'" + str29 + "' != '" + "" + "'", str29, "");
        org.junit.Assert.assertNotNull(strArray39);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "\n" + "'", str40, "\n");
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "" + "'", str41, "");
// flaky:         org.junit.Assert.assertEquals("'" + str42 + "' != '" + "" + "'", str42, "");
    }

    @Test
    public void test373() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test373");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean5 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test374() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test374");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        int int1 = uniqArgsParser0.getFileCount();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.String str3 = uniqArgsParser0.getOutputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.String str5 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean6 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str7 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test375() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test375");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String[] strArray6 = pasteArgsParser4.getFiles();
        sortArgsParser0.parse(strArray6);
        java.lang.Class<?> wildcardClass8 = sortArgsParser0.getClass();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }

    @Test
    public void test376() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test376");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean6 = wcArgsParser0.isWordCount();
        java.util.List<java.lang.String> strList7 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + true + "'", boolean6, true);
        org.junit.Assert.assertNotNull(strList7);
    }

    @Test
    public void test377() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test377");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.util.List<java.lang.String> strList2 = mvArgsParser0.getSourceFiles();
        java.util.List<java.lang.String> strList3 = mvArgsParser0.getSourceFiles();
        java.util.List<java.lang.String> strList4 = mvArgsParser0.getSourceFiles();
        java.lang.String str5 = mvArgsParser0.getDestination();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test378() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test378");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray3 = pasteArgsParser2.getFiles();
        boolean boolean4 = pasteArgsParser2.isSerial();
        java.lang.String[] strArray5 = pasteArgsParser2.getFiles();
        java.lang.String str6 = catApplication0.catFiles((java.lang.Boolean) false, strArray5);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
    }

    @Test
    public void test379() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test379");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication1 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray3 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList4 = new java.util.ArrayList<java.lang.String>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList4, strArray3);
        int[][] intArray6 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList7 = new java.util.ArrayList<int[]>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList7, intArray6);
        java.lang.String str9 = cutApplication1.cutByByte((java.util.List<java.lang.String>) strList4, (java.util.List<int[]>) intArrayList7);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication10 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray12 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList13 = new java.util.ArrayList<java.lang.String>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList13, strArray12);
        int[][] intArray15 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList16 = new java.util.ArrayList<int[]>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList16, intArray15);
        java.lang.String str18 = cutApplication10.cutByByte((java.util.List<java.lang.String>) strList13, (java.util.List<int[]>) intArrayList16);
        java.lang.String str19 = cutApplication0.cutByByte((java.util.List<java.lang.String>) strList4, (java.util.List<int[]>) intArrayList16);
        int[][] intArray22 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList23 = new java.util.ArrayList<int[]>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList23, intArray22);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser25 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray26 = pasteArgsParser25.getFiles();
        java.lang.String str27 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList23, strArray26);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertNotNull(intArray15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(intArray22);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(strArray26);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
    }

    @Test
    public void test380() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test380");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[]{"-"};
        grepArgsParser0.parse(strArray2);
        java.lang.Boolean boolean4 = grepArgsParser0.isInvert();
        java.lang.String[] strArray5 = grepArgsParser0.getFileNames();
        java.lang.String[] strArray6 = grepArgsParser0.getFileNames();
        java.lang.String[] strArray7 = grepArgsParser0.getFileNames();
        java.lang.String[] strArray8 = grepArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(strArray5);
        org.junit.Assert.assertNull(strArray6);
        org.junit.Assert.assertNull(strArray7);
        org.junit.Assert.assertNull(strArray8);
    }

    @Test
    public void test381() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test381");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) true, "\n", ": Is a directory\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: ?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
    }

    @Test
    public void test382() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test382");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication3 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray5 = new java.lang.String[]{};
        java.lang.String str6 = pasteApplication3.mergeFile((java.lang.Boolean) false, strArray5);
        java.lang.String str7 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray5);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication10 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray14 = pasteArgsParser13.getFiles();
        java.lang.String[] strArray15 = pasteArgsParser13.getFiles();
        java.lang.String str16 = lsApplication10.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray15);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser19 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray20 = new java.lang.String[]{};
        sortArgsParser19.parse(strArray20);
        java.lang.String str22 = lsApplication10.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        java.lang.String str23 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray20);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
// flaky:         org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertNotNull(strArray15);
// flaky:         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertNotNull(strArray20);
// flaky:         org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
// flaky:         org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
    }

    @Test
    public void test383() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test383");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.String str4 = uniqArgsParser3.getOutputFile();
        java.lang.Boolean boolean5 = uniqArgsParser3.isAllDuplicates();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray7 = new java.lang.String[]{};
        sortArgsParser6.parse(strArray7);
        uniqArgsParser3.parse(strArray7);
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "- -> Invalid pattern syntax/-", strArray7);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
    }

    @Test
    public void test384() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test384");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean4 = uniqArgsParser3.isCount();
        java.lang.String str5 = uniqArgsParser3.getInputFile();
        java.lang.String str6 = uniqArgsParser3.getInputFile();
        java.lang.String str7 = uniqArgsParser3.getInputFile();
        java.lang.Boolean boolean8 = uniqArgsParser3.isCount();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication9 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream11 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray14 = new java.lang.String[]{};
        sortArgsParser13.parse(strArray14);
        catArgsParser12.parse(strArray14);
        java.lang.String str17 = catApplication9.catFileAndStdin((java.lang.Boolean) true, inputStream11, strArray14);
        uniqArgsParser3.parse(strArray14);
        java.lang.String str19 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "Pattern should not be empty.", strArray14);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
    }

    @Test
    public void test386() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test386");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, "", "Null Pointer Exception");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: : This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test387() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test387");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, "-\n", "-\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/-?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test388() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test388");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList1 = catArgsParser0.getFileList();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        boolean boolean3 = catArgsParser0.isReadFromStdin();
        boolean boolean4 = catArgsParser0.isLineNumber();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test389() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test389");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray8 = new java.lang.String[]{};
        sortArgsParser7.parse(strArray8);
        catArgsParser6.parse(strArray8);
        sortArgsParser5.parse(strArray8);
        java.lang.String str12 = catApplication3.catFiles((java.lang.Boolean) false, strArray8);
        java.lang.String str13 = lsApplication0.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray8);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
// flaky:         org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test390() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test390");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isOnlyDuplicates();
        int int2 = uniqArgsParser0.getFileCount();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test391() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test391");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        int int2 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test392() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test392");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList2 = sortArgsParser1.getFileNames();
        boolean boolean3 = sortArgsParser1.isCaseIndependent();
        java.lang.Boolean boolean4 = sortArgsParser1.isFirstWordNumber();
        java.util.List<java.lang.String> strList5 = sortArgsParser1.getFileNames();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication6 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray9 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList10 = new java.util.ArrayList<int[]>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList10, intArray9);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication12 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray17 = pasteArgsParser16.getFiles();
        java.lang.String str18 = wcApplication12.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray17);
        java.lang.String str19 = cutApplication6.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList10, strArray17);
        java.lang.String str20 = cutApplication0.cutByByte(strList5, (java.util.List<int[]>) intArrayList10);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertNotNull(intArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
    }

    @Test
    public void test393() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test393");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList2 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isFirstWordNumber();
        boolean boolean5 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean6 = sortArgsParser0.isReverseOrder();
        java.util.List<java.lang.String> strList7 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNotNull(strList7);
    }

    @Test
    public void test394() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test394");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        java.lang.Class<?> wildcardClass1 = echoApplication0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test395() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test395");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("\n\n\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
    }

    @Test
    public void test396() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test396");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isReverseOrder();
        boolean boolean5 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test397() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test397");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication2 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String str8 = wcApplication2.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray7);
        java.lang.String str9 = catApplication0.catFiles((java.lang.Boolean) false, strArray7);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray12 = pasteArgsParser11.getFiles();
        boolean boolean13 = pasteArgsParser11.isSerial();
        java.lang.String[] strArray14 = pasteArgsParser11.getFiles();
        java.lang.String[] strArray15 = pasteArgsParser11.getFiles();
        java.lang.String str16 = catApplication0.catFiles((java.lang.Boolean) true, strArray15);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertNotNull(strArray15);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
    }

    @Test
    public void test398() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test398");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        boolean boolean2 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean3 = sortArgsParser0.isFirstWordNumber();
        boolean boolean4 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test399() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test399");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[]{};
        sortArgsParser1.parse(strArray2);
        java.lang.String str4 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList7 = catArgsParser6.getFileList();
        java.util.List<java.lang.String> strList8 = catArgsParser6.getFileList();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray11 = new java.lang.String[]{"-"};
        grepArgsParser9.parse(strArray11);
        catArgsParser6.parse(strArray11);
        boolean boolean14 = catArgsParser6.isReadFromStdin();
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser18 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray19 = pasteArgsParser18.getFiles();
        java.lang.String str20 = mvApplication15.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray19);
        catArgsParser6.parse(strArray19);
        pasteArgsParser5.parse(strArray19);
        java.lang.String str23 = echoApplication0.constructResult(strArray19);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
        org.junit.Assert.assertNotNull(strList7);
        org.junit.Assert.assertNotNull(strList8);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "\n" + "'", str23, "\n");
    }

    @Test
    public void test400() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest7.test400");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, "- -> Invalid pattern syntax/-", "- -> Invalid pattern syntax/-");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/- -> Invalid pattern syntax/-: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }
}
