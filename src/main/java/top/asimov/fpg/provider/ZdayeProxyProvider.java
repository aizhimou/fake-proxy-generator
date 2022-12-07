package top.asimov.fpg.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import top.asimov.fpg.model.proxy.ZdayeProxy;
import top.asimov.fpg.util.IpUtils;
import top.asimov.fpg.util.IspUtils;
import top.asimov.fpg.util.RegionUtils;

@Slf4j
public class ZdayeProxyProvider {

  private final static Random random = new Random();

  private static ZdayeProxy proxy;

  public static ZdayeProxy generateProxy(Integer num) {
    List<ZdayeProxy.Proxy> proxyList = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      proxyList.add(ZdayeProxy.Proxy.builder()
          .ip(IpUtils.getRandomIp())
          .port(random.nextInt(65535))
          .adr(RegionUtils.getRandomCityFullName().concat(" ").concat(IspUtils.randomIsp()))
          .cometime(random.nextInt(300))
          .timeout(random.nextInt(180))
          .build());
    }
    return ZdayeProxy.builder()
        .code("10001")
        .msg("获取成功")
        .data(ZdayeProxy.Data.builder()
            .count(num)
            .proxyList(proxyList)
            .build())
        .build();
  }

  public static void autoGenerateProxy(Integer num, Integer fixedRate) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        proxy = generateProxy(num);
        String proxies = proxy.getData().getProxyList().stream()
            .map(proxy -> String.format("%s:%d", proxy.getIp(), proxy.getPort()))
            .collect(Collectors.joining(","));
        log.info("new zdaye proxies [{}]", proxies);
      }
    }, 0, fixedRate);
  }

  public static ZdayeProxy getProxy() {
    return proxy;
  }
}
