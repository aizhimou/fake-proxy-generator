package top.asimov.fpg.util;

/**
 * ISP 工具
 */
public class IspUtils {

  private final static String[] ISP_LIST = {"电信", "联通", "移动", "网通", "铁通", "长城"};

  /**
   * 获取一个随机 ISP
   */
  public static String randomIsp() {
    return ISP_LIST[(int) (Math.random() * ISP_LIST.length)];
  }

}
