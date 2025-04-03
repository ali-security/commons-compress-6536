/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.compress.archivers.dump;

import org.apache.commons.compress.AbstractTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TapeInputStreamTest extends AbstractTestCase {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { -1 }, { 0 }, { Integer.MAX_VALUE / 1000 }, { Integer.MAX_VALUE }
        });
    }

    private int recsPerBlock;

    public TapeInputStreamTest(int recsPerBlock) {
        this.recsPerBlock = recsPerBlock;
    }

    @Test
    public void testResetBlockSizeWithInvalidValues() throws Exception {
        try (TapeInputStream tapeInputStream = new TapeInputStream(new ByteArrayInputStream(new byte[1]))) {
            IOException exception = Assert.assertThrows(IOException.class, () -> {
                tapeInputStream.resetBlockSize(recsPerBlock, true);
            });
            Assert.assertEquals(IOException.class, exception.getClass());
        }
    }
}

