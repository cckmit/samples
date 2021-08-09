package over35.samples.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * @author: daibin
 * @date: 2021/8/8 2:34 下午
 */
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        server.requestHandler(router).listen(8080);

        router.route().handler(req -> {
            req.response().putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        });

        Handler<AsyncResult<HttpServer>> listenHandler = http -> {
            if (http.succeeded()) {
                startPromise.complete();
                System.out.println("HTTP server started on port 8080");
            } else {
                startPromise.fail(http.cause());
            }
        };
    }
}
