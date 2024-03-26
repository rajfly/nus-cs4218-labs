package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest3 {

    public static boolean debug = false;

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test151");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream4 = null;
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication5 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication9 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication12 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray14 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList15 = new java.util.ArrayList<java.lang.String>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList15, strArray14);
        int[][] intArray17 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList18 = new java.util.ArrayList<int[]>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList18, intArray17);
        java.lang.String str20 = cutApplication12.cutByByte((java.util.List<java.lang.String>) strList15, (java.util.List<int[]>) intArrayList18);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser21 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray22 = pasteArgsParser21.getFiles();
        java.lang.String[] strArray23 = pasteArgsParser21.getFiles();
        java.lang.String str24 = cutApplication9.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList18, strArray23);
        java.lang.String str25 = sortApplication5.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray23);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str26 = wcApplication0.countFromFileAndStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, inputStream4, strArray23);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(intArray17);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "" + "'", str24, "");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "\n" + "'", str25, "\n");
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test152");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        int int6 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean7 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test153");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean2 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean3 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean4 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean5 = lsArgsParser0.isRecursive();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test154");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray7 = new java.lang.String[]{};
        sortArgsParser6.parse(strArray7);
        java.lang.String str9 = mvApplication3.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray7);
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "- -> Invalid pattern syntax/-", strArray7);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test155");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication3 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser6.getFiles();
        java.lang.String str9 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Pattern should not be empty.", strArray8);
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser13 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        java.util.List<java.lang.String> strList14 = catArgsParser13.getFileList();
        java.util.List<java.lang.String> strList15 = catArgsParser13.getFileList();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray18 = new java.lang.String[]{"-"};
        grepArgsParser16.parse(strArray18);
        catArgsParser13.parse(strArray18);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str21 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, ": Is a directory\n", strArray18);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: -: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
// flaky:         org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertNotNull(strList14);
        org.junit.Assert.assertNotNull(strList15);
        org.junit.Assert.assertNotNull(strArray18);
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test156");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[]{};
        sortArgsParser3.parse(strArray4);
        java.lang.String str6 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray4);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication9 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray12 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList13 = new java.util.ArrayList<int[]>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList13, intArray12);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication15 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray18 = new java.lang.String[]{};
        sortArgsParser17.parse(strArray18);
        java.lang.String str20 = pasteApplication15.mergeFile((java.lang.Boolean) false, strArray18);
        java.lang.String str21 = cutApplication9.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList13, strArray18);
        java.lang.String str22 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "-\nInvalid pattern syntax\nPattern should not be empty.", strArray18);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str26 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, "Is a directory", "-\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Is a directory: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(intArray12);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test157");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray9 = new java.lang.String[]{};
        sortArgsParser8.parse(strArray9);
        java.lang.String str11 = mvApplication5.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray9);
        java.lang.String str12 = grepApplication0.grepFromFiles("-", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication17 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication18 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray20 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        int[][] intArray23 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList24 = new java.util.ArrayList<int[]>();
        boolean boolean25 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList24, intArray23);
        java.lang.String str26 = cutApplication18.cutByByte((java.util.List<java.lang.String>) strList21, (java.util.List<int[]>) intArrayList24);
        int[][] intArray27 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList28 = new java.util.ArrayList<int[]>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList28, intArray27);
        java.lang.String str30 = cutApplication17.cutByByte((java.util.List<java.lang.String>) strList21, (java.util.List<int[]>) intArrayList28);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication33 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication36 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray38 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList39 = new java.util.ArrayList<java.lang.String>();
        boolean boolean40 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList39, strArray38);
        int[][] intArray41 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList42 = new java.util.ArrayList<int[]>();
        boolean boolean43 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList42, intArray41);
        java.lang.String str44 = cutApplication36.cutByByte((java.util.List<java.lang.String>) strList39, (java.util.List<int[]>) intArrayList42);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication45 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser48 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray49 = new java.lang.String[]{};
        sortArgsParser48.parse(strArray49);
        java.lang.String str51 = mvApplication45.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray49);
        java.lang.String str52 = cutApplication33.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList42, strArray49);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication53 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication55 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream57 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser58 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser59 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray60 = new java.lang.String[]{};
        sortArgsParser59.parse(strArray60);
        catArgsParser58.parse(strArray60);
        java.lang.String str63 = catApplication55.catFileAndStdin((java.lang.Boolean) true, inputStream57, strArray60);
        java.lang.String str64 = catApplication53.catFiles((java.lang.Boolean) false, strArray60);
        java.lang.String str65 = cutApplication17.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList42, strArray60);
        java.lang.String str66 = grepApplication0.grepFromFiles("hi!", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray60);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNotNull(intArray23);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "" + "'", str26, "");
        org.junit.Assert.assertNotNull(intArray27);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "" + "'", str30, "");
        org.junit.Assert.assertNotNull(strArray38);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + true + "'", boolean40 == true);
        org.junit.Assert.assertNotNull(intArray41);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "" + "'", str44, "");
        org.junit.Assert.assertNotNull(strArray49);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "" + "'", str51, "");
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "" + "'", str52, "");
        org.junit.Assert.assertNotNull(strArray60);
        org.junit.Assert.assertEquals("'" + str63 + "' != '" + "" + "'", str63, "");
        org.junit.Assert.assertEquals("'" + str64 + "' != '" + "" + "'", str64, "");
        org.junit.Assert.assertEquals("'" + str65 + "' != '" + "" + "'", str65, "");
        org.junit.Assert.assertEquals("'" + str66 + "' != '" + "\n" + "'", str66, "\n");
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test158");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        int int1 = uniqArgsParser0.getFileCount();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.String str3 = uniqArgsParser0.getOutputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test159");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication3 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray8 = pasteArgsParser7.getFiles();
        java.lang.String str9 = sortApplication3.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray8);
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "-\n", strArray8);
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication13 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray18 = pasteArgsParser17.getFiles();
        java.lang.String str19 = sortApplication13.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray18);
        java.lang.String str20 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "Invalid pattern syntax\nPattern should not be empty.", strArray18);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\n" + "'", str9, "\n");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "\n" + "'", str19, "\n");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test160");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication1 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String str5 = pasteApplication1.mergeFile((java.lang.Boolean) false, strArray4);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication7 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray9 = new java.lang.String[]{};
        java.lang.String str10 = pasteApplication7.mergeFile((java.lang.Boolean) false, strArray9);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray13 = new java.lang.String[]{};
        sortArgsParser12.parse(strArray13);
        java.lang.String str15 = pasteApplication7.mergeFile((java.lang.Boolean) false, strArray13);
        java.lang.String str16 = pasteApplication1.mergeFile((java.lang.Boolean) true, strArray13);
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray13);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test161");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray1 = grepArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = grepArgsParser0.isInvert();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(strArray1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test162");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String str5 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray4);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray9 = pasteArgsParser8.getFiles();
        java.lang.String str10 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "hi!", strArray9);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication13 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser15 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray16 = pasteArgsParser15.getFiles();
        java.lang.String str17 = pasteApplication13.mergeFile((java.lang.Boolean) false, strArray16);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication19 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray21 = new java.lang.String[]{};
        java.lang.String str22 = pasteApplication19.mergeFile((java.lang.Boolean) false, strArray21);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser24 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray25 = new java.lang.String[]{};
        sortArgsParser24.parse(strArray25);
        java.lang.String str27 = pasteApplication19.mergeFile((java.lang.Boolean) false, strArray25);
        java.lang.String str28 = pasteApplication13.mergeFile((java.lang.Boolean) true, strArray25);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str29 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "\n", strArray25);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: ?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test163");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication3 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication5 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray10 = new java.lang.String[]{};
        sortArgsParser9.parse(strArray10);
        catArgsParser8.parse(strArray10);
        sortArgsParser7.parse(strArray10);
        java.lang.String str14 = pasteApplication5.mergeFile((java.lang.Boolean) true, strArray10);
        java.lang.String str15 = pasteApplication3.mergeFile((java.lang.Boolean) true, strArray10);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray10);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test164");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str1 = mvArgsParser0.getDestination();
        java.util.List<java.lang.String> strList2 = mvArgsParser0.getSourceFiles();
        java.lang.Boolean boolean3 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean4 = mvArgsParser0.isNoOverwrite();
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test165");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList2 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        boolean boolean4 = sortArgsParser0.isCaseIndependent();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test166");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean4 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test167");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[]{};
        sortArgsParser3.parse(strArray4);
        java.lang.String str6 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray4);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, ": Is a directory\n", "");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: : Is a directory?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test168");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str4 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test169");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        int int2 = uniqArgsParser0.getFileCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test170");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray6 = new java.lang.String[]{};
        sortArgsParser5.parse(strArray6);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = grepApplication0.grepFromFiles("", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Pattern should not be empty.");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test171");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        int int1 = uniqArgsParser0.getFileCount();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.String str3 = uniqArgsParser0.getOutputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test172");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String[] strArray5 = pasteArgsParser3.getFiles();
        java.lang.String[] strArray6 = pasteArgsParser3.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) false, (java.lang.Boolean) true, strArray6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test173");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray1 = new java.lang.String[]{};
        sortArgsParser0.parse(strArray1);
        boolean boolean3 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList4 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean5 = sortArgsParser0.isReverseOrder();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test174");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.util.List<java.lang.String> strList1 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean2 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean3 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean4 = lsArgsParser0.isRecursive();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test175");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication2 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication4 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream6 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray9 = new java.lang.String[]{};
        sortArgsParser8.parse(strArray9);
        catArgsParser7.parse(strArray9);
        java.lang.String str12 = catApplication4.catFileAndStdin((java.lang.Boolean) true, inputStream6, strArray9);
        java.lang.String str13 = catApplication2.catFiles((java.lang.Boolean) false, strArray9);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser18 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray19 = pasteArgsParser18.getFiles();
        java.lang.String str20 = mvApplication15.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray19);
        java.lang.String str21 = catApplication2.catFiles((java.lang.Boolean) true, strArray19);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication23 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser25 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray26 = pasteArgsParser25.getFiles();
        java.lang.String str27 = pasteApplication23.mergeFile((java.lang.Boolean) false, strArray26);
        java.lang.String str28 = catApplication2.catFiles((java.lang.Boolean) true, strArray26);
        java.lang.String str29 = catApplication0.catFiles((java.lang.Boolean) true, strArray26);
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser31 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray33 = new java.lang.String[]{"-"};
        grepArgsParser31.parse(strArray33);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str35 = catApplication0.catFiles((java.lang.Boolean) false, strArray33);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertNotNull(strArray26);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "" + "'", str27, "");
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "" + "'", str29, "");
        org.junit.Assert.assertNotNull(strArray33);
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test176");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication2 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream4 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray7 = new java.lang.String[]{};
        sortArgsParser6.parse(strArray7);
        catArgsParser5.parse(strArray7);
        java.lang.String str10 = catApplication2.catFileAndStdin((java.lang.Boolean) true, inputStream4, strArray7);
        java.lang.String str11 = catApplication0.catFiles((java.lang.Boolean) false, strArray7);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray17 = pasteArgsParser16.getFiles();
        java.lang.String str18 = mvApplication13.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray17);
        java.lang.String str19 = catApplication0.catFiles((java.lang.Boolean) true, strArray17);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser21 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray22 = pasteArgsParser21.getFiles();
        java.lang.String[] strArray23 = pasteArgsParser21.getFiles();
        java.lang.String[] strArray24 = pasteArgsParser21.getFiles();
        java.lang.String str25 = catApplication0.catFiles((java.lang.Boolean) true, strArray24);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertNotNull(strArray24);
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "" + "'", str25, "");
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test178");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, "Is a directory", "Null Pointer Exception");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Is a directory: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test179");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray1 = pasteArgsParser0.getFiles();
        java.lang.String[] strArray2 = pasteArgsParser0.getFiles();
        java.lang.String[] strArray3 = pasteArgsParser0.getFiles();
        java.lang.String[] strArray4 = pasteArgsParser0.getFiles();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test180");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean2 = mvArgsParser0.isNoOverwrite();
        java.lang.String str3 = mvArgsParser0.getDestination();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test181");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test182");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        int int5 = uniqArgsParser0.getFileCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test183");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList2 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList6 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test184");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.String str2 = mvArgsParser0.getDestination();
        java.lang.Boolean boolean3 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean4 = mvArgsParser0.isNoOverwrite();
        java.util.List<java.lang.String> strList5 = mvArgsParser0.getSourceFiles();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test185");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        boolean boolean6 = pasteArgsParser4.isSerial();
        java.lang.String[] strArray7 = pasteArgsParser4.getFiles();
        java.lang.String str8 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray7);
        java.lang.String[] strArray12 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str13 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray12);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\n" + "'", str8, "\n");
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test186");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.util.List<java.lang.String> strList1 = null;
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication2 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray5 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList6 = new java.util.ArrayList<int[]>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList6, intArray5);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication8 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser10 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray11 = new java.lang.String[]{};
        sortArgsParser10.parse(strArray11);
        java.lang.String str13 = pasteApplication8.mergeFile((java.lang.Boolean) false, strArray11);
        java.lang.String str14 = cutApplication2.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList6, strArray11);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = cutApplication0.cutByByte(strList1, (java.util.List<int[]>) intArrayList6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.List.iterator()\" because \"<parameter1>\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test187");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray2 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList3 = new java.util.ArrayList<java.lang.String>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList3, strArray2);
        int[][] intArray5 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList6 = new java.util.ArrayList<int[]>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList6, intArray5);
        java.lang.String str8 = cutApplication0.cutByByte((java.util.List<java.lang.String>) strList3, (java.util.List<int[]>) intArrayList6);
        int[][] intArray11 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList12 = new java.util.ArrayList<int[]>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList12, intArray11);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser14 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray15 = pasteArgsParser14.getFiles();
        java.lang.String str16 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList12, strArray15);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication19 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser20 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean21 = mvArgsParser20.isNoOverwrite();
        java.util.List<java.lang.String> strList22 = mvArgsParser20.getSourceFiles();
        java.util.List<java.lang.String> strList23 = mvArgsParser20.getSourceFiles();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication24 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray26 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList27 = new java.util.ArrayList<java.lang.String>();
        boolean boolean28 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList27, strArray26);
        int[][] intArray29 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList30 = new java.util.ArrayList<int[]>();
        boolean boolean31 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList30, intArray29);
        java.lang.String str32 = cutApplication24.cutByByte((java.util.List<java.lang.String>) strList27, (java.util.List<int[]>) intArrayList30);
        java.lang.String str33 = cutApplication19.cutByByte(strList23, (java.util.List<int[]>) intArrayList30);
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication34 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray40 = new java.lang.String[]{""};
        java.lang.String str41 = grepApplication34.grepFromFiles("Is a directory", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray40);
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser46 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser47 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray48 = new java.lang.String[]{};
        sortArgsParser47.parse(strArray48);
        catArgsParser46.parse(strArray48);
        java.lang.String str51 = grepApplication34.grepFromFiles("Invalid pattern syntax\nPattern should not be empty.", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray48);
        java.lang.String str52 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList30, strArray48);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertNotNull(intArray11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(strArray15);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
        org.junit.Assert.assertEquals("'" + boolean21 + "' != '" + false + "'", boolean21, false);
        org.junit.Assert.assertNotNull(strList22);
        org.junit.Assert.assertNotNull(strList23);
        org.junit.Assert.assertNotNull(strArray26);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
        org.junit.Assert.assertNotNull(intArray29);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "" + "'", str32, "");
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "" + "'", str33, "");
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + ": Is a directory\n" + "'", str41, ": Is a directory\n");
        org.junit.Assert.assertNotNull(strArray48);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "\n" + "'", str51, "\n");
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "" + "'", str52, "");
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test188");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray5 = new java.lang.String[]{};
        sortArgsParser4.parse(strArray5);
        catArgsParser3.parse(strArray5);
        sortArgsParser2.parse(strArray5);
        java.lang.String str9 = pasteApplication0.mergeFile((java.lang.Boolean) true, strArray5);
        java.lang.String[] strArray11 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = pasteApplication0.mergeFile((java.lang.Boolean) true, strArray11);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test189");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication1 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray3 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList4 = new java.util.ArrayList<java.lang.String>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList4, strArray3);
        int[][] intArray6 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList7 = new java.util.ArrayList<int[]>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList7, intArray6);
        java.lang.String str9 = cutApplication1.cutByByte((java.util.List<java.lang.String>) strList4, (java.util.List<int[]>) intArrayList7);
        int[][] intArray10 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList11 = new java.util.ArrayList<int[]>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList11, intArray10);
        java.lang.String str13 = cutApplication0.cutByByte((java.util.List<java.lang.String>) strList4, (java.util.List<int[]>) intArrayList11);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication16 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication17 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray19 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        int[][] intArray22 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList23 = new java.util.ArrayList<int[]>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList23, intArray22);
        java.lang.String str25 = cutApplication17.cutByByte((java.util.List<java.lang.String>) strList20, (java.util.List<int[]>) intArrayList23);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication26 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray28 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList29 = new java.util.ArrayList<java.lang.String>();
        boolean boolean30 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList29, strArray28);
        int[][] intArray31 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList32 = new java.util.ArrayList<int[]>();
        boolean boolean33 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList32, intArray31);
        java.lang.String str34 = cutApplication26.cutByByte((java.util.List<java.lang.String>) strList29, (java.util.List<int[]>) intArrayList32);
        java.lang.String str35 = cutApplication16.cutByByte((java.util.List<java.lang.String>) strList20, (java.util.List<int[]>) intArrayList32);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication36 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser39 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray40 = pasteArgsParser39.getFiles();
        java.lang.String[] strArray41 = pasteArgsParser39.getFiles();
        java.lang.String str42 = lsApplication36.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray41);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser45 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray46 = new java.lang.String[]{};
        sortArgsParser45.parse(strArray46);
        java.lang.String str48 = lsApplication36.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray46);
        java.lang.String str49 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList32, strArray46);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication52 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray54 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList55 = new java.util.ArrayList<java.lang.String>();
        boolean boolean56 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList55, strArray54);
        int[][] intArray57 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList58 = new java.util.ArrayList<int[]>();
        boolean boolean59 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList58, intArray57);
        java.lang.String str60 = cutApplication52.cutByByte((java.util.List<java.lang.String>) strList55, (java.util.List<int[]>) intArrayList58);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication61 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication63 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser66 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray67 = pasteArgsParser66.getFiles();
        java.lang.String[] strArray68 = pasteArgsParser66.getFiles();
        java.lang.String str69 = lsApplication63.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray68);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser72 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray73 = pasteArgsParser72.getFiles();
        java.lang.String[] strArray74 = pasteArgsParser72.getFiles();
        java.lang.String str75 = lsApplication63.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray74);
        java.lang.String str76 = catApplication61.catFiles((java.lang.Boolean) false, strArray74);
        java.lang.String str77 = cutApplication0.cutFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList58, strArray74);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(intArray10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        org.junit.Assert.assertNotNull(intArray22);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "" + "'", str25, "");
        org.junit.Assert.assertNotNull(strArray28);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + true + "'", boolean30 == true);
        org.junit.Assert.assertNotNull(intArray31);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "" + "'", str34, "");
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "" + "'", str35, "");
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertNotNull(strArray41);
// flaky:         org.junit.Assert.assertEquals("'" + str42 + "' != '" + "" + "'", str42, "");
        org.junit.Assert.assertNotNull(strArray46);
// flaky:         org.junit.Assert.assertEquals("'" + str48 + "' != '" + "" + "'", str48, "");
        org.junit.Assert.assertEquals("'" + str49 + "' != '" + "" + "'", str49, "");
        org.junit.Assert.assertNotNull(strArray54);
        org.junit.Assert.assertTrue("'" + boolean56 + "' != '" + true + "'", boolean56 == true);
        org.junit.Assert.assertNotNull(intArray57);
        org.junit.Assert.assertTrue("'" + boolean59 + "' != '" + false + "'", boolean59 == false);
        org.junit.Assert.assertEquals("'" + str60 + "' != '" + "" + "'", str60, "");
        org.junit.Assert.assertNotNull(strArray67);
        org.junit.Assert.assertNotNull(strArray68);
// flaky:         org.junit.Assert.assertEquals("'" + str69 + "' != '" + "" + "'", str69, "");
        org.junit.Assert.assertNotNull(strArray73);
        org.junit.Assert.assertNotNull(strArray74);
// flaky:         org.junit.Assert.assertEquals("'" + str75 + "' != '" + "" + "'", str75, "");
        org.junit.Assert.assertEquals("'" + str76 + "' != '" + "" + "'", str76, "");
        org.junit.Assert.assertEquals("'" + str77 + "' != '" + "" + "'", str77, "");
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test190");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean3 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean4 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test191");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean5 = wcArgsParser0.filesContainDash();
        java.util.List<java.lang.String> strList6 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean7 = wcArgsParser0.isByteCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(strList6);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + true + "'", boolean7, true);
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test192");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str1 = mvArgsParser0.getDestination();
        java.util.List<java.lang.String> strList2 = mvArgsParser0.getSourceFiles();
        java.lang.Boolean boolean3 = mvArgsParser0.isNoOverwrite();
        java.util.List<java.lang.String> strList4 = mvArgsParser0.getSourceFiles();
        java.lang.Boolean boolean5 = mvArgsParser0.isNoOverwrite();
        java.util.List<java.lang.String> strList6 = mvArgsParser0.getSourceFiles();
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test193");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication5 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray8 = pasteArgsParser7.getFiles();
        java.lang.String[] strArray9 = pasteArgsParser7.getFiles();
        java.lang.String[] strArray10 = pasteArgsParser7.getFiles();
        java.lang.String str11 = catApplication5.catFiles((java.lang.Boolean) true, strArray10);
        java.lang.String str12 = grepApplication0.grepFromFiles("Invalid pattern syntax\nPattern should not be empty.", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray10);
        java.lang.Class<?> wildcardClass13 = grepApplication0.getClass();
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\n" + "'", str12, "\n");
        org.junit.Assert.assertNotNull(wildcardClass13);
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test194");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test195");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, "-", "hi!: No such file or directory\nInvalid pattern syntax: Is a directory\n-: Is a directory\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/-: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test196");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, "Pattern should not be empty.", "hi!");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Pattern should not be empty.: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test197");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        boolean boolean2 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList3 = catArgsParser0.getFileList();
        boolean boolean4 = catArgsParser0.isReadFromStdin();
        boolean boolean5 = catArgsParser0.isReadFromStdin();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test198");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        java.lang.Class<?> wildcardClass4 = wcArgsParser0.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test199");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.util.List<java.lang.String> strList2 = lsArgsParser1.getDirectories();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication4 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray6 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        int[][] intArray9 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList10 = new java.util.ArrayList<int[]>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList10, intArray9);
        java.lang.String str12 = cutApplication4.cutByByte((java.util.List<java.lang.String>) strList7, (java.util.List<int[]>) intArrayList10);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication13 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray15 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList16 = new java.util.ArrayList<java.lang.String>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList16, strArray15);
        int[][] intArray18 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList19 = new java.util.ArrayList<int[]>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList19, intArray18);
        java.lang.String str21 = cutApplication13.cutByByte((java.util.List<java.lang.String>) strList16, (java.util.List<int[]>) intArrayList19);
        java.lang.String str22 = cutApplication3.cutByByte((java.util.List<java.lang.String>) strList7, (java.util.List<int[]>) intArrayList19);
        java.lang.String str23 = cutApplication0.cutByByte(strList2, (java.util.List<int[]>) intArrayList19);
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser24 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.lang.Boolean boolean25 = mkdirArgsParser24.isParent();
        java.util.List<java.lang.String> strList26 = mkdirArgsParser24.getFileNames();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication27 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication30 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray32 = new java.lang.String[]{"hi!"};
        java.util.ArrayList<java.lang.String> strList33 = new java.util.ArrayList<java.lang.String>();
        boolean boolean34 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList33, strArray32);
        int[][] intArray35 = new int[][]{};
        java.util.ArrayList<int[]> intArrayList36 = new java.util.ArrayList<int[]>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList36, intArray35);
        java.lang.String str38 = cutApplication30.cutByByte((java.util.List<java.lang.String>) strList33, (java.util.List<int[]>) intArrayList36);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication39 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser42 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray43 = new java.lang.String[]{};
        sortArgsParser42.parse(strArray43);
        java.lang.String str45 = mvApplication39.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray43);
        java.lang.String str46 = cutApplication27.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList36, strArray43);
        java.lang.String str47 = cutApplication0.cutByByte(strList26, (java.util.List<int[]>) intArrayList36);
        org.junit.Assert.assertNotNull(strList2);
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
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
        org.junit.Assert.assertEquals("'" + boolean25 + "' != '" + false + "'", boolean25, false);
        org.junit.Assert.assertNotNull(strList26);
        org.junit.Assert.assertNotNull(strArray32);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertNotNull(intArray35);
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "" + "'", str38, "");
        org.junit.Assert.assertNotNull(strArray43);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "" + "'", str46, "");
        org.junit.Assert.assertEquals("'" + str47 + "' != '" + "" + "'", str47, "");
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test200");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.String str1 = uniqArgsParser0.getOutputFile();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertNull(str2);
    }
}
