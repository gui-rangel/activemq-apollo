/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.activemq.apollo.broker.path;

import java.util.ArrayList;

import org.apache.activemq.util.buffer.AsciiBuffer;


/**
 * Matches messages which match a prefix like "A.B.>"
 *
 * @version $Revision: 1.2 $
 */
public class PrefixPathFilter extends PathFilter {

    private ArrayList<AsciiBuffer> prefixes;

    /**
     * An array of paths, the last path is '>'
     *
     * @param paths
     */
    public PrefixPathFilter(ArrayList<AsciiBuffer> paths) {
        this.prefixes = paths;
    }

    public boolean matches(AsciiBuffer path) {
        ArrayList<AsciiBuffer> parts = PathSupport.parse(path);
        int length = prefixes.size();
        if (parts.size() >= length) {
            int size = length - 1;
            for (int i = 0; i < size; i++) {
                if (!prefixes.get(i).equals(parts.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String getText() {
        return PathSupport.toString(prefixes);
    }

    public String toString() {
        return super.toString() + "[path: " + getText() + "]";
    }

    public boolean isWildcard() {
        return true;
    }
}
