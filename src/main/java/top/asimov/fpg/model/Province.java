package top.asimov.fpg.model;

import java.util.List;
import lombok.Data;

/**
 * 省份
 */

@Data
public class Province {

  private String code;

  private String name;

  private List<City> cityList;
}
