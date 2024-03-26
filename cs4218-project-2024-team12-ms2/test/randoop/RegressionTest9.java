package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest9 {

    public static boolean debug = false;

    @Test
    public void test451() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test451");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream5 = null;
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser6.getFiles();
        java.lang.String[] strArray9 = pasteArgsParser6.getFiles();
        java.lang.String str10 = grepApplication0.grepFromFileAndStdin("Null Pointer Exception", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream5, strArray9);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\n" + "'", str10, "\n");
    }

    @Test
    public void test452() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test452");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        boolean boolean6 = pasteArgsParser4.isSerial();
        java.lang.String[] strArray7 = pasteArgsParser4.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser4.getFiles();
        java.lang.String str9 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray8);
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray15 = new java.lang.String[] { "-" };
        grepArgsParser13.parse(strArray15);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str17 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray15);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\n" + "'", str9, "\n");
        org.junit.Assert.assertNotNull(strArray15);
    }

    @Test
    public void test453() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test453");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean2 = lsArgsParser0.isSortByExt();
        java.util.List<java.lang.String> strList3 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean4 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean5 = lsArgsParser0.isRecursive();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test454() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test454");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray6 = pasteArgsParser5.getFiles();
        uniqArgsParser0.parse(strArray6);
        java.lang.Boolean boolean8 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.String str9 = uniqArgsParser0.getInputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test455() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test455");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray3 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList4 = new java.util.ArrayList<int[]>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList4, intArray3);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication6 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray9 = new java.lang.String[] {};
        sortArgsParser8.parse(strArray9);
        java.lang.String str11 = pasteApplication6.mergeFile((java.lang.Boolean) false, strArray9);
        java.lang.String str12 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList4, strArray9);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray14 = new java.lang.String[] {};
        sortArgsParser13.parse(strArray14);
        boolean boolean16 = sortArgsParser13.isCaseIndependent();
        java.util.List<java.lang.String> strList17 = sortArgsParser13.getFileNames();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication18 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray21 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList22 = new java.util.ArrayList<int[]>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList22, intArray21);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication24 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser28 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray29 = pasteArgsParser28.getFiles();
        java.lang.String str30 = wcApplication24.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray29);
        java.lang.String str31 = cutApplication18.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList22, strArray29);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser32 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean33 = sortArgsParser32.isCaseIndependent();
        java.util.List<java.lang.String> strList34 = sortArgsParser32.getFileNames();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication35 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication38 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray40 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList41 = new java.util.ArrayList<java.lang.String>();
        boolean boolean42 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList41, strArray40);
        int[][] intArray43 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList44 = new java.util.ArrayList<int[]>();
        boolean boolean45 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList44, intArray43);
        java.lang.String str46 = cutApplication38.cutByByte((java.util.List<java.lang.String>) strList41, (java.util.List<int[]>) intArrayList44);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser47 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray48 = pasteArgsParser47.getFiles();
        java.lang.String[] strArray49 = pasteArgsParser47.getFiles();
        java.lang.String str50 = cutApplication35.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList44, strArray49);
        java.lang.String str51 = cutApplication18.cutByByte(strList34, (java.util.List<int[]>) intArrayList44);
        java.lang.String str52 = cutApplication0.cutByByte(strList17, (java.util.List<int[]>) intArrayList44);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser53 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean54 = mvArgsParser53.isNoOverwrite();
        java.util.List<java.lang.String> strList55 = mvArgsParser53.getSourceFiles();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication56 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray58 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList59 = new java.util.ArrayList<java.lang.String>();
        boolean boolean60 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList59, strArray58);
        int[][] intArray61 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList62 = new java.util.ArrayList<int[]>();
        boolean boolean63 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList62, intArray61);
        java.lang.String str64 = cutApplication56.cutByByte((java.util.List<java.lang.String>) strList59, (java.util.List<int[]>) intArrayList62);
        java.lang.String str65 = cutApplication0.cutByByte(strList55, (java.util.List<int[]>) intArrayList62);
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(strList17);
        org.junit.Assert.assertNotNull(intArray21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(strArray29);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "" + "'", str30, "");
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "" + "'", str31, "");
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(strList34);
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + true + "'", boolean42 == true);
        org.junit.Assert.assertNotNull(intArray43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "" + "'", str46, "");
        org.junit.Assert.assertNotNull(strArray48);
        org.junit.Assert.assertNotNull(strArray49);
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "" + "'", str50, "");
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "" + "'", str51, "");
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "" + "'", str52, "");
        org.junit.Assert.assertEquals("'" + boolean54 + "' != '" + false + "'", boolean54, false);
        org.junit.Assert.assertNotNull(strList55);
        org.junit.Assert.assertNotNull(strArray58);
        org.junit.Assert.assertTrue("'" + boolean60 + "' != '" + true + "'", boolean60 == true);
        org.junit.Assert.assertNotNull(intArray61);
        org.junit.Assert.assertTrue("'" + boolean63 + "' != '" + false + "'", boolean63 == false);
        org.junit.Assert.assertEquals("'" + str64 + "' != '" + "" + "'", str64, "");
        org.junit.Assert.assertEquals("'" + str65 + "' != '" + "" + "'", str65, "");
    }

    @Test
    public void test456() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test456");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.String[] strArray2 = null;
        // The following exception was thrown during execution in test generation
        try {
            wcArgsParser0.parse(strArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read the array length because \"<local2>\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test457() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test457");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[] {};
        sortArgsParser4.parse(strArray5);
        java.lang.String str7 = mvApplication1.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray5);
        wcArgsParser0.parse(strArray5);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test458() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test458");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, "- -> Invalid pattern syntax/-", "");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/- -> Invalid pattern syntax/-: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test459() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test459");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test460() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test460");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.util.List<int[]> intArrayList3 = null;
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication4 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray9 = new java.lang.String[] {};
        java.lang.String str10 = grepApplication4.grepFromFiles("- -> Invalid pattern syntax/-", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray9);
        java.lang.String str11 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, intArrayList3, strArray9);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\n" + "'", str10, "\n");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
    }

    @Test
    public void test461() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test461");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean3 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test462() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test462");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean3 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean4 = wcArgsParser0.isLineCount();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
    }

    @Test
    public void test463() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test463");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList4 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean5 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean6 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + true + "'", boolean6, true);
    }

    @Test
    public void test464() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test464");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        int int2 = uniqArgsParser0.getFileCount();
        java.lang.String str3 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test465() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test465");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.util.List<java.lang.String> strList2 = lsArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList3 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean4 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean5 = lsArgsParser0.isRecursive();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test466() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test466");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isLineCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
    }

    @Test
    public void test467() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test467");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray3 = new java.lang.String[] {};
        sortArgsParser2.parse(strArray3);
        sortArgsParser0.parse(strArray3);
        java.util.List<java.lang.String> strList6 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test468() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test468");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        boolean boolean1 = pasteArgsParser0.isSerial();
        boolean boolean2 = pasteArgsParser0.isSerial();
        java.lang.String[] strArray3 = pasteArgsParser0.getFiles();
        boolean boolean4 = pasteArgsParser0.isSerial();
        boolean boolean5 = pasteArgsParser0.isSerial();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test469() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test469");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean2 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean3 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean4 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean5 = lsArgsParser0.isSortByExt();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test470() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test470");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        boolean boolean3 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList4 = catArgsParser0.getFileList();
        boolean boolean5 = catArgsParser0.isLineNumber();
        boolean boolean6 = catArgsParser0.isLineNumber();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test471() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test471");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray1 = new java.lang.String[] {};
        sortArgsParser0.parse(strArray1);
        boolean boolean3 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList4 = sortArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test472() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test472");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "" };
        java.lang.String str7 = grepApplication0.grepFromFiles("Is a directory", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray6);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication12 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream14 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser15 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray17 = new java.lang.String[] {};
        sortArgsParser16.parse(strArray17);
        catArgsParser15.parse(strArray17);
        java.lang.String str20 = catApplication12.catFileAndStdin((java.lang.Boolean) true, inputStream14, strArray17);
        java.lang.String str21 = grepApplication0.grepFromFiles("Is a directory", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray17);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser26 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray27 = pasteArgsParser26.getFiles();
        java.lang.String[] strArray28 = pasteArgsParser26.getFiles();
        java.lang.String str29 = grepApplication0.grepFromFiles("Pattern should not be empty.", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray28);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + ": Is a directory\n" + "'", str7, ": Is a directory\n");
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertNotNull(strArray27);
        org.junit.Assert.assertNotNull(strArray28);
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "\n" + "'", str29, "\n");
    }

    @Test
    public void test473() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test473");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.Boolean boolean4 = grepArgsParser0.isInvert();
        java.lang.String str5 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean6 = grepArgsParser0.isInvert();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
    }

    @Test
    public void test474() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test474");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        boolean boolean2 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList3 = catArgsParser0.getFileList();
        boolean boolean4 = catArgsParser0.isReadFromStdin();
        boolean boolean5 = catArgsParser0.isLineNumber();
        java.util.List<java.lang.String> strList6 = catArgsParser0.getFileList();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test475() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test475");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean2 = lsArgsParser0.isSortByExt();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test476() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test476");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList2 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        boolean boolean6 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test477() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test477");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray3 = new java.lang.String[] {};
        sortArgsParser2.parse(strArray3);
        catArgsParser1.parse(strArray3);
        sortArgsParser0.parse(strArray3);
        boolean boolean7 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean8 = sortArgsParser0.isFirstWordNumber();
        boolean boolean9 = sortArgsParser0.isCaseIndependent();
        boolean boolean10 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test478() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test478");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] {};
        sortArgsParser1.parse(strArray2);
        java.lang.String str4 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication8 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray12 = pasteArgsParser11.getFiles();
        java.lang.String[] strArray13 = pasteArgsParser11.getFiles();
        java.lang.String str14 = lsApplication8.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray13);
        java.lang.String str15 = mvApplication5.mvFilesToFolder((java.lang.Boolean) true, "Pattern should not be empty.", strArray13);
        java.lang.String str16 = echoApplication0.constructResult(strArray13);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication17 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication20 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray22 = new java.lang.String[] {};
        java.lang.String str23 = pasteApplication20.mergeFile((java.lang.Boolean) false, strArray22);
        java.lang.String str24 = lsApplication17.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray22);
        java.lang.String str25 = echoApplication0.constructResult(strArray22);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertNotNull(strArray13);
// flaky:         org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "\n" + "'", str16, "\n");
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
// flaky:         org.junit.Assert.assertEquals("'" + str24 + "' != '" + "" + "'", str24, "");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "\n" + "'", str25, "\n");
    }

    @Test
    public void test479() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test479");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String str6 = wcApplication0.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray5);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser10 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray11 = pasteArgsParser10.getFiles();
        java.lang.String[] strArray12 = pasteArgsParser10.getFiles();
        java.lang.String[] strArray13 = pasteArgsParser10.getFiles();
        java.lang.String str14 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray13);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
    }

    @Test
    public void test480() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test480");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.util.List<java.lang.String> strList1 = mkdirArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = mkdirArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean4 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean5 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean6 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean7 = mkdirArgsParser0.isParent();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
    }

    @Test
    public void test481() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test481");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isReverseOrder();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean6 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
    }

    @Test
    public void test482() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test482");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test483() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test483");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication1 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication3 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray8 = new java.lang.String[] {};
        sortArgsParser7.parse(strArray8);
        catArgsParser6.parse(strArray8);
        sortArgsParser5.parse(strArray8);
        java.lang.String str12 = pasteApplication3.mergeFile((java.lang.Boolean) true, strArray8);
        java.lang.String str13 = pasteApplication1.mergeFile((java.lang.Boolean) true, strArray8);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test484() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test484");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication1 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication3 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser6.getFiles();
        java.lang.String str9 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray13 = pasteArgsParser12.getFiles();
        java.lang.String[] strArray14 = pasteArgsParser12.getFiles();
        java.lang.String str15 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray14);
        java.lang.String str16 = catApplication1.catFiles((java.lang.Boolean) false, strArray14);
        java.lang.String str17 = echoApplication0.constructResult(strArray14);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication18 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication21 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray23 = new java.lang.String[] {};
        java.lang.String str24 = pasteApplication21.mergeFile((java.lang.Boolean) false, strArray23);
        java.lang.String str25 = lsApplication18.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray23);
        java.lang.String str26 = echoApplication0.constructResult(strArray23);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
// flaky:         org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertNotNull(strArray14);
// flaky:         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\n" + "'", str17, "\n");
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "" + "'", str24, "");
// flaky:         org.junit.Assert.assertEquals("'" + str25 + "' != '" + "" + "'", str25, "");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "\n" + "'", str26, "\n");
    }

    @Test
    public void test485() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test485");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
    }

    @Test
    public void test486() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test486");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray3 = pasteArgsParser2.getFiles();
        java.lang.String[] strArray4 = pasteArgsParser2.getFiles();
        java.lang.String[] strArray5 = pasteArgsParser2.getFiles();
        java.lang.String str6 = catApplication0.catFiles((java.lang.Boolean) true, strArray5);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray9 = pasteArgsParser8.getFiles();
        java.lang.String[] strArray10 = pasteArgsParser8.getFiles();
        java.lang.String str11 = catApplication0.catFiles((java.lang.Boolean) false, strArray10);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
    }

    @Test
    public void test487() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test487");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        boolean boolean2 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList3 = catArgsParser0.getFileList();
        boolean boolean4 = catArgsParser0.isReadFromStdin();
        boolean boolean5 = catArgsParser0.isLineNumber();
        boolean boolean6 = catArgsParser0.isLineNumber();
        java.util.List<java.lang.String> strList7 = catArgsParser0.getFileList();
        java.util.List<java.lang.String> strList8 = catArgsParser0.getFileList();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strList7);
        org.junit.Assert.assertNotNull(strList8);
    }

    @Test
    public void test488() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test488");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.util.List<java.lang.String> strList1 = mkdirArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = mkdirArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = mkdirArgsParser0.isParent();
        java.lang.Boolean boolean4 = mkdirArgsParser0.isParent();
        java.util.List<java.lang.String> strList5 = mkdirArgsParser0.getFileNames();
        java.lang.Boolean boolean6 = mkdirArgsParser0.isParent();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
    }

    @Test
    public void test489() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test489");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.String str1 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean2 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str4 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test490() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest9.test490");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean5 = mvArgsParser4.isNoOverwrite();
        java.util.List<java.lang.String> strList6 = mvArgsParser4.getSourceFiles();
        java.util.List<java.lang.String> strList7 = mvArgsParser4.getSourceFiles();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication8 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        int[][] intArray13 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList14 = new java.util.ArrayList<int[]>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList14, intArray13);
        java.lang.String str16 = cutApplication8.cutByByte((java.util.List<java.lang.String>) strList11, (java.util.List<int[]>) intArrayList14);
        java.lang.String str17 = cutApplication3.cutByByte(strList7, (java.util.List<int[]>) intArrayList14);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication18 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser20 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray21 = pasteArgsParser20.getFiles();
        java.lang.String[] strArray22 = pasteArgsParser20.getFiles();
        java.lang.String[] strArray23 = pasteArgsParser20.getFiles();
        java.lang.String str24 = pasteApplication18.mergeFile((java.lang.Boolean) false, strArray23);
        java.lang.String str25 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList14, strArray23);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(strList6);
        org.junit.Assert.assertNotNull(strList7);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(intArray13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "" + "'", str24, "");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "" + "'", str25, "");
    }
}
