package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest6 {

    public static boolean debug = false;

    @Test
    public void test301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test301");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication3 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser6.getFiles();
        java.lang.String str9 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray13 = new java.lang.String[] {};
        sortArgsParser12.parse(strArray13);
        java.lang.String str15 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray13);
        wcArgsParser0.parse(strArray13);
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
// flaky:         org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(strArray13);
// flaky:         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test302");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean2 = lsArgsParser1.isSortByExt();
        java.util.List<java.lang.String> strList3 = lsArgsParser1.getDirectories();
        java.util.List<java.lang.String> strList4 = lsArgsParser1.getDirectories();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication5 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication8 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray11 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList12 = new java.util.ArrayList<int[]>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList12, intArray11);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser14 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray15 = pasteArgsParser14.getFiles();
        java.lang.String[] strArray16 = pasteArgsParser14.getFiles();
        java.lang.String str17 = cutApplication8.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList12, strArray16);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication18 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication21 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser25 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray26 = pasteArgsParser25.getFiles();
        java.lang.String str27 = wcApplication21.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray26);
        java.lang.String str28 = lsApplication18.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray26);
        java.lang.String str29 = cutApplication5.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList12, strArray26);
        java.lang.String str30 = cutApplication0.cutByByte(strList4, (java.util.List<int[]>) intArrayList12);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication33 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray36 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList37 = new java.util.ArrayList<int[]>();
        boolean boolean38 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList37, intArray36);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication39 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser43 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray44 = pasteArgsParser43.getFiles();
        java.lang.String str45 = wcApplication39.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray44);
        java.lang.String str46 = cutApplication33.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList37, strArray44);
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser47 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser48 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser49 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser50 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray51 = new java.lang.String[] {};
        sortArgsParser50.parse(strArray51);
        catArgsParser49.parse(strArray51);
        sortArgsParser48.parse(strArray51);
        wcArgsParser47.parse(strArray51);
        java.lang.String str56 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList37, strArray51);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(intArray11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(strArray15);
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
        org.junit.Assert.assertNotNull(strArray26);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
// flaky:         org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "" + "'", str29, "");
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "" + "'", str30, "");
        org.junit.Assert.assertNotNull(intArray36);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertNotNull(strArray44);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "" + "'", str46, "");
        org.junit.Assert.assertNotNull(strArray51);
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "" + "'", str56, "");
    }

    @Test
    public void test303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test303");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
    }

    @Test
    public void test304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test304");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean3 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test305");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        boolean boolean5 = pasteArgsParser3.isSerial();
        java.lang.String[] strArray6 = pasteArgsParser3.getFiles();
        java.lang.String[] strArray7 = pasteArgsParser3.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test306");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.Boolean boolean4 = grepArgsParser0.isInvert();
        java.lang.String str5 = grepArgsParser0.getPattern();
        java.lang.String[] strArray6 = grepArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNull(strArray6);
    }

    @Test
    public void test307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test307");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication1 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList4 = new java.util.ArrayList<java.lang.String>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList4, strArray3);
        int[][] intArray6 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList7 = new java.util.ArrayList<int[]>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList7, intArray6);
        java.lang.String str9 = cutApplication1.cutByByte((java.util.List<java.lang.String>) strList4, (java.util.List<int[]>) intArrayList7);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication10 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray12 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList13 = new java.util.ArrayList<java.lang.String>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList13, strArray12);
        int[][] intArray15 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList16 = new java.util.ArrayList<int[]>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList16, intArray15);
        java.lang.String str18 = cutApplication10.cutByByte((java.util.List<java.lang.String>) strList13, (java.util.List<int[]>) intArrayList16);
        java.lang.String str19 = cutApplication0.cutByByte((java.util.List<java.lang.String>) strList4, (java.util.List<int[]>) intArrayList16);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication22 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication23 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray25 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList26 = new java.util.ArrayList<java.lang.String>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList26, strArray25);
        int[][] intArray28 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList29 = new java.util.ArrayList<int[]>();
        boolean boolean30 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList29, intArray28);
        java.lang.String str31 = cutApplication23.cutByByte((java.util.List<java.lang.String>) strList26, (java.util.List<int[]>) intArrayList29);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication32 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray34 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList35 = new java.util.ArrayList<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList35, strArray34);
        int[][] intArray37 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList38 = new java.util.ArrayList<int[]>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList38, intArray37);
        java.lang.String str40 = cutApplication32.cutByByte((java.util.List<java.lang.String>) strList35, (java.util.List<int[]>) intArrayList38);
        java.lang.String str41 = cutApplication22.cutByByte((java.util.List<java.lang.String>) strList26, (java.util.List<int[]>) intArrayList38);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser42 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray43 = pasteArgsParser42.getFiles();
        java.lang.String str44 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList38, strArray43);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication47 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication50 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray52 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList53 = new java.util.ArrayList<java.lang.String>();
        boolean boolean54 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList53, strArray52);
        int[][] intArray55 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList56 = new java.util.ArrayList<int[]>();
        boolean boolean57 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList56, intArray55);
        java.lang.String str58 = cutApplication50.cutByByte((java.util.List<java.lang.String>) strList53, (java.util.List<int[]>) intArrayList56);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication59 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser62 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray63 = new java.lang.String[] {};
        sortArgsParser62.parse(strArray63);
        java.lang.String str65 = mvApplication59.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray63);
        java.lang.String str66 = cutApplication47.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList56, strArray63);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser67 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray68 = pasteArgsParser67.getFiles();
        java.lang.String[] strArray69 = pasteArgsParser67.getFiles();
        java.lang.String str70 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList56, strArray69);
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
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
        org.junit.Assert.assertNotNull(intArray28);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "" + "'", str31, "");
        org.junit.Assert.assertNotNull(strArray34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + true + "'", boolean36 == true);
        org.junit.Assert.assertNotNull(intArray37);
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "" + "'", str40, "");
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "" + "'", str41, "");
        org.junit.Assert.assertNotNull(strArray43);
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "" + "'", str44, "");
        org.junit.Assert.assertNotNull(strArray52);
        org.junit.Assert.assertTrue("'" + boolean54 + "' != '" + true + "'", boolean54 == true);
        org.junit.Assert.assertNotNull(intArray55);
        org.junit.Assert.assertTrue("'" + boolean57 + "' != '" + false + "'", boolean57 == false);
        org.junit.Assert.assertEquals("'" + str58 + "' != '" + "" + "'", str58, "");
        org.junit.Assert.assertNotNull(strArray63);
        org.junit.Assert.assertEquals("'" + str65 + "' != '" + "" + "'", str65, "");
        org.junit.Assert.assertEquals("'" + str66 + "' != '" + "" + "'", str66, "");
        org.junit.Assert.assertNotNull(strArray68);
        org.junit.Assert.assertNotNull(strArray69);
        org.junit.Assert.assertEquals("'" + str70 + "' != '" + "" + "'", str70, "");
    }

    @Test
    public void test308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test308");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.util.List<java.lang.String> strList2 = mvArgsParser0.getSourceFiles();
        java.lang.String str3 = mvArgsParser0.getDestination();
        java.util.List<java.lang.String> strList4 = mvArgsParser0.getSourceFiles();
        java.util.List<java.lang.String> strList5 = mvArgsParser0.getSourceFiles();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test309");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.String str5 = uniqArgsParser0.getOutputFile();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication6 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser10 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray13 = new java.lang.String[] {};
        sortArgsParser12.parse(strArray13);
        catArgsParser11.parse(strArray13);
        sortArgsParser10.parse(strArray13);
        java.lang.String str17 = wcApplication6.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray13);
        uniqArgsParser0.parse(strArray13);
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
    }

    @Test
    public void test310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test310");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication4 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray6 = new java.lang.String[] {};
        sortArgsParser5.parse(strArray6);
        java.lang.String str8 = echoApplication4.constructResult(strArray6);
        java.lang.String str9 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray6);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\n" + "'", str8, "\n");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\n" + "'", str9, "\n");
    }

    @Test
    public void test311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test311");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean2 = lsArgsParser0.isRecursive();
        java.util.List<java.lang.String> strList3 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean4 = lsArgsParser0.isRecursive();
        java.util.List<java.lang.String> strList5 = lsArgsParser0.getDirectories();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test312");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean6 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.String str7 = uniqArgsParser0.getInputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test313");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.Boolean boolean4 = grepArgsParser0.isInvert();
        java.lang.String[] strArray5 = grepArgsParser0.getFileNames();
        java.lang.String[] strArray6 = grepArgsParser0.getFileNames();
        java.lang.String[] strArray7 = grepArgsParser0.getFileNames();
        java.lang.Boolean boolean8 = grepArgsParser0.isInvert();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(strArray5);
        org.junit.Assert.assertNull(strArray6);
        org.junit.Assert.assertNull(strArray7);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
    }

    @Test
    public void test314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test314");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication5 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray8 = pasteArgsParser7.getFiles();
        java.lang.String[] strArray9 = pasteArgsParser7.getFiles();
        java.lang.String[] strArray10 = pasteArgsParser7.getFiles();
        java.lang.String str11 = catApplication5.catFiles((java.lang.Boolean) true, strArray10);
        java.lang.String str12 = grepApplication0.grepFromFiles("Invalid pattern syntax\nPattern should not be empty.", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication17 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication20 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser24 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray25 = pasteArgsParser24.getFiles();
        java.lang.String str26 = wcApplication20.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray25);
        java.lang.String str27 = lsApplication17.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray25);
        java.lang.String str28 = grepApplication0.grepFromFiles("Is a directory", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray25);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\n" + "'", str12, "\n");
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "" + "'", str26, "");
// flaky:         org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "\n" + "'", str28, "\n");
    }

    @Test
    public void test315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test315");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList1 = catArgsParser0.getFileList();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        boolean boolean3 = catArgsParser0.isLineNumber();
        boolean boolean4 = catArgsParser0.isReadFromStdin();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test316");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String str6 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray5);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication10 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray14 = pasteArgsParser13.getFiles();
        java.lang.String[] strArray15 = pasteArgsParser13.getFiles();
        java.lang.String str16 = lsApplication10.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray15);
        java.lang.String str17 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray15);
        java.lang.Class<?> wildcardClass18 = sortApplication0.getClass();
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\n" + "'", str6, "\n");
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertNotNull(strArray15);
// flaky:         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\n" + "'", str17, "\n");
        org.junit.Assert.assertNotNull(wildcardClass18);
    }

    @Test
    public void test317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test317");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.util.List<java.lang.String> strList2 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean3 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean4 = lsArgsParser0.isSortByExt();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test318");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean3 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isLineCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
    }

    @Test
    public void test319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test319");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.String str2 = mvArgsParser0.getDestination();
        java.util.List<java.lang.String> strList3 = mvArgsParser0.getSourceFiles();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test320");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.String[] strArray4 = grepArgsParser0.getFileNames();
        java.lang.Boolean boolean5 = grepArgsParser0.isInvert();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNull(strArray4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test321");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean3 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.String str5 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test322");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray6 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList7 = new java.util.ArrayList<int[]>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList7, intArray6);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication9 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray14 = pasteArgsParser13.getFiles();
        java.lang.String str15 = wcApplication9.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray14);
        java.lang.String str16 = cutApplication3.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList7, strArray14);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication19 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList22 = new java.util.ArrayList<java.lang.String>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList22, strArray21);
        int[][] intArray24 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList25 = new java.util.ArrayList<int[]>();
        boolean boolean26 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList25, intArray24);
        java.lang.String str27 = cutApplication19.cutByByte((java.util.List<java.lang.String>) strList22, (java.util.List<int[]>) intArrayList25);
        int[][] intArray30 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList31 = new java.util.ArrayList<int[]>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList31, intArray30);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser33 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray34 = pasteArgsParser33.getFiles();
        java.lang.String str35 = cutApplication19.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList31, strArray34);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication36 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray38 = new java.lang.String[] {};
        java.lang.String str39 = pasteApplication36.mergeFile((java.lang.Boolean) false, strArray38);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser41 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray42 = new java.lang.String[] {};
        sortArgsParser41.parse(strArray42);
        java.lang.String str44 = pasteApplication36.mergeFile((java.lang.Boolean) false, strArray42);
        java.lang.String str45 = cutApplication3.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList31, strArray42);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray42);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
        org.junit.Assert.assertNotNull(intArray24);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertNotNull(intArray30);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertNotNull(strArray34);
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "" + "'", str35, "");
        org.junit.Assert.assertNotNull(strArray38);
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "" + "'", str39, "");
        org.junit.Assert.assertNotNull(strArray42);
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "" + "'", str44, "");
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
    }

    @Test
    public void test323() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test323");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication3 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication7 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray12 = pasteArgsParser11.getFiles();
        java.lang.String[] strArray13 = pasteArgsParser11.getFiles();
        java.lang.String str14 = sortApplication7.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray13);
        java.lang.String str15 = sortApplication3.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray13);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) false, strArray13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "\n" + "'", str14, "\n");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\n" + "'", str15, "\n");
    }

    @Test
    public void test324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test324");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.String str3 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test325");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        boolean boolean3 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean4 = sortArgsParser0.isReverseOrder();
        boolean boolean5 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test326() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test326");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList1 = catArgsParser0.getFileList();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        boolean boolean3 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList4 = catArgsParser0.getFileList();
        boolean boolean5 = catArgsParser0.isLineNumber();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test327() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test327");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean2 = lsArgsParser1.isSortByExt();
        java.lang.Boolean boolean3 = lsArgsParser1.isSortByExt();
        java.util.List<java.lang.String> strList4 = lsArgsParser1.getDirectories();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication5 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray8 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList9 = new java.util.ArrayList<int[]>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList9, intArray8);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray12 = pasteArgsParser11.getFiles();
        java.lang.String[] strArray13 = pasteArgsParser11.getFiles();
        java.lang.String str14 = cutApplication5.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList9, strArray13);
        java.lang.String str15 = cutApplication0.cutByByte(strList4, (java.util.List<int[]>) intArrayList9);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(intArray8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test328() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test328");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.String str3 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test329() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test329");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String str6 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray5);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication10 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser14 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray15 = pasteArgsParser14.getFiles();
        java.lang.String str16 = wcApplication10.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray15);
        java.lang.String str17 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray15);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\n" + "'", str6, "\n");
        org.junit.Assert.assertNotNull(strArray15);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\n" + "'", str17, "\n");
    }

    @Test
    public void test330() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test330");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean4 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isWordCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
    }

    @Test
    public void test331() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test331");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        boolean boolean2 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean3 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList4 = sortArgsParser0.getFileNames();
        java.lang.Class<?> wildcardClass5 = strList4.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test332() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test332");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.String str4 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean5 = grepArgsParser0.isInvert();
        java.lang.String str6 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean7 = grepArgsParser0.isInvert();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication8 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray13 = pasteArgsParser12.getFiles();
        java.lang.String str14 = wcApplication8.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray13);
        grepArgsParser0.parse(strArray13);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
    }

    @Test
    public void test333() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test333");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isSortByExt();
        java.util.List<java.lang.String> strList2 = lsArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList3 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean4 = lsArgsParser0.isSortByExt();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test334");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        int int1 = uniqArgsParser0.getFileCount();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.String str3 = uniqArgsParser0.getOutputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.String str5 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean6 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
    }

    @Test
    public void test335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test335");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean2 = lsArgsParser0.isRecursive();
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
    public void test336() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test336");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean2 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean3 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean4 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean5 = lsArgsParser0.isSortByExt();
        java.util.List<java.lang.String> strList6 = lsArgsParser0.getDirectories();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test337() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test337");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.String str6 = uniqArgsParser0.getInputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str6);
    }

    @Test
    public void test338() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test338");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean2 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean3 = mvArgsParser0.isNoOverwrite();
        java.lang.String str4 = mvArgsParser0.getDestination();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test339");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray6 = pasteArgsParser5.getFiles();
        java.lang.String str7 = grepApplication0.grepFromFiles("-", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray6);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\n" + "'", str7, "\n");
    }

    @Test
    public void test340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test340");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        boolean boolean5 = pasteArgsParser3.isSerial();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList7 = catArgsParser6.getFileList();
        java.util.List<java.lang.String> strList8 = catArgsParser6.getFileList();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray11 = new java.lang.String[] { "-" };
        grepArgsParser9.parse(strArray11);
        catArgsParser6.parse(strArray11);
        pasteArgsParser3.parse(strArray11);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray11);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: -: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strList7);
        org.junit.Assert.assertNotNull(strList8);
        org.junit.Assert.assertNotNull(strArray11);
    }

    @Test
    public void test341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test341");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean2 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean3 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test342");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, "Is a directory", "Invalid pattern syntax\nPattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Is a directory: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test343");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, "\n\n\n", "-\nInvalid pattern syntax\nPattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/???: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test344() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test344");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean6 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
    }

    @Test
    public void test345() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test345");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList2 = sortArgsParser0.getFileNames();
        boolean boolean3 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test346");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication1 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray6 = pasteArgsParser5.getFiles();
        java.lang.String str7 = sortApplication1.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray6);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication11 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser15 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray16 = new java.lang.String[] {};
        sortArgsParser15.parse(strArray16);
        java.lang.String str18 = wcApplication11.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray16);
        java.lang.String str19 = sortApplication1.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray16);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\n" + "'", str7, "\n");
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "\n" + "'", str19, "\n");
    }

    @Test
    public void test347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test347");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication4 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray7 = new java.lang.String[] {};
        sortArgsParser6.parse(strArray7);
        java.lang.String str9 = pasteApplication4.mergeFile((java.lang.Boolean) false, strArray7);
        java.lang.String str10 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray7);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\n" + "'", str10, "\n");
    }

    @Test
    public void test348() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test348");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean4 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean5 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test349");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest6.test350");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication1 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray6 = pasteArgsParser5.getFiles();
        java.lang.String str7 = sortApplication1.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray6);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\n" + "'", str7, "\n");
    }
}
