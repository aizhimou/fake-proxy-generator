package top.asimov.fpg.model.proxy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 青果网络代理IP数据模型
 *
 * @see <a href="https://www.qg.net/product/proxyip.html">青果网络代理IP</a>
 */
@Data
@Builder
public class QgProxy implements Serializable {

  @JsonProperty(value = "Code")
  private int code;

  @JsonProperty(value = "Num")
  private int num;

  @JsonProperty(value = "TaskID")
  private String taskId;

  @JsonProperty(value = "Data")
  private List<Proxy> ProxyList;

  @lombok.Data
  @Builder
  public static class Proxy {

    @JsonProperty(value = "IP")
    private String ip;

    private String port;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deadline;

    private String host;

    private String region;

  }

}
