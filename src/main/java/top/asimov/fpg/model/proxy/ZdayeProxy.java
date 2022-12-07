package top.asimov.fpg.model.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 站大爷代理IP数据模型
 *
 * @see <a href="https://www.zdaye.com/">站大爷</a>
 */
@Data
@Builder
public class ZdayeProxy {

  private String code;

  private String msg;

  private Data data;

  @lombok.Data
  @Builder
  public static class Data implements Serializable {

    private Integer count;

    @JsonProperty(value = "proxy_list")
    private List<Proxy> proxyList;
  }

  @lombok.Data
  @Builder
  public static class Proxy {

    private String ip;

    private Integer port;

    private String adr;

    private Integer timeout;

    private Integer cometime;
  }

}
