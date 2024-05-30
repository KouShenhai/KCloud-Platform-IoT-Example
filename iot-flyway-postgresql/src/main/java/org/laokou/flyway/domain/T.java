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

package org.laokou.flyway.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author laokou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t")
public class T implements Serializable {

    @TableId
    private Long id;

    private String name;

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;
    private String aa;
    private String bb;
    private String cc;
    private String dd;
    private String ee;
    private String ff;
    private String gg;
    private String hh;
    private String ii;
    private String jj;
    private String kk;
    private String ll;
    private String mm;
    private String nn;
    private String oo;
    private String pp;
    private String qq;
    private String rr;
    private String ss;
    private String tt;
    private String uu;
    private String vv;
    private String ww;
    private String xx;
    private String yy;
    private String zz;

}
