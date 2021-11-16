//package cn.gzsendi.huangyj.util;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//
//import java.io.File;
//import java.io.InputStream;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//
///**
// * @author duanw
// * @description：Excel工具类
// */
//@Slf4j
//public class ExcelUtil {
//
//    /**
//     * 特殊不可见字符，不可见
//     *
//     */
//    private static List<String> specialCode = new ArrayList<String>(){{
//        add("\u200E");
//    }};
//
//    public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
//        if (obj == null) {
//            return true;
//        }
//        boolean flag = true;
//        for (Field f : obj.getClass().getDeclaredFields()) {
//            f.setAccessible(true);
//            Object o = f.get(obj);
//            if (o != null) {
//                flag = false;
//            }
//            if (o instanceof String) {
//                for (String code : specialCode) {
//                    o = o.toString().replaceAll(code,"");
//                }
//                o = StringUtils.isBlank((String) o) ? "" : ((String) o).trim();
//                f.set(obj, o);
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 解析Excel
//     *
//     * @param inputStream  文件流
//     * @param sheetIndex  sheet
//     * @param titleRows  标题行数
//     * @param headerRows 头行数
//     * @param startRows  开始行数
//     * @param pojoClass  实体类
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> analysisExcel(InputStream inputStream, int sheetIndex, Integer titleRows,
//                                            Integer headerRows, Integer startRows, Class<T> pojoClass) {
//        if (inputStream == null) {
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        // 第几个sheet页
//        params.setStartSheetIndex(sheetIndex);
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        params.setStartRows(startRows);
//        List<T> result = new ArrayList<>();
//        try {
//            List<T> list = ExcelImportUtil.importExcel(inputStream, pojoClass, params);
//            for (T t : list) {
//                if (!checkObjFieldIsNull(t)) {
//                    result.add(t);
//                }
//            }
//            if (ListUtil.isEmpty(list)){
//                log.error("导入数据不能为空");
//            }
//        } catch (NoSuchElementException e) {
//            log.error("excel文件不能为空{}", e.getMessage());
//        } catch (Exception e) {
//            log.error("导入异常", e);
//        }
//        return result;
//    }
//
//
//    /**
//     * 解析Excel
//     *
//     * @param sheetIndex  sheet
//     * @param titleRows  标题行数
//     * @param headerRows 头行数
//     * @param startRows  开始行数
//     * @param pojoClass  实体类
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> analysisExcel(File file, int sheetIndex, Integer titleRows,
//                                            Integer headerRows, Integer startRows, Class<T> pojoClass) {
//        if (file == null) {
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        // 第几个sheet页
//        params.setHeadRows(1);
//        List<T> result = new ArrayList<>();
//        try {
//            List<T> list = ExcelImportUtil.importExcel(file, pojoClass, params);
//            for (T t : list) {
//                if (!checkObjFieldIsNull(t)) {
//                    result.add(t);
//                }
//            }
//            if (ListUtil.isEmpty(list)){
//                log.error("导入数据不能为空");
//            }
//        } catch (NoSuchElementException e) {
//            log.error("excel文件不能为空{}", e.getMessage());
//        } catch (Exception e) {
//            log.error("导入异常", e);
//        }
//        return result;
//    }
//}
