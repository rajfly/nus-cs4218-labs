package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest1 {

    public static boolean debug = false;

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test051");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) false, strArray4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test052");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.util.List<java.lang.String> strList1 = mkdirArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = mkdirArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = mkdirArgsParser0.isParent();
        java.util.List<java.lang.String> strList4 = mkdirArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test053");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { ": Is a directory\n", "Invalid pattern syntax", "Is a directory", "\n" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication8 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        int[][] intArray13 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList14 = new java.util.ArrayList<int[]>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList14, intArray13);
        java.lang.String str16 = cutApplication8.cutByByte((java.util.List<java.lang.String>) strList11, (java.util.List<int[]>) intArrayList14);
        java.lang.String str17 = cutApplication0.cutByByte((java.util.List<java.lang.String>) strList6, (java.util.List<int[]>) intArrayList14);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(intArray13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "\n\n\n" + "'", str17, "\n\n\n");
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test054");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray2 = pasteArgsParser1.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test055");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String str5 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray4);
        java.lang.String[] strArray8 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, ": Is a directory\n", strArray8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test056");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) true, strArray4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test057");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList2 = catArgsParser1.getFileList();
        java.util.List<java.lang.String> strList3 = catArgsParser1.getFileList();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray6 = new java.lang.String[] { "-" };
        grepArgsParser4.parse(strArray6);
        catArgsParser1.parse(strArray6);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray6);
// flaky:             org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MkdirException; message: mkdir: -: File or directory already exists");
        } catch (sg.edu.nus.comp.cs4218.exception.MkdirException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test058");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean4 = catArgsParser3.isReadFromStdin();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray7 = new java.lang.String[] { "-" };
        grepArgsParser5.parse(strArray7);
        catArgsParser3.parse(strArray7);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray7);
// flaky:             org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: -: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test059");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray3 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList4 = new java.util.ArrayList<int[]>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList4, intArray3);
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray8 = new java.lang.String[] { "-" };
        grepArgsParser6.parse(strArray8);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList4, strArray8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray8);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test060");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        int int1 = uniqArgsParser0.getFileCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test061");
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
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication44 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication45 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray47 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList48 = new java.util.ArrayList<java.lang.String>();
        boolean boolean49 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList48, strArray47);
        int[][] intArray50 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList51 = new java.util.ArrayList<int[]>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList51, intArray50);
        java.lang.String str53 = cutApplication45.cutByByte((java.util.List<java.lang.String>) strList48, (java.util.List<int[]>) intArrayList51);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication54 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray56 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList57 = new java.util.ArrayList<java.lang.String>();
        boolean boolean58 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList57, strArray56);
        int[][] intArray59 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList60 = new java.util.ArrayList<int[]>();
        boolean boolean61 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList60, intArray59);
        java.lang.String str62 = cutApplication54.cutByByte((java.util.List<java.lang.String>) strList57, (java.util.List<int[]>) intArrayList60);
        java.lang.String str63 = cutApplication44.cutByByte((java.util.List<java.lang.String>) strList48, (java.util.List<int[]>) intArrayList60);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser64 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray65 = pasteArgsParser64.getFiles();
        java.lang.String str66 = cutApplication22.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList60, strArray65);
        java.lang.String[] strArray67 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str68 = cutApplication0.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList60, strArray67);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read the array length because \"<local7>\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
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
        org.junit.Assert.assertNotNull(strArray47);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + true + "'", boolean49 == true);
        org.junit.Assert.assertNotNull(intArray50);
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertEquals("'" + str53 + "' != '" + "" + "'", str53, "");
        org.junit.Assert.assertNotNull(strArray56);
        org.junit.Assert.assertTrue("'" + boolean58 + "' != '" + true + "'", boolean58 == true);
        org.junit.Assert.assertNotNull(intArray59);
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertEquals("'" + str62 + "' != '" + "" + "'", str62, "");
        org.junit.Assert.assertEquals("'" + str63 + "' != '" + "" + "'", str63, "");
        org.junit.Assert.assertNotNull(strArray65);
        org.junit.Assert.assertEquals("'" + str66 + "' != '" + "" + "'", str66, "");
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test062");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        java.lang.String[] strArray3 = null;
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) true, strArray3);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test063");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication2 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray5 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList6 = new java.util.ArrayList<int[]>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList6, intArray5);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication8 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser10 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray13 = new java.lang.String[] {};
        sortArgsParser12.parse(strArray13);
        catArgsParser11.parse(strArray13);
        sortArgsParser10.parse(strArray13);
        java.lang.String str17 = catApplication8.catFiles((java.lang.Boolean) false, strArray13);
        java.lang.String str18 = cutApplication2.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList6, strArray13);
        java.lang.String str19 = pasteApplication0.mergeFile((java.lang.Boolean) false, strArray13);
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test064");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test065");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        java.lang.Class<?> wildcardClass6 = uniqArgsParser0.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test066");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication4 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray8 = pasteArgsParser7.getFiles();
        java.lang.String[] strArray9 = pasteArgsParser7.getFiles();
        java.lang.String str10 = lsApplication4.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        java.lang.String str11 = mvApplication1.mvFilesToFolder((java.lang.Boolean) true, "Pattern should not be empty.", strArray9);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray9);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertNotNull(strArray9);
// flaky:         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "-\nInvalid pattern syntax\nPattern should not be empty." + "'", str10, "-\nInvalid pattern syntax\nPattern should not be empty.");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test067");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test068");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.util.List<java.lang.String> strList1 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean2 = lsArgsParser0.isSortByExt();
        java.util.List<java.lang.String> strList3 = lsArgsParser0.getDirectories();
        java.lang.Class<?> wildcardClass4 = strList3.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test069");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.String str2 = mvArgsParser0.getDestination();
        java.lang.Boolean boolean3 = mvArgsParser0.isNoOverwrite();
        java.lang.String str4 = mvArgsParser0.getDestination();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test070");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        java.lang.String str4 = null; // flaky: mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, "-", "Pattern should not be empty.");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray8 = pasteArgsParser7.getFiles();
        java.lang.String[] strArray9 = pasteArgsParser7.getFiles();
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "-\nInvalid pattern syntax\nPattern should not be empty.", strArray9);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication13 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray18 = new java.lang.String[] {};
        sortArgsParser17.parse(strArray18);
        java.lang.String str20 = wcApplication13.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray18);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str21 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "\n\n\n", strArray18);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: ???: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
// flaky:         org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test071");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        int int3 = uniqArgsParser0.getFileCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test072");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "hi!", "Invalid pattern syntax", "-" };
        java.lang.String str9 = grepApplication0.grepFromFiles("-", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray8);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication14 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray18 = pasteArgsParser17.getFiles();
        java.lang.String[] strArray19 = pasteArgsParser17.getFiles();
        java.lang.String str20 = lsApplication14.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray19);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser23 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray24 = new java.lang.String[] {};
        sortArgsParser23.parse(strArray24);
        java.lang.String str26 = lsApplication14.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray24);
        java.lang.String str27 = grepApplication0.grepFromFiles("Null Pointer Exception", (java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray24);
        org.junit.Assert.assertNotNull(strArray8);
// flaky:         org.junit.Assert.assertEquals("'" + str9 + "' != '" + "hi!: No such file or directory\nInvalid pattern syntax: Is a directory\n-: Is a directory\n" + "'", str9, "hi!: No such file or directory\nInvalid pattern syntax: Is a directory\n-: Is a directory\n");
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertNotNull(strArray19);
// flaky:         org.junit.Assert.assertEquals("'" + str20 + "' != '" + "-\nInvalid pattern syntax\nPattern should not be empty." + "'", str20, "-\nInvalid pattern syntax\nPattern should not be empty.");
        org.junit.Assert.assertNotNull(strArray24);
// flaky:         org.junit.Assert.assertEquals("'" + str26 + "' != '" + "-\nInvalid pattern syntax\nPattern should not be empty." + "'", str26, "-\nInvalid pattern syntax\nPattern should not be empty.");
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test073");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
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
        java.lang.String str15 = null; // flaky: mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Invalid pattern syntax", strArray11);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strList7);
        org.junit.Assert.assertNotNull(strList8);
        org.junit.Assert.assertNotNull(strArray11);
// flaky:         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "- -> Invalid pattern syntax/-" + "'", str15, "- -> Invalid pattern syntax/-");
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test074");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList3 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test075");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList4 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test076");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, "hi!", "Pattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/hi!: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test077");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("- -> Invalid pattern syntax/-");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: - -> Invalid pattern syntax/-: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test078");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Class<?> wildcardClass2 = wcArgsParser0.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test079");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] {};
        sortArgsParser3.parse(strArray4);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test080");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.lang.Class<?> wildcardClass4 = strList3.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test081");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.String str1 = uniqArgsParser0.getInputFile();
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test082");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.Boolean boolean1 = grepArgsParser0.isInvert();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test083");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.Boolean boolean1 = grepArgsParser0.isInvert();
        java.lang.Boolean boolean2 = grepArgsParser0.isInvert();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test084");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Class<?> wildcardClass2 = uniqArgsParser0.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test085");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test086");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test087");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String[] strArray5 = pasteArgsParser3.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test088");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication2 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "hi!", "Invalid pattern syntax", "-" };
        java.lang.String str11 = grepApplication2.grepFromFiles("-", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray10);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = catApplication0.catFiles((java.lang.Boolean) false, strArray10);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray10);
// flaky:         org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!: No such file or directory\nInvalid pattern syntax: Is a directory\n-: No such file or directory\n" + "'", str11, "hi!: No such file or directory\nInvalid pattern syntax: Is a directory\n-: No such file or directory\n");
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test089");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isReverseOrder();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication6 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray10 = pasteArgsParser9.getFiles();
        java.lang.String[] strArray11 = pasteArgsParser9.getFiles();
        java.lang.String str12 = lsApplication6.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray11);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser15 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray16 = new java.lang.String[] {};
        sortArgsParser15.parse(strArray16);
        java.lang.String str18 = lsApplication6.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        sortArgsParser0.parse(strArray16);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertNotNull(strArray11);
// flaky:         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "Invalid pattern syntax\nPattern should not be empty." + "'", str12, "Invalid pattern syntax\nPattern should not be empty.");
        org.junit.Assert.assertNotNull(strArray16);
// flaky:         org.junit.Assert.assertEquals("'" + str18 + "' != '" + "Invalid pattern syntax\nPattern should not be empty." + "'", str18, "Invalid pattern syntax\nPattern should not be empty.");
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test090");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str4 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test091");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication3 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray8 = pasteArgsParser7.getFiles();
        java.lang.String[] strArray9 = pasteArgsParser7.getFiles();
        java.lang.String str10 = sortApplication3.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\n" + "'", str10, "\n");
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test092");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean3 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test093");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, "Pattern should not be empty.", "");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/Pattern should not be empty.: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test094");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.String str4 = grepArgsParser0.getPattern();
        java.lang.Boolean boolean5 = grepArgsParser0.isInvert();
        java.lang.String str6 = grepArgsParser0.getPattern();
        java.lang.String str7 = grepArgsParser0.getPattern();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test095");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] {};
        sortArgsParser3.parse(strArray4);
        java.lang.String str6 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray4);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication9 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray13 = pasteArgsParser12.getFiles();
        java.lang.String[] strArray14 = pasteArgsParser12.getFiles();
        java.lang.String str15 = lsApplication9.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray14);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "\n\n\n", strArray14);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: ???: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertNotNull(strArray14);
// flaky:         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "Invalid pattern syntax\nPattern should not be empty." + "'", str15, "Invalid pattern syntax\nPattern should not be empty.");
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test096");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, "Null Pointer Exception", "Null Pointer Exception");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/Null Pointer Exception: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test097");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        java.lang.Class<?> wildcardClass1 = lsApplication0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test098");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String str6 = wcApplication0.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray5);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser10 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray11 = pasteArgsParser10.getFiles();
        java.lang.String str12 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray11);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test099");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser.FLAG_WORD_COUNT;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'w' + "'", char0 == 'w');
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test100");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
// flaky:         cdApplication0.changeToDirectory("Invalid pattern syntax");
    }
}
