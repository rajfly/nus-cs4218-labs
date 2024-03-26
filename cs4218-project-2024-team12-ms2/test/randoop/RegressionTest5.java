package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest5 {

    public static boolean debug = false;

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test251");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication3 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray8 = pasteArgsParser7.getFiles();
        java.lang.String str9 = sortApplication3.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray8);
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "-\n", strArray8);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, "Pattern should not be empty.", "Is a directory");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Pattern should not be empty.: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\n" + "'", str9, "\n");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test252");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean2 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean3 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean4 = mvArgsParser0.isNoOverwrite();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test253");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList4 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test254");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isLineCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test255");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isLineCount();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test256");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication3 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        boolean boolean6 = pasteArgsParser5.isSerial();
        java.lang.String[] strArray7 = pasteArgsParser5.getFiles();
        java.lang.String str8 = pasteApplication3.mergeFile((java.lang.Boolean) false, strArray7);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) true, strArray7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test257");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList4 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test258");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication2 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray9 = new java.lang.String[] {};
        sortArgsParser8.parse(strArray9);
        catArgsParser7.parse(strArray9);
        sortArgsParser6.parse(strArray9);
        java.lang.String str13 = wcApplication2.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray9);
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser18 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray19 = new java.lang.String[] {};
        sortArgsParser18.parse(strArray19);
        catArgsParser17.parse(strArray19);
        java.lang.String str22 = wcApplication2.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray19);
        java.lang.String str23 = catApplication0.catFiles((java.lang.Boolean) true, strArray19);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication25 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser27 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray28 = pasteArgsParser27.getFiles();
        java.lang.String str29 = pasteApplication25.mergeFile((java.lang.Boolean) false, strArray28);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser31 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray32 = pasteArgsParser31.getFiles();
        boolean boolean33 = pasteArgsParser31.isSerial();
        java.lang.String[] strArray34 = pasteArgsParser31.getFiles();
        java.lang.String str35 = pasteApplication25.mergeFile((java.lang.Boolean) true, strArray34);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser37 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean38 = sortArgsParser37.isCaseIndependent();
        java.lang.Boolean boolean39 = sortArgsParser37.isFirstWordNumber();
        java.lang.Boolean boolean40 = sortArgsParser37.isReverseOrder();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser41 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray42 = pasteArgsParser41.getFiles();
        java.lang.String[] strArray43 = pasteArgsParser41.getFiles();
        sortArgsParser37.parse(strArray43);
        java.lang.String str45 = pasteApplication25.mergeFile((java.lang.Boolean) true, strArray43);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser47 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray48 = pasteArgsParser47.getFiles();
        boolean boolean49 = pasteArgsParser47.isSerial();
        java.lang.String[] strArray50 = pasteArgsParser47.getFiles();
        java.lang.String str51 = pasteApplication25.mergeFile((java.lang.Boolean) true, strArray50);
        java.lang.String str52 = catApplication0.catFiles((java.lang.Boolean) false, strArray50);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
        org.junit.Assert.assertNotNull(strArray28);
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "" + "'", str29, "");
        org.junit.Assert.assertNotNull(strArray32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(strArray34);
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "" + "'", str35, "");
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertEquals("'" + boolean39 + "' != '" + false + "'", boolean39, false);
        org.junit.Assert.assertEquals("'" + boolean40 + "' != '" + false + "'", boolean40, false);
        org.junit.Assert.assertNotNull(strArray42);
        org.junit.Assert.assertNotNull(strArray43);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertNotNull(strArray48);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertNotNull(strArray50);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "" + "'", str51, "");
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "" + "'", str52, "");
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test259");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isAllDuplicates();
        java.lang.Class<?> wildcardClass5 = uniqArgsParser0.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test260");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication2 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray5 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList6 = new java.util.ArrayList<int[]>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList6, intArray5);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication8 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray13 = pasteArgsParser12.getFiles();
        java.lang.String str14 = wcApplication8.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray13);
        java.lang.String str15 = cutApplication2.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList6, strArray13);
        java.lang.String str16 = pasteApplication0.mergeFile((java.lang.Boolean) true, strArray13);
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
    }

    @Test
    public void test261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test261");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.util.List<java.lang.String> strList1 = mkdirArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean3 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean4 = mkdirArgsParser0.isParent();
        java.util.List<java.lang.String> strList5 = mkdirArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test262");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication3 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication8 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication11 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray13 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList14 = new java.util.ArrayList<java.lang.String>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList14, strArray13);
        int[][] intArray16 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList17 = new java.util.ArrayList<int[]>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList17, intArray16);
        java.lang.String str19 = cutApplication11.cutByByte((java.util.List<java.lang.String>) strList14, (java.util.List<int[]>) intArrayList17);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication20 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser23 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray24 = new java.lang.String[] {};
        sortArgsParser23.parse(strArray24);
        java.lang.String str26 = mvApplication20.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray24);
        java.lang.String str27 = cutApplication8.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList17, strArray24);
        java.lang.String str28 = grepApplication3.grepFromFiles("Null Pointer Exception", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray24);
        java.lang.String str29 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray24);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
        org.junit.Assert.assertNotNull(intArray16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strArray24);
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "" + "'", str26, "");
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
// flaky:         org.junit.Assert.assertEquals("'" + str29 + "' != '" + "" + "'", str29, "");
    }

    @Test
    public void test263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test263");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.String str4 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean5 = grepArgsParser0.isInvert();
        java.lang.String[] strArray6 = grepArgsParser0.getFileNames();
        java.lang.String str7 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean8 = grepArgsParser0.isInvert();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(strArray6);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
    }

    @Test
    public void test264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test264");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        boolean boolean3 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean4 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test265");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        java.io.InputStream inputStream2 = null;
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String[] strArray5 = pasteArgsParser3.getFiles();
        java.lang.String[] strArray6 = pasteArgsParser3.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = teeApplication0.teeFromStdin((java.lang.Boolean) true, inputStream2, strArray6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: Could not read from input stream");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test266");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        int int4 = uniqArgsParser0.getFileCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test267");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.Boolean boolean4 = grepArgsParser0.isInvert();
        java.lang.String str5 = grepArgsParser0.getPattern();
        java.lang.String str6 = grepArgsParser0.getPattern();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNull(str6);
    }

    @Test
    public void test268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test268");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str6 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean7 = uniqArgsParser0.isAllDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
    }

    @Test
    public void test269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test269");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("Null Pointer Exception");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: Null Pointer Exception: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
    }

    @Test
    public void test270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test270");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray1 = grepArgsParser0.getFileNames();
        java.lang.String[] strArray2 = grepArgsParser0.getFileNames();
        org.junit.Assert.assertNull(strArray1);
        org.junit.Assert.assertNull(strArray2);
    }

    @Test
    public void test271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test271");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList4 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean5 = wcArgsParser0.isWordCount();
        java.util.List<java.lang.String> strList6 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test272");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] {};
        sortArgsParser1.parse(strArray2);
        java.lang.String str4 = echoApplication0.constructResult(strArray2);
        java.lang.String[] strArray5 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = echoApplication0.constructResult(strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
    }

    @Test
    public void test273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test273");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String str6 = wcApplication0.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray5);
        java.lang.String[] strArray10 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray10);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: No files provided");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
    }

    @Test
    public void test274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test274");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean4 = wcArgsParser0.filesContainDash();
        java.util.List<java.lang.String> strList5 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test275");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray1 = new java.lang.String[] {};
        sortArgsParser0.parse(strArray1);
        boolean boolean3 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList4 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean5 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean6 = sortArgsParser0.isFirstWordNumber();
        boolean boolean7 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test276");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.lang.Boolean boolean1 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean2 = mkdirArgsParser0.isParent();
        java.util.List<java.lang.String> strList3 = mkdirArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test277");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.util.List<java.lang.String> strList2 = mvArgsParser0.getSourceFiles();
        java.util.List<java.lang.String> strList3 = mvArgsParser0.getSourceFiles();
        java.lang.Boolean boolean4 = mvArgsParser0.isNoOverwrite();
        java.lang.String str5 = mvArgsParser0.getDestination();
        java.lang.Boolean boolean6 = mvArgsParser0.isNoOverwrite();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
    }

    @Test
    public void test278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test278");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList2 = catArgsParser1.getFileList();
        java.util.List<java.lang.String> strList3 = catArgsParser1.getFileList();
        java.util.List<java.lang.String> strList4 = catArgsParser1.getFileList();
        java.util.List<java.lang.String> strList5 = catArgsParser1.getFileList();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication6 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray9 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList10 = new java.util.ArrayList<int[]>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList10, intArray9);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication12 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser14 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser15 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray17 = new java.lang.String[] {};
        sortArgsParser16.parse(strArray17);
        catArgsParser15.parse(strArray17);
        sortArgsParser14.parse(strArray17);
        java.lang.String str21 = catApplication12.catFiles((java.lang.Boolean) false, strArray17);
        java.lang.String str22 = cutApplication6.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList10, strArray17);
        java.lang.String str23 = cutApplication0.cutByByte(strList5, (java.util.List<int[]>) intArrayList10);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertNotNull(intArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
    }

    @Test
    public void test279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test279");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, "- -> Invalid pattern syntax/-", "");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/- -> Invalid pattern syntax/-: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test280");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray3 = new java.lang.String[] {};
        sortArgsParser2.parse(strArray3);
        catArgsParser1.parse(strArray3);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test281");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = sortArgsParser0.isReverseOrder();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test282");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        boolean boolean2 = catArgsParser0.isReadFromStdin();
        boolean boolean3 = catArgsParser0.isReadFromStdin();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test283");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean3 = uniqArgsParser0.isOnlyDuplicates();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication4 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String str8 = pasteApplication4.mergeFile((java.lang.Boolean) false, strArray7);
        uniqArgsParser0.parse(strArray7);
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test284");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        int int1 = uniqArgsParser0.getFileCount();
        int int2 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isCount();
        java.lang.String str4 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test285");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray6 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList7 = new java.util.ArrayList<int[]>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList7, intArray6);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication9 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray14 = new java.lang.String[] {};
        sortArgsParser13.parse(strArray14);
        catArgsParser12.parse(strArray14);
        sortArgsParser11.parse(strArray14);
        java.lang.String str18 = catApplication9.catFiles((java.lang.Boolean) false, strArray14);
        java.lang.String str19 = cutApplication3.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList7, strArray14);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication20 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser22 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser23 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser24 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray25 = new java.lang.String[] {};
        sortArgsParser24.parse(strArray25);
        catArgsParser23.parse(strArray25);
        sortArgsParser22.parse(strArray25);
        java.lang.String str29 = pasteApplication20.mergeFile((java.lang.Boolean) true, strArray25);
        java.lang.String str30 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList7, strArray25);
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser31 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean32 = wcArgsParser31.filesContainDash();
        java.lang.Boolean boolean33 = wcArgsParser31.isByteCount();
        java.lang.Boolean boolean34 = wcArgsParser31.isWordCount();
        java.lang.Boolean boolean35 = wcArgsParser31.isByteCount();
        java.util.List<java.lang.String> strList36 = wcArgsParser31.getFileNames();
        int[][] intArray37 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList38 = new java.util.ArrayList<int[]>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList38, intArray37);
        java.lang.String str40 = cutApplication0.cutByByte(strList36, (java.util.List<int[]>) intArrayList38);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "" + "'", str29, "");
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "" + "'", str30, "");
        org.junit.Assert.assertEquals("'" + boolean32 + "' != '" + false + "'", boolean32, false);
        org.junit.Assert.assertEquals("'" + boolean33 + "' != '" + true + "'", boolean33, true);
        org.junit.Assert.assertEquals("'" + boolean34 + "' != '" + true + "'", boolean34, true);
        org.junit.Assert.assertEquals("'" + boolean35 + "' != '" + true + "'", boolean35, true);
        org.junit.Assert.assertNotNull(strList36);
        org.junit.Assert.assertNotNull(intArray37);
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "" + "'", str40, "");
    }

    @Test
    public void test286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test286");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] {};
        sortArgsParser3.parse(strArray4);
        java.lang.String str6 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray4);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication9 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray12 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList13 = new java.util.ArrayList<int[]>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList13, intArray12);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication15 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray18 = new java.lang.String[] {};
        sortArgsParser17.parse(strArray18);
        java.lang.String str20 = pasteApplication15.mergeFile((java.lang.Boolean) false, strArray18);
        java.lang.String str21 = cutApplication9.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList13, strArray18);
        java.lang.String str22 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "-\nInvalid pattern syntax\nPattern should not be empty.", strArray18);
        java.lang.Class<?> wildcardClass23 = strArray18.getClass();
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(intArray12);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
        org.junit.Assert.assertNotNull(wildcardClass23);
    }

    @Test
    public void test287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test287");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean3 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test288");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String str6 = mvApplication1.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray5);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
    }

    @Test
    public void test289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test289");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication1 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream5 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray8 = new java.lang.String[] {};
        sortArgsParser7.parse(strArray8);
        catArgsParser6.parse(strArray8);
        java.lang.String str11 = catApplication3.catFileAndStdin((java.lang.Boolean) true, inputStream5, strArray8);
        java.lang.String str12 = catApplication1.catFiles((java.lang.Boolean) false, strArray8);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
    }

    @Test
    public void test290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test290");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication5 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray10 = new java.lang.String[] {};
        sortArgsParser9.parse(strArray10);
        catArgsParser8.parse(strArray10);
        sortArgsParser7.parse(strArray10);
        java.lang.String str14 = pasteApplication5.mergeFile((java.lang.Boolean) true, strArray10);
        java.lang.String str15 = grepApplication0.grepFromFiles("- -> Invalid pattern syntax/-", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray10);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\n" + "'", str15, "\n");
    }

    @Test
    public void test291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test291");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.String str4 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean5 = grepArgsParser0.isInvert();
        java.lang.String str6 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean7 = grepArgsParser0.isInvert();
        java.lang.String[] strArray8 = grepArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
        org.junit.Assert.assertNull(strArray8);
    }

    @Test
    public void test292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test292");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray3 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList4 = new java.util.ArrayList<int[]>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList4, intArray3);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication6 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser10 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray11 = pasteArgsParser10.getFiles();
        java.lang.String str12 = wcApplication6.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray11);
        java.lang.String str13 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList4, strArray11);
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser14 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean15 = lsArgsParser14.isRecursive();
        java.lang.Boolean boolean16 = lsArgsParser14.isRecursive();
        java.util.List<java.lang.String> strList17 = lsArgsParser14.getDirectories();
        int[][] intArray18 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList19 = new java.util.ArrayList<int[]>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList19, intArray18);
        java.lang.String str21 = cutApplication0.cutByByte(strList17, (java.util.List<int[]>) intArrayList19);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication24 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray27 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList28 = new java.util.ArrayList<int[]>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList28, intArray27);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication30 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser34 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray35 = pasteArgsParser34.getFiles();
        java.lang.String str36 = wcApplication30.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray35);
        java.lang.String str37 = cutApplication24.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList28, strArray35);
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser38 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean39 = lsArgsParser38.isRecursive();
        java.lang.Boolean boolean40 = lsArgsParser38.isRecursive();
        java.util.List<java.lang.String> strList41 = lsArgsParser38.getDirectories();
        int[][] intArray42 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList43 = new java.util.ArrayList<int[]>();
        boolean boolean44 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList43, intArray42);
        java.lang.String str45 = cutApplication24.cutByByte(strList41, (java.util.List<int[]>) intArrayList43);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication46 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser49 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray50 = pasteArgsParser49.getFiles();
        java.lang.String str51 = mvApplication46.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray50);
        java.lang.String str52 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList43, strArray50);
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertEquals("'" + boolean15 + "' != '" + false + "'", boolean15, false);
        org.junit.Assert.assertEquals("'" + boolean16 + "' != '" + false + "'", boolean16, false);
        org.junit.Assert.assertNotNull(strList17);
        org.junit.Assert.assertNotNull(intArray18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertNotNull(intArray27);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertNotNull(strArray35);
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "" + "'", str36, "");
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + "" + "'", str37, "");
        org.junit.Assert.assertEquals("'" + boolean39 + "' != '" + false + "'", boolean39, false);
        org.junit.Assert.assertEquals("'" + boolean40 + "' != '" + false + "'", boolean40, false);
        org.junit.Assert.assertNotNull(strList41);
        org.junit.Assert.assertNotNull(intArray42);
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + false + "'", boolean44 == false);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertNotNull(strArray50);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "" + "'", str51, "");
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "" + "'", str52, "");
    }

    @Test
    public void test293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test293");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String[] strArray6 = pasteArgsParser4.getFiles();
        java.lang.String str7 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray6);
        java.io.InputStream inputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            long[] longArray9 = wcApplication0.getCountReport(inputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test294");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication3 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser6.getFiles();
        java.lang.String str9 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Pattern should not be empty.", strArray8);
        java.lang.String[] strArray13 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "\n\n\n", strArray13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
// flaky:         org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
    }

    @Test
    public void test295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test295");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        java.lang.Class<?> wildcardClass6 = strList5.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test296");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
    }

    @Test
    public void test297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test297");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication2 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream4 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray7 = new java.lang.String[] {};
        sortArgsParser6.parse(strArray7);
        catArgsParser5.parse(strArray7);
        java.lang.String str10 = catApplication2.catFileAndStdin((java.lang.Boolean) true, inputStream4, strArray7);
        java.lang.String str11 = catApplication0.catFiles((java.lang.Boolean) false, strArray7);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray17 = pasteArgsParser16.getFiles();
        java.lang.String str18 = mvApplication13.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray17);
        java.lang.String str19 = catApplication0.catFiles((java.lang.Boolean) true, strArray17);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser21 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser22 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser23 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray24 = new java.lang.String[] {};
        sortArgsParser23.parse(strArray24);
        catArgsParser22.parse(strArray24);
        sortArgsParser21.parse(strArray24);
        java.lang.String str28 = catApplication0.catFiles((java.lang.Boolean) true, strArray24);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strArray24);
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
    }

    @Test
    public void test298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test298");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isWordCount();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
    }

    @Test
    public void test299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test299");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean5 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean6 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList7 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + true + "'", boolean6, true);
        org.junit.Assert.assertNotNull(strList7);
    }

    @Test
    public void test300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest5.test300");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray6 = pasteArgsParser5.getFiles();
        java.lang.String[] strArray7 = pasteArgsParser5.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser5.getFiles();
        java.lang.String str9 = catApplication3.catFiles((java.lang.Boolean) true, strArray8);
        java.lang.String str10 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
// flaky:         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
    }
}
