package top.asimov;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.asimov.fpg.util.RegionUtils;

@Slf4j
public class RegionUtilTest {

  @Test
  public void test() {
    log.info(RegionUtils.getRandomCityFullName());
  }

}
