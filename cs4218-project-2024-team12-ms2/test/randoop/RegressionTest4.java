package randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest4 {

    public static boolean debug = false;

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test201");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] { "-" };
        grepArgsParser2.parse(strArray4);
        catArgsParser0.parse(strArray4);
        boolean boolean7 = catArgsParser0.isLineNumber();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test202");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("-\nInvalid pattern syntax\nPattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: -?Invalid pattern syntax?Pattern should not be empty.: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test203");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication1 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication6 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray9 = pasteArgsParser8.getFiles();
        java.lang.String[] strArray10 = pasteArgsParser8.getFiles();
        java.lang.String[] strArray11 = pasteArgsParser8.getFiles();
        java.lang.String str12 = catApplication6.catFiles((java.lang.Boolean) true, strArray11);
        java.lang.String str13 = grepApplication1.grepFromFiles("Invalid pattern syntax\nPattern should not be empty.", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray11);
        wcArgsParser0.parse(strArray11);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\n" + "'", str13, "\n");
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test204");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test205");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str5 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test206");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        java.lang.String str6 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean7 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.String str8 = uniqArgsParser0.getOutputFile();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test207");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean4 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test208");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean4 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isWordCount();
        java.util.List<java.lang.String> strList6 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test209");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, "-\n", "Invalid pattern syntax");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/-?: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test210");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication3 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication7 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray12 = pasteArgsParser11.getFiles();
        java.lang.String str13 = wcApplication7.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray12);
        java.lang.String str14 = wcApplication3.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray12);
        java.lang.String str15 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray12);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
// flaky:         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test211");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, "", "Invalid pattern syntax");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: : This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test212");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.util.List<java.lang.String> strList1 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean2 = lsArgsParser0.isSortByExt();
        java.util.List<java.lang.String> strList3 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean4 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean5 = lsArgsParser0.isSortByExt();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test213");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList6 = wcArgsParser0.getFileNames();
        java.lang.Class<?> wildcardClass7 = strList6.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
        org.junit.Assert.assertNotNull(strList6);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test214");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str2 = uniqArgsParser0.getOutputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean4 = uniqArgsParser0.isAllDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test215");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray4 = pasteArgsParser3.getFiles();
        java.lang.String str5 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "hi!", strArray4);
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication8 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray13 = pasteArgsParser12.getFiles();
        java.lang.String[] strArray14 = pasteArgsParser12.getFiles();
        java.lang.String str15 = sortApplication8.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray14);
        java.lang.String str16 = mvApplication0.mvFilesToFolder((java.lang.Boolean) false, "Invalid pattern syntax\nPattern should not be empty.", strArray14);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\n" + "'", str15, "\n");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test216");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, "Invalid pattern syntax", "Pattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: /Users/james/James/CS4218-Repo/Invalid pattern syntax: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test217");
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
        java.lang.Class<?> wildcardClass23 = mvApplication0.getClass();
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
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test218");
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        boolean boolean1 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList2 = catArgsParser0.getFileList();
        boolean boolean3 = catArgsParser0.isReadFromStdin();
        java.util.List<java.lang.String> strList4 = catArgsParser0.getFileList();
        boolean boolean5 = catArgsParser0.isLineNumber();
        java.util.List<java.lang.String> strList6 = catArgsParser0.getFileList();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test219");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, "", ": Is a directory\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: : This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test220");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.String str3 = uniqArgsParser0.getInputFile();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean6 = uniqArgsParser0.isOnlyDuplicates();
        int int7 = uniqArgsParser0.getFileCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test221");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean2 = mvArgsParser0.isNoOverwrite();
        java.lang.Boolean boolean3 = mvArgsParser0.isNoOverwrite();
        java.util.List<java.lang.String> strList4 = mvArgsParser0.getSourceFiles();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strList4);
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test222");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray1 = null;
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray1);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MkdirException; message: mkdir: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.MkdirException e) {
            // Expected exception.
        }
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test223");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.String str4 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean5 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean6 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.Boolean boolean7 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test224");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isOnlyDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test225");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication3 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray7 = pasteArgsParser6.getFiles();
        java.lang.String[] strArray8 = pasteArgsParser6.getFiles();
        java.lang.String str9 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray13 = new java.lang.String[] {};
        sortArgsParser12.parse(strArray13);
        java.lang.String str15 = lsApplication3.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray13);
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.remove((java.lang.Boolean) true, (java.lang.Boolean) false, strArray13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertNotNull(strArray8);
// flaky:         org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(strArray13);
// flaky:         org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test226");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean5 = wcArgsParser0.isLineCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test227");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        boolean boolean1 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray5 = pasteArgsParser4.getFiles();
        java.lang.String[] strArray6 = pasteArgsParser4.getFiles();
        sortArgsParser0.parse(strArray6);
        java.lang.Boolean boolean8 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test228");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, "", "-");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: : This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test229");
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
        java.util.List<java.lang.String> strList15 = lsArgsParser14.getDirectories();
        java.lang.Boolean boolean16 = lsArgsParser14.isSortByExt();
        java.util.List<java.lang.String> strList17 = lsArgsParser14.getDirectories();
        java.util.List<java.lang.String> strList18 = lsArgsParser14.getDirectories();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication19 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication20 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray22 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList23 = new java.util.ArrayList<java.lang.String>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList23, strArray22);
        int[][] intArray25 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList26 = new java.util.ArrayList<int[]>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList26, intArray25);
        java.lang.String str28 = cutApplication20.cutByByte((java.util.List<java.lang.String>) strList23, (java.util.List<int[]>) intArrayList26);
        int[][] intArray29 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList30 = new java.util.ArrayList<int[]>();
        boolean boolean31 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList30, intArray29);
        java.lang.String str32 = cutApplication19.cutByByte((java.util.List<java.lang.String>) strList23, (java.util.List<int[]>) intArrayList30);
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication35 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication38 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        java.lang.String[] strArray40 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList41 = new java.util.ArrayList<java.lang.String>();
        boolean boolean42 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList41, strArray40);
        int[][] intArray43 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList44 = new java.util.ArrayList<int[]>();
        boolean boolean45 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList44, intArray43);
        java.lang.String str46 = cutApplication38.cutByByte((java.util.List<java.lang.String>) strList41, (java.util.List<int[]>) intArrayList44);
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication47 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser50 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray51 = new java.lang.String[] {};
        sortArgsParser50.parse(strArray51);
        java.lang.String str53 = mvApplication47.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray51);
        java.lang.String str54 = cutApplication35.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList44, strArray51);
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication55 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication57 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream59 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser60 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser61 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray62 = new java.lang.String[] {};
        sortArgsParser61.parse(strArray62);
        catArgsParser60.parse(strArray62);
        java.lang.String str65 = catApplication57.catFileAndStdin((java.lang.Boolean) true, inputStream59, strArray62);
        java.lang.String str66 = catApplication55.catFiles((java.lang.Boolean) false, strArray62);
        java.lang.String str67 = cutApplication19.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList44, strArray62);
        java.lang.String str68 = cutApplication0.cutByByte(strList18, (java.util.List<int[]>) intArrayList44);
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertNotNull(strList15);
        org.junit.Assert.assertEquals("'" + boolean16 + "' != '" + false + "'", boolean16, false);
        org.junit.Assert.assertNotNull(strList17);
        org.junit.Assert.assertNotNull(strList18);
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + true + "'", boolean24 == true);
        org.junit.Assert.assertNotNull(intArray25);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
        org.junit.Assert.assertNotNull(intArray29);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "" + "'", str32, "");
        org.junit.Assert.assertNotNull(strArray40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + true + "'", boolean42 == true);
        org.junit.Assert.assertNotNull(intArray43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "" + "'", str46, "");
        org.junit.Assert.assertNotNull(strArray51);
        org.junit.Assert.assertEquals("'" + str53 + "' != '" + "" + "'", str53, "");
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "" + "'", str54, "");
        org.junit.Assert.assertNotNull(strArray62);
        org.junit.Assert.assertEquals("'" + str65 + "' != '" + "" + "'", str65, "");
        org.junit.Assert.assertEquals("'" + str66 + "' != '" + "" + "'", str66, "");
        org.junit.Assert.assertEquals("'" + str67 + "' != '" + "" + "'", str67, "");
        org.junit.Assert.assertEquals("'" + str68 + "' != '" + "" + "'", str68, "");
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test230");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray2 = pasteArgsParser1.getFiles();
        java.lang.String[] strArray3 = pasteArgsParser1.getFiles();
        java.lang.String[] strArray4 = pasteArgsParser1.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray4);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test231");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean2 = uniqArgsParser0.isOnlyDuplicates();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test232");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean2 = uniqArgsParser0.isCount();
        java.lang.Boolean boolean3 = uniqArgsParser0.isAllDuplicates();
        java.lang.Boolean boolean4 = uniqArgsParser0.isOnlyDuplicates();
        int int5 = uniqArgsParser0.getFileCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test233");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test234");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication4 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray9 = pasteArgsParser8.getFiles();
        java.lang.String str10 = wcApplication4.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        java.lang.String str11 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray9);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication15 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray18 = pasteArgsParser17.getFiles();
        java.lang.String str19 = pasteApplication15.mergeFile((java.lang.Boolean) false, strArray18);
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser21 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray22 = pasteArgsParser21.getFiles();
        boolean boolean23 = pasteArgsParser21.isSerial();
        java.lang.String[] strArray24 = pasteArgsParser21.getFiles();
        java.lang.String str25 = pasteApplication15.mergeFile((java.lang.Boolean) true, strArray24);
        java.lang.String str26 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray24);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(strArray24);
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "" + "'", str25, "");
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "" + "'", str26, "");
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test235");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray2 = new java.lang.String[] { "-" };
        grepArgsParser0.parse(strArray2);
        java.lang.Boolean boolean4 = grepArgsParser0.isInvert();
        java.lang.Boolean boolean5 = grepArgsParser0.isInvert();
        java.lang.String str6 = grepArgsParser0.getPattern();
        java.lang.String str7 = grepArgsParser0.getPattern();
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test236");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.filesContainDash();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test237");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean4 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean5 = sortArgsParser0.isFirstWordNumber();
        java.lang.Boolean boolean6 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test238");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.filesContainDash();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isWordCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test239");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        boolean boolean2 = pasteArgsParser1.isSerial();
        boolean boolean3 = pasteArgsParser1.isSerial();
        java.lang.String[] strArray4 = pasteArgsParser1.getFiles();
        // The following exception was thrown during execution in test generation
        try {
            mkdirApplication0.createFolder(strArray4);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test240");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray4 = new java.lang.String[] {};
        sortArgsParser3.parse(strArray4);
        java.lang.String str6 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Null Pointer Exception", strArray4);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication9 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.lang.String[] strArray11 = new java.lang.String[] {};
        java.lang.String str12 = pasteApplication9.mergeFile((java.lang.Boolean) false, strArray11);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str13 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "", strArray11);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: : No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test241");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        boolean boolean2 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList3 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test242");
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
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication26 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication28 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser30 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser catArgsParser31 = new sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser32 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray33 = new java.lang.String[] {};
        sortArgsParser32.parse(strArray33);
        catArgsParser31.parse(strArray33);
        sortArgsParser30.parse(strArray33);
        java.lang.String str37 = pasteApplication28.mergeFile((java.lang.Boolean) true, strArray33);
        java.lang.String str38 = pasteApplication26.mergeFile((java.lang.Boolean) true, strArray33);
        java.lang.String str39 = grepApplication0.grepFromFiles("-", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray33);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + ": Is a directory\n" + "'", str7, ": Is a directory\n");
        org.junit.Assert.assertNotNull(strArray17);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
        org.junit.Assert.assertNotNull(strArray33);
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + "" + "'", str37, "");
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "" + "'", str38, "");
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "\n" + "'", str39, "\n");
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test243");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray1 = pasteArgsParser0.getFiles();
        java.lang.String[] strArray2 = pasteArgsParser0.getFiles();
        boolean boolean3 = pasteArgsParser0.isSerial();
        java.lang.String[] strArray4 = pasteArgsParser0.getFiles();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test244");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication4 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream9 = null;
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication10 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray16 = new java.lang.String[] { "" };
        java.lang.String str17 = grepApplication10.grepFromFiles("Is a directory", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray16);
        java.lang.String str18 = grepApplication4.grepFromFileAndStdin("Null Pointer Exception", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream9, strArray16);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str19 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + ": Is a directory\n" + "'", str17, ": Is a directory\n");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + ": Is a directory\n" + "'", str18, ": Is a directory\n");
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test245");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray5 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = grepApplication0.grepFromFiles("-\n", (java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
            // Expected exception.
        }
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test246");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication3 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray6 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList7 = new java.util.ArrayList<int[]>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList7, intArray6);
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication9 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.String[] strArray12 = new java.lang.String[] {};
        sortArgsParser11.parse(strArray12);
        java.lang.String str14 = pasteApplication9.mergeFile((java.lang.Boolean) false, strArray12);
        java.lang.String str15 = cutApplication3.cutFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList7, strArray12);
        java.lang.String str16 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray12);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
// flaky:         org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test247");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.isWordCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test248");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.util.List<java.lang.String> strList1 = lsArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList2 = lsArgsParser0.getDirectories();
        java.lang.Boolean boolean3 = lsArgsParser0.isRecursive();
        java.lang.Boolean boolean4 = lsArgsParser0.isSortByExt();
        java.lang.Boolean boolean5 = lsArgsParser0.isSortByExt();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test249");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        java.lang.Boolean boolean1 = uniqArgsParser0.isCount();
        java.lang.String str2 = uniqArgsParser0.getInputFile();
        java.lang.Boolean boolean3 = uniqArgsParser0.isOnlyDuplicates();
        int int4 = uniqArgsParser0.getFileCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test250");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.String[] strArray3 = pasteArgsParser2.getFiles();
        java.lang.String[] strArray4 = pasteArgsParser2.getFiles();
        java.lang.String[] strArray5 = pasteArgsParser2.getFiles();
        java.lang.String str6 = catApplication0.catFiles((java.lang.Boolean) true, strArray5);
        java.lang.Class<?> wildcardClass7 = catApplication0.getClass();
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNotNull(wildcardClass7);
    }
}
