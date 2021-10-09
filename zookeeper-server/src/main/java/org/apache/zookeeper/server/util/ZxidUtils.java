/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zookeeper.server.util;
/**
 *  默认zxid，为64个字节，
 *  前32位代表 epoch，当前的时代，或者理解为第几代leader，epoch左移32位，代表当前leader的版本
 *  后32位代表 counter，当前leader提交了多少条数据，顺序递增保证了，保证zk的顺序一致性
 *  integer 的大小是32位，所以一个leader最大提交的条目为 0xffffffffL = 4294967295
 *  默认zxid为：00000000 00000000 00000000 00000001 00000000 00000000 00000000 00000000
 */
public class ZxidUtils {
    /**
     * 得到当前leader的版本
     * @param zxid
     * @return
     */
    public static long getEpochFromZxid(long zxid) {
        return zxid >> 32L;
    }

    /**
     * 得到zxid的序号
     * @param zxid
     * @return
     */
    public static long getCounterFromZxid(long zxid) {
        return zxid & 0xffffffffL;
    }

    /**
     * 生成一个zxid
     * @param epoch
     * @param counter
     * @return
     */
    public static long makeZxid(long epoch, long counter) {
        return (epoch << 32L) | (counter & 0xffffffffL);
    }
    public static String zxidToString(long zxid) {
        return Long.toHexString(zxid);
    }

}
