/*
 * Copyright (c) 2022-2024 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.flyway.mapper;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author laokou
 */
public class Test2 {

   static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(32, 32, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        CompletableFuture<List<String>> f1 = CompletableFuture.supplyAsync(() -> List.of("33"),threadPoolExecutor);
        CompletableFuture<List<String>> f2 = CompletableFuture.supplyAsync(() -> List.of("33"),threadPoolExecutor);
        CompletableFuture<List<String>> f3 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f123213 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f2313 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f122323213 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f21323 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f22133 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f322223 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f223 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f222223 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f32 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f223223 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f21232123 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        CompletableFuture<List<String>> f11 = CompletableFuture.supplyAsync(() -> List.of("3333"),threadPoolExecutor);
        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
        System.out.println(System.currentTimeMillis() - l);
    }

}
