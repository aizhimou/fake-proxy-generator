package top.asimov.fpg.model;

import java.util.List;
import lombok.Data;

/**
 * 地级市
 */

@Data
public class City {

  private String code;

  private String name;

  private List<Area> areaList;
}

