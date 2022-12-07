package top.asimov.fpg.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import top.asimov.fpg.model.City;
import top.asimov.fpg.model.Province;

public class RegionUtils {

  private static final List<Province> provinceList;

  static {
    try(InputStream resource = RegionUtils.class.getClassLoader().getResourceAsStream("China-Province-City.json")) {
      ObjectMapper objectMapper = new ObjectMapper();
      provinceList = objectMapper.readValue(resource, new TypeReference<List<Province>>() {});
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static Province getRandomProvince() {
    int size = provinceList.size();
    Province province = provinceList.get(randomIndexInArray(size));
    if (province.getCityList().size() == 0) {
      return getRandomProvince();
    }
    return province;
  }

  public static List<Object> getRandomCityWithProvince() {
    List<Object> list = new ArrayList<>();
    Province province = getRandomProvince();
    list.add(province);
    List<City> cityList = province.getCityList();
    City city = cityList.get(randomIndexInArray(cityList.size()));
    list.add(city);
    return list;
  }

  public static String getRandomCityFullName() {
    List<Object> cityWithProvince = getRandomCityWithProvince();
    Province province = (Province) cityWithProvince.get(0);
    City city = (City) cityWithProvince.get(1);
    return province.getName().concat(city.getName());
  }

  private static Integer randomIndexInArray(Integer arraySize) {
    return (int) (Math.random() * arraySize);
  }
}
