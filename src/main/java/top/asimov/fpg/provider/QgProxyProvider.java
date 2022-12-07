package top.asimov.fpg.provider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import top.asimov.fpg.model.proxy.QgProxy;
import top.asimov.fpg.model.proxy.QgProxy.Proxy;
import top.asimov.fpg.util.IpUtils;
import top.asimov.fpg.util.IspUtils;
import top.asimov.fpg.util.RegionUtils;

@Slf4j
public class QgProxyProvider {

  private final static Random random = new Random();

  private static QgProxy proxy;

  public static QgProxy generateProxy(Integer num) {
    List<QgProxy.Proxy> proxyList = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      String ip = IpUtils.getRandomIp();
      String port = String.valueOf(random.nextInt(65535));
      proxyList.add(QgProxy.Proxy.builder()
          .ip(ip)
          .port(port)
          .host(ip.concat(":").concat(port))
          .region(RegionUtils.getRandomCityFullName().concat(IspUtils.randomIsp()))
          .deadline(LocalDateTime.now().plusSeconds(random.nextInt(70)))
          .build());
    }
    return QgProxy.builder()
        .code(0)
        .taskId(UUID.randomUUID().toString())
        .num(num)
        .ProxyList(proxyList)
        .build();
  }

  public static void autoGenerateProxy(Integer num, Integer fixedRate) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        proxy = generateProxy(num);
        String proxies = proxy.getProxyList().stream()
            .map(Proxy::getHost)
            .collect(Collectors.joining(","));
        log.info("new qg proxies [{}]", proxies);
      }
    }, 0, fixedRate);
  }

  public static QgProxy getProxy() {
    return proxy;
  }

}
