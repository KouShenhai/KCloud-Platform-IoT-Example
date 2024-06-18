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

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author laokou
 */
public class Test {

   static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        AtomicReference<List<String>> l1 = new AtomicReference<>();
        AtomicReference<List<String>> l2 = new AtomicReference<>();
        AtomicReference<List<String>> l3 = new AtomicReference<>();
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> l1.set(List.of("33")),threadPoolExecutor);
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> l2.set(List.of("33")),threadPoolExecutor);
        CompletableFuture<Void> f3 = CompletableFuture.runAsync(() -> l3.set(List.of("3333")),threadPoolExecutor);
        CompletableFuture.allOf(f1, f2, f3).join();
        System.out.println(l1.get());
        System.out.println(l2.get());
        System.out.println(l3.get());
        System.out.println(System.currentTimeMillis() - l);
        threadPoolExecutor.shutdown();
    }

}
