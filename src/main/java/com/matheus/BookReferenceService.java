package com.matheus;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookReferenceService {

  @Inject
  RedisClient redisClient;

  @Inject
  ReactiveRedisClient reactiveRedisClient;

  Uni<Void> del(String key) {
    return reactiveRedisClient.del(List.of(key))
        .map(response -> null);
  }

  String get(String key) {
    return redisClient.get(key).toString();
  }

  void set(String key, String name) {
    redisClient.set(Arrays.asList(key, name));
  }

  Uni<List<String>> keys() {
    return reactiveRedisClient
        .keys("*")
        .map(response -> {
          List<String> result = new ArrayList<>();
          for (Response r : response) {
            result.add(r.toString());
          }
          return result;
        });
  }
}
