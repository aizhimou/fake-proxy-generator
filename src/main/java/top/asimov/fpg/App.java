package top.asimov.fpg;

import io.javalin.Javalin;
import top.asimov.fpg.provider.QgProxyProvider;
import top.asimov.fpg.provider.ZdayeProxyProvider;

public class App {

  public static void main(String[] args) {
    Javalin app = Javalin.create().start(7000);

    app.get("/qg", ctx -> ctx.json(QgProxyProvider.getProxy()));
    app.get("/zdaye", ctx -> ctx.json(ZdayeProxyProvider.getProxy()));

    QgProxyProvider.autoGenerateProxy(2, 60 * 1000);
    ZdayeProxyProvider.autoGenerateProxy(5, 60 * 1000);
  }
}
